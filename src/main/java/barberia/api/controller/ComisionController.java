package barberia.api.controller;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import barberia.api.entity.Comision;
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
    public Optional<Comision> getById(@PathVariable int id) {
        return comisionService.getById(id);
    }

    @PostMapping
    @Operation(summary = "Crear una nueva comision", description = "Agrega una nueva comision a la base de datos")
    public Comision add(@RequestBody Comision comision) {
        return comisionService.add(comision);
    }

    @Operation(summary = "Modificar una comision", description = "Modifica una comision existene en la base de datos")
    @PutMapping("/{idComision}")
    public Comision update(@PathVariable int id, @RequestBody Comision comision) {
        return comisionService.update(id, comision);
    }

    @Operation(summary = "Eliminar una comision", description = "Elimina una comision de la base de datos")
    @DeleteMapping("/{idComision}")
    public void delete(@PathVariable int id) {
        comisionService.delete(id);
    }
}
