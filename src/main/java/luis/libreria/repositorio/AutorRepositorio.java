package luis.libreria.repositorio;

import luis.libreria.entidad.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepositorio extends JpaRepository<Autor, Long> {
    @Query("Select a from Autor a where a.id = :id")
    public Autor autorXid(@Param("id") Long id);
}
