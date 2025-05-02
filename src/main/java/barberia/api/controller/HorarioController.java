package barberia.api.controller;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import barberia.api.entity.Horario;
import barberia.api.entity.Usuario;
import barberia.api.service.HorarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin(origins = "*") // Permitir acceso desde cualquier origen
@Tag(name = "Horario", description = "API para gestionar horarios") // Grupo en Swagger
@RestController
@RequestMapping("/horario")
public class HorarioController {
    @Autowired
    private HorarioService horarioService;

    @GetMapping
    @Operation(summary = "Obtener todas los horarios", description = "Devuelve una lista de horarios")
    public List<Horario> get() {
        return horarioService.get();
    }

    @GetMapping("/{idHorarario}")
    @Operation(summary = "Obtener un horario por ID", description = "Busca un horario en la base de datos según su ID")
    public ResponseEntity<Horario> getById(@PathVariable("idHorarario") int id) {
        return horarioService.getById(id)
                .map(horario -> ResponseEntity.ok(horario))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo horario")
    public ResponseEntity<?> add(
            @RequestParam Integer idUsuario, // Recibes el ID por parámetro (puede ser @RequestParam o parte de un DTO)
            @RequestBody Horario horario // Recibes el resto de datos del repositorio
    ) {
        try {
            // Crea un objeto Usuario mínimo con el ID recibido
            Usuario usuario = new Usuario();
            usuario.setIdUsuario(idUsuario);

            // Asigna este usuario al horario
            horario.setUsuario(usuario);

            // Llama al servicio existente 
            Horario horarioGuardado = horarioService.add(horario);
            return ResponseEntity.ok(horarioGuardado);

        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @PutMapping("/{idHorarario}")
    @Operation(summary = "Modificar un horario", description = "Actualiza un horario a la base de datos")
    public ResponseEntity<?> update(@PathVariable("idHorarario") int id, @RequestBody Horario horario) {
        try {
            Horario updatedHorario = horarioService.update(id, horario);
            return ResponseEntity.ok(updatedHorario);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @Operation(summary = "Eliminar un horario", description = "Elimina un horario de la base de datos")
    @DeleteMapping("/{idHorarario}")
    public void delete(@PathVariable ("idHorarario") int id) {
        horarioService.delete(id);
    }
}
