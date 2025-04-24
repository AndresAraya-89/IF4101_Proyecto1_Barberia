package barberia.api.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "comision")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comicion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idComision;

    @ManyToOne()
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    @Column(name = "fechaHoraDeposito", nullable = false)
    private String fechaHoraDeposito;

    @Column(name = "monto", nullable = false)
    private double monto;

    @Column(name = "estado", nullable = false)
    private int estado;

}
