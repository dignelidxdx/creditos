package ar.com.ada.creditos.services;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import ar.com.ada.creditos.entities.Cancelacion;
import ar.com.ada.creditos.entities.Prestamo;
import ar.com.ada.creditos.managers.CancelacionManager;
import ar.com.ada.creditos.managers.PrestamoManager;


public class CancelacionService {
    public static Scanner Teclado = new Scanner(System.in);

    // public CancelacionManager repo = new CancelacionManager();
    public CancelacionManager repo = new CancelacionManager();
    PrestamoManager repoPrestamo;

    public CancelacionService(CancelacionManager repo, PrestamoManager repoPrestamo) {
        this.repo = repo;
        this.repoPrestamo = repoPrestamo;
        repo.setup();
        repoPrestamo.setup();
    }

    

    public List<Cancelacion> getCancelaciones() {
        return null;
    }

    public void agregarCancelacion() {
        Cancelacion cancelacion = new Cancelacion();

        System.out.println("Ingrese importe:");
        cancelacion.setImporte(Teclado.nextInt());

        System.out.println("Introduzca la fecha con formato dd/mm/yyyy");
        cancelacion.setFechaCancelacion(new Date());

        System.out.println("Introduzca # de cuota cancelada:");
        cancelacion.setCuota(Teclado.nextInt());

        System.out.println("Vinculacion del prestamo, introduzca ID del prestamo:");
        Prestamo prestamoID = repoPrestamo.buscarPorIdPrestamo(Teclado.nextInt());

        while(prestamoID == null){
            System.out.println("No existe un prestamo con ese número de ID.");
            System.out.println("Introduzca ID del prestamo:");
            prestamoID = repoPrestamo.buscarPorIdPrestamo(Teclado.nextInt());
        }

        cancelacion.setPrestamo(prestamoID);

        repo.create(cancelacion);

        System.out.println("cancelacion generada con exito con el monto de:  " + cancelacion.getImporte()
                + " y el identificador del prestamo Id: " + cancelacion.getPrestamo().getPrestamoId());
        System.out.println(cancelacion);
    }

    public void listarCancelacion() {

        List<Cancelacion> todos = repo.buscarTodos();
        for (Cancelacion p : todos) {
            mostrarCancelacion(p);
        }
    }

    public void mostrarCancelacion(Cancelacion c) {

        System.out.print("Id: " + c.getCancelacionId() + " Importe: " + c.getImporte() + " Prestamo ID: "
                + c.getPrestamo().getPrestamoId() + " Fecha: " + c.getFechaCancelacion());

    }

    public void eliminarUnaCancelacion(int cancelacionId){
        Cancelacion cancelacion = repo.buscarPorIdDCancelacion(cancelacionId);
        if(repo.eliminacionLogica(cancelacionId)){
            repo.update(cancelacion);
            System.out.println("Se ha desactivado exitosamente el registro de cancelacion con id: " + cancelacionId);
        } else{
            System.out.println("Ocurrio un error al actualizarlo");
        }
    }  


    
}