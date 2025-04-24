package barberia.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import barberia.api.entity.Rol;

public interface RolRepository extends JpaRepository<Rol, Integer> {

}
