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

    public void guardarAutor(String nombre) {
        Autor autorNew = new Autor();
        autorNew.setNombre(nombre);
        autorNew.setAlta(true);

        autorRepositorio.save(autorNew);

    }
    public void actualizar(Long id, String nombre) {
        Autor autor = autorRepositorio.autorXid(id);
        autor.setNombre(nombre);
        autorRepositorio.save(autor);

    }

    public void bajaAutor(Long id) {
        Autor autor = autorRepositorio.autorXid(id);
        autor.setAlta(false);
        autorRepositorio.save(autor);

    }
}
