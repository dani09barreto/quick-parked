package puj.quickparked.service;

import org.springframework.beans.factory.annotation.Autowired;
import puj.quickparked.domain.EstadoRegistroParqueadero;
import puj.quickparked.model.EstadoRegistroParqueaderoDTO;
import puj.quickparked.repos.EstadoRegistroParqueaderoRepository;
import puj.quickparked.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class EstadoRegistroParqueaderoService {

    private final EstadoRegistroParqueaderoRepository estadoRegistroParqueaderoRepository;

    public EstadoRegistroParqueaderoService(
            final EstadoRegistroParqueaderoRepository estadoRegistroParqueaderoRepository) {
        this.estadoRegistroParqueaderoRepository = estadoRegistroParqueaderoRepository;
    }

    public List<EstadoRegistroParqueaderoDTO> findAll() {
        final List<EstadoRegistroParqueadero> estadoRegistroParqueaderos = estadoRegistroParqueaderoRepository.findAll(Sort.by("id"));
        return estadoRegistroParqueaderos.stream()
                .map((estadoRegistroParqueadero) -> mapToDTO(estadoRegistroParqueadero, new EstadoRegistroParqueaderoDTO()))
                .toList();
    }

    public EstadoRegistroParqueaderoDTO get(final Integer id) {
        return estadoRegistroParqueaderoRepository.findById(id)
                .map(estadoRegistroParqueadero -> mapToDTO(estadoRegistroParqueadero, new EstadoRegistroParqueaderoDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final EstadoRegistroParqueaderoDTO estadoRegistroParqueaderoDTO) {
        final EstadoRegistroParqueadero estadoRegistroParqueadero = new EstadoRegistroParqueadero();
        mapToEntity(estadoRegistroParqueaderoDTO, estadoRegistroParqueadero);
        return estadoRegistroParqueaderoRepository.save(estadoRegistroParqueadero).getId();
    }

    public void update(final Integer id,
            final EstadoRegistroParqueaderoDTO estadoRegistroParqueaderoDTO) {
        final EstadoRegistroParqueadero estadoRegistroParqueadero = estadoRegistroParqueaderoRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(estadoRegistroParqueaderoDTO, estadoRegistroParqueadero);
        estadoRegistroParqueaderoRepository.save(estadoRegistroParqueadero);
    }

    public void delete(final Integer id) {
        estadoRegistroParqueaderoRepository.deleteById(id);
    }

    private EstadoRegistroParqueaderoDTO mapToDTO(
            final EstadoRegistroParqueadero estadoRegistroParqueadero,
            final EstadoRegistroParqueaderoDTO estadoRegistroParqueaderoDTO) {
        estadoRegistroParqueaderoDTO.setId(estadoRegistroParqueadero.getId());
        estadoRegistroParqueaderoDTO.setEstado(estadoRegistroParqueadero.getEstado());
        return estadoRegistroParqueaderoDTO;
    }

    private EstadoRegistroParqueadero mapToEntity(
            final EstadoRegistroParqueaderoDTO estadoRegistroParqueaderoDTO,
            final EstadoRegistroParqueadero estadoRegistroParqueadero) {
        estadoRegistroParqueadero.setEstado(estadoRegistroParqueaderoDTO.getEstado());
        return estadoRegistroParqueadero;
    }

}
