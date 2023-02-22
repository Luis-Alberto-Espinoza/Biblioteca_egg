package luis.libreria.controlador;

import luis.libreria.entidad.Autor;
import luis.libreria.servicio.AutorServicio;
import luis.libreria.servicio.EditorialServicio;
import luis.libreria.servicio.LibroServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/autor")
public class AutorControlador {
    @Autowired
    LibroServicio libroServicio;

    @Autowired
    AutorServicio autorServicio;

    @Autowired
    EditorialServicio editorialServicio;

    @GetMapping("/")
    public String altaAutor(ModelMap model) {


        model.addAttribute("editorial", "editorial");
        model.addAttribute("formulario", "Formulario alta Autor");
        model.addAttribute("tituloFormulario", "Dar de alta al Autor");
        model.addAttribute("action", "/autor/alta");
        model.addAttribute("lable1", "Nombre del Autor");
        model.addAttribute("boton", "Registrar Autor");

        return "alta";
    }

    @GetMapping("/alta")
    public String altaAutor(ModelMap model, @RequestParam String nombre) {
        System.out.println("----34 alta_Autor");
        LocalDate actual = LocalDate.now();
        autorServicio.GuardarAutor(nombre);


        model.addAttribute("titulo", "genial fue recibido");
        return "exito";
    }

    @GetMapping("/baja")
    public String bajaEditorial(ModelMap model, Long id) {
        autorServicio.bajaAutor(id);
        model.addAttribute("titulo", "genial fue dado de baja");

        return "exito";
    }

    @GetMapping("/tabla")
    public String tabla(ModelMap model) {
        List<Autor> autores = autorServicio.buscarAutores();
        model.put("objetoAiterar", autores);
        model.addAttribute("tituloFormulario", "todos los autores");

        return "tabla_autor";
    }

    @GetMapping("/pre_update")
    public String pre_update(ModelMap model, Long id) {
        Autor autor = autorServicio.autorXid(id);

        model.addAttribute("objetoIterador", autor);
        model.addAttribute("formulario", "Formulario editar Autor");
        model.addAttribute("tituloFormulario", "Modificar datos de un Autor");
        model.addAttribute("action", "/autor/update_autor");
        model.addAttribute("boton", "Actualizar Autor");

        return "update_autor";
    }

    @GetMapping("/update_autor")
    public String actualizarAutor(ModelMap model,
                                  @RequestParam Long id,
                                  @RequestParam String nombre
                                ) {
        autorServicio.actualizar(id, nombre);
        model.addAttribute("titulo", "genial fue modificado el autor");

        return "exito";
    }
}
