package luis.libreria.controlador;

import luis.libreria.entidad.Autor;
import luis.libreria.entidad.Cliente;
import luis.libreria.entidad.Editorial;
import luis.libreria.entidad.Libro;
import luis.libreria.servicio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping(value = "/libro")
public class LibroControlador {
    @Autowired
    LibroServicio libroServicio;

    @Autowired
    AutorServicio autorServicio;

    @Autowired
    EditorialServicio editorialServicio;


    @Autowired
    ClienteServicio clienteServicio;

    @Autowired
    PrestamoServicio prestamoServicio;

    @GetMapping("/tabla")
    public String tablaLibros(ModelMap model) {
        List<Libro> libros = libroServicio.buscarTodos();
        model.put("objetoAiterar", libros);
        model.addAttribute("urlLend", "/libro/rango_fechas");
        return "libro_tabla";
    }


    @GetMapping("/pre_alta")
    public String CargarDatosLibro(ModelMap model, Long id) {
        List<Editorial> editoriales = editorialServicio.BuscarEditoriales();
        List<Autor> autores = autorServicio.buscarAutores();

        model.addAttribute("autores", autores);
        model.addAttribute("editoriales", editoriales);

        model.addAttribute("alta", "alta_libro");
        model.addAttribute("formulario", "Formulario alta Libro");
        model.addAttribute("tituloFormulario", "Dar de alta al Libro");
        model.addAttribute("action", "/libro/alta");
        model.addAttribute("boton", "Registrar Libro");


        return "libro_formulario";
    }

    @GetMapping(value = "/alta")
    public String nuevoLibro(ModelMap model,
                             @RequestParam String titulo,
                             @RequestParam String isbn,
                             @RequestParam("anio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate anio,
                             @RequestParam Integer ejemplares,
                             @RequestParam String editorial,
                             @RequestParam String autor) {
        Editorial editorial1 = editorialServicio.BuscarEditorialxId(Long.parseLong(editorial));
        Autor autor1 = autorServicio.autorXid(Long.parseLong(autor));

        libroServicio.crearLibro(titulo, isbn, anio, ejemplares, editorial1, autor1);

        model.addAttribute("titulo", "genial fue recibido");
        return "exito";
    }

    @GetMapping("/baja")
    public String BajaLibro(ModelMap model, Long id) {

        libroServicio.bajaLibro(id);
        model.addAttribute("titulo", "genial fue dado de baja");
        return "exito";
    }

    @GetMapping("/pre_update")
    public String pre_update(ModelMap model, Long id) {

        Libro libro = libroServicio.buscarUno(id);

        List<Editorial> editoriales = editorialServicio.BuscarEditoriales();
        List<Autor> autores = autorServicio.buscarAutores();

        model.addAttribute("autores", autores);
        model.addAttribute("editoriales", editoriales);

        model.put("libro", libro);
        model.addAttribute("selected_autor", libro.getAutor().getNombre());
        model.addAttribute("selected_editorial", libro.getEditorial().getNombre());

        model.addAttribute("update", "update_libro");
        model.addAttribute("formulario", "Formulario editar Libro");
        model.addAttribute("tituloFormulario", "Modificar datos de un Libro");
        model.addAttribute("action", "/libro/update");
        model.addAttribute("boton", "Actualizar Libro");
        return "libro_formulario";
    }

    @GetMapping("/update")
    public String updateLibro(ModelMap model, Long id,
                              @RequestParam String titulo,
                              @RequestParam String isbn,
                              @RequestParam("anio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate anio,
                              @RequestParam Integer ejemplares,
                              @RequestParam String editorial,
                              @RequestParam String autor) {
        Autor autor1 = autorServicio.autorXid(Long.parseLong(autor));
        Editorial editorial1 = editorialServicio.BuscarEditorialxId(Long.parseLong(editorial));
        libroServicio.ModificarLibro(id, titulo, isbn, anio, ejemplares, editorial1, autor1);
        model.addAttribute("titulo", "genial fue el libro modificado");
        return "exito";
    }

    @GetMapping("/busqueda")
    public String busquedaLibro(ModelMap model, @RequestParam(value = "query", required = false) String q) {
        List<Libro> libros = libroServicio.findByTitulo(q);
        System.out.println("cantidad de objetos " + libros.size());
        model.addAttribute("objetoAiterar", libros);
        model.addAttribute("urlLend", "/libro/rango_fechas");

        return "/libro_busqueda";
    }

    @GetMapping("/rango_fechas")
    public String rango_fechas(ModelMap model, @RequestParam(value = "id_libro", required = false) Long id) {
        Libro libro = libroServicio.buscarUno(id);
        model.addAttribute("activar_rango", "activar_rango");
        model.addAttribute("id_libro", id);
        model.addAttribute("objetoAiterar", libro);
        model.addAttribute("urlLend", "/libro/prestar");
        model.addAttribute("action", "/libro/prestar");
        model.addAttribute("boton", "Actualizar Libro");

        return "/libro_busqueda";
    }


    @GetMapping("/prestar")
    public String prestar(ModelMap model,
                          @RequestParam("prestamo") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate prestamo,
                          @RequestParam("devolucion") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate devolucion,
                          @RequestParam(value = "id_libro", required = false) Long id) {
        System.out.println("llegue a prestar");
        System.out.println("el id es " + id);
        // Libro libros = libroServicio.findById(id);
        //model.addAttribute("objetoAiterar", libros);
        model.addAttribute("prestamo", prestamo);
        model.addAttribute("devolucion", devolucion);
        model.addAttribute("id_libro", id);
        model.addAttribute("action", "/libro/prestar2");
        model.addAttribute("boton", "Buscar Cliente");

        return "/prestar";
    }

    @GetMapping("/prestar2")
    public String prestar(ModelMap model, Long id_libro,
                          @RequestParam("prestamo") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate prestamo,
                          @RequestParam("devolucion") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate devolucion,
                          @RequestParam(value = "query", required = false) String q) {
        System.out.println("llegue a prestar 2");
        System.out.println("el id del libro " + id_libro);
        System.out.println("el query " + q);
        List<Cliente> clientes = clienteServicio.busquedaXstring(q);
        System.out.println("cantidad de registros encontrdo " + clientes.size());

        model.addAttribute("prestamo", prestamo);
        model.addAttribute("devolucion", devolucion);
        model.addAttribute("id_libro", id_libro);
        model.addAttribute("objetoAiterar", clientes);
        model.addAttribute("urlLend", "/libro/prestar3");
        return "clientes_prestar";
    }

    @GetMapping("/prestar3")
    public String prestar3(ModelMap model, Long id_libro,
                           @RequestParam("prestamo") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate prestamo,
                           @RequestParam("devolucion") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate devolucion,
                           @RequestParam(value = "id", required = false) Long id) {
        System.out.println("llegue a prestar 3 ");
        System.out.println("esto trae la fecha de prestamo " + prestamo);
        System.out.println("id libro " + id_libro + "  id cliente " + id);
//        LocalDate prestamo = "2023-03-15";
        prestamoServicio.guardarPrestamo(prestamo, devolucion, String.valueOf(id), String.valueOf(id_libro));
        model.addAttribute("titulo", "genial el libro fe prestado");

        return "exito";
    }
}
