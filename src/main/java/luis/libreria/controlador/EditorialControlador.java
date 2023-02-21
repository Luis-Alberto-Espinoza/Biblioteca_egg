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
@RequestMapping("/editorial")
public class EditorialControlador {
    @Autowired
    LibroServicio libroServicio;

    @Autowired
    AutorServicio autorServicio;

    @Autowired
    EditorialServicio editorialServicio;

    @GetMapping("/")
    public String index_editorial(ModelMap model){
        System.out.println("--index editorial ");

        return "formulario_alta_editorial";
    }

    @GetMapping("/alta_editorial")
    public String alta_editorial(ModelMap model, @RequestParam String nombre){
        System.out.println( "----34 alta_editorial");
        LocalDate actual = LocalDate.now();
        editorialServicio.GuardarEditorial(nombre);

        model.addAttribute("titulo", "genial fue recibido");
        return "exito";
    }




}
