package luis.libreria.servicio;

import luis.libreria.entidad.Autor;
import luis.libreria.entidad.Editorial;
import luis.libreria.entidad.Libro;
import luis.libreria.repositorio.AutorRepositorio;
import luis.libreria.repositorio.EditorialRepositorio;
import luis.libreria.repositorio.LibroRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class LibroServicio {

    @Autowired
    public LibroRepositorio libroRepositorio;


    public Libro buscarUno(Long id) {
        System.out.println("llegue a lbserv ");
        System.out.println("este es el id  " + id);

        Optional<Libro> libro = libroRepositorio.findById(id);
        return libro.get();
    }

    public List<Libro> buscarTodos() {
        return libroRepositorio.findAll();

    }

    public void crearLibro(String titulo, String isbn, LocalDate anio, Integer ejemplares,
                           Editorial editorial1, Autor autor1) {
        Libro libro = new Libro();
        libro.setTitulo(titulo);
        libro.setAnio(anio);
        libro.setAlta(true);
        libro.setIsbn(isbn);
        libro.setEjemplares(ejemplares);
        libro.setEjemplaresRestantes(ejemplares);
        libro.setEjemplaresPrestados(0);
        libro.setEditorial(editorial1);
        libro.setAutor(autor1);
        libroRepositorio.save(libro);

    }

    public void ModificarLibro(Long id, String titulo, String isbn,
                               LocalDate anio, Integer ejemplares,
                               Editorial editorial, Autor autor) {
        Libro libro = buscarUno(id);
        libro.setTitulo(titulo);
        libro.setAnio(anio);
        libro.setIsbn(isbn);
        libro.setEjemplares(ejemplares);
        libro.setEditorial(editorial);
        libro.setAutor(autor);
        libroRepositorio.save(libro);
    }

    public void bajaLibro(Long id) {
        Libro libro = buscarUno(id);
        libro.setAlta(false);
        libroRepositorio.save(libro);

    }


    public List<Libro> findByTitulo(String q) {

        return libroRepositorio.librosXtitulo(q);
    }

    public Libro findById(Long id) {
        return libroRepositorio.libroXid(id);

    }
}
