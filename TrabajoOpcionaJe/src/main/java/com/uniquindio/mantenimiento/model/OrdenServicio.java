package com.uniquindio.mantenimiento.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class OrdenServicio {

    private final String idOrden;
    private final Tecnico tecnico;
    private final Vivienda vivienda;
    private final Servicio servicio;
    private final List<Material> materiales;

    public OrdenServicio(String idOrden, Tecnico tecnico, Vivienda vivienda, Servicio servicio) {
        if (idOrden == null || idOrden.isBlank())
            throw new IllegalArgumentException("El ID de orden no puede ser nulo o vacío.");
        Objects.requireNonNull(tecnico, "El técnico no puede ser nulo.");
        Objects.requireNonNull(vivienda, "La vivienda no puede ser nula.");
        Objects.requireNonNull(servicio, "El servicio no puede ser nulo.");

        this.idOrden = idOrden;
        this.tecnico = tecnico;
        this.vivienda = vivienda;
        this.servicio = servicio;
        this.materiales = new ArrayList<>();
    }

    public void agregarMaterial(Material material) {
        Objects.requireNonNull(material, "El material no puede ser nulo.");
        materiales.add(material);
    }

    public String getIdOrden() {
        return idOrden;
    }

    public Tecnico getTecnico() {
        return tecnico;
    }

    public Vivienda getVivienda() {
        return vivienda;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public List<Material> getMateriales() {
        return Collections.unmodifiableList(materiales);
    }

    @Override
    public String toString() {
        return String.format("OrdenServicio[id=%s, vivienda=%s, técnico=%s, servicio=%s]",
                idOrden, vivienda.getDireccion(),
                tecnico.getNombreCompleto(), servicio.getDescripcion());
    }
}
