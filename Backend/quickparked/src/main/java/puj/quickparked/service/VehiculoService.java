package puj.quickparked.service;

import puj.quickparked.domain.TipoVehiculo;
import puj.quickparked.domain.Usuario;
import puj.quickparked.domain.Vehiculo;
import puj.quickparked.model.VehiculoDTO;
import puj.quickparked.repos.TipoVehiculoRepository;
import puj.quickparked.repos.UsuarioRepository;
import puj.quickparked.repos.VehiculoRepository;
import puj.quickparked.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class VehiculoService {

    private final VehiculoRepository vehiculoRepository;
    private final TipoVehiculoRepository tipoVehiculoRepository;
    private final UsuarioRepository usuarioRepository;

    public VehiculoService(final VehiculoRepository vehiculoRepository,
            final TipoVehiculoRepository tipoVehiculoRepository,
            final UsuarioRepository usuarioRepository) {
        this.vehiculoRepository = vehiculoRepository;
        this.tipoVehiculoRepository = tipoVehiculoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<VehiculoDTO> findAll() {
        final List<Vehiculo> vehiculos = vehiculoRepository.findAll(Sort.by("id"));
        return vehiculos.stream()
                .map((vehiculo) -> mapToDTO(vehiculo, new VehiculoDTO()))
                .toList();
    }

    public VehiculoDTO get(final Integer id) {
        return vehiculoRepository.findById(id)
                .map(vehiculo -> mapToDTO(vehiculo, new VehiculoDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final VehiculoDTO vehiculoDTO) {
        final Vehiculo vehiculo = new Vehiculo();
        mapToEntity(vehiculoDTO, vehiculo);
        return vehiculoRepository.save(vehiculo).getId();
    }

    public void update(final Integer id, final VehiculoDTO vehiculoDTO) {
        final Vehiculo vehiculo = vehiculoRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(vehiculoDTO, vehiculo);
        vehiculoRepository.save(vehiculo);
    }

    public void delete(final Integer id) {
        vehiculoRepository.deleteById(id);
    }

    private VehiculoDTO mapToDTO(final Vehiculo vehiculo, final VehiculoDTO vehiculoDTO) {
        vehiculoDTO.setId(vehiculo.getId());
        vehiculoDTO.setPlaca(vehiculo.getPlaca());
        vehiculoDTO.setTipoVehiculo(vehiculo.getTipoVehiculo() == null ? null : vehiculo.getTipoVehiculo().getId());
        vehiculoDTO.setUsuario(vehiculo.getUsuario() == null ? null : vehiculo.getUsuario().getId());
        return vehiculoDTO;
    }

    private Vehiculo mapToEntity(final VehiculoDTO vehiculoDTO, final Vehiculo vehiculo) {
        vehiculo.setPlaca(vehiculoDTO.getPlaca());
        final TipoVehiculo tipoVehiculo = vehiculoDTO.getTipoVehiculo() == null ? null : tipoVehiculoRepository.findById(vehiculoDTO.getTipoVehiculo())
                .orElseThrow(() -> new NotFoundException("tipoVehiculo not found"));
        vehiculo.setTipoVehiculo(tipoVehiculo);
        final Usuario usuario = vehiculoDTO.getUsuario() == null ? null : usuarioRepository.findById(vehiculoDTO.getUsuario())
                .orElseThrow(() -> new NotFoundException("usuario not found"));
        vehiculo.setUsuario(usuario);
        return vehiculo;
    }

}
