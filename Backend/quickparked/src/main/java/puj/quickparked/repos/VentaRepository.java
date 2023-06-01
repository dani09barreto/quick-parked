package puj.quickparked.repos;

import org.springframework.data.jpa.repository.Query;
import puj.quickparked.domain.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface VentaRepository extends JpaRepository<Venta, Integer> {

    Venta findByReservaId(Integer reservaId);
    @Query("SELECT v FROM Venta v WHERE v.reserva.sedeParqueadero.id = :id AND v.fechaPago != null")
    List<Venta> findBySedeParqueaderoId(Integer id);
}
