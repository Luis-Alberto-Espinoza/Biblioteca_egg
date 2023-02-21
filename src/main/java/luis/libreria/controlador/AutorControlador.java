package luis.libreria.controlador;

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
    public String altaAutor(ModelMap model, @RequestParam String nombre){
        System.out.println( "----34 alta_Autor");
        LocalDate actual = LocalDate.now();
        autorServicio.GuardarAutor(nombre);


        model.addAttribute("titulo", "genial fue recibido");
        return "exito";
    }
}
