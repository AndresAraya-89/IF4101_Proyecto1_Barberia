package barberia.api.controller;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import barberia.api.entity.Corte;
import barberia.api.service.CorteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin(origins = "*") // Permitir acceso desde cualquier origen
@Tag(name = "Corte", description = "API para gestionar cortes") // Grupo en Swagger
@RestController
@RequestMapping("/corte")
public class CorteController {
    @Autowired
    private CorteService corteService;

    @GetMapping
    @Operation(summary = "Obtener todas los cortes", description = "Devuelve una lista de cortes")
    public List<Corte> get() {
        return corteService.get();
    }

    @GetMapping("/{idCorte}")
    @Operation(summary = "Obtener un corte por ID", description = "Busca un corte en la base de datos según su ID")
    public Optional<Corte> getById(@PathVariable int id) {
        return corteService.getById(id);
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo corte", description = "Agrega un nuevo corte a la base de datos")
    public Corte add(@RequestBody Corte corte) {
        return corteService.add(corte);
    }

    @Operation(summary = "Modificar un corte", description = "Modifica un corte existene en la base de datos")
    @PutMapping("/{idCorte}")
    public Corte update(@PathVariable int id, @RequestBody Corte corte) {
        return corteService.update(id, corte);
    }

    @Operation(summary = "Eliminar un corte", description = "Elimina un corte de la base de datos")
    @DeleteMapping("/{idCorte}")
    public void delete(@PathVariable int id) {
        corteService.delete(id);
    }
}
