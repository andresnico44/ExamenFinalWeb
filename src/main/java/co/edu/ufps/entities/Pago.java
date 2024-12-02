package co.edu.ufps.entities;
import java.util.Optional;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "compra_id")
    private Compra compra;

    @ManyToOne
    @JoinColumn(name = "tipo_pago_id")
    private TipoPago tipoPago;

    private String tarjetaTipo;
    private Integer cuotas;
    private Double valor;

}
