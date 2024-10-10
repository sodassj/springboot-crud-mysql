package com.tecsup.demo.modelo.entidades;

import jakarta.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Email;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    @NotNull
    @Size(min = 2, max = 30)
    private String nombre;

    @Column
    @NotNull
    @Size(min = 2, max = 100) // Aumentado el tamaño máximo para dirección
    private String direccion;

    @Column
    @NotNull
    @Size(min = 7, max = 15)
    private String telefono;

    @Column(unique = true)
    @NotNull
    @Email
    private String email;

    @Column
    @NotNull
    @Temporal(TemporalType.DATE) // Especificar que es una fecha
    @DateTimeFormat(pattern = "yyyy-MM-dd") // Especificar el formato de fecha
    private Date fechaRegistro;

    @Column
    @NotNull
    @Size(max = 255) // Asegúrate de definir un tamaño máximo para el campo
    private String autosInteres; // Cambiado a String

    // Constructor por defecto
    public Cliente() {
    }

    // Constructor completo
    public Cliente(int id, String nombre, String direccion, String telefono, String email, Date fechaRegistro, String autosInteres) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.fechaRegistro = fechaRegistro;
        this.autosInteres = autosInteres;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getAutosInteres() {
        return autosInteres;
    }

    public void setAutosInteres(String autosInteres) {
        this.autosInteres = autosInteres;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' +
                ", fechaRegistro=" + fechaRegistro +
                ", autosInteres='" + autosInteres + '\'' +
                '}';
    }
}
