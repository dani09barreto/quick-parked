package puj.quickparked.rest;

import puj.quickparked.model.ParqueaderoDTO;
import puj.quickparked.service.ParqueaderoService;
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
@RequestMapping(value = "/api/parqueaderos", produces = MediaType.APPLICATION_JSON_VALUE)
public class ParqueaderoResource {

    private final ParqueaderoService parqueaderoService;

    public ParqueaderoResource(final ParqueaderoService parqueaderoService) {
        this.parqueaderoService = parqueaderoService;
    }

    @GetMapping
    public ResponseEntity<List<ParqueaderoDTO>> getAllParqueaderos() {
        return ResponseEntity.ok(parqueaderoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParqueaderoDTO> getParqueadero(@PathVariable final Integer id) {
        return ResponseEntity.ok(parqueaderoService.get(id));
    }

    @PostMapping
    public ResponseEntity<Integer> createParqueadero(
            @RequestBody @Valid final ParqueaderoDTO parqueaderoDTO) {
        final Integer createdId = parqueaderoService.create(parqueaderoDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateParqueadero(@PathVariable final Integer id,
            @RequestBody @Valid final ParqueaderoDTO parqueaderoDTO) {
        parqueaderoService.update(id, parqueaderoDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParqueadero(@PathVariable final Integer id) {
        parqueaderoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
