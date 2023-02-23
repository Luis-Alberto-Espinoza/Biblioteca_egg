package luis.libreria.entidad;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Prestamo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;
    private LocalDate prestamo;
    private LocalDate devolucion;
    private boolean alta;
    @OneToOne
    @JoinColumn(name = "libro_id")
    private Libro libro;
    @OneToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
}
