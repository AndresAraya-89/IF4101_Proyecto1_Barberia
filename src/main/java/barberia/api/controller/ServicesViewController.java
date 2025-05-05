package barberia.api.controller;

import barberia.api.service.CorteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/vista/servicios")
public class ServicesViewController {

    @Autowired
    private CorteService corteService;

    @GetMapping
    public String mostrarServicios(Model model) {
        model.addAttribute("cortes", corteService.get());
        return "services"; // Renderiza services.html
    }
}
