package soda.api.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import soda.api.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{
    
}
