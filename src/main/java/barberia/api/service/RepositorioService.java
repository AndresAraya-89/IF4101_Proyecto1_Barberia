package barberia.api.service;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import barberia.api.entity.Repositorio;
import barberia.api.entity.Usuario;
import barberia.api.repository.RepositorioRepository;
import barberia.api.repository.UsuarioRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RepositorioService {

    @Autowired
    private RepositorioRepository repositorioRepository;
    private UsuarioRepository usuarioRepository;

    public Repositorio add(Repositorio repositorio) {
        // Verifica si el usuario existe
        Usuario usuario = usuarioRepository.findById(repositorio.getUsuario().getIdUsuario())
                .orElseThrow(() -> new RuntimeException(
                        "Usuario no encontrado con ID: " + repositorio.getUsuario().getIdUsuario()));

        repositorio.setUsuario(usuario);

        return repositorioRepository.save(repositorio);
    }

    public List<Repositorio> get() {
        return repositorioRepository.findAll();
    }

    public Optional<Repositorio> getById(int id) {
        return repositorioRepository.findById(id);
    }

    public void delete(int id) {
        repositorioRepository.deleteById(id);
    }

    public Repositorio update(int id, Repositorio repositorioActualizado) {
        // Buscar el repositorio existente
        Repositorio repositorioExistente = repositorioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Repositorio no encontrado con ID: " + id));

        // Actualizar relacion con usuario
        if (repositorioActualizado.getUsuario() != null) {
            Usuario usuario = usuarioRepository.findById(
                    repositorioActualizado.getUsuario().getIdUsuario())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            repositorioExistente.setUsuario(usuario);
        }

        // Actualizar campos simples
        repositorioExistente.setNombre(repositorioActualizado.getNombre());
        repositorioExistente.setDescripcion(repositorioActualizado.getDescripcion());
        repositorioExistente.setEstado(repositorioActualizado.getEstado());

        return repositorioRepository.save(repositorioExistente);
    }
}
