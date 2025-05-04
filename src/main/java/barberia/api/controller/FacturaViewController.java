package barberia.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import barberia.api.entity.Factura;
import barberia.api.entity.Cita;
import barberia.api.service.FacturaService;
import barberia.api.service.CitaService;

@Controller
@RequestMapping("/view/facturas")
public class FacturaViewController {
    
    private final FacturaService facturaService;
    private final CitaService citaService;

    public FacturaViewController(FacturaService facturaService, CitaService citaService) {
        this.facturaService = facturaService;
        this.citaService = citaService;
    }

    // Listar todas las facturas (READ)
    @GetMapping
    public String listarFacturas(Model model) {
        model.addAttribute("facturas", facturaService.get());
        model.addAttribute("nuevaFactura", new Factura());
        model.addAttribute("citas", citaService.get());
        return "factura";
    }

    // Mostrar formulario de edición (UPDATE)
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable Integer id, Model model) {
        Factura factura = facturaService.getById(id)
            .orElseThrow(() -> new IllegalArgumentException("ID de factura inválido: " + id));
        model.addAttribute("factura", factura);
        model.addAttribute("citas", citaService.get());
        return "editar-factura";
    }

    // Procesar actualización (UPDATE)
    @PostMapping("/actualizar")
    public String actualizarFactura(@ModelAttribute Factura factura,
                                  @RequestParam Integer idCita) {
        Cita cita = new Cita();
        cita.setIdCita(idCita);
        factura.setIdCita(cita);
        
        facturaService.update(factura.getIdFactura(), factura);
        return "redirect:/view/facturas";
    }

    // Procesar creación (CREATE)
    @PostMapping("/crear")
    public String crearFactura(@ModelAttribute("nuevaFactura") Factura factura,
                             @RequestParam Integer idCita) {
        Cita cita = new Cita();
        cita.setIdCita(idCita);
        factura.setIdCita(cita);
        
        facturaService.add(factura);
        return "redirect:/view/facturas";
    }

    // Eliminar factura (DELETE)
    @GetMapping("/eliminar/{id}")
    public String eliminarFactura(@PathVariable Integer id) {
        facturaService.delete(id);
        return "redirect:/view/facturas";
    }

    // Consultar factura por ID (READ ONE)
    @GetMapping("/{id}")
    public String verDetalleFactura(@PathVariable Integer id, Model model) {
        Factura factura = facturaService.getById(id)
            .orElseThrow(() -> new IllegalArgumentException("Factura no encontrada con ID: " + id));
        model.addAttribute("factura", factura);
        return "detalle-factura";
    }
}