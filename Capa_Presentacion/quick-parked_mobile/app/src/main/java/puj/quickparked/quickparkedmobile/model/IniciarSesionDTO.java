package puj.quickparked.quickparkedmobile.model;

import java.io.Serializable;

public class IniciarSesionDTO implements Serializable {
    private String username;
    private String password;

    public IniciarSesionDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public IniciarSesionDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


}
