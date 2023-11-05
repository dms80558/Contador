package com.example.contador.practicas;

public class Personajes {
    public final String nombre;
    public final int imagen;

    public Personajes(String nombre, int imagen) {
        this.nombre = nombre;
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public int getImagen() {
        return imagen;
    }
}
