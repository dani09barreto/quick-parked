package puj.quickparked.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import puj.quickparked.domain.Parqueadero;


public interface ParqueaderoRepository extends JpaRepository<Parqueadero, Integer> {
}
