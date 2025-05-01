package barberia.api.controller;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @Operation(summary = "Obtener un rol por ID", description = "Consulta un rol a la base de datos")
    public ResponseEntity<Rol> getById(@PathVariable("idRol") int id) {
        return rolService.getById(id)
                .map(rol -> ResponseEntity.ok(rol))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping //Devuelve un error 500 si no existe el rol
    @Operation(summary = "Crear un nuevo rol", description = "Agrega un nuevo rol a la base de datos")
    public Rol add(@RequestBody Rol category) {
        return rolService.add(category);
    }

    @PutMapping("/{idRol}")
    @Operation(summary = "Modificar un rol", description = "Actualiza un rol a la base de datos")
    public ResponseEntity<?> update(@PathVariable("idRol") int id, @RequestBody Rol rol) {
        try {
            Rol updatedRol = rolService.update(id, rol);
            return ResponseEntity.ok(updatedRol);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @Operation(summary = "Eliminar un rol", description = "Elimina un rol de la base de datos")
    @DeleteMapping("/{idRol}")
    public void delete(@PathVariable("idRol") int id) {
        rolService.delete(id);
    }
}
