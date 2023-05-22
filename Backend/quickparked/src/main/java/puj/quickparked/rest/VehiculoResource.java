package puj.quickparked.rest;

import puj.quickparked.model.VehiculoDTO;
import puj.quickparked.service.VehiculoService;
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
@RequestMapping(value = "/vehiculos", produces = MediaType.APPLICATION_JSON_VALUE)
public class VehiculoResource {

    private final VehiculoService vehiculoService;

    public VehiculoResource(final VehiculoService vehiculoService) {
        this.vehiculoService = vehiculoService;
    }

    @GetMapping
    public ResponseEntity<List<VehiculoDTO>> getAllVehiculos() {
        return ResponseEntity.ok(vehiculoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehiculoDTO> getVehiculo(@PathVariable final Integer id) {
        return ResponseEntity.ok(vehiculoService.get(id));
    }

    @PostMapping
    public ResponseEntity<Integer> createVehiculo(
            @RequestBody @Valid final VehiculoDTO vehiculoDTO) {
        final Integer createdId = vehiculoService.create(vehiculoDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateVehiculo(@PathVariable final Integer id,
            @RequestBody @Valid final VehiculoDTO vehiculoDTO) {
        vehiculoService.update(id, vehiculoDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehiculo(@PathVariable final Integer id) {
        vehiculoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
