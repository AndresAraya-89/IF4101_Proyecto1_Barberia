package barberia.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import barberia.api.entity.Corte;

public interface CorteRepository extends JpaRepository<Corte, Integer> {

}
