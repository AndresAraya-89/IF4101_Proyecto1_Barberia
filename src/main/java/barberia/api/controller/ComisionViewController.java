package barberia.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import barberia.api.entity.Comision;
import barberia.api.entity.Usuario;
import barberia.api.service.ComisionService;
import barberia.api.service.UsuarioService;

@Controller
@RequestMapping("/view/comisiones")
public class ComisionViewController {
    
    private final ComisionService comisionService;
    private final UsuarioService usuarioService;

    public ComisionViewController(ComisionService comisionService, UsuarioService usuarioService) {
        this.comisionService = comisionService;
        this.usuarioService = usuarioService;
    } 

    // Listar todas las comisiones (READ)
    @GetMapping
    public String listarComisiones(Model model) {
        model.addAttribute("comisiones", comisionService.get());
        model.addAttribute("nuevaComision", new Comision());
        model.addAttribute("barberos", usuarioService.getBarberos()); // Método para obtener solo barberos
        return "comision";
    }

    // Mostrar formulario de edición (UPDATE)
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable Integer id, Model model) {
        Comision comision = comisionService.getById(id)
            .orElseThrow(() -> new IllegalArgumentException("ID de comisión inválido: " + id));
        model.addAttribute("comision", comision);
        model.addAttribute("barberos", usuarioService.getBarberos());
        return "editar-comision";
    }

    // Procesar actualización (UPDATE)
    @PostMapping("/actualizar")
    public String actualizarComision(@ModelAttribute Comision comision,
                                   @RequestParam Integer idUsuario) {
        Usuario barbero = new Usuario();
        barbero.setIdUsuario(idUsuario);
        comision.setUsuario(barbero);
        
        comisionService.update(comision.getIdComision(), comision);
        return "redirect:/view/comisiones";
    }

    // Procesar creación (CREATE)
    @PostMapping("/crear")
    public String crearComision(@ModelAttribute("nuevaComision") Comision comision,
                              @RequestParam Integer idUsuario) {
        Usuario barbero = new Usuario();
        barbero.setIdUsuario(idUsuario);
        comision.setUsuario(barbero);
        
        comisionService.add(comision);
        return "redirect:/view/comisiones";
    }

    // Eliminar comisión (DELETE)
    @GetMapping("/eliminar/{id}")
    public String eliminarComision(@PathVariable Integer id) {
        comisionService.delete(id);
        return "redirect:/view/comisiones";
    }

    // Consultar comisión por ID (READ ONE)
    @GetMapping("/{id}")
    public String verDetalleComision(@PathVariable Integer id, Model model) {
        Comision comision = comisionService.getById(id)
            .orElseThrow(() -> new IllegalArgumentException("Comisión no encontrada con ID: " + id));
        model.addAttribute("comision", comision);
        return "detalle-comision";
    }
}