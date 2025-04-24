package barberia.api.service;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import barberia.api.entity.Factura;
import barberia.api.repository.FacturaRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FacturaService {
    @Autowired
    private FacturaRepository facturaRepository;

    public Factura add(Factura factura) {
        return facturaRepository.save(factura);
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
        Optional<Factura> existingFactura = facturaRepository.findById(id);
        if (existingFactura.isPresent()) {
            Factura updatedFactura = existingFactura.get();
            // Si la factura existe que realice una copia completa del objeto recibido por
            // parametro
            updatedFactura = factura;
            return facturaRepository.save(updatedFactura);
        } else {
            throw new RuntimeException("Factura no encontrada con ID: " + id);
        }
    }
}
