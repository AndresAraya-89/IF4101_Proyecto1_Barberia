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

    public int getIdCita() {
        return idCita;
    }

    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }

    public Usuario getUsuarioCliente() {
        return usuarioCliente;
    }

    public void setUsuarioCliente(Usuario usuarioCliente) {
        this.usuarioCliente = usuarioCliente;
    }

    public Usuario getUsuarioBarbero() {
        return usuarioBarbero;
    }

    public void setUsuarioBarbero(Usuario usuarioBarbero) {
        this.usuarioBarbero = usuarioBarbero;
    }

    public Corte getCorte() {
        return corte;
    }

    public void setCorte(Corte corte) {
        this.corte = corte;
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
}
