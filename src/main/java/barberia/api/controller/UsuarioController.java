package barberia.api.controller;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Usuario> getById(@PathVariable("idUsuario") int id) {
        return usuarioService.getById(id)
                .map(usuario -> ResponseEntity.ok(usuario))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo usuario", description = "Agrega un nuevo usuario a la base de datos")
    public Usuario add(@RequestBody Usuario usuario) {
        return usuarioService.add(usuario);
    }


    @PutMapping("/{idUsuario}")
    @Operation(summary = "Modificar un usuario", description = "Actualiza un usuario a la base de datos")
    public ResponseEntity<?> update(@PathVariable("idUsuario") int id, @RequestBody Usuario usuario) {
        try {
            Usuario updatedUsuario = usuarioService.update(id, usuario);
            return ResponseEntity.ok(updatedUsuario);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @Operation(summary = "Eliminar un usuario", description = "Elimina un usuario de la base de datos")
    @DeleteMapping("/{idUsuario}")
    public void delete(@PathVariable ("idUsuario") int id) {
        usuarioService.delete(id);
    }
}
