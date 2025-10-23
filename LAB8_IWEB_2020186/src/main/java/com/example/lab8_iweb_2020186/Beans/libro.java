package com.example.lab8_iweb_2020186.Beans;

import java.sql.Date;

public class libro {
    private int id;
    private String titulo;
    private String autor;
    private editorial editorial;
    private genero genero;
    private double precio;
    private Date fechaPublicacion;

    public libro() {
    }

    public libro(int id, String titulo, String autor, editorial editorial, genero genero, double precio, Date fechaPublicacion) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.genero = genero;
        this.precio = precio;
        this.fechaPublicacion = fechaPublicacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public editorial getEditorial() {
        return editorial;
    }

    public void setEditorial(editorial editorial) {
        this.editorial = editorial;
    }

    public genero getGenero() {
        return genero;
    }

    public void setGenero(genero genero) {
        this.genero = genero;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }
}
