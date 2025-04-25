package barberia.api.controller;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import barberia.api.entity.Usuario;
import barberia.api.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin(origins = "*") // Permitir acceso desde cualquier origen
@Tag(name = "Usuario", description = "API para gestionar usuarios") // Grupo en Swagger
@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    @Operation(summary = "Obtener todas los usuarios", description = "Devuelve una lista de usuarios")
    public List<Usuario> get() {
        return usuarioService.get();
    }

    @GetMapping("/{idUsuario}")
    @Operation(summary = "Obtener un usuario por ID", description = "Busca un usuario en la base de datos según su ID")
    public Optional<Usuario> getById(@PathVariable int id) {
        return usuarioService.getById(id);
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo usuario", description = "Agrega un nuevo usuario a la base de datos")
    public Usuario add(@RequestBody Usuario usuario) {
        return usuarioService.add(usuario);
    }

    @Operation(summary = "Modificar un usuario", description = "Modifica un usuario existene en la base de datos")
    @PutMapping("/{idUsuario}")
    public Usuario update(@PathVariable int id, @RequestBody Usuario usuario) {
        return usuarioService.update(id, usuario);
    }

    @Operation(summary = "Eliminar un usuario", description = "Elimina un usuario de la base de datos")
    @DeleteMapping("/{idUsuario}")
    public void delete(@PathVariable int id) {
        usuarioService.delete(id);
    }
}
