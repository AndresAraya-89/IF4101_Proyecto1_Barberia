package soda.api.controller;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import soda.api.entity.Category;
import soda.api.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
@CrossOrigin(origins = "*") // Permitir acceso desde cualquier origen

@Tag(name = "Categories", description = "API para gestionar categorías") // Grupo en Swagger
@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping
    @Operation(summary = "Obtener todas las cagegorías", description = "Devuelve una lista de categorías")
    public List<Category> get() {
        return categoryService.get();
    }

    @GetMapping("/{id}")
 @Operation(summary = "Obtener una categoría por ID", description = "Busca una categoría en la base de datos según su ID")
    public Optional<Category> getById(@PathVariable int id) {
        return categoryService.getById(id);
    }

    @PostMapping
 @Operation(summary = "Crear una nueva categoría", description = "Agrega una nueva categoría a la base de datos")
    public Category add(@RequestBody Category category) {
        return categoryService.add(category);
    }

 @Operation(summary = "Modificar una categoría", description = "Modifica una categoría existene en la base de datos")
    @PutMapping("/{id}")
    public Category update(@PathVariable int id, @RequestBody Category category) {
        return categoryService.update(id, category);
    }

    @Operation(summary = "Eliminar una categoría", description = "Elimina una categoría de la base de datos")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        categoryService.delete(id);
    }
}
