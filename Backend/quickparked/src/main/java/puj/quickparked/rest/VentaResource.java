package puj.quickparked.rest;

import puj.quickparked.model.IngresoVehiculoDTO;
import puj.quickparked.model.RespuestaCobroDTO;
import puj.quickparked.model.VentaDTO;
import puj.quickparked.service.VentaService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/ventas", produces = MediaType.APPLICATION_JSON_VALUE)
public class VentaResource {

    private final VentaService ventaService;

    public VentaResource(final VentaService ventaService) {
        this.ventaService = ventaService;
    }

    @GetMapping
    public ResponseEntity<List<VentaDTO>> getAllVentas() {
        return ResponseEntity.ok(ventaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VentaDTO> getVenta(@PathVariable final Integer id) {
        return ResponseEntity.ok(ventaService.get(id));
    }

    @PostMapping
    public ResponseEntity<Integer> createVenta(@RequestBody @Valid final VentaDTO ventaDTO) {
        final Integer createdId = ventaService.create(ventaDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateVenta(@PathVariable final Integer id,
            @RequestBody @Valid final VentaDTO ventaDTO) {
        ventaService.update(id, ventaDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVenta(@PathVariable final Integer id) {
        ventaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/cobrar/{placa}")
    public ResponseEntity<?> cobrar(@PathVariable ("placa") String placa) {
        try {
            final RespuestaCobroDTO respuesta = ventaService.cobrar(placa);
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        } catch (Exception errorMessage) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage.getMessage());
        }
    }

    @GetMapping("/confirmarVenta/{placa}/{monto}")
    public ResponseEntity<String> confirmarVenta(@PathVariable ("placa") String placa, @PathVariable Double monto) {
        try {
            final String vueltas = ventaService.confirmarVenta(placa, monto);
            return new ResponseEntity<>(vueltas.toString(), HttpStatus.OK);
        } catch (Exception errorMessage) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage.getMessage());
        }
    }

}
