package luis.libreria.controlador;

import luis.libreria.entidad.Autor;
import luis.libreria.entidad.Editorial;
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

    @GetMapping("/")
    public String CargarDatosLibro(ModelMap model) {
        libroServicio.buscarUno(1022l);
        libroServicio.buscarTodos();
        List<Editorial> editoriales = editorialServicio.BuscarEditoriales();
        List<Autor> autores = autorServicio.buscarAutores();


        model.addAttribute("autores", autores);
        model.addAttribute("editoriales", editoriales);

        model.addAttribute("alta_libro","alta_libro");
        model.addAttribute("formulario","Formulario alta Libro");
        model.addAttribute("tituloFormulario","Dar de alta al Libro");
        model.addAttribute("action","/libro/alta");
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
                             @RequestParam String autor

    ) {
        Editorial editorial1 = editorialServicio.BuscarEditorialxId(Long.parseLong(editorial));
        Autor autor1 = autorServicio.autorXid(Long.parseLong(autor));

        libroServicio.crearLibro(titulo,isbn, anio,ejemplares,editorial1,autor1);

        model.addAttribute("titulo", "genial fue recibido");
        return "exito";
    }
}
