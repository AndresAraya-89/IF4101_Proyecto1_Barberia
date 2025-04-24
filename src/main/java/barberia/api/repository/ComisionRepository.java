package barberia.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import barberia.api.entity.Comicion;

public interface ComisionRepository extends JpaRepository<Comicion, Integer> {

}