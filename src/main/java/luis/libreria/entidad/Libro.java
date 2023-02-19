package luis.libreria.entidad;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.*;


@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Libro {
    private String id;
    private Long isbn;
    private String titulo;
    private Integer anio;
    private Integer ejemplares;
    private Integer ejemplaresPrestados;
    private Integer ejemplaresRestantes;
    private Boolean alta;
    private Autor autor;
    private Editorial editorial;
}
