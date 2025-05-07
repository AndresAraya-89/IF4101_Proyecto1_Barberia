package barberia.api.controller;

import barberia.api.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/vista")
public class LoginViewController {

    // Mostrar formulario de login
    @GetMapping("/login")
    public String mostrarFormularioLogin(@RequestParam(name = "error", required = false) String error, Model model) {
        model.addAttribute("usuario", new Usuario());

        if (error != null) {
            model.addAttribute("error", "Usuario o contraseña incorrectos");
        }

        return "login";
    }
}
