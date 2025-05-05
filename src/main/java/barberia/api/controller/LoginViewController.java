package barberia.api.controller;

import barberia.api.entity.Usuario;
import barberia.api.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/vista")
public class LoginViewController {

    @Autowired
    private UsuarioService usuarioService;

    // Mostrar formulario de login
    @GetMapping("/login")
    public String mostrarFormularioLogin(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "login";
    }

    // Procesar login
    @PostMapping("/login")
    public String procesarLogin(@ModelAttribute("usuario") Usuario usuario, Model model) {
        String correo = usuario.getCorreo();
        String contrasena = usuario.getContrasena();

        Usuario usuarioEncontrado = usuarioService.get().stream()
                .filter(u -> u.getCorreo().equals(correo) && u.getContrasena().equals(contrasena))
                .findFirst()
                .orElse(null);

        if (usuarioEncontrado == null) {
            model.addAttribute("error", "Usuario o contraseña incorrectos");
            return "login"; // Vuelve al login con mensaje de error
        }

        // Según el rol, redirige a diferentes vistas
        String rol = usuarioEncontrado.getRol().getNombre();
        if (rol.equalsIgnoreCase("ADMIN")) {
            return "redirect:/vista/dashboard";
        } else if (rol.equalsIgnoreCase("BARBERO")) {
            return "redirect:/vista/citas";
        } else if (rol.equalsIgnoreCase("CLIENTE")) {
            return "redirect:/vista/services";
        } else {
            model.addAttribute("error", "Rol no reconocido");
            return "login";
        }
    }
}
