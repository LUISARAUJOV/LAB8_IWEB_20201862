package com.example.lab8_iweb_2020186.Beans;

public class genero {
    private int id;
    private String nombre;

    public genero() {
    }

    public genero(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
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
}

