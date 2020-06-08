package ar.com.ada.creditos.services;

import java.util.List;
import ar.com.ada.creditos.entities.Cliente;
import ar.com.ada.creditos.managers.ClienteManager;

public class ClienteService {
    
    ClienteManager repo;

    protected ClienteManager ABMCliente = new ClienteManager();

    public List<Cliente> getClientes(String nombre) {
        return repo.buscarPor(nombre);
    }

    public void listarCliente() {

        List<Cliente> todos = ABMCliente.buscarTodos();
        for (Cliente c : todos) {
            mostrarCliente(c);
        }
    }

    public void mostrarCliente(Cliente cliente) {

        System.out.print("Id: " + cliente.getClienteId() + " Nombre: " + cliente.getNombre() + " DNI: "
                + cliente.getDni() + " Domicilio: " + cliente.getDomicilio());

        if (cliente.getDomicilioAlternativo() != null)
            System.out.println(" Alternativo: " + cliente.getDomicilioAlternativo());
        else
            System.out.println();
    }
}