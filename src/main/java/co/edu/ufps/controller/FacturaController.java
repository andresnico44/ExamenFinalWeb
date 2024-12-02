package co.edu.ufps.controller;

import co.edu.ufps.dto.FacturaRequestDTO;
import co.edu.ufps.entities.Compra;
import co.edu.ufps.services.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/crear")
public class FacturaController {

    @Autowired
    private FacturaService facturaService;

    @PostMapping("/{uuidTienda}")
    public Compra crearFactura(@PathVariable String uuidTienda, @RequestBody FacturaRequestDTO facturaRequest) {
        return facturaService.procesarFactura(uuidTienda, facturaRequest);
    }
}

