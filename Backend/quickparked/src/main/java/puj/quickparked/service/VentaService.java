package puj.quickparked.service;

import puj.quickparked.domain.RegistroParqueadero;
import puj.quickparked.domain.Venta;
import puj.quickparked.model.VentaDTO;
import puj.quickparked.repos.RegistroParqueaderoRepository;
import puj.quickparked.repos.VentaRepository;
import puj.quickparked.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class VentaService {

    private final VentaRepository ventaRepository;
    private final RegistroParqueaderoRepository registroParqueaderoRepository;

    public VentaService(final VentaRepository ventaRepository,
            final RegistroParqueaderoRepository registroParqueaderoRepository) {
        this.ventaRepository = ventaRepository;
        this.registroParqueaderoRepository = registroParqueaderoRepository;
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

}
