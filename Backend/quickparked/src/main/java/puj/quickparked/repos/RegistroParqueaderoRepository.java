package puj.quickparked.repos;

import puj.quickparked.domain.RegistroParqueadero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RegistroParqueaderoRepository extends JpaRepository<RegistroParqueadero, Integer> {
}
