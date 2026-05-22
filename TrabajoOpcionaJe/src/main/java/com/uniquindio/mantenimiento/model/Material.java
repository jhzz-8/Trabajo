package com.uniquindio.mantenimiento.model;
public final class Material {

    private final String nombre;
    private final int cantidad;
    private final boolean esReciclable;

    public Material(String nombre, int cantidad, boolean esReciclable) {
        if (nombre == null || nombre.isBlank())
            throw new IllegalArgumentException("debe de tener algo");
        if (cantidad <= 0)
            throw new IllegalArgumentException("no puede ser menor a 0");

        this.nombre = nombre;
        this.cantidad = cantidad;
        this.esReciclable = esReciclable;
    }

    public String getNombre() { return nombre; }
    public int getCantidad() { return cantidad; }
    public boolean isEsReciclable() { return esReciclable; }

    @Override
    public String toString() {
        return String.format("Material[nombre=%s, cantidad=%d, reciclable=%s]",
                nombre, cantidad, esReciclable ? "Sí" : "No");
    }
}
