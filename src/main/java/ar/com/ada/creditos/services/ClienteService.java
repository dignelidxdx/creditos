package ar.com.ada.creditos.services;

import java.util.List;

import ar.com.ada.creditos.entities.Cliente;
import ar.com.ada.creditos.managers.ClienteManager;

public class ClienteService {

    public ClienteManager repo = new ClienteManager();
 
    public ClienteService(ClienteManager repo) {
        this.repo = repo;
        repo.setup();
    }    

    public List<Cliente> getClientes(String nombre) {
        return repo.buscarPor(nombre);
    }

    public void listarCliente() {

        List<Cliente> todos = repo.buscarTodos();
        for (Cliente c : todos) {
            mostrarCliente(c);
        }
    }

    public void listarPorNombreDeCliente(String nombre) {

        List<Cliente> clientes = repo.buscarPor(nombre);
        for (Cliente cliente : clientes) {
            mostrarCliente(cliente);
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