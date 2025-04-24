package barberia.api.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "corte")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Corte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCorte;

    @ManyToOne()
    @JoinColumn(name = "idRepositorio")
    private Repositorio repositorio;

    @Column(name = "detalle", nullable = false)
    private String detalle;

    @Column(name = "url_imagen", nullable = false)
    private String url_imagen;

    @Column(name = "costo", nullable = false)
    private double costo;

    @Column(name = "estado", nullable = false)
    private int estado;

}
