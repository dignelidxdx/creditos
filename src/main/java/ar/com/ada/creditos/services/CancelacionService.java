package ar.com.ada.creditos.services;

import java.util.List;
import ar.com.ada.creditos.entities.Cancelacion;
import ar.com.ada.creditos.managers.CancelacionManager;

public class CancelacionService {

    // public CancelacionManager repo = new CancelacionManager();
    CancelacionManager repo;

    public List<Cancelacion> getCancelaciones() {
        return null;
    }

    public Cancelacion agregarCancelacion() {
        return null;
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