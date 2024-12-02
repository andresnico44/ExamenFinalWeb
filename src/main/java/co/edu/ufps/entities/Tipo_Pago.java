package co.edu.ufps.entities;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Tipo_Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;

}
