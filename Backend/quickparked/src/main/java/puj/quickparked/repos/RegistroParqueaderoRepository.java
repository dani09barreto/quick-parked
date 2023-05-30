package puj.quickparked.repos;

import org.springframework.data.jpa.repository.Query;
import puj.quickparked.domain.RegistroParqueadero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RegistroParqueaderoRepository extends JpaRepository<RegistroParqueadero, Integer> {

    @Query(value = "SELECT " +
            "FLOOR(1 + RAND() * (SELECT Cupo FROM SedeParqueadero WHERE Id = :sedeParqueadero))  AS Slot " +
            "FROM " +
            "RegistroParqueadero " +
            "WHERE " +
            "Slot NOT IN (SELECT COALESCE(Slot, 0) FROM RegistroParqueadero WHERE EstadoRegistroParqueaderoId = (SELECT Id FROM EstadoRegistroParqueadero WHERE Estado = 'Estacionado')) " +
            "LIMIT 1", nativeQuery = true)
    Integer getSlotDisponible(Integer sedeParqueadero);

    @Query("SELECT r FROM RegistroParqueadero r WHERE r.vehiculo.placa = :placa " +
            "AND r.estadoRegistroParqueadero.id = (SELECT er.id FROM EstadoRegistroParqueadero er WHERE er.estado = 'Estacionado')")
    RegistroParqueadero findByPlacaAndEstacionado(String placa);

    @Query("SELECT r FROM RegistroParqueadero r WHERE r.vehiculo.placa = :placa " +
            "AND r.estadoRegistroParqueadero.id = (SELECT er.id FROM EstadoRegistroParqueadero er WHERE er.estado = 'Reservado')")
    RegistroParqueadero findByPlacaAndReservado(String placa);
}
