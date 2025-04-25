package barberia.api.controller;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import barberia.api.entity.Factura;
import barberia.api.service.FacturaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin(origins = "*") // Permitir acceso desde cualquier origen
@Tag(name = "Factura", description = "API para gestionar facturas") // Grupo en Swagger
@RestController
@RequestMapping("/factura")
public class FacturaController {
    @Autowired
    private FacturaService facturaService;

    @GetMapping
    @Operation(summary = "Obtener todas las facturas", description = "Devuelve una lista de facturas")
    public List<Factura> get() {
        return facturaService.get();
    }

    @GetMapping("/{idFactura}")
    @Operation(summary = "Obtener una factura por ID", description = "Busca una factura en la base de datos según su ID")
    public Optional<Factura> getById(@PathVariable int id) {
        return facturaService.getById(id);
    }

    @PostMapping
    @Operation(summary = "Crear una nueva factura", description = "Agrega una nueva factura a la base de datos")
    public Factura add(@RequestBody Factura factura) {
        return facturaService.add(factura);
    }

    @Operation(summary = "Modificar una factura", description = "Modifica una factura existene en la base de datos")
    @PutMapping("/{idFactura}")
    public Factura update(@PathVariable int id, @RequestBody Factura factura) {
        return facturaService.update(id, factura);
    }

    @Operation(summary = "Eliminar una factura", description = "Elimina una factura de la base de datos")
    @DeleteMapping("/{idFactura}")
    public void delete(@PathVariable int id) {
        facturaService.delete(id);
    }
}
