package barberia.api.controller;

import barberia.api.entity.Usuario;
import barberia.api.service.RolService;
import barberia.api.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/vista/usuarios")
public class UsuarioViewController {

    private final UsuarioService usuarioService;
    private final RolService rolService;

    public UsuarioViewController(UsuarioService usuarioService, RolService rolService) {
        this.usuarioService = usuarioService;
        this.rolService = rolService;
    }

    // Mostrar listado de usuarios y formulario de registro
    @GetMapping
    public String listarUsuarios(Model model) {
        model.addAttribute("usuarios", usuarioService.get());
        model.addAttribute("usuario", new Usuario()); // objeto para el formulario
        model.addAttribute("listaRoles", rolService.get()); // lista de roles para el formulario
        return "users"; // este debe coincidir con users.html
    }

    // Procesar nuevo usuario
    @PostMapping("/crear")
    public String crearUsuario(@ModelAttribute("usuario") Usuario usuario) {
        usuarioService.add(usuario);
        return "redirect:/vista/usuarios";
    }

    // Mostrar formulario de edición
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable Integer id, Model model) {
        Usuario usuario = usuarioService.getById(id)
            .orElseThrow(() -> new IllegalArgumentException("ID de usuario inválido: " + id));
        model.addAttribute("usuario", usuario);
        model.addAttribute("listaRoles", rolService.get());
        return "editar-usuario";
    }

    // Procesar actualización
    @PostMapping("/actualizar")
    public String actualizarUsuario(@ModelAttribute Usuario usuario) {
        usuarioService.update(usuario.getIdUsuario(), usuario);
        return "redirect:/vista/usuarios";
    }

    // Eliminar usuario
    @GetMapping("/eliminar/{id}")
    public String eliminarUsuario(@PathVariable Integer id) {
        usuarioService.delete(id);
        return "redirect:/vista/usuarios";
    }

    // Ver detalle individual (opcional)
    @GetMapping("/{id}")
    public String verDetalleUsuario(@PathVariable Integer id, Model model) {
        Usuario usuario = usuarioService.getById(id)
            .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con ID: " + id));
        model.addAttribute("usuario", usuario);
        return "detalle-usuario";
    }
}
