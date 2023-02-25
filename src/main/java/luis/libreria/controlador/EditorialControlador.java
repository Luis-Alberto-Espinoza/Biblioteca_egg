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

    @GetMapping("/tabla")
    public String tabla(ModelMap model) {
        List<Editorial> editoriales = editorialServicio.BuscarEditoriales();
        model.put("objetoAiterar", editoriales);
        model.addAttribute("tituloFormulario", "Todas las editoriales");
        model.addAttribute("titulo_html", "Lista de Editoriales");
        model.addAttribute("urlDelete", "/editorial/baja");
        model.addAttribute("urlEdit", "/editorial/pre_update");
        model.addAttribute("urlAdd", "/editorial/alta");
        return "editorial_tabla";
    }

    @GetMapping("/alta")
    public String alta_editorial(ModelMap model) {
        model.addAttribute("alta", "alta");
        model.addAttribute("formulario", "Agrega Editorial");
        model.addAttribute("titulo_html", "Lista de Editoriales");
        model.addAttribute("tituloFormulario", "Agregar una nueva Editorial");
        model.addAttribute("action", "/editorial/save");
        model.addAttribute("boton", "Agrega una Editorial");
        return "editorial_formulario";
    }
    @GetMapping("/save")
    public String guardar(ModelMap model, String nombre) {
        editorialServicio.guardarAutor(nombre);
        model.addAttribute("titulo", "genial fue dado de alta");
        return "exito";
    }
    @GetMapping("/pre_update")
    public String pre_update(ModelMap model, Long id) {
        Editorial editorial = editorialServicio.BuscarEditorialxId(id);
        model.addAttribute("update", "activa update");
        model.addAttribute("objetoIterador", editorial);
        model.addAttribute("formulario", "Formulario editar Editorial");
        model.addAttribute("titulo_html", "Lista de Editoriales");
        model.addAttribute("tituloFormulario", "Modificar datos de un Editorial");
        model.addAttribute("action", "/editorial/update");
        model.addAttribute("boton", "Actualizar Editorial");
        return "editorial_formulario";
    }

    @GetMapping("/update")
    public String actualizarAutor(ModelMap model,
                                  @RequestParam Long id,
                                  @RequestParam boolean alta,
                                  @RequestParam String nombre) {
        editorialServicio.actualizar(id, nombre,alta);
        model.addAttribute("titulo", "genial fue modificado la editorial");
        model.addAttribute("titulo", "genial fue modificado la editorial");
        return "exito";
    }

    @GetMapping("/baja")
    public String bajaEditorial(Long id, ModelMap model) {
        editorialServicio.darBajaEditorial(id);
        model.addAttribute("titulo", "genial fue dado de baja");
        return "exito";
    }


}
