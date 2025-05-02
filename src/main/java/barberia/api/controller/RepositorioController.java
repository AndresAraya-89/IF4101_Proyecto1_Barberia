package barberia.api.controller;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import barberia.api.entity.Repositorio;
import barberia.api.entity.Usuario;
import barberia.api.service.RepositorioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin(origins = "*") // Permitir acceso desde cualquier origen
@Tag(name = "Repositorio", description = "API para gestionar repositorios") // Grupo en Swagger
@RestController
@RequestMapping("/repositorio")
public class RepositorioController {
    @Autowired
    private RepositorioService repositorioService;

    @GetMapping
    @Operation(summary = "Obtener todas los repositorios", description = "Devuelve una lista de repositorios")
    public List<Repositorio> get() {
        return repositorioService.get();
    }

    @GetMapping("/{idRepositorio}")
    @Operation(summary = "Obtener un repositorio por ID", description = "Busca un repositorio en la base de datos según su ID")
    public ResponseEntity<Repositorio> getById(@PathVariable("idRepositorio") int id) {
        return repositorioService.getById(id)
                .map(repositorio -> ResponseEntity.ok(repositorio))
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping
    @Operation(summary = "Crear un nuevo repositorio")
    public ResponseEntity<?> add(
            @RequestParam Integer idUsuario, // Recibes el ID por parámetro (puede ser @RequestParam o parte de un DTO)
            @RequestBody Repositorio repositorio // Recibes el resto de datos del repositorio
    ) {
        try {
            // Crea un objeto Usuario mínimo con el ID recibido
            Usuario usuario = new Usuario();
            usuario.setIdUsuario(idUsuario);

            // Asigna este usuario al repositorio
            repositorio.setUsuario(usuario);

            // Llama al servicio existente 
            Repositorio repositorioGuardado = repositorioService.add(repositorio);
            return ResponseEntity.ok(repositorioGuardado);

        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

   @PutMapping("/{idRepositorio}")
    @Operation(summary = "Modificar un repositorio", description = "Actualiza un repositorio a la base de datos")
    public ResponseEntity<?> update(@PathVariable("idRepositorio") int id, @RequestBody Repositorio Repositorio) {
        try {
            Repositorio updatedRepositorio = repositorioService.update(id, Repositorio);
            return ResponseEntity.ok(updatedRepositorio);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    @Operation(summary = "Eliminar un rrepositorio", description = "Elimina un repositorio de la base de datos")
    @DeleteMapping("/{idRepositorio}")
    public void delete(@PathVariable ("idRepositorio") int id) {
        repositorioService.delete(id);
    }
}
