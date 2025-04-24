package barberia.api.service;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import barberia.api.entity.Repositorio;
import barberia.api.repository.RepositorioRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RepositorioService {

    @Autowired
    private RepositorioRepository repositorioRepository;

    public Repositorio add(Repositorio repositorio) {
        return repositorioRepository.save(repositorio);
    }

    public List<Repositorio> get() {
        return repositorioRepository.findAll();
    }

    public Optional<Repositorio> getById(int id) {
        return repositorioRepository.findById(id);
    }

    public void delete(int id) {
        repositorioRepository.deleteById(id);
    }

    public Repositorio update(int id, Repositorio repositorio) {
        Optional<Repositorio> existingRepositorio = repositorioRepository.findById(id);
        if (existingRepositorio.isPresent()) {
            Repositorio updatedRepositorio = existingRepositorio.get();
            // Si el repositorio existe que realice una copia completa del objeto recibido
            // por parametro
            updatedRepositorio = repositorio;
            return repositorioRepository.save(updatedRepositorio);
        } else {
            throw new RuntimeException("Repositorio no encontrada con ID: " + id);
        }
    }
}
