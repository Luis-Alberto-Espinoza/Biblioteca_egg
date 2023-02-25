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

    @GetMapping("/tabla")
    public String todos(ModelMap model) {
        List<Prestamo> Prestamos = prestamoServicio.todos();
        model.put("objetoAiterar", Prestamos);

        model.addAttribute("titulo_html", "Lista de Clintes");
        model.addAttribute("formulario", "Tabla de Prestamos");
        model.addAttribute("tituloFormulario", "Gestionar Prestamos");
        model.addAttribute("action", "/prestamo/alta");
        model.addAttribute("urlDelete", "/prestamo/baja");
        model.addAttribute("urlEdit", "/prestamo/pre_update");
        model.addAttribute("urlAdd", "/prestamo/alta");


        return "prestamo_tabla";
    }

    @GetMapping("/alta")
    public String alta_cliente(ModelMap model) {
        model.addAttribute("alta", "alta");
        model.addAttribute("formulario", "Agrega Prestamo");
        model.addAttribute("tituloFormulario", "Agregar un nuevo Prestamo");
        model.addAttribute("action", "/prestamo/save");
        model.addAttribute("titulo_html", "Lista de Clientes");
        model.addAttribute("boton", "Agrega un Prestamo");
        return "prestamo_formulario";
    }

    @GetMapping("/save")
    public String save(ModelMap model,
                       @RequestParam ("prestamo") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)  LocalDate prestamo,
                       @RequestParam ("devolucion") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate devolucion,
                       @RequestParam String id_cliente,
                       @RequestParam String id_libro
    ) {
        prestamoServicio.guardarPrestamo(prestamo,devolucion, id_cliente,id_libro);
        model.addAttribute("titulo", "genial fue guardado en nuevo Prestamo");
        return "exito";
    }


    @GetMapping("/pre_update")
    public String pre_update(ModelMap model, Long id) {
        Prestamo prestamo = prestamoServicio.buscarXid(id);
        model.put("objetoIterador", prestamo);
        model.addAttribute("titulo_html", "Lista de Clintes");
        model.addAttribute("update", "update");

        model.addAttribute("formulario", "Formulario Prestamo");
        model.addAttribute("tituloFormulario", "Gestionar Prestamo");
        model.addAttribute("action", "/prestamo/update");
        model.addAttribute("boton", "Actualizar Prestamo");


        return "prestamo_formulario";
    }

    @GetMapping("/update")
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



    @GetMapping("/baja")
    public String bajaPrestamo(Long id, ModelMap model) {
        prestamoServicio.darBajaPrestamo(id);
        model.addAttribute("titulo", "genial fue dado de baja el Prestamo");
        return "exito";
    }


}
