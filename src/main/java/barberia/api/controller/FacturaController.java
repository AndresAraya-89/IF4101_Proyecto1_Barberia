package barberia.api.controller;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import barberia.api.entity.Cita;
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
    public ResponseEntity<Factura> getById(@PathVariable("idFactura") int id) {
        return facturaService.getById(id)
                .map(factura -> ResponseEntity.ok(factura))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Crear una nueva factura", description = "Agrega una nueva factura a la base de datos")
    public ResponseEntity<?> add(
            @RequestParam Integer idCita,
            @RequestBody Factura factura) {
        try {
            // Crea un objeto cita mínimo con el ID recibido
            Cita cita = new Cita();
            cita.setIdCita(idCita);

            // Asigna esta cita a la factura
            factura.setIdCita(cita);

            // Llama al servicio existente
            Factura facturaGuardado = facturaService.add(factura);
            return ResponseEntity.ok(facturaGuardado);

        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @Operation(summary = "Modificar una factura", description = "Modifica una factura existene en la base de datos")
    @PutMapping("/{idFactura}")
    public Factura update(@PathVariable ("idFactura") int id, @RequestBody Factura factura) {
        return facturaService.update(id, factura);
    }

    @Operation(summary = "Eliminar una factura", description = "Elimina una factura de la base de datos")
    @DeleteMapping("/{idFactura}")
    public void delete(@PathVariable ("idFactura") int id) {
        facturaService.delete(id);
    }
}
