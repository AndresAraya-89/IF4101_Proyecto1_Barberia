package barberia.api.controller;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import barberia.api.entity.Comision;
import barberia.api.entity.Usuario;
import barberia.api.service.ComisionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin(origins = "*") // Permitir acceso desde cualquier origen
@Tag(name = "Comision", description = "API para gestionar comisiones") // Grupo en Swagger
@RestController
@RequestMapping("/comision")
public class ComisionController {
    @Autowired
    private ComisionService comisionService;

    @GetMapping
    @Operation(summary = "Obtener todas las comisiones", description = "Devuelve una lista de comisiones")
    public List<Comision> get() {
        return comisionService.get();
    }

    @GetMapping("/{idComision}")
    @Operation(summary = "Obtener una comision por ID", description = "Busca una comision en la base de datos según su ID")
    public ResponseEntity<Comision> getById(@PathVariable("idComision") int id) {
        return comisionService.getById(id)
                .map(comision -> ResponseEntity.ok(comision))
                .orElse(ResponseEntity.notFound().build());
    }
 
    @PostMapping
    @Operation(summary = "Crear una nueva comision", description = "Agrega una nueva comision a la base de datos")
    public ResponseEntity<?> add(
            @RequestParam Integer idUsuario, 
            @RequestBody Comision comision
    ) {
        try {
            // Crea un objeto Usuario mínimo con el ID recibido
            Usuario usuario = new Usuario();
            usuario.setIdUsuario(idUsuario);

            // Asigna este usuario a la comision
            comision.setUsuario(usuario);

            // Llama al servicio existente 
            Comision comisionGuardada = comisionService.add(comision);
            return ResponseEntity.ok(comisionGuardada);

        } catch (RuntimeException ex) {
          return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @Operation(summary = "Modificar una comision", description = "Modifica una comision existene en la base de datos")
    @PutMapping("/{idComision}")
   public ResponseEntity<?> update(@PathVariable("idComision") int id, @RequestBody Comision comision) {
        try {
            Comision updatedComision = comisionService.update(id, comision);
            return ResponseEntity.ok(updatedComision);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @Operation(summary = "Eliminar una comision", description = "Elimina una comision de la base de datos")
    @DeleteMapping("/{idComision}")
    public void delete(@PathVariable ("idComision") int id) {
        comisionService.delete(id);
    }
}

