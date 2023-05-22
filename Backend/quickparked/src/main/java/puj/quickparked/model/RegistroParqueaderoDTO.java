package puj.quickparked.model;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class RegistroParqueaderoDTO {

    private Integer id;

    @NotNull
    private LocalDate horaEntrada;

    @NotNull
    private LocalDate horaSalida;

    private LocalDate horaReserva;

    private Double montoReserva;

    @NotNull
    private Integer usuarioTrabajador;

    @NotNull
    private Integer estadoRegistroParqueadero;

    @NotNull
    private Integer sedeParqueadero;

    @NotNull
    private Integer vehiculo;

}
