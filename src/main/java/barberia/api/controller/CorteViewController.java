package barberia.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import barberia.api.entity.Corte;
import barberia.api.service.CorteService;

@Controller
@RequestMapping("/view/cortes")
public class CorteViewController {
    
    private final CorteService corteService;

    public CorteViewController(CorteService corteService) {
        this.corteService = corteService;
    }

    // Listar todos los cortes (READ)
    @GetMapping
    public String listarCortes(Model model) {
        model.addAttribute("cortes", corteService.get());
        model.addAttribute("nuevoCorte", new Corte()); // Para el formulario de creación
        return "corte";
    }

    // Mostrar formulario de edición (UPDATE)
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable Integer id, Model model) {
        Corte corte = corteService.getById(id)
            .orElseThrow(() -> new IllegalArgumentException("ID de corte inválido: " + id));
        model.addAttribute("corte", corte);
        return "editar-corte";
    }

    // Procesar actualización (UPDATE)
    @PostMapping("/actualizar")
    public String actualizarCorte(@ModelAttribute Corte corte) {
        corteService.update(corte.getIdCorte(), corte);
        return "redirect:/view/cortes";
    }

    // Procesar creación (CREATE)
    @PostMapping("/crear")
    public String crearCorte(@ModelAttribute("nuevoCorte") Corte corte) {
        corteService.add(corte);
        return "redirect:/view/cortes";
    }

    // Eliminar corte (DELETE)
    @GetMapping("/eliminar/{id}")
    public String eliminarCorte(@PathVariable Integer id) {
        corteService.delete(id);
        return "redirect:/view/cortes";
    }

    // Consultar corte por ID (READ ONE)
    @GetMapping("/{id}")
    public String verDetalleCorte(@PathVariable Integer id, Model model) {
        Corte corte = corteService.getById(id)
            .orElseThrow(() -> new IllegalArgumentException("Corte no encontrado con ID: " + id));
        model.addAttribute("corte", corte);
        return "detalle-corte"; // Nueva plantilla para detalles
    }
}