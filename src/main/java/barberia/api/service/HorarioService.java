package barberia.api.service;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import barberia.api.entity.Horario;
import barberia.api.entity.Usuario;
import barberia.api.repository.HorarioRepository;
import barberia.api.repository.UsuarioRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class HorarioService {

    @Autowired
    private HorarioRepository horarioRepository;
    private UsuarioRepository usuarioRepository;

     public Horario add(Horario horario) {
        // Verifica si el usuario existe
        Usuario usuario = usuarioRepository.findById(horario.getUsuario().getIdUsuario())
                .orElseThrow(() -> new RuntimeException(
                        "Repositorio no encontrado con ID: " + horario.getUsuario().getIdUsuario()));

        // Asigna el repositorio al corte
        horario.setUsuario(usuario);

        // Guarda el corte
        return horarioRepository.save(horario);
    }

    public List<Horario> get() {
        return horarioRepository.findAll();
    }

    public Optional<Horario> getById(int id) {
        return horarioRepository.findById(id);
    }

    public void delete(int id) {
        horarioRepository.deleteById(id);
    }

    public Horario update(int id, Horario horarioActualizado) {
        //Buscar el horario existente
        Horario horarioExistente = horarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Horario no encontrado con ID: " + id));


        //Actualizar relación con Usuario (barbero)
        if (horarioActualizado.getUsuario() != null && horarioActualizado.getUsuario().getIdUsuario() != 0) {
            Usuario barbero = usuarioRepository.findById(horarioActualizado.getUsuario().getIdUsuario())
                    .orElseThrow(() -> new RuntimeException(
                            "Barbero no encontrado con ID: " + horarioActualizado.getUsuario().getIdUsuario()));
            horarioExistente.setUsuario(barbero);
        }

        //Actualizar estado (si se proporciona) y horas con fecha
            horarioExistente.setEstado(horarioActualizado.getEstado());
            horarioExistente.setFecha(horarioActualizado.getFecha());
            horarioExistente.setHoraFinal(horarioActualizado.getHoraFinal());
            horarioExistente.setHoraInicio(horarioActualizado.getHoraInicio());

        //Guardar cambios
        return horarioRepository.save(horarioExistente);
    }
}
