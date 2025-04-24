package soda.api.service;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soda.api.entity.Category;
import soda.api.repository.CategoryRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public Category add(Category category) {
        return categoryRepository.save(category);
    }

    public List<Category> get() {
        return categoryRepository.findAll();
    }

    public Optional<Category> getById(int id) {
        return categoryRepository.findById(id);
    }

    public void delete(int id) {
        categoryRepository.deleteById(id);
    }

    public Category update(int id, Category category) {
        Optional<Category> existingCategory = categoryRepository.findById(id);
        if (existingCategory.isPresent()) {
            Category updatedCategory = existingCategory.get();
            updatedCategory.setDescription(category.getDescription());
            return categoryRepository.save(updatedCategory);
        } else {
            throw new RuntimeException("Categoría no encontrada con ID: " + id);
        }
    }
}
