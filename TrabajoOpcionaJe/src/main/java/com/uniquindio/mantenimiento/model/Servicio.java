package com.uniquindio.mantenimiento.model;
public abstract class Servicio {

    private final String id;
    private final String descripcion;
    private final int duracionEstimadaMinutos;
    private String estado;

    public Servicio(String id, String descripcion, int duracionEstimadaMinutos) {
        if (id == null || id.isBlank())
            throw new IllegalArgumentException("El ID del servicio no puede ser nulo o vacío.");
        if (descripcion == null || descripcion.isBlank())
            throw new IllegalArgumentException("La descripción no puede ser nula o vacía.");
        if (duracionEstimadaMinutos <= 0)
            throw new IllegalArgumentException("La duración debe ser mayor a cero.");

        this.id = id;
        this.descripcion = descripcion;
        this.duracionEstimadaMinutos = duracionEstimadaMinutos;
        this.estado = "programado";
    }

    public abstract double calcularCosto();

    public String getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getDuracionEstimadaMinutos() {
        return duracionEstimadaMinutos;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String nuevoEstado) {
        if (nuevoEstado == null || nuevoEstado.isBlank())
            throw new IllegalArgumentException("El estado no puede ser nulo o vacío.");
        this.estado = nuevoEstado;
    }

    @Override
    public String toString() {
        return String.format("[%s] ID: %s | Descripción: %s | Duración: %d min | Estado: %s | Costo: $%.0f",
                getClass().getSimpleName(), id, descripcion, duracionEstimadaMinutos, estado, calcularCosto());
    }
}
