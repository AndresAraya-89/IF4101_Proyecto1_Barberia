package barberia.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import barberia.api.entity.Horario;

public interface HorarioRepository extends JpaRepository<Horario, Integer> {

}
