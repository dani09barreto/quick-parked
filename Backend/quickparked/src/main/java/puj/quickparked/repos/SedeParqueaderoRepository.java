package puj.quickparked.repos;

import puj.quickparked.domain.SedeParqueadero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SedeParqueaderoRepository extends JpaRepository<SedeParqueadero, Integer> {
}
