package ar.com.ada.creditos.entities.reportes;

import javax.persistence.Entity;
import javax.persistence.Id;

import ar.com.ada.creditos.entities.Cliente;
import ar.com.ada.creditos.entities.Prestamo;

@Entity
public class ReportePrestamoPorCliente {
    
    @Id
    private int cantidadPrestamos;
    private int importeMaximo;
    private int totalPrestamo;
    private Cliente cliente;
    private Prestamo prestamo;

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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Prestamo getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(Prestamo prestamo) {
        this.prestamo = prestamo;
    }
}