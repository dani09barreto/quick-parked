package puj.quickparked.repos;

import puj.quickparked.domain.Venta;
import org.springframework.data.jpa.repository.JpaRepository;


public interface VentaRepository extends JpaRepository<Venta, Integer> {
}
