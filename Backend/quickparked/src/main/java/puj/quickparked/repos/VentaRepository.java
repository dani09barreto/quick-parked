package puj.quickparked.repos;

import puj.quickparked.domain.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface VentaRepository extends JpaRepository<Venta, Integer> {

    Venta findByReservaId(Integer reservaId);
}
