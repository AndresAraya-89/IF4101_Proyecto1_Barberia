package barberia.service;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import barberia.api.entity.Rol;
import barberia.api.repository.RolRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RolService {

    @Autowired
    private RolRepository rolRepository;

    public Rol add(Rol rol) {
        return rolRepository.save(rol);
    }

    public List<Rol> get() {
        return rolRepository.findAll();
    }

    public Optional<Rol> getById(int id) {
        return rolRepository.findById(id);
    }

    public void delete(int id) {
        rolRepository.deleteById(id);
    }

    public Rol update(int id, Rol rol) {
        Optional<Rol> existingRol = rolRepository.findById(id);
        if (existingRol.isPresent()) {
            Rol updatedRol = existingRol.get();
            // Si el rol existe que realice una copia completa del objeto recibido por
            // parametro
            updatedRol = rol;
            return rolRepository.save(updatedRol);
        } else {
            throw new RuntimeException("Rol no encontrada con ID: " + id);
        }
    }
}
