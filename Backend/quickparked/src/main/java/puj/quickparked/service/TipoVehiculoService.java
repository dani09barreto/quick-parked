package puj.quickparked.service;

import puj.quickparked.domain.TipoVehiculo;
import puj.quickparked.model.TipoVehiculoDTO;
import puj.quickparked.repos.TipoVehiculoRepository;
import puj.quickparked.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class TipoVehiculoService {

    private final TipoVehiculoRepository tipoVehiculoRepository;

    public TipoVehiculoService(final TipoVehiculoRepository tipoVehiculoRepository) {
        this.tipoVehiculoRepository = tipoVehiculoRepository;
    }

    public List<TipoVehiculoDTO> findAll() {
        final List<TipoVehiculo> tipoVehiculos = tipoVehiculoRepository.findAll(Sort.by("id"));
        return tipoVehiculos.stream()
                .map((tipoVehiculo) -> mapToDTO(tipoVehiculo, new TipoVehiculoDTO()))
                .toList();
    }

    public TipoVehiculoDTO get(final Integer id) {
        return tipoVehiculoRepository.findById(id)
                .map(tipoVehiculo -> mapToDTO(tipoVehiculo, new TipoVehiculoDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final TipoVehiculoDTO tipoVehiculoDTO) {
        final TipoVehiculo tipoVehiculo = new TipoVehiculo();
        mapToEntity(tipoVehiculoDTO, tipoVehiculo);
        return tipoVehiculoRepository.save(tipoVehiculo).getId();
    }

    public void update(final Integer id, final TipoVehiculoDTO tipoVehiculoDTO) {
        final TipoVehiculo tipoVehiculo = tipoVehiculoRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(tipoVehiculoDTO, tipoVehiculo);
        tipoVehiculoRepository.save(tipoVehiculo);
    }

    public void delete(final Integer id) {
        tipoVehiculoRepository.deleteById(id);
    }

    private TipoVehiculoDTO mapToDTO(final TipoVehiculo tipoVehiculo,
            final TipoVehiculoDTO tipoVehiculoDTO) {
        tipoVehiculoDTO.setId(tipoVehiculo.getId());
        tipoVehiculoDTO.setTipo(tipoVehiculo.getTipo());
        return tipoVehiculoDTO;
    }

    private TipoVehiculo mapToEntity(final TipoVehiculoDTO tipoVehiculoDTO,
            final TipoVehiculo tipoVehiculo) {
        tipoVehiculo.setTipo(tipoVehiculoDTO.getTipo());
        return tipoVehiculo;
    }

}
