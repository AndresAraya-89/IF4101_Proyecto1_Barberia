package barberia.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import barberia.api.entity.*;
import barberia.api.service.*;

@Controller
@RequestMapping("/view/citas")
public class CitaViewController {
    
    private final CitaService citaService;
    private final UsuarioService usuarioService;
    private final CorteService corteService;
    private final HorarioService horarioService;

    public CitaViewController(CitaService citaService, UsuarioService usuarioService, 
                            CorteService corteService, HorarioService horarioService) {
        this.citaService = citaService;
        this.usuarioService = usuarioService;
        this.corteService = corteService;
        this.horarioService = horarioService;
    } 

    // Listar todas las citas (READ)
    @GetMapping
    public String listarCitas(Model model) {
        model.addAttribute("citas", citaService.get());
        model.addAttribute("nuevaCita", new Cita());
        model.addAttribute("clientes", usuarioService.getClientes()); // Método para obtener solo clientes
        model.addAttribute("barberos", usuarioService.getBarberos()); // Método para obtener solo barberos
        model.addAttribute("cortes", corteService.get());
        model.addAttribute("horarios", horarioService.get());
        return "cita";
    }

    // Mostrar formulario de edición (UPDATE)
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable Integer id, Model model) {
        Cita cita = citaService.getById(id)
            .orElseThrow(() -> new IllegalArgumentException("ID de cita inválido: " + id));
        model.addAttribute("cita", cita);
        model.addAttribute("clientes", usuarioService.getClientes());
        model.addAttribute("barberos", usuarioService.getBarberos());
        model.addAttribute("cortes", corteService.get());
        model.addAttribute("horarios", horarioService.get());
        return "editar-cita";
    }

    // Procesar actualización (UPDATE)
    @PostMapping("/actualizar")
    public String actualizarCita(@ModelAttribute Cita cita,
                               @RequestParam Integer idUsuarioCliente,
                               @RequestParam Integer idUsuarioBarbero,
                               @RequestParam Integer idCorte,
                               @RequestParam Integer idHorario) {
        
        asignarRelaciones(cita, idUsuarioCliente, idUsuarioBarbero, idCorte, idHorario);
        citaService.update(cita.getIdCita(), cita);
        return "redirect:/view/citas";
    }

    // Procesar creación (CREATE)
    @PostMapping("/crear")
    public String crearCita(@ModelAttribute("nuevaCita") Cita cita,
                          @RequestParam Integer idUsuarioCliente,
                          @RequestParam Integer idUsuarioBarbero,
                          @RequestParam Integer idCorte,
                          @RequestParam Integer idHorario) {
        
        asignarRelaciones(cita, idUsuarioCliente, idUsuarioBarbero, idCorte, idHorario);
        citaService.add(cita);
        return "redirect:/view/citas";
    }

    // Eliminar cita (DELETE)
    @GetMapping("/eliminar/{id}")
    public String eliminarCita(@PathVariable Integer id) {
        citaService.delete(id);
        return "redirect:/view/citas";
    }

    // Consultar cita por ID (READ ONE)
    @GetMapping("/{id}")
    public String verDetalleCita(@PathVariable Integer id, Model model) {
        Cita cita = citaService.getById(id)
            .orElseThrow(() -> new IllegalArgumentException("Cita no encontrada con ID: " + id));
        model.addAttribute("cita", cita);
        return "detalle-cita";
    }

    // Método auxiliar para asignar relaciones
    private void asignarRelaciones(Cita cita, Integer idUsuarioCliente, Integer idUsuarioBarbero, 
                                 Integer idCorte, Integer idHorario) {
        Usuario cliente = new Usuario();
        cliente.setIdUsuario(idUsuarioCliente);
        cita.setUsuarioCliente(cliente);

        Usuario barbero = new Usuario();
        barbero.setIdUsuario(idUsuarioBarbero);
        cita.setUsuarioBarbero(barbero);

        Corte corte = new Corte();
        corte.setIdCorte(idCorte);
        cita.setCorte(corte);

        Horario horario = new Horario();
        horario.setIdHorarario(idHorario);
        cita.setHorario(horario);
    }
}