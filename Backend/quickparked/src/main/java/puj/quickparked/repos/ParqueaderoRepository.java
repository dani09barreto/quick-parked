package puj.quickparked.repos;

import puj.quickparked.domain.Parqueadero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ParqueaderoRepository extends JpaRepository<Parqueadero, Integer> {
}
