package puj.quickparked.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class VehiculoDTO {

    private Integer id;

    @NotNull
    @Size(max = 255)
    private String placa;

    @NotNull
    private Integer tipoVehiculo;

    @NotNull
    private Integer usuario;

}
