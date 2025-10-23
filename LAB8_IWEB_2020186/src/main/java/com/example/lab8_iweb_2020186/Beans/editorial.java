package com.example.lab8_iweb_2020186.Beans;

public class editorial {
    private int id;
    private String nombre;

    public editorial() {
    }

    public editorial(int id, String nombre) {
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

