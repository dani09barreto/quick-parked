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
import jakarta.persistence.SequenceGenerator;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class RegistroParqueadero {

    @Id
    @Column(
        nullable = false,
        updatable = false
    )
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    private Integer id;

    @Column(nullable = false)
    private LocalDateTime horaEntrada;

    @Column
    private LocalDateTime horaSalida;

    @Column
    private LocalDateTime horaReserva;

    @Column
    private Double montoReserva;

    @Column(nullable = false)
    private String slot;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuarioTrabajadorId", nullable = false)
    private Usuario usuarioTrabajador;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estadoRegistroParqueaderoId", nullable = false)
    private EstadoRegistroParqueadero estadoRegistroParqueadero;

    @OneToMany(mappedBy = "reserva")
    private Set<Venta> reservaVentas;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sedeParqueaderoId", nullable = false)
    private SedeParqueadero sedeParqueadero;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehiculoId", nullable = false)
    private Vehiculo vehiculo;

}
