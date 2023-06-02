package puj.quickparked.service;

import puj.quickparked.domain.TipoUsuario;
import puj.quickparked.model.TipoUsuarioDTO;
import puj.quickparked.repos.TipoUsuarioRepository;
import puj.quickparked.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class TipoUsuarioService {

    private final TipoUsuarioRepository tipoUsuarioRepository;

    public TipoUsuarioService(final TipoUsuarioRepository tipoUsuarioRepository) {
        this.tipoUsuarioRepository = tipoUsuarioRepository;
    }

    public List<TipoUsuarioDTO> findAll() {
        final List<TipoUsuario> tipoUsuarios = tipoUsuarioRepository.findAll(Sort.by("id"));
        return tipoUsuarios.stream()
                .map((tipoUsuario) -> mapToDTO(tipoUsuario, new TipoUsuarioDTO()))
                .toList();
    }

    public TipoUsuarioDTO get(final Integer id) {
        return tipoUsuarioRepository.findById(id)
                .map(tipoUsuario -> mapToDTO(tipoUsuario, new TipoUsuarioDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final TipoUsuarioDTO tipoUsuarioDTO) {
        final TipoUsuario tipoUsuario = new TipoUsuario();
        mapToEntity(tipoUsuarioDTO, tipoUsuario);
        return tipoUsuarioRepository.save(tipoUsuario).getId();
    }

    public void update(final Integer id, final TipoUsuarioDTO tipoUsuarioDTO) {
        final TipoUsuario tipoUsuario = tipoUsuarioRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(tipoUsuarioDTO, tipoUsuario);
        tipoUsuarioRepository.save(tipoUsuario);
    }

    public void delete(final Integer id) {
        tipoUsuarioRepository.deleteById(id);
    }

    private TipoUsuarioDTO mapToDTO(final TipoUsuario tipoUsuario,
            final TipoUsuarioDTO tipoUsuarioDTO) {
        tipoUsuarioDTO.setId(tipoUsuario.getId());
        tipoUsuarioDTO.setRol(tipoUsuario.getRol());
        return tipoUsuarioDTO;
    }

    private TipoUsuario mapToEntity(final TipoUsuarioDTO tipoUsuarioDTO,
            final TipoUsuario tipoUsuario) {
        tipoUsuario.setRol(tipoUsuarioDTO.getRol());
        return tipoUsuario;
    }

}
