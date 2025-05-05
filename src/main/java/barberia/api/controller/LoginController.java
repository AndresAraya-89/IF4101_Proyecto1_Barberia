package barberia.api.controller;

import barberia.api.entity.Usuario;
import barberia.api.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
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
                        return "redirect:/vista/citas";
                    default:
                        return "redirect:/error";
                }
            }
        }

        return "redirect:/login?error=true"; // Si no encuentra usuario válido
    }
}
