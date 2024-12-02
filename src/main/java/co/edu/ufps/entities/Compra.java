package co.edu.ufps.entities;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "tienda_id")
    private Tienda tienda;

    @ManyToOne
    @JoinColumn(name = "vendedor_id")
    private Vendedor vendedor;

    @ManyToOne
    @JoinColumn(name = "cajero_id")
    private Cajero cajero;

    private Double total;
    private Double impuestos;
    private LocalDate fecha;
    private String observaciones;

    @OneToMany(mappedBy = "compra", cascade = CascadeType.ALL)
    private List<Detalles_Compra> detallesCompra;

    @OneToMany(mappedBy = "compra", cascade = CascadeType.ALL)
    private List<Pago> pagos;

}
