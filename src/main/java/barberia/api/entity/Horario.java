package barberia.api.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "horario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Horario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idHorarario;

    @ManyToOne()
    @JoinColumn(name = "idUsuario") //Usuario de tipo barbero
    private Usuario usuario;

    @Column(name = "horaInicio", nullable = false)
    private String horaInicio;

    @Column(name = "horaFinal", nullable = false)
    private String horaFinal;

    @Column(name = "fecha", nullable = false) //Anteriormente estaba dia pero no es lo suficiente mente significativo
    private String fecha;   //Fecha ej: 23/05/2025 

    @Column(name = "estado", nullable = false)
    private int estado;
}