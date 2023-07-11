package CleanNow01.CleanNow.Models;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "cancelaciones")
public class Cancelacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_cancelacion")
    private Date fechaCancelacion;

    @Column(name = "motivo")
    private String motivo;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reserva_id")
    private Reserva reserva;

}
