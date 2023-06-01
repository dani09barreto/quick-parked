package puj.quickparked.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RespuestaSedeParquaderoDTO {


    private Integer id;

    private String nombreSede;

    private String latitud;

    private String longitud;

    private Integer cupo;

    private Integer cupoOcupado;

    private String imagen;

    private Integer parqueadero;

    private Double tarifa;

    private Integer calificacion;
}
