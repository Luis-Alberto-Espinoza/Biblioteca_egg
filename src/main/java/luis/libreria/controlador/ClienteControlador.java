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
    ClienteServicio clienteServicio;

    @GetMapping("/tabla")
    public String todos(ModelMap model) {
        List<Cliente> clientes = clienteServicio.todos();
        model.put("objetoAiterar", clientes);
        model.addAttribute("tituloFormulario", "Todos los Clientes");
        model.addAttribute("titulo_html", "Lista de Clintes");
        model.addAttribute("urlDelete", "/cliente/baja");
        model.addAttribute("urlEdit", "/cliente/pre_update");
        model.addAttribute("urlAdd", "/cliente/alta");
        return "cliente_tabla";
    }

    @GetMapping("/alta")
    public String alta_cliente(ModelMap model) {
        model.addAttribute("alta", "alta");
        model.addAttribute("formulario", "Agrega Cliente");
        model.addAttribute("tituloFormulario", "Agregar un nuevo Cliente");
        model.addAttribute("action", "/cliente/save");
        model.addAttribute("titulo_html", "Lista de Clientes");
        model.addAttribute("boton", "Agrega un Cliente");
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
        model.addAttribute("titulo", "genial fue guardado en nuevo cliente");
        return "exito";
    }


    @GetMapping("/pre_update")
    public String pre_update(ModelMap model, Long id) {
        Cliente cliente = clienteServicio.buscarXid(id);

        model.put("objetoIterador", cliente);
        model.addAttribute("titulo_html", "Lista de Clientes");
        model.addAttribute("update", "update");
        model.addAttribute("formulario", "Formulario Cliente");
        model.addAttribute("tituloFormulario", "Gestionar Cliente");
        model.addAttribute("action", "/cliente/update");
        model.addAttribute("boton", "Actualizar Cliente");
        return "cliente_formulario";
    }

    @GetMapping("/update")
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
        model.addAttribute("titulo", "genial fue modificado la editorial");
        return "exito";
    }

    @GetMapping("/baja")
    public String bajaCliente(Long id, ModelMap model) {
        clienteServicio.darBajaCliente(id);
        model.addAttribute("titulo", "genial fue dado de baja el cliente");
        return "exito";
    }
}
