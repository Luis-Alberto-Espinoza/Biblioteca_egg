package luis.libreria.controlador;

import luis.libreria.entidad.Cliente;
import luis.libreria.entidad.Libro;
import luis.libreria.entidad.Prestamo;
import luis.libreria.servicio.*;
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
@RequestMapping("/prestamo")
public class PrestamoControlador {

    @Autowired
    PrestamoServicio prestamoServicio;

    @GetMapping("/todos")
    public String todos(ModelMap model) {
        List<Prestamo> Prestamos = prestamoServicio.todos();
        model.put("objetoAiterar", Prestamos);

        model.addAttribute("formulario", "Tabla de Prestamos");
        model.addAttribute("tituloFormulario", "Gestionar Prestamos");
        model.addAttribute("action", "/prestamo/alta");


        return "tabla_prestamo";
    }

    @GetMapping("/pre_update")
    public String pre_update(ModelMap model, Long id) {
        Prestamo prestamo = prestamoServicio.buscarXid(id);
        model.put("objetoIterador", prestamo);

        model.addAttribute("formulario", "Formulario Prestamo");
        model.addAttribute("tituloFormulario", "Gestionar Prestamo");
        model.addAttribute("action", "/prestamo/update_prestamo");
        model.addAttribute("boton", "Actualizar Prestamo");


        return "update_prestamo";
    }

    @GetMapping("/update_prestamo")
    public String actualizarPrestamo(ModelMap model,
                                     @RequestParam long id,
                                     @RequestParam("prestamo") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate prestamo,
                                     @RequestParam("devolucion") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate devolucion,
                                     @RequestParam String alta,
                                     @RequestParam String id_libro,
                                     @RequestParam String id_cliente
    ) {
        boolean estado;
        if (alta.equals("true")) {
            estado = true;

        } else {
            estado = false;
        }

        System.out.println("update_Prestamo llegue");
        prestamoServicio.actualizar(id, prestamo, devolucion, Long.parseLong(id_libro), Long.parseLong(id_cliente), estado);
        // model.addAttribute("titulo", "genial fue modificado el autor");
        model.addAttribute("titulo", "genial fue modificado el prestamo");
        return "exito";
    }

    @GetMapping("/alta")
    public String alta_Prestamo(ModelMap model,
                                @RequestParam("prestamo") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate prestamo,
                                @RequestParam("devolucion") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate devolucion,
                                @RequestParam String id_libro,
                                @RequestParam String id_cliente
    ) {
        prestamoServicio.guardarPrestamo(prestamo, devolucion, id_cliente, id_libro);
        model.addAttribute("titulo", "genial fue dado de alta al Prestamo");
        return "exito";
    }

    @GetMapping("/baja")
    public String bajaPrestamo(Long id, ModelMap model) {
        prestamoServicio.darBajaPrestamo(id);
        model.addAttribute("titulo", "genial fue dado de baja el Prestamo");
        return "exito";
    }


}
