package com.uniquindio.mantenimiento.service;

import com.uniquindio.mantenimiento.model.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
public class GestorMantenimiento {

    private final List<Tecnico> tecnicos;
    private final List<Vivienda> viviendas;
    private final List<OrdenServicio> ordenes;

    public GestorMantenimiento() {
        this.tecnicos  = new ArrayList<>();
        this.viviendas = new ArrayList<>();
        this.ordenes   = new ArrayList<>();
    }


    public double calcularCostoServicio(Servicio servicio) {
        Objects.requireNonNull(servicio, "El servicio no puede ser nulo.");
        return servicio.calcularCosto();
    }

    public void registrarTecnico(Tecnico tecnico) {
        Objects.requireNonNull(tecnico, "El técnico no puede ser nulo.");
        if (tecnicos.contains(tecnico)) {
            throw new IllegalArgumentException(
                    "El técnico con ID '" + tecnico.getNumeroIdentificacion() + "' ya está registrado.");
        }
        tecnicos.add(tecnico);
    }

    public void registrarVivienda(Vivienda vivienda) {
        Objects.requireNonNull(vivienda, "La vivienda no puede ser nula.");
        if (viviendas.contains(vivienda)) {
            throw new IllegalArgumentException(
                    "La vivienda con dirección '" + vivienda.getDireccion() + "' ya está registrada.");
        }
        viviendas.add(vivienda);
    }

    public void registrarOrden(OrdenServicio orden) {
        Objects.requireNonNull(orden, "La orden no puede ser nula.");
        if (!tecnicos.contains(orden.getTecnico())) {
            throw new IllegalArgumentException("El técnico de la orden no está registrado en el sistema.");
        }
        if (!viviendas.contains(orden.getVivienda())) {
            throw new IllegalArgumentException("La vivienda de la orden no está registrada en el sistema.");
        }
        ordenes.add(orden);
    }

    public List<Servicio> obtenerServiciosPorVivienda(String direccion) {
        if (direccion == null || direccion.isBlank())
            throw new IllegalArgumentException("La dirección no puede ser nula o vacía.");

        List<Servicio> resultado = ordenes.stream()
                .filter(o -> o.getVivienda().getDireccion().equalsIgnoreCase(direccion))
                .map(OrdenServicio::getServicio)
                .collect(Collectors.toList());

        return Collections.unmodifiableList(resultado);
    }

    public void actualizarEstadoServicio(String idServicio, String nuevoEstado) {
        if (idServicio == null || idServicio.isBlank())
            throw new IllegalArgumentException("El ID del servicio no puede ser nulo o vacío.");
        if (nuevoEstado == null || nuevoEstado.isBlank())
            throw new IllegalArgumentException("El nuevo estado no puede ser nulo o vacío.");

        Servicio servicio = ordenes.stream()
                .map(OrdenServicio::getServicio)
                .filter(s -> s.getId().equalsIgnoreCase(idServicio))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "No se encontró un servicio con ID: " + idServicio));

        servicio.setEstado(nuevoEstado);
    }

    public double calcularCostoTotalVivienda(String direccion) {
        if (direccion == null || direccion.isBlank())
            throw new IllegalArgumentException("La dirección no puede ser nula o vacía.");

        return ordenes.stream()
                .filter(o -> o.getVivienda().getDireccion().equalsIgnoreCase(direccion))
                .mapToDouble(o -> o.getServicio().calcularCosto())
                .sum();
    }

    public List<Tecnico> getTecnicos() {
        return Collections.unmodifiableList(tecnicos);
    }

    public List<Vivienda> getViviendas() {
        return Collections.unmodifiableList(viviendas);
    }

    public List<OrdenServicio> getOrdenes() {
        return Collections.unmodifiableList(ordenes);
    }
}
