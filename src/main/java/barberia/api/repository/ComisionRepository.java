package barberia.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import barberia.api.entity.Comision;

public interface ComisionRepository extends JpaRepository<Comision, Integer> {

}