package puj.quickparked.repos;

import puj.quickparked.domain.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;


public interface VehiculoRepository extends JpaRepository<Vehiculo, Integer> {
}
