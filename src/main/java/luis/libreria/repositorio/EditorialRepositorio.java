package luis.libreria.repositorio;

import luis.libreria.entidad.Editorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EditorialRepositorio extends JpaRepository<Editorial, Long> {

    @Query("Select e from Editorial e where e.id = :id")
    public Editorial editorialXid(@Param("id") Long id);

}

