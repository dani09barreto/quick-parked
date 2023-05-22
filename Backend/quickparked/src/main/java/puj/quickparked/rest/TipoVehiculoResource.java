package puj.quickparked.rest;

import puj.quickparked.model.TipoVehiculoDTO;
import puj.quickparked.service.TipoVehiculoService;
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
@RequestMapping(value = "/tipoVehiculos", produces = MediaType.APPLICATION_JSON_VALUE)
public class TipoVehiculoResource {

    private final TipoVehiculoService tipoVehiculoService;

    public TipoVehiculoResource(final TipoVehiculoService tipoVehiculoService) {
        this.tipoVehiculoService = tipoVehiculoService;
    }

    @GetMapping
    public ResponseEntity<List<TipoVehiculoDTO>> getAllTipoVehiculos() {
        return ResponseEntity.ok(tipoVehiculoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoVehiculoDTO> getTipoVehiculo(@PathVariable final Integer id) {
        return ResponseEntity.ok(tipoVehiculoService.get(id));
    }

    @PostMapping
    public ResponseEntity<Integer> createTipoVehiculo(
            @RequestBody @Valid final TipoVehiculoDTO tipoVehiculoDTO) {
        final Integer createdId = tipoVehiculoService.create(tipoVehiculoDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateTipoVehiculo(@PathVariable final Integer id,
            @RequestBody @Valid final TipoVehiculoDTO tipoVehiculoDTO) {
        tipoVehiculoService.update(id, tipoVehiculoDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTipoVehiculo(@PathVariable final Integer id) {
        tipoVehiculoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
