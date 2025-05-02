package barberia.api.service;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import barberia.api.entity.Corte;
import barberia.api.entity.Repositorio;
import barberia.api.repository.CorteRepository;
import barberia.api.repository.RepositorioRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CorteService {

    @Autowired
    private CorteRepository corteRepository;
    private RepositorioRepository repositorioRepository;

    public Corte add(Corte corte) {
        // Verifica si el repositorio existe
        Repositorio repositorio = repositorioRepository.findById(corte.getRepositorio().getIdRepositorio())
                .orElseThrow(() -> new RuntimeException(
                        "Repositorio no encontrado con ID: " + corte.getRepositorio().getIdRepositorio()));

        corte.setRepositorio(repositorio);

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

    public Corte update(int id, Corte corteActualizado) {
        // Verifica si los objetos existen
        Corte corteExistente = corteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Corte no encontrado con ID: " + id));

        // Actualizar repositorio
        if (corteActualizado.getRepositorio() != null) {
            Repositorio repositorio = repositorioRepository.findById(
                    corteActualizado.getRepositorio().getIdRepositorio())
                    .orElseThrow(() -> new RuntimeException("Repositorio no encontrado"));

            corteExistente.setRepositorio(repositorio);
        }

        // Actualizar campos simples
        corteExistente.setCosto(corteActualizado.getCosto());
        corteExistente.setDetalle(corteActualizado.getDetalle());
        corteExistente.setUrl_imagen(corteActualizado.getUrl_imagen());

        return corteRepository.save(corteExistente);
    }

}
