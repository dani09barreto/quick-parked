package puj.quickparked.service;

import org.springframework.cglib.core.Local;
import puj.quickparked.domain.*;
import puj.quickparked.model.IngresoVehiculoDTO;
import puj.quickparked.model.RegistroParqueaderoDTO;
import puj.quickparked.model.ReservaVehiculoDTO;
import puj.quickparked.repos.*;
import puj.quickparked.util.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class RegistroParqueaderoService {

    private final RegistroParqueaderoRepository registroParqueaderoRepository;
    private final UsuarioRepository usuarioRepository;
    private final EstadoRegistroParqueaderoRepository estadoRegistroParqueaderoRepository;
    private final SedeParqueaderoRepository sedeParqueaderoRepository;
    private final VehiculoRepository vehiculoRepository;
    private final TipoVehiculoRepository tipoVehiculoRepository;

    public RegistroParqueaderoService(
            final RegistroParqueaderoRepository registroParqueaderoRepository,
            final UsuarioRepository usuarioRepository,
            final EstadoRegistroParqueaderoRepository estadoRegistroParqueaderoRepository,
            final SedeParqueaderoRepository sedeParqueaderoRepository,
            final VehiculoRepository vehiculoRepository, TipoVehiculoRepository tipoVehiculoRepository) {
        this.registroParqueaderoRepository = registroParqueaderoRepository;
        this.usuarioRepository = usuarioRepository;
        this.estadoRegistroParqueaderoRepository = estadoRegistroParqueaderoRepository;
        this.sedeParqueaderoRepository = sedeParqueaderoRepository;
        this.vehiculoRepository = vehiculoRepository;
        this.tipoVehiculoRepository = tipoVehiculoRepository;
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

    public Integer ingresarVehiculo(final IngresoVehiculoDTO ingresoVehiculoDTO) {
        RegistroParqueadero registroParqueadero = new RegistroParqueadero();

        Vehiculo vehiculo = vehiculoRepository.findByPlaca(ingresoVehiculoDTO.getPlaca());
        if (vehiculo != null) {
            registroParqueadero.setVehiculo(vehiculo);

            EstadoRegistroParqueadero estadoRegistroParqueadero = estadoRegistroParqueaderoRepository.findByEstado("Estacionado");
            registroParqueadero.setEstadoRegistroParqueadero(estadoRegistroParqueadero);
            Optional<Usuario> usuarioQuery = usuarioRepository.findById(ingresoVehiculoDTO.getUsuarioTrabajadorId());

            if (usuarioQuery.isPresent()) {
                Usuario usuarioTrabajador = usuarioQuery.get();
                registroParqueadero.setUsuarioTrabajador(usuarioTrabajador);
                registroParqueadero.setSedeParqueadero(usuarioTrabajador.getSedeParqueadero());

                RegistroParqueadero registroValidacion = registroParqueaderoRepository.findByPlacaAndEstacionado(ingresoVehiculoDTO.getPlaca());
                if (registroValidacion == null) {
                    Integer slot = registroParqueaderoRepository.getSlotDisponible(usuarioTrabajador.getSedeParqueadero().getId());

                    if (slot != null) {
                        registroParqueadero.setHoraEntrada(LocalDateTime.now());
                        registroParqueaderoRepository.save(registroParqueadero);
                        return slot;
                    } else {
                        throw new RuntimeException("No se encontró un slot disponible para el parqueadero.");
                    }
                } else {
                    throw new RuntimeException("El vehículo ya se encuentra estacionado en esa sede.");
                }
            } else {
                throw new RuntimeException("No se encontró un usuario con el ID " + ingresoVehiculoDTO.getUsuarioTrabajadorId());
            }
        } else {
            throw new RuntimeException("El vehiculo con placa " + ingresoVehiculoDTO.getPlaca() + " no fue encontrado. Debe crearlo primero.");
        }
    }

    public Integer crearReserva(ReservaVehiculoDTO reservaVehiculoDTO) {
        String placa = reservaVehiculoDTO.getPlaca();
        Integer sedeParqueaderoId = reservaVehiculoDTO.getSedeParqueaderoId();
        Vehiculo vehiculo = vehiculoRepository.findByPlaca(placa);
        if (vehiculo != null) {
            Optional<SedeParqueadero> sedeParqueaderoQuery = sedeParqueaderoRepository.findById(sedeParqueaderoId);
            if (sedeParqueaderoQuery.isPresent()) {
                SedeParqueadero sedeParqueadero = sedeParqueaderoQuery.get();
                RegistroParqueadero registroValidacion = registroParqueaderoRepository.findByPlacaAndReservado(placa);
                if (registroValidacion == null) {
                    RegistroParqueadero registroParqueadero = new RegistroParqueadero();
                    EstadoRegistroParqueadero estadoRegistroParqueadero = estadoRegistroParqueaderoRepository.findByEstado("Reservado");
                    registroParqueadero.setEstadoRegistroParqueadero(estadoRegistroParqueadero);
                    registroParqueadero.setVehiculo(vehiculo);
                    registroParqueadero.setHoraReserva(LocalDateTime.now());
                    registroParqueadero.setSedeParqueadero(sedeParqueadero);
                    Integer slot = registroParqueaderoRepository.getSlotDisponible(sedeParqueaderoId);
                    registroParqueadero.setSlot(slot.toString());
                    registroParqueaderoRepository.save(registroParqueadero);
                    if (slot != null) {
                        return slot;
                    } else {
                        throw new RuntimeException("No se encontró un slot disponible para el parqueadero " + sedeParqueadero.getNombreSede());
                    }
                } else {
                    throw new RuntimeException("El vehículo ya cuenta con una reserva o está estacionado en una sede..");
                }
            } else {
                throw new RuntimeException("No se encontró una sede parqueadero con el ID " + sedeParqueaderoId);
            }
        } else {
            throw new RuntimeException("No se encontró un vehículo con la placa " + placa);
        }
    }
}

