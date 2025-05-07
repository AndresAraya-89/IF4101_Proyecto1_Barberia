package barberia.api.controller;

import barberia.api.entity.Usuario;
import barberia.api.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private UsuarioService usuarioService;

    // Procesar login (⚠️ distinta ruta para evitar conflicto con GET)
    @PostMapping("/procesar-login")
    public String procesarLogin(@RequestParam String correo, @RequestParam String contrasena, HttpSession session) {
        List<Usuario> usuarios = usuarioService.get();

        for (Usuario usuario : usuarios) {
            if (usuario.getCorreo().equalsIgnoreCase(correo) && usuario.getContrasena().equals(contrasena)) {
                session.setAttribute("usuario", usuario);

                String rol = usuario.getRol().getNombre();

                switch (rol) {
                    case "ADMIN":
                        return "redirect:/vista/dashboard";
                    case "BARBERO":
                        return "redirect:/vista/citas";
                    case "CLIENTE":
                        return "redirect:/vista/servicios";
                    default:
                        return "redirect:/error";
                }
            }
        }

        // ❗ Si no se encontró usuario válido
        return "redirect:/vista/login?error=true";
    }
}
