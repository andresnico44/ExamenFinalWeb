package co.edu.ufps.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.ufps.repositories.CompraRepository;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import co.edu.ufps.entities.Compra;
@RestController
@RequestMapping("/compras")
public class CompraController {
    @Autowired
    private CompraRepository compraRepository;

    @GetMapping
    public List<Compra> getAll() {
        return compraRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Compra> getById(@PathVariable Integer id) {
        return compraRepository.findById(id);
    }

    @PostMapping
    public Compra create(@RequestBody Compra compra) {
        return compraRepository.save(compra);
    }

    @PutMapping("/{id}")
    public Compra update(@PathVariable Integer id, @RequestBody Compra compra) {
        compra.setId(id);
        return compraRepository.save(compra);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        compraRepository.deleteById(id);
    }
}
