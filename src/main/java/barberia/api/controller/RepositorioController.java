package barberia.api.controller;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import barberia.api.entity.Repositorio;
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
    public Optional<Repositorio> getById(@PathVariable int id) {
        return repositorioService.getById(id);
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo repositorio", description = "Agrega un nuevo repositorio a la base de datos")
    public Repositorio add(@RequestBody Repositorio repositorio) {
        return repositorioService.add(repositorio);
    }

    @Operation(summary = "Modificar un repositorio", description = "Modifica un repositorio existene en la base de datos")
    @PutMapping("/{idRepositorio}")
    public Repositorio update(@PathVariable int id, @RequestBody Repositorio repositorio) {
        return repositorioService.update(id, repositorio);
    }

    @Operation(summary = "Eliminar un rrepositorio", description = "Elimina un repositorio de la base de datos")
    @DeleteMapping("/{idRepositorio}")
    public void delete(@PathVariable int id) {
        repositorioService.delete(id);
    }
}
