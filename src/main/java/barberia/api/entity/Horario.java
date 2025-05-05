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

    @Column(name = "horaInicio", nullable = false) //12:00
    private String horaInicio;

    @Column(name = "horaFinal", nullable = false) //14:00
    private String horaFinal;

    @Column(name = "fecha", nullable = false) //Anteriormente estaba dia pero no es lo suficiente mente significativo
    private String fecha;   //Fecha ej: 23/05/2025 

    @Column(name = "estado", nullable = false)
    private int estado;

    public int getIdHorarario() {
        return idHorarario;
    }

    public void setIdHorarario(int idHorarario) {
        this.idHorarario = idHorarario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(String horaFinal) {
        this.horaFinal = horaFinal;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
}