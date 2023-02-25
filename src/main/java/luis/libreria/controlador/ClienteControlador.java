package luis.libreria.controlador;

import luis.libreria.entidad.Cliente;
import luis.libreria.servicio.AutorServicio;
import luis.libreria.servicio.ClienteServicio;
import luis.libreria.servicio.EditorialServicio;
import luis.libreria.servicio.LibroServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/cliente")
public class ClienteControlador {
    @Autowired
    LibroServicio libroServicio;

    @Autowired
    AutorServicio autorServicio;

    @Autowired
    EditorialServicio editorialServicio;

    @Autowired
    ClienteServicio clienteServicio;

    @GetMapping("/todos")
    public String todos(ModelMap model) {
        List<Cliente> clientes = clienteServicio.todos();
        model.put("objetoAiterar", clientes);

        model.addAttribute("formulario", "Tabla de clientes");
        model.addAttribute("tituloFormulario", "Gestionar Clientes");
        model.addAttribute("action", "/cliente/alta");


        return "tabla_clientes";
    }

    @GetMapping("/alta")
    public String alta_cliente(ModelMap model) {
        model.addAttribute("alta", "alta");
        model.addAttribute("formulario", "Agrega Cliente");
        model.addAttribute("tituloFormulario", "Agregar un nuevo Cliente");
        model.addAttribute("action", "/cliente/save");
        return "cliente_formulario";
    }

    @GetMapping("/save")
    public String save(ModelMap model,
                       @RequestParam String nombre,
                       @RequestParam String apellido,
                       @RequestParam long dni,
                       @RequestParam String telefono
    ) {
        clienteServicio.guardarCliente(nombre, apellido, dni, telefono);
        model.addAttribute("titulo", "genial fue modificado la editorial");
        return "exito";
    }


    @GetMapping("/pre_update")
    public String pre_update(ModelMap model, Long id) {
        Cliente cliente = clienteServicio.buscarXid(id);

        model.put("objetoIterador", cliente);

        model.addAttribute("update", "update");
        model.addAttribute("formulario", "Formulario Cliente");
        model.addAttribute("tituloFormulario", "Gestionar Cliente");
        model.addAttribute("action", "/cliente/update_cliente");
        model.addAttribute("boton", "Actualizar Cliente");
        return "cliente_formulario";
    }

    @GetMapping("/update_cliente")
    public String actualizarCliente(ModelMap model,
                                    @RequestParam long id,
                                    @RequestParam String nombre,
                                    @RequestParam String apellido,
                                    @RequestParam long dni,
                                    @RequestParam String telefono,
                                    @RequestParam String alta
    ) {
        boolean estado;
        if (alta.equals("true")) {
            estado = true;

        } else {
            estado = false;
        }

        System.out.println("update_cliente llegue");
        clienteServicio.actualizar(id, nombre, apellido, dni, telefono, estado);
        // model.addAttribute("titulo", "genial fue modificado el autor");
        model.addAttribute("titulo", "genial fue modificado la editorial");
        return "exito";
    }

    //            baja
//    alta
//    @GetMapping("/alta")
//    public String alta_cliente(ModelMap model,
//                               @RequestParam String nombre,
//                               @RequestParam String apellido,
//                               @RequestParam long dni,
//                               @RequestParam String telefono
//    ) {
//        clienteServicio.guardarCliente(nombre, apellido, dni, telefono);
//        model.addAttribute("titulo", "genial fue dado de alta al cliente");
//        return "exito";
//    }

    @GetMapping("/baja")
    public String bajaCliente(Long id, ModelMap model) {
        clienteServicio.darBajaCliente(id);
        model.addAttribute("titulo", "genial fue dado de baja el cliente");
        return "exito";
    }


}
