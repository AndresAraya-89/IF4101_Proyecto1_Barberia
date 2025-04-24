package soda.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import soda.api.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{
    
}
