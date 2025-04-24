package barberia.service;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import barberia.api.entity.Usuario;
import barberia.api.repository.UsuarioRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario add(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> get() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> getById(int id) {
        return usuarioRepository.findById(id);
    }

    public void delete(int id) {
        usuarioRepository.deleteById(id);
    }

    public Usuario update(int id, Usuario usuario) {
        Optional<Usuario> existingUsuario = usuarioRepository.findById(id);
        if (existingUsuario.isPresent()) {
            Usuario updatedUsuario = existingUsuario.get();
            // Si el usuario existe que realice una copia completa del objeto recibido por
            // parametro
            updatedUsuario = usuario;
            return usuarioRepository.save(updatedUsuario);
        } else {
            throw new RuntimeException("Usuario no encontrada con ID: " + id);
        }
    }
}
