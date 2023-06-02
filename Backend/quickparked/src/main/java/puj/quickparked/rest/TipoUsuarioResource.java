package puj.quickparked.rest;

import puj.quickparked.model.TipoUsuarioDTO;
import puj.quickparked.service.TipoUsuarioService;
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
@RequestMapping(value = "/api/tipoUsuarios", produces = MediaType.APPLICATION_JSON_VALUE)
public class TipoUsuarioResource {

    private final TipoUsuarioService tipoUsuarioService;

    public TipoUsuarioResource(final TipoUsuarioService tipoUsuarioService) {
        this.tipoUsuarioService = tipoUsuarioService;
    }

    @GetMapping
    public ResponseEntity<List<TipoUsuarioDTO>> getAllTipoUsuarios() {
        return ResponseEntity.ok(tipoUsuarioService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoUsuarioDTO> getTipoUsuario(@PathVariable final Integer id) {
        return ResponseEntity.ok(tipoUsuarioService.get(id));
    }

    @PostMapping
    public ResponseEntity<Integer> createTipoUsuario(
            @RequestBody @Valid final TipoUsuarioDTO tipoUsuarioDTO) {
        final Integer createdId = tipoUsuarioService.create(tipoUsuarioDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateTipoUsuario(@PathVariable final Integer id,
            @RequestBody @Valid final TipoUsuarioDTO tipoUsuarioDTO) {
        tipoUsuarioService.update(id, tipoUsuarioDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTipoUsuario(@PathVariable final Integer id) {
        tipoUsuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
