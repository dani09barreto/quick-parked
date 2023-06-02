package puj.quickparked.repos;

import puj.quickparked.domain.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface VehiculoRepository extends JpaRepository<Vehiculo, Integer> {

    Vehiculo findByPlaca(String placa);
}
