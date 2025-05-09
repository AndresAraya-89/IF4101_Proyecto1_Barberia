package barberia.api.controller;

import barberia.api.entity.Cita;
import barberia.api.entity.Usuario;
import barberia.api.service.CitaService;
import barberia.api.service.CorteService;
import barberia.api.service.HorarioService;
import barberia.api.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/vista/citas")
public class AppointmentsViewController {

    @Autowired 
    private CitaService citaService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private CorteService corteService;

    @Autowired
    private HorarioService horarioService;

    @GetMapping
    public String mostrarCitas(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if (usuario == null) {
            return "redirect:/vista/login";
        }

        List<Cita> citas = citaService.get();

        List<Cita> citasFiltradas = citas.stream().filter(c -> {
            if (usuario.getRol().getNombre().equals("ADMIN")) return true;
            if (usuario.getRol().getNombre().equals("BARBERO"))
                return c.getUsuarioBarbero() != null && c.getUsuarioBarbero().getIdUsuario() == usuario.getIdUsuario();
            if (usuario.getRol().getNombre().equals("CLIENTE"))
                return c.getUsuarioCliente() != null && c.getUsuarioCliente().getIdUsuario() == usuario.getIdUsuario();
            return false;
        }).collect(Collectors.toList());

        model.addAttribute("usuario", usuario);
        model.addAttribute("citas", citasFiltradas);
        model.addAttribute("nuevaCita", new Cita());

        model.addAttribute("clientes", usuarioService.get().stream()
                .filter(u -> u.getRol().getNombre().equals("CLIENTE")).collect(Collectors.toList()));
        model.addAttribute("barberos", usuarioService.get().stream()
                .filter(u -> u.getRol().getNombre().equals("BARBERO")).collect(Collectors.toList()));
        model.addAttribute("cortes", corteService.get());
        model.addAttribute("horarios", horarioService.get());

        return "appointments";
    }

    @PostMapping("/crear")
    public String guardarCita(@ModelAttribute("nuevaCita") Cita cita) {
        citaService.add(cita);
        return "redirect:/vista/citas";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarCita(@PathVariable("id") int id) {
        citaService.delete(id);
        return "redirect:/vista/citas";
    }
    @GetMapping("/editar/{id}")
public String mostrarFormularioEditar(@PathVariable("id") int id, Model model) {
    Cita cita = citaService.getById(id).orElseThrow();
    model.addAttribute("cita", cita);

    model.addAttribute("clientes", usuarioService.get().stream()
            .filter(u -> u.getRol().getNombre().equals("CLIENTE")).collect(Collectors.toList()));
    model.addAttribute("barberos", usuarioService.get().stream()
            .filter(u -> u.getRol().getNombre().equals("BARBERO")).collect(Collectors.toList()));
    model.addAttribute("cortes", corteService.get());
    model.addAttribute("horarios", horarioService.get());

    return "editar-cita";
}

@PostMapping("/editar/{id}")
public String actualizarCita(@PathVariable("id") int id, @ModelAttribute("cita") Cita cita) {
    cita.setIdCita(id); // Asegurar que se setea el ID
    citaService.update(cita); // Este método debe existir en tu servicio
    return "redirect:/vista/citas";
}

}
