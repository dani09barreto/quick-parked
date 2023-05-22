package puj.quickparked.model;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class VentaDTO {

    private Integer id;

    @NotNull
    private LocalDate fechaPago;

    @NotNull
    private Double iva;

    @NotNull
    private Double monto;

    @NotNull
    private Integer reserva;

}
