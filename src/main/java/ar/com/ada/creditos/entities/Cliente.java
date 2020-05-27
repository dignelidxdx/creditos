package ar.com.ada.creditos.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.NaturalId;

import ar.com.ada.creditos.excepciones.*;

@Entity
@Table(name = "cliente")
public class Cliente {
    @Id
    @Column(name = "idcliente")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int clienteId;
    private String nombre;
    @NaturalId 
    private int dni;
    private String domicilio;
    @Column(name = "domicilio_alternativo")
    private String domicilioAlternativo;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Prestamo> prestamos = new ArrayList<>();

    public Cliente(String nombre) {
        this.nombre = nombre;
        // constructor
    }

    public Cliente() {
        // constructor vacio
    }

    public int getClienteId() {
        return clienteId;
        // getter
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
        // setter
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDni(int dni) throws ClienteDNIException {

        if (dni < 0) {
            // no se ejecuta nada mas despues del throw
            throw new ClienteDNIException(this, "ocurrio un error con el DNI");

        }
        this.dni = dni;
    }

    public int getDni() {
        return dni;
    }

    @Override
    public String toString() {
        return "Cliente [dni=" + getDni() + ", nombre=" + nombre + "]";
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getDomicilioAlternativo() {
        return domicilioAlternativo;
    }

    public void setDomicilioAlternativo(String domicilioAlternativo) {
        this.domicilioAlternativo = domicilioAlternativo;
    }

    public List<Prestamo> getPrestamos() {
        return prestamos;
    }

    public void setPrestamos(List<Prestamo> prestamos) {
        this.prestamos = prestamos;
    }

}