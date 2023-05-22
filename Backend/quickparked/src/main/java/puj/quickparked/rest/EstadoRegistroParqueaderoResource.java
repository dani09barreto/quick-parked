package puj.quickparked.rest;

import puj.quickparked.model.EstadoRegistroParqueaderoDTO;
import puj.quickparked.service.EstadoRegistroParqueaderoService;
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
@RequestMapping(value = "/estadoRegistroParqueaderos", produces = MediaType.APPLICATION_JSON_VALUE)
public class EstadoRegistroParqueaderoResource {

    private final EstadoRegistroParqueaderoService estadoRegistroParqueaderoService;

    public EstadoRegistroParqueaderoResource(
            final EstadoRegistroParqueaderoService estadoRegistroParqueaderoService) {
        this.estadoRegistroParqueaderoService = estadoRegistroParqueaderoService;
    }

    @GetMapping
    public ResponseEntity<List<EstadoRegistroParqueaderoDTO>> getAllEstadoRegistroParqueaderos() {
        return ResponseEntity.ok(estadoRegistroParqueaderoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstadoRegistroParqueaderoDTO> getEstadoRegistroParqueadero(
            @PathVariable final Integer id) {
        return ResponseEntity.ok(estadoRegistroParqueaderoService.get(id));
    }

    @PostMapping
    public ResponseEntity<Integer> createEstadoRegistroParqueadero(
            @RequestBody @Valid final EstadoRegistroParqueaderoDTO estadoRegistroParqueaderoDTO) {
        final Integer createdId = estadoRegistroParqueaderoService.create(estadoRegistroParqueaderoDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateEstadoRegistroParqueadero(@PathVariable final Integer id,
            @RequestBody @Valid final EstadoRegistroParqueaderoDTO estadoRegistroParqueaderoDTO) {
        estadoRegistroParqueaderoService.update(id, estadoRegistroParqueaderoDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEstadoRegistroParqueadero(@PathVariable final Integer id) {
        estadoRegistroParqueaderoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
