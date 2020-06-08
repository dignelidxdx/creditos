package ar.com.ada.creditos.entities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import ar.com.ada.creditos.entities.*;

// @Entity  Indica que esa clase actuara como entidad
// @Table Indica a que tabla va a persistirse ese objeto.
// "@" son anotaciones

@Entity
@Table(name="prestamo")
public class Prestamo {

    @Id // que es una PK.
    @Column(name="prestamo_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Autoincremental
    private int prestamoId;    
    private BigDecimal importe;
    private Date fecha;
    @Column(name="cuota")
    private int cuotas;
    @Column(name="fecha_alta")
    private Date fechaAlta;

    @ManyToOne
    @JoinColumn(name = "idcliente", referencedColumnName = "idcliente")
    private Cliente cliente;

    // @OneToMany(mappedBy = "prestamo", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    // private List<Cancelacion> cancelaciones = new ArrayList<>();

    public int getPrestamoId() {
        return prestamoId;
    }

    public void setPrestamoId(int prestamoId) {
        this.prestamoId = prestamoId;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getCuotas() {
        return cuotas;
    }

    public void setCuotas(int cuotas) {
        this.cuotas = cuotas;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }


    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
        this.cliente.getPrestamos().add(this);
    }


    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "[prestamo= " + this.prestamoId + "]";
    }

    // public List<Cancelacion> getCancelaciones() {
    //     return cancelaciones;
    // }

    // public void setCancelaciones(List<Cancelacion> cancelaciones) {
    //     this.cancelaciones = cancelaciones;
    // }
}