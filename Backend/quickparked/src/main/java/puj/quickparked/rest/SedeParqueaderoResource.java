package puj.quickparked.rest;

import puj.quickparked.model.SedeParqueaderoDTO;
import puj.quickparked.service.SedeParqueaderoService;
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
@RequestMapping(value = "/api/sedeParqueaderos", produces = MediaType.APPLICATION_JSON_VALUE)
public class SedeParqueaderoResource {

    private final SedeParqueaderoService sedeParqueaderoService;

    public SedeParqueaderoResource(final SedeParqueaderoService sedeParqueaderoService) {
        this.sedeParqueaderoService = sedeParqueaderoService;
    }

    @GetMapping
    public ResponseEntity<List<SedeParqueaderoDTO>> getAllSedeParqueaderos() {
        return ResponseEntity.ok(sedeParqueaderoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SedeParqueaderoDTO> getSedeParqueadero(@PathVariable final Integer id) {
        return ResponseEntity.ok(sedeParqueaderoService.get(id));
    }

    @PostMapping
    public ResponseEntity<Integer> createSedeParqueadero(
            @RequestBody @Valid final SedeParqueaderoDTO sedeParqueaderoDTO) {
        final Integer createdId = sedeParqueaderoService.create(sedeParqueaderoDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateSedeParqueadero(@PathVariable final Integer id,
            @RequestBody @Valid final SedeParqueaderoDTO sedeParqueaderoDTO) {
        sedeParqueaderoService.update(id, sedeParqueaderoDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSedeParqueadero(@PathVariable final Integer id) {
        sedeParqueaderoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
