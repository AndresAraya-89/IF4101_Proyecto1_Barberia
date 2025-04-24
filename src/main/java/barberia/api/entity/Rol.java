package barberia.api.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "rol")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Rol {
    
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int idRol;

    @Column (name = "nombre", nullable = false)
    private String nombre;

    @Column (name = "descripcion", nullable = false)
    private String descripcion;

    @Column (name = "estado", nullable = false)
    private int estado;

}

