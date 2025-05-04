package barberia.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import barberia.api.entity.Usuario;
import barberia.api.service.UsuarioService;

@Controller
@RequestMapping("/view/usuarios")
public class UsuarioViewController {
    
    private final UsuarioService usuarioService;

    public UsuarioViewController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // Listar todos los usuarios (READ)
    @GetMapping
    public String listarUsuarios(Model model) {
        model.addAttribute("usuarios", usuarioService.get());
        model.addAttribute("nuevoUsuario", new Usuario()); // Para el formulario de creación
        return "usuario";
    }

    // Mostrar formulario de edición (UPDATE)
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable Integer id, Model model) {
        Usuario usuario = usuarioService.getById(id)
            .orElseThrow(() -> new IllegalArgumentException("ID de usuario inválido: " + id));
        model.addAttribute("usuario", usuario);
        return "editar-usuario";
    }

    // Procesar actualización (UPDATE)
    @PostMapping("/actualizar")
    public String actualizarUsuario(@ModelAttribute Usuario usuario) {
        usuarioService.update(usuario.getIdUsuario(), usuario);
        return "redirect:/view/usuarios";
    }

    // Procesar creación (CREATE)
    @PostMapping("/crear")
    public String crearUsuario(@ModelAttribute("nuevoUsuario") Usuario usuario) {
        usuarioService.add(usuario);
        return "redirect:/view/usuarios";
    }

    // Eliminar usuario (DELETE)
    @GetMapping("/eliminar/{id}")
    public String eliminarUsuario(@PathVariable Integer id) {
        usuarioService.delete(id);
        return "redirect:/view/usuarios";
    }

    // Consultar usuario por ID (READ ONE)
    @GetMapping("/{id}")
    public String verDetalleUsuario(@PathVariable Integer id, Model model) {
        Usuario usuario = usuarioService.getById(id)
            .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con ID: " + id));
        model.addAttribute("usuario", usuario);
        return "detalle-usuario";
    }
}