package puj.quickparked.rest;

import puj.quickparked.model.RegistroParqueaderoDTO;
import puj.quickparked.service.RegistroParqueaderoService;
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
@RequestMapping(value = "/registroParqueaderos", produces = MediaType.APPLICATION_JSON_VALUE)
public class RegistroParqueaderoResource {

    private final RegistroParqueaderoService registroParqueaderoService;

    public RegistroParqueaderoResource(
            final RegistroParqueaderoService registroParqueaderoService) {
        this.registroParqueaderoService = registroParqueaderoService;
    }

    @GetMapping
    public ResponseEntity<List<RegistroParqueaderoDTO>> getAllRegistroParqueaderos() {
        return ResponseEntity.ok(registroParqueaderoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegistroParqueaderoDTO> getRegistroParqueadero(
            @PathVariable final Integer id) {
        return ResponseEntity.ok(registroParqueaderoService.get(id));
    }

    @PostMapping
    public ResponseEntity<Integer> createRegistroParqueadero(
            @RequestBody @Valid final RegistroParqueaderoDTO registroParqueaderoDTO) {
        final Integer createdId = registroParqueaderoService.create(registroParqueaderoDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateRegistroParqueadero(@PathVariable final Integer id,
            @RequestBody @Valid final RegistroParqueaderoDTO registroParqueaderoDTO) {
        registroParqueaderoService.update(id, registroParqueaderoDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRegistroParqueadero(@PathVariable final Integer id) {
        registroParqueaderoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
