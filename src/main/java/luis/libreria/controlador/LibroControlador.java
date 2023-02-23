package luis.libreria.controlador;

import luis.libreria.entidad.Autor;
import luis.libreria.entidad.Editorial;
import luis.libreria.entidad.Libro;
import luis.libreria.servicio.AutorServicio;
import luis.libreria.servicio.EditorialServicio;
import luis.libreria.servicio.LibroServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
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


    @GetMapping("/tabla")
    public String tablaLibros(ModelMap model) {
        List<Libro> libros = libroServicio.libroRepositorio.findAll();
        model.put("objetoAiterar", libros);
        return "tabla";
    }


    @GetMapping("/pre_alta")
    public String CargarDatosLibro(ModelMap model, Long id) {
        List<Editorial> editoriales = editorialServicio.BuscarEditoriales();
        List<Autor> autores = autorServicio.buscarAutores();


        model.addAttribute("autores", autores);
        model.addAttribute("editoriales", editoriales);

        model.addAttribute("alta_libro", "alta_libro");
        model.addAttribute("formulario", "Formulario alta Libro");
        model.addAttribute("tituloFormulario", "Dar de alta al Libro");
        model.addAttribute("action", "/libro/alta");
        model.addAttribute("boton", "Registrar Libro");


        return "alta";
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

        model.addAttribute("alta_libro", "alta_libro");
        model.addAttribute("formulario", "Formulario editar Libro");
        model.addAttribute("tituloFormulario", "Modificar datos de un Libro");
        model.addAttribute("action", "/libro/update");
        model.addAttribute("boton", "Actualizar Libro");


        return "update";
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
         libroServicio.ModificarLibro(id,titulo, isbn, anio, ejemplares, editorial1, autor1);
        model.addAttribute("titulo", "genial fue el libro modificado");
        return "exito";
    }
}
