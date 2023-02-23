package luis.libreria.repositorio;

import luis.libreria.entidad.Libro;
import luis.libreria.entidad.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrestamoRepositorio extends JpaRepository<Prestamo, Long> {
}
