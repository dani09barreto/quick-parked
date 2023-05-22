package puj.quickparked.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.time.LocalDate;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class RegistroParqueadero {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private LocalDate horaEntrada;

    @Column(nullable = false)
    private LocalDate horaSalida;

    @Column
    private LocalDate horaReserva;

    @Column
    private Double montoReserva;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_trabajador_id", nullable = false)
    private Usuario usuarioTrabajador;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estado_registro_parqueadero_id", nullable = false)
    private EstadoRegistroParqueadero estadoRegistroParqueadero;

    @OneToMany(mappedBy = "reserva")
    private Set<Venta> reservaVentas;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sede_parqueadero_id", nullable = false)
    private SedeParqueadero sedeParqueadero;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehiculo_id", nullable = false)
    private Vehiculo vehiculo;

}
