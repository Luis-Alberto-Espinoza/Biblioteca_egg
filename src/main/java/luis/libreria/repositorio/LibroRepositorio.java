package luis.libreria.repositorio;

import luis.libreria.entidad.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface LibroRepositorio extends JpaRepository<Libro, Long> {

//    @Query("SELECT Libro FROM Libro ORDER BY ejemplares")
//    public List<Libro> groupByEditorial();
}
