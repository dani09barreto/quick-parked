package puj.quickparked.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class RespuestaIniciarSesionDTO implements Serializable {
    private String nombres;
    private String apellidos;
    private String usuarioId;
    private String rolId;
    private String rolNombre;
    private String username;
    private String token;
}
