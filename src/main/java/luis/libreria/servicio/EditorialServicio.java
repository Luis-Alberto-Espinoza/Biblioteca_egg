package luis.libreria.servicio;

import luis.libreria.entidad.Editorial;
import luis.libreria.repositorio.EditorialRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EditorialServicio {

    @Autowired
    public EditorialRepositorio editorialRepositorio;

    public List<Editorial> BuscarEditoriales(){
        return editorialRepositorio.findAll();

    }
    public Editorial BuscarEditorialxId(Long id){
        return editorialRepositorio.editorialXid(id);

    }
}
