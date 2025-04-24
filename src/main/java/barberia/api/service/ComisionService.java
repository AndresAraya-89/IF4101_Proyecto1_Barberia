package barberia.api.service;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import barberia.api.entity.Comision;
import barberia.api.repository.ComisionRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ComisionService {
    @Autowired
    private ComisionRepository comisionRepository;

    public Comision add(Comision comision) {
        return comisionRepository.save(comision);
    }

    public List<Comision> get() {
        return comisionRepository.findAll();
    }

    public Optional<Comision> getById(int id) {
        return comisionRepository.findById(id);
    }

    public void delete(int id) {
        comisionRepository.deleteById(id);
    }

    public Comision update(int id, Comision comision) {
        Optional<Comision> existingComision = comisionRepository.findById(id);
        if (existingComision.isPresent()) {
            Comision updatedComision = existingComision.get();
            // Si la comision existe que realice una copia completa del objeto recibido por
            // parametro
            updatedComision = comision;
            return comisionRepository.save(updatedComision);
        } else {
            throw new RuntimeException("Comision no encontrada con ID: " + id);
        }
    }
}
