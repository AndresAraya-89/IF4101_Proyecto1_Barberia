package barberia.api.controller;

import barberia.api.entity.Cita;
import barberia.api.service.CitaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*") // Permitir solicitudes desde cualquier origen
@Tag(name = "Citas", description = "API para gestión de citas")
@RestController
@RequestMapping("/api/citas")
public class AppointmentsController {

    @Autowired
    private CitaService citaService;

    @GetMapping
    @Operation(summary = "Obtener todas las citas", description = "Devuelve una lista con todas las citas registradas")
    public List<Cita> getAll() {
        return citaService.get();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener una cita por ID", description = "Devuelve los detalles de una cita específica")
    public Optional<Cita> getById(@PathVariable int id) {
        return citaService.getById(id);
    }

    @PostMapping
    @Operation(summary = "Crear una nueva cita", description = "Guarda una nueva cita en la base de datos")
    public Cita add(@RequestBody Cita cita) {
        return citaService.add(cita);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una cita", description = "Modifica una cita existente en la base de datos")
    public Cita update(@PathVariable int id, @RequestBody Cita cita) {
        return citaService.update(id, cita);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una cita", description = "Elimina una cita de la base de datos")
    public void delete(@PathVariable int id) {
        citaService.delete(id);
    }
}
