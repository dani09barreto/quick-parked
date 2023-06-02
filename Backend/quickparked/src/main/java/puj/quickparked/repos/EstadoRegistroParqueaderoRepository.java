package puj.quickparked.repos;

import puj.quickparked.domain.EstadoRegistroParqueadero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EstadoRegistroParqueaderoRepository extends JpaRepository<EstadoRegistroParqueadero, Integer> {
    EstadoRegistroParqueadero findByEstado(String estado);
}
