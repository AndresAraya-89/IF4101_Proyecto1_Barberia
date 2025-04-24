package barberia.api.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Factura")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Factura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idFactura;

    @ManyToOne()
    @JoinColumn(name = "idCita")
    private Cita idCita;

    @Column(name = "fechaHora", nullable = false)
    private String fechaHora;

    @Column(name = "metodoPago", nullable = false)
    private String metodoPago;

    @Column(name = "total", nullable = false)
    private double total;

    @Column(name = "estado", nullable = false)
    private int estado;

}
