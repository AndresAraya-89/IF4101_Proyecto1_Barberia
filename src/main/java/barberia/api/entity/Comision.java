package barberia.api.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "comision")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comision {
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

    public int getIdComision() {
        return idComision;
    }

    public void setIdComision(int idComision) {
        this.idComision = idComision;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getFechaHoraDeposito() {
        return fechaHoraDeposito;
    }

    public void setFechaHoraDeposito(String fechaHoraDeposito) {
        this.fechaHoraDeposito = fechaHoraDeposito;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
}
