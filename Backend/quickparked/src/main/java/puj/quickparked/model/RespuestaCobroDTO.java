package puj.quickparked.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RespuestaCobroDTO {

    private String placa;

    private String tipoVehiculo;

    private LocalDateTime horaIngreso;

    private Double tarifa;

    private Double valor;

    private Double montoReserva;
}
