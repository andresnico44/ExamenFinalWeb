package co.edu.ufps.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.ufps.dto.FacturaRequestDTO;
import co.edu.ufps.entities.Cajero;
import co.edu.ufps.entities.Cliente;
import co.edu.ufps.entities.Compra;
import co.edu.ufps.entities.DetallesCompra;
import co.edu.ufps.entities.Pago;
import co.edu.ufps.entities.Producto;
import co.edu.ufps.entities.Tienda;
import co.edu.ufps.entities.Vendedor;
import co.edu.ufps.repositories.CajeroRepository;
import co.edu.ufps.repositories.ClienteRepository;
import co.edu.ufps.repositories.CompraRepository;
import co.edu.ufps.repositories.DetallesCompraRepository;
import co.edu.ufps.repositories.PagoRepository;
import co.edu.ufps.repositories.ProductoRepository;
import co.edu.ufps.repositories.TiendaRepository;
import co.edu.ufps.repositories.TipoPagoRepository;
import co.edu.ufps.repositories.VendedorRepository;

@Service
public class FacturaService {

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private VendedorRepository vendedorRepository;

    @Autowired
    private CajeroRepository cajeroRepository;

    @Autowired
    private TiendaRepository tiendaRepository;

    @Autowired
    private DetallesCompraRepository detallesCompraRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private PagoRepository pagoRepository;
    
    @Autowired
    private TipoPagoRepository tipoPagoRepository;

    @Transactional
    public Compra procesarFactura(String tiendaUuid, FacturaRequestDTO facturaRequest) {
        // Validar existencia de la tienda
        Tienda tienda = tiendaRepository.findByUuid(tiendaUuid)
                .orElseThrow(() -> new RuntimeException("Tienda no encontrada"));

        // Validar existencia del cliente
        Cliente cliente = clienteRepository.findById(facturaRequest.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        // Validar existencia del vendedor
        Vendedor vendedor = vendedorRepository.findById(facturaRequest.getVendedorId())
                .orElseThrow(() -> new RuntimeException("Vendedor no encontrado"));

        // Validar existencia del cajero
        Cajero cajero = cajeroRepository.findById(facturaRequest.getCajeroId())
                .orElseThrow(() -> new RuntimeException("Cajero no encontrado"));

        // Crear la compra
        Compra compra = new Compra();
        compra.setCliente(cliente);
        compra.setTienda(tienda);
        compra.setVendedor(vendedor);
        compra.setCajero(cajero);
        compra.setImpuestos(facturaRequest.getImpuestos());
        compra.setTotal(0.0); // Lo calcularemos después
        compra.setObservaciones(facturaRequest.getObservaciones());
        compra.setFecha(java.time.LocalDate.now());
        compra = compraRepository.save(compra);

        // Procesar los detalles de compra
        double total = 0.0;
        for (FacturaRequestDTO.DetalleDTO detalleDTO : facturaRequest.getDetalles()) {
            Producto producto = productoRepository.findById(detalleDTO.getProductoId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            DetallesCompra detalle = new DetallesCompra();
            detalle.setCompra(compra);
            detalle.setProducto(producto);
            detalle.setCantidad(detalleDTO.getCantidad());
            detalle.setPrecio(detalleDTO.getPrecio());
            detalle.setDescuento(detalleDTO.getDescuento());
            total += detalleDTO.getCantidad() * detalleDTO.getPrecio() - detalleDTO.getDescuento();
            detallesCompraRepository.save(detalle);
        }

        // Procesar los pagos
        for (FacturaRequestDTO.PagoDTO pagoDTO : facturaRequest.getPagos()) {
            Pago pago = new Pago();
            pago.setCompra(compra);
            pago.setTipoPago(tipoPagoRepository.findById(pagoDTO.getTipoPagoId())
                    .orElseThrow(() -> new RuntimeException("Tipo de pago no encontrado")));
            pago.setTarjetaTipo(pagoDTO.getTarjetaTipo());
            pago.setCuotas(pagoDTO.getCuotas());
            pago.setValor(pagoDTO.getValor());
            pagoRepository.save(pago);
        }

        // Actualizar el total de la compra
        compra.setTotal(total + facturaRequest.getImpuestos());
        compraRepository.save(compra);

        return compra;
    }
}


