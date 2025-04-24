package barberia.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import barberia.api.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

}