package com.uniquindio.mantenimiento.model;

import java.util.Objects;

public final class Tecnico {

    private final String nombreCompleto;
    private final String numeroIdentificacion;
    private final String certificacion;
    private final int aniosExperiencia;

    public Tecnico(String nombreCompleto, String numeroIdentificacion,
                   String certificacion, int aniosExperiencia) {
        if (nombreCompleto == null || nombreCompleto.isBlank())
            throw new IllegalArgumentException("El nombre completo no puede ser nulo o vacío.");
        if (numeroIdentificacion == null || numeroIdentificacion.isBlank())
            throw new IllegalArgumentException("El número de identificación no puede ser nulo o vacío.");
        if (certificacion == null || certificacion.isBlank())
            throw new IllegalArgumentException("La certificación no puede ser nula o vacía.");
        if (aniosExperiencia < 0)
            throw new IllegalArgumentException("Los años de experiencia no pueden ser negativos.");

        this.nombreCompleto = nombreCompleto;
        this.numeroIdentificacion = numeroIdentificacion;
        this.certificacion = certificacion;
        this.aniosExperiencia = aniosExperiencia;
    }

    // ─── Getters únicamente (sin setters → inmutabilidad requerida) ───────────
    public String getNombreCompleto() { return nombreCompleto; }
    public String getNumeroIdentificacion() { return numeroIdentificacion; }
    public String getCertificacion() { return certificacion; }
    public int getAniosExperiencia() { return aniosExperiencia; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tecnico)) return false;
        Tecnico tecnico = (Tecnico) o;
        return Objects.equals(numeroIdentificacion, tecnico.numeroIdentificacion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numeroIdentificacion);
    }

    @Override
    public String toString() {
        return String.format("Técnico[id=%s, nombre=%s, cert=%s, exp=%d años]",
                numeroIdentificacion, nombreCompleto, certificacion, aniosExperiencia);
    }
}
