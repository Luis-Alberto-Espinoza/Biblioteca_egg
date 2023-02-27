package luis.libreria.repositorio;

import luis.libreria.entidad.Cliente;
import luis.libreria.entidad.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente, Long> {

    @Query(value="select * from cliente c\n" +
                     "WHERE c.nombre Like %:q% OR c.apellido Like %:q%", nativeQuery = true)
    List<Cliente> buscarXstring(@Param("q") String q);
}

