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
    @Column(name="estado_id")
    private int estadoId;

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

    public EstadoCancelacionEnum getEstadoId() {
        return EstadoCancelacionEnum.parse(this.estadoId);
    }

    public void setEstadoId(EstadoCancelacionEnum estadoId) {
        this.estadoId = estadoId.getValue();
    }

    public enum EstadoCancelacionEnum {
        ACTIVA(1), PARCIALMENTE_CONFIRMADO(2), ANULADA(99);

        private final int value; // NOTE: Enum constructor tiene que estar en privado

        private EstadoCancelacionEnum(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public static EstadoCancelacionEnum parse(int id) {
            EstadoCancelacionEnum status = null;
            // Default
            for (EstadoCancelacionEnum item : EstadoCancelacionEnum.values()) {
                if (item.getValue() == id) {
                    status = item;
                    break;
                }
            }
            return status;
        }
    }

}