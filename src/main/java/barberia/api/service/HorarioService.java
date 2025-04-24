package barberia.api.service;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import barberia.api.entity.Horario;
import barberia.api.repository.HorarioRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class HorarioService {

    @Autowired
    private HorarioRepository horarioRepository;

    public Horario add(Horario horario) {
        return horarioRepository.save(horario);
    }

    public List<Horario> get() {
        return horarioRepository.findAll();
    }

    public Optional<Horario> getById(int id) {
        return horarioRepository.findById(id);
    }

    public void delete(int id) {
        horarioRepository.deleteById(id);
    }

    public Horario update(int id, Horario horario) {
        Optional<Horario> existingHorario = horarioRepository.findById(id);
        if (existingHorario.isPresent()) {
            Horario updatedHorario = existingHorario.get();
            // Si el horario existe que realice una copia completa del objeto recibido por
            // parametro
            updatedHorario = horario;
            return horarioRepository.save(updatedHorario);
        } else {
            throw new RuntimeException("Horario no encontrada con ID: " + id);
        }
    }
}
