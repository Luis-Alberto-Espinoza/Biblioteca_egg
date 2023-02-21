package luis.libreria.entidad;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class Libro implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;
    private Long isbn;
    private String titulo;
    private LocalDate anio;
    private Integer ejemplares;
    private Integer ejemplaresPrestados;
    private Integer ejemplaresRestantes;
    private Boolean alta;

    @OneToOne
    @JoinColumn(name = "autor_fk")
    private Autor autor;

    @OneToOne
    @JoinColumn(name = "editorial_fk")
    private Editorial editorial;
}

