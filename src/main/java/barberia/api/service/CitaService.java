package barberia.api.service;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import barberia.api.entity.Cita;
import barberia.api.entity.Corte;
import barberia.api.entity.Horario;
import barberia.api.entity.Usuario;
import barberia.api.repository.CitaRepository;
import barberia.api.repository.CorteRepository;
import barberia.api.repository.HorarioRepository;
import barberia.api.repository.UsuarioRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CitaService {
    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CorteRepository corteRepository;

    @Autowired
    private HorarioRepository horarioRepository;

    public Cita add(Cita cita) {
        // Verifica si los objetos existen
        Usuario usuarioCliente = usuarioRepository.findById(cita.getUsuarioCliente().getIdUsuario())
                .orElseThrow(() -> new RuntimeException(
                        "Usuario (cliente) no encontrado con ID: " + cita.getUsuarioCliente().getIdUsuario()));

        Usuario usuarioBarbero = usuarioRepository.findById(cita.getUsuarioBarbero().getIdUsuario())
                .orElseThrow(() -> new RuntimeException(
                        "Usuario (Barbero) no encontrado con ID: " + cita.getUsuarioBarbero().getIdUsuario()));

        Corte corte = corteRepository.findById(cita.getCorte().getIdCorte())
                .orElseThrow(() -> new RuntimeException(
                        "Corte no encontrado con ID: " + cita.getCorte().getIdCorte()));

        Horario horario = horarioRepository.findById(cita.getHorario().getIdHorarario())
                .orElseThrow(() -> new RuntimeException(
                        "Horario no encontrado con ID: " + cita.getHorario().getIdHorarario()));

        // Asigna a la cita los objetos
        cita.setUsuarioCliente(usuarioCliente);
        cita.setUsuarioBarbero(usuarioBarbero);
        cita.setCorte(corte);
        cita.setHorario(horario);

        // Guarda el corte
        return citaRepository.save(cita);
    }

    public List<Cita> get() {
        return citaRepository.findAll();
    }

    public Optional<Cita> getById(int id) {
        return citaRepository.findById(id);
    }

    public void delete(int id) {
        citaRepository.deleteById(id);
    }

    public Cita update(int id, Cita citaActualizado) {
        // Buscar la cita existente
        Cita citaExistente = citaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cita no encontrado con ID: " + id));

        // Actualizar relación con Usuario (Cliente)
        if (citaActualizado.getUsuarioCliente() != null && citaActualizado.getUsuarioCliente().getIdUsuario() != 0) {
            Usuario cliente = usuarioRepository.findById(citaActualizado.getUsuarioCliente().getIdUsuario())
                    .orElseThrow(() -> new RuntimeException(
                            "Cliente no encontrado con ID: " + citaActualizado.getUsuarioCliente().getIdUsuario()));
            citaExistente.setUsuarioCliente(cliente);
        }

        // Actualizar relación con Usuario (barbero)
        if (citaActualizado.getUsuarioBarbero() != null && citaActualizado.getUsuarioBarbero().getIdUsuario() != 0) {
            Usuario barbero = usuarioRepository.findById(citaActualizado.getUsuarioBarbero().getIdUsuario())
                    .orElseThrow(() -> new RuntimeException(
                            "Barbero no encontrado con ID: " + citaActualizado.getUsuarioBarbero().getIdUsuario()));
            citaExistente.setUsuarioBarbero(barbero);
        }

        // Actualizar relación con Corte
        if (citaActualizado.getCorte() != null && citaActualizado.getCorte().getIdCorte() != 0) {
            Corte corte = corteRepository.findById(citaActualizado.getCorte().getIdCorte())
                    .orElseThrow(() -> new RuntimeException(
                            "Corte no encontrado con ID: " + citaActualizado.getCorte().getIdCorte()));
            citaExistente.setCorte(corte);
        }

        // Actualizar relación con Horario
        if (citaActualizado.getHorario() != null && citaActualizado.getHorario().getIdHorarario() != 0) {
            Horario horario = horarioRepository.findById(citaActualizado.getHorario().getIdHorarario())
                    .orElseThrow(() -> new RuntimeException(
                            "Horario no encontrado con ID: " + citaActualizado.getHorario().getIdHorarario()));
            citaExistente.setHorario(horario);
        }

        citaExistente.setEstado(citaActualizado.getEstado());

        return citaRepository.save(citaExistente);
    }
}
