package barberia.api.controller;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import barberia.api.entity.Horario;
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
    public Optional<Horario> getById(@PathVariable int id) {
        return horarioService.getById(id);
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo horario", description = "Agrega un nuevo horario a la base de datos")
    public Horario add(@RequestBody Horario horario) {
        return horarioService.add(horario);
    }

    @Operation(summary = "Modificar un horario", description = "Modifica un horario existene en la base de datos")
    @PutMapping("/{idHorarario}")
    public Horario update(@PathVariable int id, @RequestBody Horario horario) {
        return horarioService.update(id, horario);
    }

    @Operation(summary = "Eliminar un horario", description = "Elimina un horario de la base de datos")
    @DeleteMapping("/{idHorarario}")
    public void delete(@PathVariable int id) {
        horarioService.delete(id);
    }
}
