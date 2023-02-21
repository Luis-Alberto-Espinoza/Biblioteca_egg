package luis.libreria.controlador;

import luis.libreria.servicio.LibroServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller

@RequestMapping(value = "/libro")
public  class LibroControlador {
    @Autowired
    LibroServicio libroServicio;

    @GetMapping("/index_libro")
    public  String CargarDatosLibro(ModelMap model){
        libroServicio.buscarUno(1022l);
        libroServicio.buscarTodos();
        return "formulario_alta_libro";
    }

    @GetMapping(value = "/alta_libro")
    public String nuevoLibro(ModelMap model,
                             @RequestParam String titulo,
                             @RequestParam Integer anio,
                             @RequestParam Integer ejemplares
            ){
        System.out.println("llegue a el alta libro");
        System.out.println("esto tiene este parametro  titulo "+titulo);

        model.addAttribute("titulo", "genial fue recibido");
        return "exito";
    }
}
