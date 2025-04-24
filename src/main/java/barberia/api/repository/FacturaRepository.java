package barberia.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import barberia.api.entity.Factura;

public interface FacturaRepository extends JpaRepository<Factura, Integer> {

}
