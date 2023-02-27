package luis.libreria.repositorio;

import luis.libreria.entidad.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibroRepositorio extends JpaRepository<Libro, Long> {

@Query(value="select * from libro l\n" +
        "inner join editorial e on l.editorial_fk = e.id\n" +
        "INNER join autor a on l.autor_fk = a.id\n" +
        "WHERE l.titulo Like %:q% OR e.nombre Like %:q% or a.nombre Like %:q%", nativeQuery = true)
    public List<Libro> librosXtitulo(@Param("q") String q);

@Query(value="Select * from libro l where l.id = %:id% and l.alta = true", nativeQuery = true)
    Libro libroXid(@Param("id") Long id);
//    @Query("SELECT Libro FROM Libro ORDER BY ejemplares")
//    public List<Libro> groupByEditorial();
}
