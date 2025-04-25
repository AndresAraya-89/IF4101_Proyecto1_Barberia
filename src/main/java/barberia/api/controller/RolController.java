package barberia.api.controller;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import barberia.api.entity.Rol;
import barberia.api.service.RolService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin(origins = "*") // Permitir acceso desde cualquier origen
@Tag(name = "Rol", description = "API para gestionar roles") // Grupo en Swagger
@RestController
@RequestMapping("/rol")
public class RolController {
    @Autowired
    private RolService rolService;

    @GetMapping
    @Operation(summary = "Obtener todas los roles", description = "Devuelve una lista de roles")
    public List<Rol> get() {
        return rolService.get();
    }

    @GetMapping("/{idRol}")
    @Operation(summary = "Obtener un rol por ID", description = "Busca un rol en la base de datos según su ID")
    public Optional<Rol> getById(@PathVariable int id) {
        return rolService.getById(id);
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo rol", description = "Agrega un nuevo rol a la base de datos")
    public Rol add(@RequestBody Rol category) {
        return rolService.add(category);
    }

    @Operation(summary = "Modificar un rol", description = "Modifica un rol existene en la base de datos")
    @PutMapping("/{idRol}")
    public Rol update(@PathVariable int id, @RequestBody Rol rol) {
        return rolService.update(id, rol);
    }

    @Operation(summary = "Eliminar un rol", description = "Elimina un rol de la base de datos")
    @DeleteMapping("/{idRol}")
    public void delete(@PathVariable int id) {
        rolService.delete(id);
    }
}
