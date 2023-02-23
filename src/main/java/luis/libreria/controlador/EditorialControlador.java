package luis.libreria.controlador;

import luis.libreria.entidad.Editorial;
import luis.libreria.servicio.AutorServicio;
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
@RequestMapping("/editorial")
public class EditorialControlador {
    @Autowired
    LibroServicio libroServicio;
    @Autowired
    AutorServicio autorServicio;
    @Autowired
    EditorialServicio editorialServicio;

    @GetMapping("/")
    public String index_editorial(ModelMap model) {
        model.addAttribute("editorial", "editorial");
        model.addAttribute("formulario", "Formulario alta Editorial");
        model.addAttribute("tituloFormulario", "Dar de alta al editorial");
        model.addAttribute("action", "/editorial/alta");
        model.addAttribute("lable1", "Nombre de la Editorial");
        model.addAttribute("boton", "Registrar Editorial");
        return "alta";
    }

    @GetMapping("/alta")
    public String alta_editorial(ModelMap model, @RequestParam String nombre) {
        editorialServicio.GuardarEditorial(nombre);
        model.addAttribute("titulo", "genial fue recibido");
        return "exito";
    }

    @GetMapping("/baja")
    public String bajaEditorial(Long id, ModelMap model) {
        editorialServicio.darBajaEditorial(id);
        model.addAttribute("titulo", "genial fue dado de baja");
        return "exito";
    }

    @GetMapping("/pre_update")
    public String pre_update(ModelMap model, Long id) {
        Editorial editorial = editorialServicio.BuscarEditorialxId(id);
        model.addAttribute("objetoIterador", editorial);
        model.addAttribute("formulario", "Formulario editar Editorial");
        model.addAttribute("tituloFormulario", "Modificar datos de un Editorial");
        model.addAttribute("action", "/editorial/update_editorial");
        model.addAttribute("boton", "Actualizar Autor");
        return "update_editorial";
    }

    @GetMapping("/update_editorial")
    public String actualizarAutor(ModelMap model,
                                  @RequestParam Long id,
                                  @RequestParam String nombre) {
        editorialServicio.actualizar(id, nombre);
        model.addAttribute("titulo", "genial fue modificado el autor");
        model.addAttribute("titulo", "genial fue modificado la editorial");
        return "exito";
    }

    @GetMapping("/tabla")
    public String tabla(ModelMap model) {
        List<Editorial> editoriales = editorialServicio.BuscarEditoriales();
        model.put("objetoAiterar", editoriales);
        model.addAttribute("tituloFormulario", "todos los editoriales");
        return "tabla_editorial";
    }

}
