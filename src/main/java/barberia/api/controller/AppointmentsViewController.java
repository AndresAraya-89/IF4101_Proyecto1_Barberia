package barberia.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/vista")
public class AppointmentsViewController {

    @GetMapping("/citas")
    public String mostrarCitas() {
        return "appointments"; // appointments.html en /templates
    }
}
