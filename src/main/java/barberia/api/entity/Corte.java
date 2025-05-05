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

    public int getIdCorte() {
        return idCorte;
    }

    public void setIdCorte(int idCorte) {
        this.idCorte = idCorte;
    }

    public Repositorio getRepositorio() {
        return repositorio;
    }

    public void setRepositorio(Repositorio repositorio) {
        this.repositorio = repositorio;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public String getUrl_imagen() {
        return url_imagen;
    }

    public void setUrl_imagen(String url_imagen) {
        this.url_imagen = url_imagen;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
}
