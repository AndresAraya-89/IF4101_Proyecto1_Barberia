package barberia.service;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import barberia.api.entity.Cita;
import barberia.api.repository.CitaRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CitaService {
    @Autowired
    private CitaRepository citaRepository;

    public Cita add(Cita cita) {
        return citaRepository.save(cita);
    }

    public List<Cita> get() {
        return citaRepository.findAll();
    }

    public Optional<Cita> getById(int id) {
        return citaRepository.findById(id);
    }

    public void delete(int id) {
        citaRepository.deleteById(id);
    }

    public Cita update(int id, Cita cita) {
        Optional<Cita> existingCita = citaRepository.findById(id);
        if (existingCita.isPresent()) {
            Cita updatedCita = existingCita.get();
            // Si el corte existe que realice una copia completa del objeto recibido por
            // parametro
            updatedCita = cita;
            return citaRepository.save(updatedCita);
        } else {
            throw new RuntimeException("Cita no encontrada con ID: " + id);
        }
    }
}
