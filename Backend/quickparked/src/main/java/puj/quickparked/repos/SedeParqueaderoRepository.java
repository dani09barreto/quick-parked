package puj.quickparked.repos;

import org.springframework.data.jpa.repository.Query;
import puj.quickparked.domain.SedeParqueadero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;


@Repository
public interface SedeParqueaderoRepository extends JpaRepository<SedeParqueadero, Integer> {

    @Query("SELECT c.parqueaderoSedeParqueaderos FROM Parqueadero c WHERE c.usuarioPropietario.id = :id")
    Set<SedeParqueadero> findByPropietarioId (Integer id);
}
