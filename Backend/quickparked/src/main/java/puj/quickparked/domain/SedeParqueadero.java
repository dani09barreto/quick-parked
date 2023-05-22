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
import java.util.Set;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class SedeParqueadero {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nombreSede;

    @Column(nullable = false)
    private String latitud;

    @Column(nullable = false)
    private String longitud;

    @Column(nullable = false)
    private Integer cupo;

    @Column(columnDefinition = "longtext")
    private String imagen;

    @OneToMany(mappedBy = "sedeParqueadero")
    private Set<Usuario> sedeParqueaderoUsuarios;

    @OneToMany(mappedBy = "sedeParqueadero")
    private Set<RegistroParqueadero> sedeParqueaderoRegistroParqueaderos;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parqueadero_id", nullable = false)
    private Parqueadero parqueadero;

}
