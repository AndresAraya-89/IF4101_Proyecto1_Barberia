package barberia.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import barberia.api.entity.Usuario;
import barberia.api.entity.Rol;
import barberia.api.service.UsuarioService;
import barberia.api.service.RolService;

import java.util.List;

@Controller
@RequestMapping("/vista")
public class UsuarioVistaController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RolService rolService;

    // Mostrar formulario de registro
    @GetMapping("/registro")
    public String mostrarFormularioRegistro(Model model) {
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setEstado(1); // Activo por defecto
        model.addAttribute("usuario", nuevoUsuario);

        List<Rol> listaRoles = rolService.get();
        model.addAttribute("listaRoles", listaRoles);

        return "register"; // Carga el HTML: register.html
    }

    // Procesar el formulario
    @PostMapping("/registro")
    public String registrarUsuario(@ModelAttribute("usuario") Usuario usuario) {
        usuarioService.add(usuario);
        return "redirect:/vista/login"; // Cambiar si no hay login
    }
}
