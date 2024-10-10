package com.tecsup.demo.modelo.entidades;

import jakarta.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "cochesemi")
public class Cochesemi {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    @Size(min = 10, max = 200)
    private String descripcion; // Descripción del coche semi nuevo

    @Column
    @NotNull
    @Size(min = 2, max = 30)
    private String marca;

    @Column
    @NotNull
    @Size(min = 2, max = 30)
    private String modelo;

    @Column
    @NotNull
    @Size(min = 3, max = 30)
    private String tipo; // Tipo de coche semi nuevo (SUV, sedán, etc.)

    @Column
    @NotNull
    @Size(min = 3, max = 50)
    private String localizacion; // Localización del coche semi nuevo

    @Column
    @NotNull
    @Positive // Asegura que el precio sea positivo
    @DecimalMin(value = "0.01", message = "El precio debe ser mayor a cero") // Precio mínimo
    private double precio; // Precio del coche semi nuevo

    public Cochesemi() {
    }

    public Cochesemi(int id, String descripcion, String marca, String modelo, String tipo,
                     String localizacion, double precio) {
        this.id = id;
        this.descripcion = descripcion;
        this.marca = marca;
        this.modelo = modelo;
        this.tipo = tipo;
        this.localizacion = localizacion;
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Cochesemi{" +
                "id=" + id +
                ", descripcion='" + descripcion + '\'' +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", tipo='" + tipo + '\'' +
                ", localizacion='" + localizacion + '\'' +
                ", precio=" + precio +
                '}';
    }
}
