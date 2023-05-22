package puj.quickparked.repos;

import puj.quickparked.domain.RegistroParqueadero;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RegistroParqueaderoRepository extends JpaRepository<RegistroParqueadero, Integer> {
}
