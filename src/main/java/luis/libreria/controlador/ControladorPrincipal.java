package luis.libreria.controlador;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControladorPrincipal {
    @GetMapping ("/")
    public String index(ModelMap model){
        return "index";
    }

}
