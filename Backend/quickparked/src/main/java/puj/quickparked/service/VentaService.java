package puj.quickparked.service;

import puj.quickparked.domain.*;
import puj.quickparked.model.RespuestaCobroDTO;
import puj.quickparked.model.VentaDTO;
import puj.quickparked.repos.*;
import puj.quickparked.util.NotFoundException;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class VentaService {
    private final VentaRepository ventaRepository;
    private final RegistroParqueaderoRepository registroParqueaderoRepository;
    private final VehiculoRepository vehiculoRepository;
    private final EstadoRegistroParqueaderoRepository estadoRegistroParqueaderoRepository;
    private final SedeParqueaderoRepository sedeParqueaderoRepository;

    public VentaService(final VentaRepository ventaRepository,
                        final RegistroParqueaderoRepository registroParqueaderoRepository, VehiculoRepository vehiculoRepository, EstadoRegistroParqueaderoRepository estadoRegistroParqueaderoRepository, SedeParqueaderoRepository sedeParqueaderoRepository) {
        this.ventaRepository = ventaRepository;
        this.registroParqueaderoRepository = registroParqueaderoRepository;
        this.vehiculoRepository = vehiculoRepository;
        this.estadoRegistroParqueaderoRepository = estadoRegistroParqueaderoRepository;
        this.sedeParqueaderoRepository = sedeParqueaderoRepository;
    }

    public List<VentaDTO> findAll() {
        final List<Venta> ventas = ventaRepository.findAll(Sort.by("id"));
        return ventas.stream()
                .map((venta) -> mapToDTO(venta, new VentaDTO()))
                .toList();
    }

    public VentaDTO get(final Integer id) {
        return ventaRepository.findById(id)
                .map(venta -> mapToDTO(venta, new VentaDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public List <VentaDTO> getVentasSedeParqueadero(final Integer sedeParqueaderoId) {
        List <Venta> ventas = ventaRepository.findBySedeParqueaderoId(sedeParqueaderoId);
        List <VentaDTO> ventasDto = new ArrayList<>();
        for (Venta venta : ventas) {
            VentaDTO ventaDTO = new VentaDTO();
            mapToDTO(venta, ventaDTO);
            ventasDto.add(ventaDTO);
        }
        return ventasDto;
    }

    public Integer create(final VentaDTO ventaDTO) {
        final Venta venta = new Venta();
        mapToEntity(ventaDTO, venta);
        return ventaRepository.save(venta).getId();
    }

    public void update(final Integer id, final VentaDTO ventaDTO) {
        final Venta venta = ventaRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(ventaDTO, venta);
        ventaRepository.save(venta);
    }

    public void delete(final Integer id) {
        ventaRepository.deleteById(id);
    }

    private VentaDTO mapToDTO(final Venta venta, final VentaDTO ventaDTO) {
        ventaDTO.setId(venta.getId());
        ventaDTO.setFechaPago(venta.getFechaPago());
        ventaDTO.setIva(venta.getIva());
        ventaDTO.setMonto(venta.getMonto());
        ventaDTO.setReserva(venta.getReserva() == null ? null : venta.getReserva().getId());
        return ventaDTO;
    }

    private Venta mapToEntity(final VentaDTO ventaDTO, final Venta venta) {
        venta.setFechaPago(ventaDTO.getFechaPago());
        venta.setIva(ventaDTO.getIva());
        venta.setMonto(ventaDTO.getMonto());
        final RegistroParqueadero reserva = ventaDTO.getReserva() == null ? null : registroParqueaderoRepository.findById(ventaDTO.getReserva())
                .orElseThrow(() -> new NotFoundException("reserva not found"));
        venta.setReserva(reserva);
        return venta;
    }

    public RespuestaCobroDTO cobrar(final String placa) {
        final Vehiculo vehiculo = vehiculoRepository.findByPlaca(placa);
        if (vehiculo != null) {
            RegistroParqueadero registroParqueadero = registroParqueaderoRepository.findByPlacaAndEstacionado(placa);
            if (registroParqueadero != null) {
                registroParqueadero.setHoraSalida(LocalDateTime.now());

                // Calculando el precio a 95 pesos el minuto.

                Long duracionMinutos = ChronoUnit.MINUTES.between(registroParqueadero.getHoraEntrada(), registroParqueadero.getHoraSalida());
                Double precio = duracionMinutos * registroParqueadero.getSedeParqueadero().getTarifa();

                if (registroParqueadero.getHoraReserva() != null) {
                    registroParqueadero.setMontoReserva(precio * 0.03d);
                }

                Double IVA = precio * 0.19d;

                Venta venta = ventaRepository.findByReservaId(registroParqueadero.getId());
                if (venta == null) venta = new Venta();

                venta.setMonto(precio);
                venta.setIva(IVA);
                venta.setReserva(registroParqueadero);

                ventaRepository.save(venta);
                registroParqueaderoRepository.save(registroParqueadero);

                RespuestaCobroDTO respuestaCobroDTO = new RespuestaCobroDTO();
                respuestaCobroDTO.setValor(precio);
                respuestaCobroDTO.setPlaca(vehiculo.getPlaca());
                respuestaCobroDTO.setHoraIngreso(registroParqueadero.getHoraEntrada());
                respuestaCobroDTO.setTarifa(registroParqueadero.getVehiculo().getTipoVehiculo().getTipo().equals("Moto") ?
                          registroParqueadero.getSedeParqueadero().getTarifaMoto()
                        : registroParqueadero.getSedeParqueadero().getTarifa());
                respuestaCobroDTO.setTipoVehiculo(vehiculo.getTipoVehiculo().getTipo());
                respuestaCobroDTO.setMontoReserva(registroParqueadero.getMontoReserva() != null ? registroParqueadero.getMontoReserva() : 0d);

                return respuestaCobroDTO;

            } else {
                throw new RuntimeException("El vehiculo con placa " + placa + " no se encuentra estacionado en ninguna sede.");
            }
        } else {
            throw new RuntimeException("El vehiculo con placa " + placa + " no fue encontrado.");
        }
    }

    public String confirmarVenta(String placa, Double monto) {
        Vehiculo vehiculo = vehiculoRepository.findByPlaca(placa);
        if (vehiculo != null) {
            RegistroParqueadero registroParqueadero = registroParqueaderoRepository.findByPlacaAndEstacionado(placa);
            if (registroParqueadero != null) {
                Venta venta = ventaRepository.findByReservaId(registroParqueadero.getId());
                if (venta != null) {
                    Double vueltas = monto - venta.getMonto();
                    if (vueltas >= 0) {
                        EstadoRegistroParqueadero estadoRegistroParqueadero = estadoRegistroParqueaderoRepository.findByEstado("Pagado");
                        registroParqueadero.setEstadoRegistroParqueadero(estadoRegistroParqueadero);
                        estadoRegistroParqueaderoRepository.save(estadoRegistroParqueadero);
                        SedeParqueadero sedeParqueadero = registroParqueadero.getSedeParqueadero();
                        sedeParqueadero.setCupoOcupado(sedeParqueadero.getCupoOcupado() - 1);
                        sedeParqueaderoRepository.save(sedeParqueadero);
                        venta.setFechaPago(LocalDateTime.now());
                        ventaRepository.save(venta);
                        return vueltas.toString();
                    } else {
                        return "El valor ingresado no es suficiente para pagar. Pago requerido: $" + venta.getMonto().toString();
                    }
                } else {
                    throw new RuntimeException("No se encontró una venta registrada para la placa " + placa);
                }
            } else {
                throw new RuntimeException("El vehiculo con placa " + placa + " no se encuentra estacionado en ninguna sede.");
            }
        } else {
            throw new RuntimeException("El vehiculo con placa " + placa + " no fue encontrado.");
        }
    }
}
