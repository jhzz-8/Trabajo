package com.uniquindio.mantenimiento.model;

import java.util.Objects;

public final class Vivienda {

    private final String direccion;
    private final String tipoInmueble;
    private final int numeroResidentes;

    public Vivienda(String direccion, String tipoInmueble, int numeroResidentes) {
        if (direccion == null || direccion.isBlank())
            throw new IllegalArgumentException("La dirección no puede ser nula o vacía.");
        if (tipoInmueble == null || tipoInmueble.isBlank())
            throw new IllegalArgumentException("El tipo de inmueble no puede ser nulo o vacío.");
        if (numeroResidentes < 0)
            throw new IllegalArgumentException("El número de residentes no puede ser negativo.");

        this.direccion = direccion;
        this.tipoInmueble = tipoInmueble;
        this.numeroResidentes = numeroResidentes;
    }

    // ─── Getters únicamente (sin setters → inmutabilidad requerida) ───────────
    public String getDireccion() { return direccion; }
    public String getTipoInmueble() { return tipoInmueble; }
    public int getNumeroResidentes() { return numeroResidentes; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vivienda)) return false;
        Vivienda vivienda = (Vivienda) o;
        return Objects.equals(direccion, vivienda.direccion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(direccion);
    }

    @Override
    public String toString() {
        return String.format("Vivienda[dirección=%s, tipo=%s, residentes=%d]",
                direccion, tipoInmueble, numeroResidentes);
    }
}
