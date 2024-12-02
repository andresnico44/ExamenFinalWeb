package co.edu.ufps.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;
    private String descripcion;
    private Double precio;
    private Integer cantidad;

    @ManyToOne
    @JoinColumn(name = "tipo_producto_id")
    private Tipo_Producto tipoProducto;

}

