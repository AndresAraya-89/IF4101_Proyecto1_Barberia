package barberia.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import barberia.api.entity.Horario;
import barberia.api.entity.Usuario;
import barberia.api.service.HorarioService;
import barberia.api.service.UsuarioService;

@Controller
@RequestMapping("/view/horarios")
public class HorarioViewController {
    
    private final HorarioService horarioService;
    private final UsuarioService usuarioService;

    public HorarioViewController(HorarioService horarioService, UsuarioService usuarioService) {
        this.horarioService = horarioService;
        this.usuarioService = usuarioService;
    } 

    // Listar todos los horarios (READ)
    @GetMapping
    public String listarHorarios(Model model) {
        model.addAttribute("horarios", horarioService.get());
        model.addAttribute("nuevoHorario", new Horario());
        model.addAttribute("usuarios", usuarioService.get()); // Para selector de usuarios
        return "horario";
    }

    // Mostrar formulario de edición (UPDATE)
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable Integer id, Model model) {
        Horario horario = horarioService.getById(id)
            .orElseThrow(() -> new IllegalArgumentException("ID de horario inválido: " + id));
        model.addAttribute("horario", horario);
        model.addAttribute("usuarios", usuarioService.get());
        return "editar-horario";
    }

    // Procesar actualización (UPDATE)
    @PostMapping("/actualizar")
    public String actualizarHorario(@ModelAttribute Horario horario, 
                                  @RequestParam Integer idUsuario) {
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(idUsuario);
        horario.setUsuario(usuario);
        
        horarioService.update(horario.getIdHorarario(), horario);
        return "redirect:/view/horarios";
    }

    // Procesar creación (CREATE)
    @PostMapping("/crear")
    public String crearHorario(@ModelAttribute("nuevoHorario") Horario horario,
                             @RequestParam Integer idUsuario) {
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(idUsuario);
        horario.setUsuario(usuario);
        
        horarioService.add(horario);
        return "redirect:/view/horarios";
    }

    // Eliminar horario (DELETE)
    @GetMapping("/eliminar/{id}")
    public String eliminarHorario(@PathVariable Integer id) {
        horarioService.delete(id);
        return "redirect:/view/horarios";
    }

    // Consultar horario por ID (READ ONE)
    @GetMapping("/{id}")
    public String verDetalleHorario(@PathVariable Integer id, Model model) {
        Horario horario = horarioService.getById(id)
            .orElseThrow(() -> new IllegalArgumentException("Horario no encontrado con ID: " + id));
        model.addAttribute("horario", horario);
        return "detalle-horario";
    }
}