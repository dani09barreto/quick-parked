package puj.quickparked.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ParqueaderoDTO {

    private Integer id;

    @NotNull
    private Integer nombre;

    @NotNull
    @Size(max = 255)
    private String nit;

}
