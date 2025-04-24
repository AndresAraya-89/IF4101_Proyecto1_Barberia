package soda.api.service;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import soda.api.entity.Product;
import soda.api.repository.ProductRepository;
import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class ProductService {
      @Autowired
    private ProductRepository productRepository;

    public Product add(Product category) {
        return productRepository.save(category);
    }

    public List<Product> get() {
        return productRepository.findAll();
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
