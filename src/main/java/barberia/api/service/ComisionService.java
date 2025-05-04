package barberia.api.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import barberia.api.entity.Comision;
import barberia.api.entity.Usuario;
import barberia.api.repository.ComisionRepository;
import barberia.api.repository.UsuarioRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ComisionService {
    @Autowired
    private ComisionRepository comisionRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Comision add(Comision comision) {
        // Verifica si el usuario existe
        Usuario usuario = usuarioRepository.findById(comision.getUsuario().getIdUsuario())
                .orElseThrow(() -> new RuntimeException(
                        "Usuario no encontrado con ID: " + comision.getUsuario().getIdUsuario()));

        String fechaHora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        comision.setFechaHoraDeposito(fechaHora);
        comision.setUsuario(usuario);

        return comisionRepository.save(comision);
    }

    public List<Comision> get() {
        return comisionRepository.findAll();
    }

    public Optional<Comision> getById(int id) {
        return comisionRepository.findById(id);
    }

    public void delete(int id) {
        comisionRepository.deleteById(id);
    }

    public Comision update(int id, Comision comision) {
        Optional<Comision> existingComision = comisionRepository.findById(id);
        if (existingComision.isPresent()) {
            Comision updatedComision = existingComision.get();

            // Si la comision existe que realice una copia
            String fechaHora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            updatedComision.setFechaHoraDeposito(fechaHora);
            updatedComision.setEstado(comision.getEstado());
            updatedComision.setMonto(comision.getMonto());
            updatedComision.setUsuario(comision.getUsuario());
            return comisionRepository.save(updatedComision);
        } else {
            throw new RuntimeException("Comision no encontrada con ID: " + id);
        }
    }
}
