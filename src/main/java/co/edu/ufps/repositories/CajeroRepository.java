package co.edu.ufps.repositories;

import co.edu.ufps.entities.Cajero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CajeroRepository extends JpaRepository<Cajero, Integer> {
    Cajero findByToken(String token);
}
