package barberia.service;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import barberia.api.entity.Corte;
import barberia.api.repository.CorteRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CorteService {

    @Autowired
    private CorteRepository corteRepository;

    public Corte add(Corte corte) {
        return corteRepository.save(corte);
    }

    public List<Corte> get() {
        return corteRepository.findAll();
    }

    public Optional<Corte> getById(int id) {
        return corteRepository.findById(id);
    }

    public void delete(int id) {
        corteRepository.deleteById(id);
    }

    public Corte update(int id, Corte corte) {
        Optional<Corte> existingCorte = corteRepository.findById(id);
        if (existingCorte.isPresent()) {
            Corte updatedCorte = existingCorte.get();
            // Si el corte existe que realice una copia completa del objeto recibido por
            // parametro
            updatedCorte = corte;
            return corteRepository.save(updatedCorte);
        } else {
            throw new RuntimeException("Corte no encontrada con ID: " + id);
        }
    }
}
