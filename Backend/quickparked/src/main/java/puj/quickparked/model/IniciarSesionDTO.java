package puj.quickparked.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class IniciarSesionDTO implements Serializable {
    private String username;
    private String password;
}
