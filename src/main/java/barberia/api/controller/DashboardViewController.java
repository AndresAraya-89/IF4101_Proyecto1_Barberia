package barberia.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/vista/dashboard")
public class DashboardViewController {

    @GetMapping
    public String mostrarDashboard() {
        return "dashboard"; // Renderiza dashboard.html
    }
}
