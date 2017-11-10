package com.google.prototipo.seminarioii.celiacos;

public class Review {


    private String nombre;
    private String texto;
    private float estrellas;

    public Review() {
    }

    public  Review (String nombre, String texto, float estrellas)
    {
        this.nombre = nombre;
        this.texto = texto;
        this.estrellas = estrellas;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public float getEstrellas() {
        return estrellas;
    }

    public void setEstrellas(float estrellas) {
        this.estrellas = estrellas;
    }
}
