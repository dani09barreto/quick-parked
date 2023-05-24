package puj.quickparked.service;

import puj.quickparked.domain.Parqueadero;
import puj.quickparked.domain.Usuario;
import puj.quickparked.model.ParqueaderoDTO;
import puj.quickparked.repos.ParqueaderoRepository;
import puj.quickparked.repos.UsuarioRepository;
import puj.quickparked.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class ParqueaderoService {

    private final ParqueaderoRepository parqueaderoRepository;
    private final UsuarioRepository usuarioRepository;

    public ParqueaderoService(final ParqueaderoRepository parqueaderoRepository,
            final UsuarioRepository usuarioRepository) {
        this.parqueaderoRepository = parqueaderoRepository;
        this.usuarioRepository = usuarioRepository;
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
        parqueaderoDTO.setUsuarioPropietario(parqueadero.getUsuarioPropietario() == null ? null : parqueadero.getUsuarioPropietario().getId());
        return parqueaderoDTO;
    }

    private Parqueadero mapToEntity(final ParqueaderoDTO parqueaderoDTO,
            final Parqueadero parqueadero) {
        parqueadero.setNombre(parqueaderoDTO.getNombre());
        parqueadero.setNit(parqueaderoDTO.getNit());
        final Usuario usuarioPropietario = parqueaderoDTO.getUsuarioPropietario() == null ? null : usuarioRepository.findById(parqueaderoDTO.getUsuarioPropietario())
                .orElseThrow(() -> new NotFoundException("usuarioPropietario not found"));
        parqueadero.setUsuarioPropietario(usuarioPropietario);
        return parqueadero;
    }

}
