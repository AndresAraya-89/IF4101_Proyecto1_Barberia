package barberia.api.repository;

import java.util.Locale.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CitaRepository extends JpaRepository<Category, Integer> {

}