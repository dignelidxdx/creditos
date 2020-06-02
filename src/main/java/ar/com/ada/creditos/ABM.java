package ar.com.ada.creditos;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.hibernate.exception.ConstraintViolationException;

import ar.com.ada.creditos.entities.*;
import ar.com.ada.creditos.excepciones.*;
import ar.com.ada.creditos.managers.*;

public class ABM {

    public static Scanner Teclado = new Scanner(System.in);

    protected ClienteManager ABMCliente = new ClienteManager();

    protected PrestamoManager ABMPrestamo = new PrestamoManager();

    public void iniciar() throws Exception {

        try {

            ABMCliente.setup();
            ABMPrestamo.setup();

            int opcion = 1;
            int numero = printEntrada();
            opcion = Teclado.nextInt();
            Teclado.nextLine();

            while (opcion > 0) {
                if (numero == 1) {

                    switch (opcion) {
                        case 1:

                            try {
                                altaCliente();
                            } catch (ClienteDNIException exdni) {
                                System.out.println("Error en el DNI. Indique uno valido");
                            }
                            break;

                        case 2:
                            bajaCliente();
                            break;

                        case 3:
                            modificaCliente();
                            break;

                        case 4:
                            listarCliente();
                            break;

                        case 5:
                            listarPorNombreDeCliente();
                            break;

                        default:
                            System.out.println("La opcion no es correcta.");
                            break;
                    }

                } else if (numero == 2) {

                    switch (opcion) {
                        case 1:

                            altaPrestamo();
                            break;

                        case 2:
                            // bajaPrestamo();
                            break;

                        case 3:
                            // modificaPrestamo();
                            break;

                        case 4:
                            listarPrestamo();
                            break;

                        case 5:
                            // listarPorNombreDePrestamo();
                            break;

                        default:
                            System.out.println("La opcion no es correcta.");
                            break;
                    }

                }
                printEntrada();
                opcion = Teclado.nextInt();
                Teclado.nextLine();
            }

            // Hago un safe exit del manager
            ABMCliente.exit();

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Que lindo mi sistema,se rompio mi sistema");
            throw e;
        } finally {
            System.out.println("Saliendo del sistema, bye bye...");

        }

    }

    public void altaCliente() throws Exception {
        Cliente cliente = new Cliente();
        System.out.println("Ingrese el nombre:");
        cliente.setNombre(Teclado.nextLine());
        System.out.println("Ingrese el DNI:");
        cliente.setDni(Teclado.nextInt());
        Teclado.nextLine();
        System.out.println("Ingrese la domicilio:");
        cliente.setDomicilio(Teclado.nextLine());
        System.out.println("Ingrese el Domicilio alternativo(OPCIONAL):");

        // cliente.setDomicilioAlternativo(Teclado.nextLine());
        String domAlternativo = Teclado.nextLine();
        // cliente.setDomicilioAlternativo(Teclado.nextLine());
        // Teclado.nextLine();
        if (domAlternativo != null)
            cliente.setDomicilioAlternativo(domAlternativo);

        // Prestamo prestamo = new Prestamo();
        // BigDecimal importePrestamo = new BigDecimal(5000);
        // prestamo.setImporte(importePrestamo);
        // prestamo.setFecha(new Date());
        // prestamo.setFechaAlta(new Date());
        // prestamo.setCuotas(10);
        // prestamo.setCliente(cliente);

        ABMCliente.create(cliente);

        /*
         * Si concateno el OBJETO directamente, me trae todo lo que este en el metodo
         * toString() mi recomendacion es NO usarlo para imprimir cosas en pantallas, si
         * no para loguear info Lo mejor es usar:
         * System.out.println("Cliente generada con exito.  " + cliente.getClienteId);
         */
        System.out.println("Cliente generada con exito.  " + cliente.getNombre());
    }

    public void altaPrestamo() throws Exception {
        Prestamo prestamo = new Prestamo();
        System.out.println("Ingrese importe:");
        prestamo.setImporte(Teclado.nextBigDecimal());

        System.out.println("Introduzca la fecha con formato dd/mm/yyyy");
        prestamo.setFecha(new Date());

        // SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        // Date testDate = null;
        // String date = fecha;
        // try{
        // testDate = df.parse(date);
        // System.out.println("Ahora hemos creado un objeto date con la fecha indicada,
        // "+testDate);
        // } catch (Exception e){ System.out.println("invalid format");}

        System.out.println("Generando fecha alta:");
        prestamo.setFechaAlta(new Date());

        System.out.println("Cuotas del prestamo:");
        prestamo.setCuotas(Teclado.nextInt());
        Teclado.nextLine();

        System.out.println("Vinculacion del cliente, introduzca DNI del Cliente:");
        Cliente clienteID = ABMCliente.readByDNI(Teclado.nextLine());

        prestamo.setCliente(clienteID);

        ABMPrestamo.create(prestamo);

        System.out.println("Prestamo generada con exito con el mondo de:  " + prestamo.getImporte()
                + " y por el cliente: " + prestamo.getCliente().getNombre());
        System.out.println(prestamo);
    }

    public void bajaCliente() {
        System.out.println("Ingrese el nombre:");
        String nombre = Teclado.nextLine();
        System.out.println("Ingrese el ID de Cliente:");
        int id = Teclado.nextInt();
        Teclado.nextLine();
        Cliente clienteEncontrado = ABMCliente.read(id);

        if (clienteEncontrado == null) {
            System.out.println("Cliente no encontrado.");

        } else {

            try {
                ABMCliente.delete(clienteEncontrado);
                System.out
                        .println("El registro del cliente " + clienteEncontrado.getClienteId() + " ha sido eliminado.");
            } catch (Exception e) {
                System.out.println("Ocurrio un error al eliminar una cliente. Error: " + e.getCause());
            }
        }
    }

    public void bajaPorDNICliente() {
        System.out.println("Ingrese el nombre:");
        String nombre = Teclado.nextLine();
        System.out.println("Ingrese el DNI de Cliente:");
        String dni = Teclado.nextLine();
        Cliente clienteEncontrado = ABMCliente.readByDNI(dni);

        if (clienteEncontrado == null) {
            System.out.println("Cliente no encontrado.");

        } else {
            ABMCliente.delete(clienteEncontrado);
            System.out.println("El registro del DNI " + clienteEncontrado.getDni() + " ha sido eliminado.");
        }
    }

    public void modificaCliente() throws Exception {
        // System.out.println("Ingrese el nombre de la cliente a modificar:");
        // String n = Teclado.nextLine();

        System.out.println("Ingrese el ID de la cliente a modificar:");
        int id = Teclado.nextInt();
        Teclado.nextLine();
        Cliente clienteEncontrado = ABMCliente.read(id);

        if (clienteEncontrado != null) {

            // RECOMENDACION NO USAR toString(), esto solo es a nivel educativo.
            System.out.println(clienteEncontrado.toString() + " seleccionado para modificacion.");

            System.out.println(
                    "Elija qué dato de la cliente desea modificar: \n1: nombre, \n2: DNI, \n3: domicilio, \n4: domicilio alternativo");
            int selecper = Teclado.nextInt();

            switch (selecper) {
                case 1:
                    System.out.println("Ingrese el nuevo nombre:");
                    Teclado.nextLine();
                    clienteEncontrado.setNombre(Teclado.nextLine());

                    break;
                case 2:
                    System.out.println("Ingrese el nuevo DNI:");
                    Teclado.nextLine();
                    clienteEncontrado.setDni(Teclado.nextInt());
                    Teclado.nextLine();

                    break;
                case 3:
                    System.out.println("Ingrese el nuevo domicilio:");
                    Teclado.nextLine();
                    clienteEncontrado.setDomicilio(Teclado.nextLine());

                    break;
                case 4:
                    System.out.println("Ingrese el nuevo domicilio alternativo:");
                    Teclado.nextLine();
                    clienteEncontrado.setDomicilioAlternativo(Teclado.nextLine());

                    break;

                default:
                    break;
            }

            // Teclado.nextLine();

            ABMCliente.update(clienteEncontrado);

            System.out.println("El registro de " + clienteEncontrado.getNombre() + " ha sido modificado.");

        } else {
            System.out.println("Cliente no encontrado.");
        }
    }

    public void listarCliente() {

        List<Cliente> todos = ABMCliente.buscarTodos();
        for (Cliente c : todos) {
            mostrarCliente(c);
        }
    }

    public void listarPrestamo() {

        List<Prestamo> todos = ABMPrestamo.buscarTodos();
        for (Prestamo p : todos) {
            mostrarPrestamo(p);
        }
    }

    public void listarPorNombreDeCliente() {

        System.out.println("Ingrese el nombre:");
        String nombre = Teclado.nextLine();

        List<Cliente> clientes = ABMCliente.buscarPor(nombre);
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

    public void mostrarPrestamo(Prestamo prestamo) {

        System.out.print("Id: " + prestamo.getPrestamoId() + " Importe: " + prestamo.getImporte() + " Cliente nombre: "
                + prestamo.getCliente().getNombre() + " Fecha: " + prestamo.getFecha());

        // if (cliente.getDomicilioAlternativo() != null)
        // System.out.println(" Alternativo: " + cliente.getDomicilioAlternativo());
        // else
        // System.out.println();
    }

    public static int printEntrada() {

        System.out.println("=======================================");
        System.out.println("");
        System.out.println("1. Menu Cliente.");
        System.out.println("2. Menu Prestamo.");
        System.out.println("0. Para terminar.");
        System.out.println("");
        System.out.println("=======================================");

        int opcion = Teclado.nextInt();
        Teclado.nextLine();
        int resultado = 0;

        switch (opcion) {
            case 1:

                resultado = printOpciones();
                break;

            case 2:
                resultado = printOpcionesPrestamo();
                break;
  
            default:
                System.out.println("La opcion no es correcta.");
                printEntrada();
                break;
        }

        return resultado;
    }

    public static int printOpciones() {
        int numero = 1;
        System.out.println("=======================================");
        System.out.println("");
        System.out.println("1. Para agregar un cliente.");
        System.out.println("2. Para eliminar un cliente.");
        System.out.println("3. Para modificar un cliente.");
        System.out.println("4. Para ver el listado.");
        System.out.println("5. Buscar un cliente por nombre especifico(SQL Injection)).");
        System.out.println("0. Para terminar.");
        System.out.println("");
        System.out.println("=======================================");

        return numero;
    }

    public static int printOpcionesPrestamo() {
        int numero = 2;
        System.out.println("=======================================");
        System.out.println("");
        System.out.println("1. Para agregar un prestamo.");
        System.out.println("2. Para eliminar un prestamo.");
        System.out.println("3. Para modificar un prestamo.");
        System.out.println("4. Para ver el listado.");
        System.out.println("5. Buscar un prestamo por nombre especifico(SQL Injection)).");
        System.out.println("0. Para terminar.");
        System.out.println("");
        System.out.println("=======================================");
        return numero;
    }
}