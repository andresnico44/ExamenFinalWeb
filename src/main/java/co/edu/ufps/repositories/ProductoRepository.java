package co.edu.ufps.repositories;

import co.edu.ufps.entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    Producto findByNombre(String nombre); // Método para encontrar producto por nombre
}
