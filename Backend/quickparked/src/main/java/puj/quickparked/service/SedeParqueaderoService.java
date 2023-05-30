package puj.quickparked.service;

import puj.quickparked.domain.Parqueadero;
import puj.quickparked.domain.SedeParqueadero;
import puj.quickparked.model.SedeParqueaderoDTO;
import puj.quickparked.repos.ParqueaderoRepository;
import puj.quickparked.repos.SedeParqueaderoRepository;
import puj.quickparked.util.NotFoundException;
import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class SedeParqueaderoService {

    private final SedeParqueaderoRepository sedeParqueaderoRepository;
    private final ParqueaderoRepository parqueaderoRepository;

    public SedeParqueaderoService(final SedeParqueaderoRepository sedeParqueaderoRepository,
            final ParqueaderoRepository parqueaderoRepository) {
        this.sedeParqueaderoRepository = sedeParqueaderoRepository;
        this.parqueaderoRepository = parqueaderoRepository;
    }

    public List<SedeParqueaderoDTO> findAll() {
        final List<SedeParqueadero> sedeParqueaderos = sedeParqueaderoRepository.findAll(Sort.by("id"));
        return sedeParqueaderos.stream()
                .map((sedeParqueadero) -> mapToDTO(sedeParqueadero, new SedeParqueaderoDTO()))
                .toList();
    }

    public SedeParqueaderoDTO get(final Integer id) {
        return sedeParqueaderoRepository.findById(id)
                .map(sedeParqueadero -> mapToDTO(sedeParqueadero, new SedeParqueaderoDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final SedeParqueaderoDTO sedeParqueaderoDTO) {
        final SedeParqueadero sedeParqueadero = new SedeParqueadero();
        mapToEntity(sedeParqueaderoDTO, sedeParqueadero);
        return sedeParqueaderoRepository.save(sedeParqueadero).getId();
    }

    public void update(final Integer id, final SedeParqueaderoDTO sedeParqueaderoDTO) {
        final SedeParqueadero sedeParqueadero = sedeParqueaderoRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(sedeParqueaderoDTO, sedeParqueadero);
        sedeParqueaderoRepository.save(sedeParqueadero);
    }

    public void delete(final Integer id) {
        sedeParqueaderoRepository.deleteById(id);
    }

    private SedeParqueaderoDTO mapToDTO(final SedeParqueadero sedeParqueadero,
            final SedeParqueaderoDTO sedeParqueaderoDTO) {
        sedeParqueaderoDTO.setId(sedeParqueadero.getId());
        sedeParqueaderoDTO.setNombreSede(sedeParqueadero.getNombreSede());
        sedeParqueaderoDTO.setLatitud(sedeParqueadero.getLatitud());
        sedeParqueaderoDTO.setLongitud(sedeParqueadero.getLongitud());
        sedeParqueaderoDTO.setCupo(sedeParqueadero.getCupo());
        sedeParqueaderoDTO.setImagen(sedeParqueadero.getImagen());
        sedeParqueaderoDTO.setParqueadero(sedeParqueadero.getParqueadero() == null ? null : sedeParqueadero.getParqueadero().getId());
        return sedeParqueaderoDTO;
    }

    private SedeParqueadero mapToEntity(final SedeParqueaderoDTO sedeParqueaderoDTO,
            final SedeParqueadero sedeParqueadero) {
        sedeParqueadero.setNombreSede(sedeParqueaderoDTO.getNombreSede());
        sedeParqueadero.setLatitud(sedeParqueaderoDTO.getLatitud());
        sedeParqueadero.setLongitud(sedeParqueaderoDTO.getLongitud());
        sedeParqueadero.setCupo(sedeParqueaderoDTO.getCupo());
        sedeParqueadero.setImagen(sedeParqueaderoDTO.getImagen());
        final Parqueadero parqueadero = sedeParqueaderoDTO.getParqueadero() == null ? null : parqueaderoRepository.findById(sedeParqueaderoDTO.getParqueadero())
                .orElseThrow(() -> new NotFoundException("parqueadero not found"));
        sedeParqueadero.setParqueadero(parqueadero);
        return sedeParqueadero;
    }

    public List<SedeParqueaderoDTO> getByPropietarioId(final Integer id) {
        final Set<SedeParqueadero> sedeParqueaderos = sedeParqueaderoRepository.findByPropietarioId(id);
        return sedeParqueaderos.stream()
                .map((sedeParqueadero) -> mapToDTO(sedeParqueadero, new SedeParqueaderoDTO()))
                .toList();
    }

}
