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

    public Optional<Product> getById(int id) {
        return productRepository.findById(id);
    }

    public void delete(int id) {
        productRepository.deleteById(id);
    }

    public Product update(int id, Product product) {
        Optional<Product> existingProduct = productRepository.findById(id);
        if (existingProduct.isPresent()) {
            Product updatedCategory = existingProduct.get();
            updatedCategory.setDescription(product.getDescription());
            return productRepository.save(updatedCategory);
        } else {
            throw new RuntimeException("Categoría no encontrada con ID: " + id);
        }
    }
}
