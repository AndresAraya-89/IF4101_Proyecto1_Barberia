package barberia.api.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import barberia.api.entity.Cita;
import barberia.api.entity.Comision;
import barberia.api.entity.Factura;
import barberia.api.entity.Usuario;
import barberia.api.repository.CitaRepository;
import barberia.api.repository.FacturaRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FacturaService {
      @Autowired
    private FacturaRepository facturaRepository;

    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private ComisionService comisionService;


    public Factura add(Factura factura) {
        // Verifica si la cita existe
        Cita cita = citaRepository.findById(factura.getIdCita().getIdCita())
                .orElseThrow(() -> new RuntimeException(
                        "Cita no encontrado con ID: " + factura.getIdCita().getIdCita()));

        String fechaHora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        double montoPagar = cita.getCorte().getCosto();

        factura.setFechaHora(fechaHora);
        factura.setIdCita(cita);
        factura.setTotal(montoPagar);
        factura.setEstado(1);

        generarComision(factura.getIdCita().getUsuarioBarbero(), montoPagar);
        
        return facturaRepository.save(factura);
    }

    
    public void generarComision(Usuario barbero, double monto){
        Comision comision = new Comision();
        comision.setUsuario(barbero);
        comision.setMonto(monto);
        comision.setEstado(1); // 1 = Pendiente de pago
        
        comisionService.add(comision);
    }


    public List<Factura> get() {
        return facturaRepository.findAll();
    }

    public Optional<Factura> getById(int id) {
        return facturaRepository.findById(id);
    }

    public void delete(int id) {
        facturaRepository.deleteById(id);
    }

    public Factura update(int id, Factura factura) {
        Optional<Factura> existingComision = facturaRepository.findById(id);
        if (existingComision.isPresent()) {
            Factura updatedFactura = existingComision.get();

            // Si la comision existe que realice una copia
            String fechaHora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            updatedFactura.setFechaHora(fechaHora);
            updatedFactura.setEstado(factura.getEstado());
            return facturaRepository.save(updatedFactura);
        } else {
            throw new RuntimeException("Factura no encontrada con ID: " + id);
        }
    }
}
