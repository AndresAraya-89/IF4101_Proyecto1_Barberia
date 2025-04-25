package barberia.api.controller;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import barberia.api.entity.Cita;
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
    public Optional<Cita> getById(@PathVariable int id) {
        return citaService.getById(id);
    }

    @PostMapping
    @Operation(summary = "Crear una nueva cita", description = "Agrega una nueva cita a la base de datos")
    public Cita add(@RequestBody Cita cita) {
        return citaService.add(cita);
    }

    @Operation(summary = "Modificar una cita", description = "Modifica una cita existene en la base de datos")
    @PutMapping("/{idCita}")
    public Cita update(@PathVariable int id, @RequestBody Cita cita) {
        return citaService.update(id, cita);
    }

    @Operation(summary = "Eliminar una cita", description = "Elimina una  cita de la base de datos")
    @DeleteMapping("/{idCita}")
    public void delete(@PathVariable int id) {
        citaService.delete(id);
    }
}
