package barberia.api.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cita")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCita;

    @ManyToOne()
    @JoinColumn(name = "idUsuarioCliente")
    private Usuario usuarioCliente; // Cliente

    @ManyToOne()
    @JoinColumn(name = "idUsuarioBarbero")
    private Usuario usuarioBarbero; // Barbero

    @ManyToOne()
    @JoinColumn(name = "idCorte")
    private Corte corte;

    @ManyToOne()
    @JoinColumn(name = "idHorario")
    private Horario horario;

    @Column(name = "estado", nullable = false)
    private int estado;
}
