package ar.com.ada.creditos.entities.reportes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ReporteDePrestamos {
    
    @Id
    @Column(name="prestamo_id")
    private int prestamoId;
    private int cantidadDePrestamos;
    private int totalDeCash;

    public int getPrestamoId() {
        return prestamoId;
    }

    public void setPrestamoId(int prestamoId) {
        this.prestamoId = prestamoId;
    }

    public int getCantidadDePrestamos() {
        return cantidadDePrestamos;
    }

    public void setCantidadDePrestamos(int cantidadDePrestamos) {
        this.cantidadDePrestamos = cantidadDePrestamos;
    }

    public int getTotalDeCash() {
        return totalDeCash;
    }

    public void setTotalDeCash(int totalDeCash) {
        this.totalDeCash = totalDeCash;
    }
}