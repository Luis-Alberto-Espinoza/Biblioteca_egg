package luis.libreria.servicio;

import luis.libreria.entidad.Cliente;
import luis.libreria.repositorio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServicio {

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

    public List<Cliente> todos() {
        return clienteRepositorio.findAll();
    }

    public Cliente buscarXid(Long id) {
        return clienteRepositorio.findById(id).get();
    }

    public void actualizar(long id, String nombre, String apellido, long dni, String telefono, boolean alta) {
        Cliente cliente = buscarXid(id);
        cliente.setNombre(nombre);
        cliente.setApellido(apellido);
        cliente.setDni(dni);
        cliente.setAlta(alta);
        cliente.setTelefono(telefono);
        clienteRepositorio.save(cliente);
    }

    public void guardarCliente(String nombre, String apellido, long dni, String telefono) {
        Cliente newCliente = new Cliente();
        newCliente.setNombre(nombre);
        newCliente.setApellido(apellido);
        newCliente.setDni(dni);
        newCliente.setTelefono(telefono);
        newCliente.setAlta(true);
        clienteRepositorio.save(newCliente);
    }

    public void darBajaCliente(Long id) {
        Cliente cliente = buscarXid(id);
        cliente.setAlta(false);
        clienteRepositorio.save(cliente);
    }

    public List<Cliente> busquedaXstring(String q) {
        return clienteRepositorio.buscarXstring(q);
    }
}
