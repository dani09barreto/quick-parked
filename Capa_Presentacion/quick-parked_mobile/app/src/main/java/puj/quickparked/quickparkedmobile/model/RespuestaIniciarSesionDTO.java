package puj.quickparked.quickparkedmobile.model;

import java.io.Serializable;

public class RespuestaIniciarSesionDTO implements Serializable {
    private String nombres;
    private String apellidos;
    private String usuarioId;
    private String rolId;
    private String rolNombre;
    private String username;
    private String token;

    public RespuestaIniciarSesionDTO() {
    }

    public RespuestaIniciarSesionDTO(String nombres, String apellidos, String usuarioId, String rolId, String rolNombre, String username, String token) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.usuarioId = usuarioId;
        this.rolId = rolId;
        this.rolNombre = rolNombre;
        this.username = username;
        this.token = token;
    }


    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getRolId() {
        return rolId;
    }

    public void setRolId(String rolId) {
        this.rolId = rolId;
    }

    public String getRolNombre() {
        return rolNombre;
    }

    public void setRolNombre(String rolNombre) {
        this.rolNombre = rolNombre;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
