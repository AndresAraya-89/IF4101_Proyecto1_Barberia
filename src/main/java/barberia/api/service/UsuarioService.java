package barberia.api.service;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import barberia.api.entity.Rol;
import barberia.api.entity.Usuario;
import barberia.api.repository.RolRepository;
import barberia.api.repository.UsuarioRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private RolRepository rolRepository;

    // public Usuario add(Usuario usuario) {
    // return usuarioRepository.save(usuario);
    // }

    public Usuario add(Usuario usuario) {
        // Verifica si el Rol existe
        Rol rol = rolRepository.findById(usuario.getRol().getIdRol())
                .orElseThrow(() -> new RuntimeException("Rol no encontrado con ID: " + usuario.getRol().getIdRol()));

        // Asigna el Rol completo al Usuario
        usuario.setRol(rol);

        return usuarioRepository.save(usuario);
    }

    public List<Usuario> get() {
        return usuarioRepository.findAll();
    }

    public List<Usuario> getBarberos() {
        List <Usuario> listaGeneral  = usuarioRepository.findAll();
        List <Usuario> listaBarberos = new ArrayList<>();

        for (int i = 0; i < listaGeneral.size(); i++){
            if (listaGeneral.get(i).getEstado() == 2){
                listaBarberos.add(listaGeneral.get(i));
            }
        }

        return listaBarberos;
    }

    public List<Usuario> getClientes() {
        List <Usuario> listaGeneral  = usuarioRepository.findAll();
        List <Usuario> listaClientes = new ArrayList<>();

        for (int i = 0; i < listaGeneral.size(); i++){
            if (listaGeneral.get(i).getEstado() == 3){
                listaClientes.add(listaGeneral.get(i));
            }
        }

        return listaClientes;
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
            updatedUsuario.setIdUsuario(id);
            return usuarioRepository.save(updatedUsuario);
        } else {
            throw new RuntimeException("Usuario no encontrada con ID: " + id);
        }
    }
}
