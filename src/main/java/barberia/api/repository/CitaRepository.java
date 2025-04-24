package barberia.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import barberia.api.entity.Cita;

public interface CitaRepository extends JpaRepository<Cita, Integer> {

}