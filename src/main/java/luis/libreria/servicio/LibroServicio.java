package luis.libreria.servicio;

import luis.libreria.entidad.Libro;
import luis.libreria.repositorio.LibroRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Service
public class LibroServicio {

    @Autowired
    public LibroRepositorio libroRepositorio;

    public void buscarUno(Long id) {
        System.out.println("llegue a lbserv ");
        System.out.println("este es el id");

        Optional<Libro> libro = libroRepositorio.findById(id);
        System.out.println("este es el resultado titulo " + libro.get().getTitulo());
        System.out.println(libro.get().getIsbn() + "< es el isbn del 1022");

    }
    public  void buscarTodos (){
        System.out.println("llegue a buscar a todos los linros ");
        System.out.println("cant "+libroRepositorio.findAll().size());
        System.out.println("el tercero "+libroRepositorio.findAll().get(2).getTitulo());

    }
//    public void librosOrderByEditorial(){
//        System.out.println("llegue a Ordenar por editorial ");
//        System.out.println(libroRepositorio.groupByEditorial().size()+" tamaño");
//        System.out.println(libroRepositorio.groupByEditorial().get(2).getTitulo()+" titulo 2");
//        System.out.println(libroRepositorio.groupByEditorial().get(2).getEditorial()+" editorial 2");
//        System.out.println(libroRepositorio.groupByEditorial().get(3).getTitulo()+" titulo 3");
//        System.out.println(libroRepositorio.groupByEditorial().get(3).getEditorial()+" editorial 3");
//    }

    public void crearLibro(


    ) {


    }

    public void ModificarLibro() {

    }

    public void darBajaLibro(String id) {

    }
}
