package puj.quickparked.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SedeParqueaderoDTO {

    private Integer id;

    @NotNull
    @Size(max = 255)
    private String nombreSede;

    @NotNull
    @Size(max = 255)
    private Double latitud;

    @NotNull
    @Size(max = 255)
    private Double longitud;

    @NotNull
    private Integer cupo;

    private Integer cupoOcupado;

    private String imagen;

    @NotNull
    private Integer parqueadero;

    private Double tarifa;

    private Double tarifaMoto;

    private Integer calificacion;
}
