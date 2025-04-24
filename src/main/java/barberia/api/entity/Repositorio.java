package barberia.api.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "repositorio")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Repositorio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRepositorio;

    @ManyToOne()
    @JoinColumn(name = "idUsuario") // Usuario de tipo barbero
    private Usuario usuario;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @Column(name = "estado", nullable = false)
    private int estado;

}
