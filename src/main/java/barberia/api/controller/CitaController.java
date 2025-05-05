package barberia.api.controller;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import barberia.api.entity.Cita;
import barberia.api.entity.Corte;
import barberia.api.entity.Horario;
import barberia.api.entity.Usuario;
import barberia.api.service.CitaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin(origins = "*") // Permitir acceso desde cualquier origen
@Tag(name = "Cita", description = "API para gestionar citas") // Grupo en Swagger
@RestController
@RequestMapping("/cita")
public class CitaController {
    @Autowired
    private CitaService citaService;

    @GetMapping
    @Operation(summary = "Obtener todas las citas", description = "Devuelve una lista de citas")
    public List<Cita> get() {
        return citaService.get();
    }

    @GetMapping("/{idCita}")
    @Operation(summary = "Obtener una cita por ID", description = "Busca una cita en la base de datos según su ID")
    public ResponseEntity<Cita> getById(@PathVariable("idCita") int id) {
        return citaService.getById(id)
                .map(cita -> ResponseEntity.ok(cita))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Crear una nueva cita", description = "Agrega una nueva cita a la base de datos")
    public ResponseEntity<?> add(
            @RequestParam Integer idUsuarioCliente, // Recibes el ID por parámetro
            @RequestParam Integer idUsuarioBarbero,
            @RequestParam Integer idCorte,
            @RequestParam Integer idHorario,
            @RequestBody Cita cita // Recibes el resto de datos del repositorio
    ) {  
        try {
            // Crea los objetos
            Usuario usuarioCliente = new Usuario();
            usuarioCliente.setIdUsuario(idUsuarioCliente);

            Usuario usuarioBarbero = new Usuario();
            usuarioBarbero.setIdUsuario(idUsuarioBarbero);

            Corte corte = new Corte();
            corte.setIdCorte(idCorte);

            Horario horario = new Horario();
            horario.setIdHorarario(idHorario);

            // Asigna los objetos a la cita
            cita.setUsuarioCliente(usuarioCliente);
            cita.setUsuarioBarbero(usuarioBarbero);
            cita.setCorte(corte);
            cita.setHorario(horario);

            // Llama al servicio existente
            Cita citaGuardada = citaService.add(cita);
            return ResponseEntity.ok(citaGuardada);

        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @PutMapping("/{idCita}")
    @Operation(summary = "Actualizar cita", description = "Actualiza una cita existente")
    public ResponseEntity<?> update(
            @PathVariable("idCita") int id,
            @RequestBody Cita citaActualizado) {

        try {
            Cita citaActualizada = citaService.update(id, citaActualizado);
            return ResponseEntity.ok(citaActualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @Operation(summary = "Eliminar una cita", description = "Elimina una  cita de la base de datos")
    @DeleteMapping("/{idCita}")
    public void delete(@PathVariable ("idCita") int id) {
        citaService.delete(id);
    }
}
