package com.example.myrecyclerviewexample.model;

public class Usuario {
    private int imagen;
    private String nombre;
    private String apellidos;
    private String oficio;

    public Usuario(int imagen, String nombre, String apellidos, String oficio) {
        this.imagen = imagen;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.oficio = oficio;
    }

    public int getImagen() {
        return imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getOficio() {
        return oficio;
    }
}
