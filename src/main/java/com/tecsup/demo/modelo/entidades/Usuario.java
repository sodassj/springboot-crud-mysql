package com.tecsup.demo.modelo.entidades;

import jakarta.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Email;

@Entity
@Table(name = "usuario")
public class Usuario {
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
    @Size(min = 2, max = 30)
    private String apellido;

    @Column(unique = true)
    @NotNull
    @Email
    private String email;

    @Column
    @NotNull
    @Size(min = 7, max = 15)
    private String telefono;

    @Column
    @NotNull
    @Size(min = 2, max = 100) // Aumentar el tamaño máximo para la dirección
    private String direccion;

    @Column
    @NotNull
    @Size(min = 2, max = 20) // Definido para el rol, puede ajustarse según sea necesario
    private String rol;

    @Column
    @NotNull
    @Size(min = 2, max = 20)
    private String estado;

    public Usuario() {
    }

    public Usuario(int id, String nombre, String apellido, String email, String telefono, String direccion, String rol, String estado) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.direccion = direccion;
        this.rol = rol;
        this.estado = estado;
    }

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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
            this.estado = estado;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", email='" + email + '\'' +
                ", telefono='" + telefono + '\'' +
                ", direccion='" + direccion + '\'' +
                ", rol='" + rol + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }
}
