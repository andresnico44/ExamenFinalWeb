package co.edu.ufps.dto;
import lombok.Data;
import java.util.List;

@Data
public class FacturaRequestDTO {
    private Integer clienteId;
    private Integer vendedorId;
    private Integer cajeroId;
    private Double impuestos;
    private String observaciones;
    private List<DetalleDTO> detalles;
    private List<PagoDTO> pagos;

    @Data
    public static class DetalleDTO {
        private Integer productoId;
        private Integer cantidad;
        private Double precio;
        private Double descuento;
    }

    @Data
    public static class PagoDTO {
        private Integer tipoPagoId;
        private String tarjetaTipo;
        private Integer cuotas;
        private Double valor;
    }
}
