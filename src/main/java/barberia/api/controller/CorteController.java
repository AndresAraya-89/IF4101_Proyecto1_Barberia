package barberia.api.controller;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import barberia.api.entity.Corte;
import barberia.api.entity.Repositorio;
import barberia.api.service.CorteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin(origins = "*") // Permitir acceso desde cualquier origen
@Tag(name = "Corte", description = "API para gestionar cortes") // Grupo en Swagger
@RestController
//@Controller
@RequestMapping("/corte")
public class CorteController {
    @Autowired
    private CorteService corteService;

    @GetMapping
    @Operation(summary = "Obtener todas los cortes", description = "Devuelve una lista de cortes")
    public List<Corte> get() {
        return corteService.get();
    }

    // Endpoint para mostrar la vista HTML con datos
    @GetMapping("/listar")
    public String listarCortes(Model model) {
        List<Corte> cortes = corteService.get();
        model.addAttribute("cortes", cortes); // Asegúrate que este nombre coincida con el HTML
        return "corte"; // Nombre del template sin extensión
    }


    @GetMapping("/{idCorte}")
    @ResponseBody
    @Operation(summary = "Obtener un corte por ID", description = "Busca un corte en la base de datos según su ID")
    public ResponseEntity<Corte> getById(@PathVariable("idCorte") int id) {
        return corteService.getById(id)
                .map(corte -> ResponseEntity.ok(corte))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo corte", description = "Crea un corte a la base de datos")
    public ResponseEntity<?> add(
            @RequestParam Integer idRepositorio, // Recibes el ID por parámetro
            @RequestBody Corte corte // Recibes el resto de datos del corte
    ) {
        try {
            // Crea un objeto Repositorio mínimo con el ID recibido
            Repositorio repositorio = new Repositorio();
            repositorio.setIdRepositorio(idRepositorio);

            // Asigna este repositorio al corte
            corte.setRepositorio(repositorio);

            // Llama al servicio existente
            Corte corteGuardado = corteService.add(corte);
            return ResponseEntity.ok(corteGuardado);
 
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @PutMapping("/{idCorte}")
    @Operation(summary = "Modificar un corte", description = "Actualiza un corte a la base de datos")
    public ResponseEntity<?> update(@PathVariable("idCorte") int id, @RequestBody Corte corte) {
        try {
            Corte updatedCorte = corteService.update(id, corte);
            return ResponseEntity.ok(updatedCorte);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @Operation(summary = "Eliminar un corte", description = "Elimina un corte de la base de datos")
    @DeleteMapping("/{idCorte}")
    public void delete(@PathVariable("idCorte") int id) {
        corteService.delete(id);
    }
}
