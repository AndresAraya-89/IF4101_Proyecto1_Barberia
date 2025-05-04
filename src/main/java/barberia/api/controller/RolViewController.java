package barberia.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import barberia.api.entity.Rol;
import barberia.api.service.RolService;

@Controller
@RequestMapping("/view/roles")
public class RolViewController {
    
    private final RolService rolService;

    public RolViewController(RolService rolService) {
        this.rolService = rolService;
    }

    // Listar todos los roles (READ)
    @GetMapping
    public String listarRoles(Model model) {
        model.addAttribute("roles", rolService.get());
        model.addAttribute("nuevoRol", new Rol()); // Para el formulario de creación
        return "rol";
    }

    // Mostrar formulario de edición (UPDATE)
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable Integer id, Model model) {
        Rol rol = rolService.getById(id)
            .orElseThrow(() -> new IllegalArgumentException("ID de rol inválido: " + id));
        model.addAttribute("rol", rol);
        return "editar-rol";
    }

    // Procesar actualización (UPDATE)
    @PostMapping("/actualizar")
    public String actualizarRol(@ModelAttribute Rol rol) {
        rolService.update(rol.getIdRol(), rol);
        return "redirect:/view/roles";
    }

    // Procesar creación (CREATE)
    @PostMapping("/crear")
    public String crearRol(@ModelAttribute("nuevoRol") Rol rol) {
        rolService.add(rol);
        return "redirect:/view/roles";
    }

    // Eliminar rol (DELETE)
    @GetMapping("/eliminar/{id}")
    public String eliminarRol(@PathVariable Integer id) {
        rolService.delete(id);
        return "redirect:/view/roles";
    }

    // Consultar rol por ID (READ ONE)
    @GetMapping("/{id}")
    public String verDetalleRol(@PathVariable Integer id, Model model) {
        Rol rol = rolService.getById(id)
            .orElseThrow(() -> new IllegalArgumentException("Rol no encontrado con ID: " + id));
        model.addAttribute("rol", rol);
        return "detalle-rol";
    }
}