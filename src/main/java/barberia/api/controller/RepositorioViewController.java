package barberia.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import barberia.api.entity.Repositorio;
import barberia.api.entity.Usuario;
import barberia.api.service.RepositorioService;
import barberia.api.service.UsuarioService;

@Controller
@RequestMapping("/view/repositorios")
public class RepositorioViewController {
    
    private final RepositorioService repositorioService;
    private final UsuarioService usuarioService;

    public RepositorioViewController(RepositorioService repositorioService, UsuarioService usuarioService) {
        this.repositorioService = repositorioService;
        this.usuarioService = usuarioService;
    }

    // Listar todos los repositorios (READ)
    @GetMapping
    public String listarRepositorios(Model model) {
        model.addAttribute("repositorios", repositorioService.get());
        model.addAttribute("nuevoRepositorio", new Repositorio());
        model.addAttribute("usuarios", usuarioService.get()); // Para selector de usuarios
        return "repositorio";
    }

    // Mostrar formulario de edición (UPDATE)
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable Integer id, Model model) {
        Repositorio repositorio = repositorioService.getById(id)
            .orElseThrow(() -> new IllegalArgumentException("ID de repositorio inválido: " + id));
        model.addAttribute("repositorio", repositorio);
        model.addAttribute("usuarios", usuarioService.get());
        return "editar-repositorio";
    }

    // Procesar actualización (UPDATE)
    @PostMapping("/actualizar")
    public String actualizarRepositorio(@ModelAttribute Repositorio repositorio, 
                                      @RequestParam Integer idUsuario) {
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(idUsuario);
        repositorio.setUsuario(usuario);
        
        repositorioService.update(repositorio.getIdRepositorio(), repositorio);
        return "redirect:/view/repositorios";
    }

    // Procesar creación (CREATE)
    @PostMapping("/crear")
    public String crearRepositorio(@ModelAttribute("nuevoRepositorio") Repositorio repositorio,
                                 @RequestParam Integer idUsuario) {
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(idUsuario);
        repositorio.setUsuario(usuario);
        
        repositorioService.add(repositorio);
        return "redirect:/view/repositorios";
    }

    // Eliminar repositorio (DELETE)
    @GetMapping("/eliminar/{id}")
    public String eliminarRepositorio(@PathVariable Integer id) {
        repositorioService.delete(id);
        return "redirect:/view/repositorios";
    }

    // Consultar repositorio por ID (READ ONE)
    @GetMapping("/{id}")
    public String verDetalleRepositorio(@PathVariable Integer id, Model model) {
        Repositorio repositorio = repositorioService.getById(id)
            .orElseThrow(() -> new IllegalArgumentException("Repositorio no encontrado con ID: " + id));
        model.addAttribute("repositorio", repositorio);
        return "detalle-repositorio";
    }
}