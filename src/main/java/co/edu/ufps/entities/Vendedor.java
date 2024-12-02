package co.edu.ufps.entities;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Vendedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;
    private String documento;
    private String email;

}

