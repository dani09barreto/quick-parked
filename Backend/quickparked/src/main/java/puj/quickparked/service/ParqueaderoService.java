package puj.quickparked.service;

import puj.quickparked.domain.Parqueadero;
import puj.quickparked.model.ParqueaderoDTO;
import puj.quickparked.repos.ParqueaderoRepository;
import puj.quickparked.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class ParqueaderoService {

    private final ParqueaderoRepository parqueaderoRepository;

    public ParqueaderoService(final ParqueaderoRepository parqueaderoRepository) {
        this.parqueaderoRepository = parqueaderoRepository;
    }

    public List<ParqueaderoDTO> findAll() {
        final List<Parqueadero> parqueaderos = parqueaderoRepository.findAll(Sort.by("id"));
        return parqueaderos.stream()
                .map((parqueadero) -> mapToDTO(parqueadero, new ParqueaderoDTO()))
                .toList();
    }

    public ParqueaderoDTO get(final Integer id) {
        return parqueaderoRepository.findById(id)
                .map(parqueadero -> mapToDTO(parqueadero, new ParqueaderoDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final ParqueaderoDTO parqueaderoDTO) {
        final Parqueadero parqueadero = new Parqueadero();
        mapToEntity(parqueaderoDTO, parqueadero);
        return parqueaderoRepository.save(parqueadero).getId();
    }

    public void update(final Integer id, final ParqueaderoDTO parqueaderoDTO) {
        final Parqueadero parqueadero = parqueaderoRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(parqueaderoDTO, parqueadero);
        parqueaderoRepository.save(parqueadero);
    }

    public void delete(final Integer id) {
        parqueaderoRepository.deleteById(id);
    }

    private ParqueaderoDTO mapToDTO(final Parqueadero parqueadero,
            final ParqueaderoDTO parqueaderoDTO) {
        parqueaderoDTO.setId(parqueadero.getId());
        parqueaderoDTO.setNombre(parqueadero.getNombre());
        parqueaderoDTO.setNit(parqueadero.getNit());
        return parqueaderoDTO;
    }

    private Parqueadero mapToEntity(final ParqueaderoDTO parqueaderoDTO,
            final Parqueadero parqueadero) {
        parqueadero.setNombre(parqueaderoDTO.getNombre());
        parqueadero.setNit(parqueaderoDTO.getNit());
        return parqueadero;
    }

}
