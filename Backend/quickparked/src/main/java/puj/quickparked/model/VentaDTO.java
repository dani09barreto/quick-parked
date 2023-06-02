package puj.quickparked.model;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class VentaDTO {

    private Integer id;

    @NotNull
    private LocalDateTime fechaPago;

    @NotNull
    private Double iva;

    @NotNull
    private Double monto;

    @NotNull
    private Integer reserva;

}
