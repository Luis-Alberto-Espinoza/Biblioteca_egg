package luis.libreria.servicio;

import luis.libreria.entidad.Autor;
import luis.libreria.repositorio.AutorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorServicio {

    @Autowired
    AutorRepositorio autorRepositorio;

    public List<Autor> buscarAutores(){
        System.out.println("llegue a buscar autores ");
        List<Autor> autores=  autorRepositorio.findAll();
        return autores;
    }
    public Autor autorXid(long id){
        return autorRepositorio.autorXid(id);
    }
}
