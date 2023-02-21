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
import java.util.Optional;

@Service
public class LibroServicio {

    @Autowired
    public LibroRepositorio libroRepositorio;

    @Autowired
    public AutorRepositorio autorRepositorio;

    @Autowired
    public EditorialRepositorio editorialRepositorio;

    public void buscarUno(Long id) {
        System.out.println("llegue a lbserv ");
        System.out.println("este es el id");

        Optional<Libro> libro = libroRepositorio.findById(id);
        System.out.println("este es el resultado titulo " + libro.get().getTitulo());
        System.out.println(libro.get().getIsbn() + "< es el isbn del 1022");

    }

    public void buscarTodos() {
        System.out.println("llegue a buscar a todos los linros ");
        System.out.println("cant " + libroRepositorio.findAll().size());
        System.out.println("el tercero " + libroRepositorio.findAll().get(2).getTitulo());

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
    public void ModificarLibro() {

    }

    public void darBajaLibro(String id) {

    }


}
