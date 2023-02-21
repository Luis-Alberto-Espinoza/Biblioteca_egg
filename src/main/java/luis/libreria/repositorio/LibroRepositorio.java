package luis.libreria.repositorio;

import luis.libreria.entidad.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroRepositorio extends JpaRepository<Libro, Long> {

//    @Query("SELECT Libro FROM Libro ORDER BY ejemplares")
//    public List<Libro> groupByEditorial();
}
