package ar.com.ada.creditos.entities.reportes;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ReporteDePrestamos {
    
    @Id
    private int cantidad;
    private int total;

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}