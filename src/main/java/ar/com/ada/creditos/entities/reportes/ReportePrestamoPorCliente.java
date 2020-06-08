package ar.com.ada.creditos.entities.reportes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class ReportePrestamoPorCliente {
    
    @Id
    @Column(name = "idcliente")
    private int clienteId;
    private int cantidadPrestamos;
    private int importeMaximo;
    private int totalPrestamo;
    private String nombre;

    public int getCantidadPrestamos() {
        return cantidadPrestamos;
    }

    public void setCantidadPrestamos(int cantidadPrestamos) {
        this.cantidadPrestamos = cantidadPrestamos;
    }

    public int getImporteMaximo() {
        return importeMaximo;
    }

    public void setImporteMaximo(int importeMaximo) {
        this.importeMaximo = importeMaximo;
    }

    public int getTotalPrestamo() {
        return totalPrestamo;
    }

    public void setTotalPrestamo(int totalPrestamo) {
        this.totalPrestamo = totalPrestamo;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}