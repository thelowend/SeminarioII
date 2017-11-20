package com.google.prototipo.seminarioii.celiacos.establecimiento.entities;


public class Establecimiento {

    private String longitud;
    private String latitud;
    private String nombre;
    private String idEstablecimiento;

    public Establecimiento(String longitud, String latitud, String nombre, String idEstablecimiento) {
        this.longitud = longitud;
        this.latitud = latitud;
        this.nombre = nombre;
        this.idEstablecimiento = idEstablecimiento;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdEstablecimiento() {
        return idEstablecimiento;
    }

    public void setIdEstablecimiento(String idEstablecimiento) {
        this.idEstablecimiento = idEstablecimiento;
    }
}
