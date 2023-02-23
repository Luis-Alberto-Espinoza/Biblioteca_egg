package luis.libreria.servicio;


import luis.libreria.entidad.Cliente;
import luis.libreria.entidad.Libro;
import luis.libreria.entidad.Prestamo;
import luis.libreria.repositorio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PrestamoServicio {
    @Autowired
    private LibroRepositorio libroRepositorio;

    @Autowired
    private AutorRepositorio autorRepositorio;

    @Autowired
    private EditorialRepositorio editorialRepositorio;

    @Autowired
    private PrestamoRepositorio prestamoRepositorio;

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    public List<Prestamo> todos() {
        return prestamoRepositorio.findAll();
    }

    public Prestamo buscarXid(Long id) {
        return prestamoRepositorio.findById(id).get();
    }

    public void actualizar(
            long id, LocalDate fprestamo, LocalDate fdevolucion, Long id_l, Long id_cliente, boolean alta
    ) {
        Libro libro = libroRepositorio.findById(id_l).get();
        Cliente cliente = clienteRepositorio.findById(id_cliente).get();

        Prestamo prestamo = buscarXid(id);
        prestamo.setPrestamo(fprestamo);
        prestamo.setDevolucion(fdevolucion);
        prestamo.setAlta(alta);
        prestamo.setCliente(cliente);
        prestamo.setLibro(libro);
        prestamoRepositorio.save(prestamo);
    }



    public void darBajaPrestamo(Long id) {
        Prestamo cliente = buscarXid(id);
        cliente.setAlta(false);
        prestamoRepositorio.save(cliente);
    }

    public void guardarPrestamo(LocalDate prestamo, LocalDate devolucion, String id_cliente, String id_libro) {
        Libro libro = libroRepositorio.findById(Long.parseLong(id_libro)).get();
        Cliente cliente = clienteRepositorio.findById(Long.parseLong(id_cliente)).get();
        Prestamo newPrestamo = new Prestamo();
        newPrestamo.setPrestamo(prestamo);
        newPrestamo.setDevolucion(devolucion);
        newPrestamo.setAlta(true);
        newPrestamo.setCliente(cliente);
        newPrestamo.setLibro(libro);
        prestamoRepositorio.save(newPrestamo);
    }
}
