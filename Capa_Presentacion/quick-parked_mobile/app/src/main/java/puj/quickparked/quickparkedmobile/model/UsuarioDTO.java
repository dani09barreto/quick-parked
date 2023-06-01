package puj.quickparked.quickparkedmobile.model;

import java.io.Serializable;

public class UsuarioDTO implements Serializable {

    private Integer id;

    private String username;

    private String password;

    private String nombres;

    private String apellidos;

    private String email;

    private String telefono;

    private Integer sedeParqueadero;

    private Integer tipoUsuario;

    public UsuarioDTO() {
    }

    public UsuarioDTO(Integer id, String username, String password, String nombres, String apellidos, String email, String telefono, Integer sedeParqueadero, Integer tipoUsuario) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.email = email;
        this.telefono = telefono;
        this.sedeParqueadero = sedeParqueadero;
        this.tipoUsuario = tipoUsuario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Integer getSedeParqueadero() {
        return sedeParqueadero;
    }

    public void setSedeParqueadero(Integer sedeParqueadero) {
        this.sedeParqueadero = sedeParqueadero;
    }

    public Integer getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(Integer tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
}
