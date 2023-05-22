package puj.quickparked.service;

import puj.quickparked.domain.EstadoRegistroParqueadero;
import puj.quickparked.domain.RegistroParqueadero;
import puj.quickparked.domain.SedeParqueadero;
import puj.quickparked.domain.Usuario;
import puj.quickparked.domain.Vehiculo;
import puj.quickparked.model.RegistroParqueaderoDTO;
import puj.quickparked.repos.EstadoRegistroParqueaderoRepository;
import puj.quickparked.repos.RegistroParqueaderoRepository;
import puj.quickparked.repos.SedeParqueaderoRepository;
import puj.quickparked.repos.UsuarioRepository;
import puj.quickparked.repos.VehiculoRepository;
import puj.quickparked.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class RegistroParqueaderoService {

    private final RegistroParqueaderoRepository registroParqueaderoRepository;
    private final UsuarioRepository usuarioRepository;
    private final EstadoRegistroParqueaderoRepository estadoRegistroParqueaderoRepository;
    private final SedeParqueaderoRepository sedeParqueaderoRepository;
    private final VehiculoRepository vehiculoRepository;

    public RegistroParqueaderoService(
            final RegistroParqueaderoRepository registroParqueaderoRepository,
            final UsuarioRepository usuarioRepository,
            final EstadoRegistroParqueaderoRepository estadoRegistroParqueaderoRepository,
            final SedeParqueaderoRepository sedeParqueaderoRepository,
            final VehiculoRepository vehiculoRepository) {
        this.registroParqueaderoRepository = registroParqueaderoRepository;
        this.usuarioRepository = usuarioRepository;
        this.estadoRegistroParqueaderoRepository = estadoRegistroParqueaderoRepository;
        this.sedeParqueaderoRepository = sedeParqueaderoRepository;
        this.vehiculoRepository = vehiculoRepository;
    }

    public List<RegistroParqueaderoDTO> findAll() {
        final List<RegistroParqueadero> registroParqueaderos = registroParqueaderoRepository.findAll(Sort.by("id"));
        return registroParqueaderos.stream()
                .map((registroParqueadero) -> mapToDTO(registroParqueadero, new RegistroParqueaderoDTO()))
                .toList();
    }

    public RegistroParqueaderoDTO get(final Integer id) {
        return registroParqueaderoRepository.findById(id)
                .map(registroParqueadero -> mapToDTO(registroParqueadero, new RegistroParqueaderoDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final RegistroParqueaderoDTO registroParqueaderoDTO) {
        final RegistroParqueadero registroParqueadero = new RegistroParqueadero();
        mapToEntity(registroParqueaderoDTO, registroParqueadero);
        return registroParqueaderoRepository.save(registroParqueadero).getId();
    }

    public void update(final Integer id, final RegistroParqueaderoDTO registroParqueaderoDTO) {
        final RegistroParqueadero registroParqueadero = registroParqueaderoRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(registroParqueaderoDTO, registroParqueadero);
        registroParqueaderoRepository.save(registroParqueadero);
    }

    public void delete(final Integer id) {
        registroParqueaderoRepository.deleteById(id);
    }

    private RegistroParqueaderoDTO mapToDTO(final RegistroParqueadero registroParqueadero,
            final RegistroParqueaderoDTO registroParqueaderoDTO) {
        registroParqueaderoDTO.setId(registroParqueadero.getId());
        registroParqueaderoDTO.setHoraEntrada(registroParqueadero.getHoraEntrada());
        registroParqueaderoDTO.setHoraSalida(registroParqueadero.getHoraSalida());
        registroParqueaderoDTO.setHoraReserva(registroParqueadero.getHoraReserva());
        registroParqueaderoDTO.setMontoReserva(registroParqueadero.getMontoReserva());
        registroParqueaderoDTO.setUsuarioTrabajador(registroParqueadero.getUsuarioTrabajador() == null ? null : registroParqueadero.getUsuarioTrabajador().getId());
        registroParqueaderoDTO.setEstadoRegistroParqueadero(registroParqueadero.getEstadoRegistroParqueadero() == null ? null : registroParqueadero.getEstadoRegistroParqueadero().getId());
        registroParqueaderoDTO.setSedeParqueadero(registroParqueadero.getSedeParqueadero() == null ? null : registroParqueadero.getSedeParqueadero().getId());
        registroParqueaderoDTO.setVehiculo(registroParqueadero.getVehiculo() == null ? null : registroParqueadero.getVehiculo().getId());
        return registroParqueaderoDTO;
    }

    private RegistroParqueadero mapToEntity(final RegistroParqueaderoDTO registroParqueaderoDTO,
            final RegistroParqueadero registroParqueadero) {
        registroParqueadero.setHoraEntrada(registroParqueaderoDTO.getHoraEntrada());
        registroParqueadero.setHoraSalida(registroParqueaderoDTO.getHoraSalida());
        registroParqueadero.setHoraReserva(registroParqueaderoDTO.getHoraReserva());
        registroParqueadero.setMontoReserva(registroParqueaderoDTO.getMontoReserva());
        final Usuario usuarioTrabajador = registroParqueaderoDTO.getUsuarioTrabajador() == null ? null : usuarioRepository.findById(registroParqueaderoDTO.getUsuarioTrabajador())
                .orElseThrow(() -> new NotFoundException("usuarioTrabajador not found"));
        registroParqueadero.setUsuarioTrabajador(usuarioTrabajador);
        final EstadoRegistroParqueadero estadoRegistroParqueadero = registroParqueaderoDTO.getEstadoRegistroParqueadero() == null ? null : estadoRegistroParqueaderoRepository.findById(registroParqueaderoDTO.getEstadoRegistroParqueadero())
                .orElseThrow(() -> new NotFoundException("estadoRegistroParqueadero not found"));
        registroParqueadero.setEstadoRegistroParqueadero(estadoRegistroParqueadero);
        final SedeParqueadero sedeParqueadero = registroParqueaderoDTO.getSedeParqueadero() == null ? null : sedeParqueaderoRepository.findById(registroParqueaderoDTO.getSedeParqueadero())
                .orElseThrow(() -> new NotFoundException("sedeParqueadero not found"));
        registroParqueadero.setSedeParqueadero(sedeParqueadero);
        final Vehiculo vehiculo = registroParqueaderoDTO.getVehiculo() == null ? null : vehiculoRepository.findById(registroParqueaderoDTO.getVehiculo())
                .orElseThrow(() -> new NotFoundException("vehiculo not found"));
        registroParqueadero.setVehiculo(vehiculo);
        return registroParqueadero;
    }

}
