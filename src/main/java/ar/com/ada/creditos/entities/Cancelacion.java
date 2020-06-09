package ar.com.ada.creditos.entities;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="cancelacion")
public class Cancelacion {

    @Id // que es una PK.
    @Column(name="cancelacion_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Autoincremental
    private int cancelacionId;
    @Column(name="fecha_cancelacion")
    private Date fechaCancelacion;
    private int importe;
    private int cuota;
    private int status;

    @ManyToOne
    @JoinColumn(name = "prestamo_id", referencedColumnName = "prestamo_id")
    private Prestamo prestamo;

    public int getCancelacionId() {
        return cancelacionId;
    }

    public void setCancelacionId(int cancelacionId) {
        this.cancelacionId = cancelacionId;
    }

    public Date getFechaCancelacion() {
        return fechaCancelacion;
    }

    public void setFechaCancelacion(Date fechaCancelacion) {
        this.fechaCancelacion = fechaCancelacion;
    }

    public int getImporte() {
        return importe;
    }

    public void setImporte(int importe) {
        this.importe = importe;
    }

    public int getCuota() {
        return cuota;
    }

    public void setCuota(int cuota) {
        this.cuota = cuota;
    }

    public Prestamo getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(Prestamo prestamo) {
        this.prestamo = prestamo;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}