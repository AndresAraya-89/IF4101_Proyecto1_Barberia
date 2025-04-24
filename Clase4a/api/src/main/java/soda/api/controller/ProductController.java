package soda.api.controller;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import soda.api.entity.Product;
import soda.api.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@CrossOrigin(origins = "*") // Permitir acceso desde cualquier origen
@Tag(name = "Categories", description = "API para gestionar categorías") // Grupo en Swagger
@RestController
@RequestMapping("/products")
@NoArgsConstructor @AllArgsConstructor
public class ProductController {
    @Autowired
    private ProductService productService;
    @GetMapping
@Operation(summary = "Obtener todos los productos", description = "Devuelve una lista de productos")
    public List<Product> get() {
        return productService.get();
    }

    @GetMapping("/{id}")
@Operation(summary = "Obtener un producto por ID", description = "Busca un producto en la base de datos según su ID")
    public Optional<Product> getById(@PathVariable int id) {
        return productService.getById(id);
    }

    @PostMapping
@Operation(summary = "Crear un nuevo producto", description = "Agrega un producto a la base de datos")
    public Product add(@RequestBody Product product) {
     return productService.add(product);
    }

@Operation(summary = "Modificar un producto", description = "Modifica un producto existene en la base de datos")
    @PutMapping("/{id}")
    public Product update(@PathVariable int id, @RequestBody Product product) {
        return productService.update(id, product);
    }

@Operation(summary = "Eliminar un producto", description = "Elimina un produto de la base de datos")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        productService.delete(id);
    }
}