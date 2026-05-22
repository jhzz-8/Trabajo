package com.uniquindio.mantenimiento.model;

public class ServicioElectricidad extends Servicio {

    private static final double TARIFA_BASE = 50_000.0;
    private static final double CARGO_ALTURA = 20_000.0;

    private final boolean requiereTrabajoenAlturas;
    private final int cantidadCircuitos;

    public ServicioElectricidad(String id, String descripcion, int duracionEstimadaMinutos,
                                 boolean requiereTrabajoenAlturas, int cantidadCircuitos) {
        super(id, descripcion, duracionEstimadaMinutos);
        if (cantidadCircuitos < 0)
            throw new IllegalArgumentException("La cantidad de circuitos no puede ser negativa.");
        this.requiereTrabajoenAlturas = requiereTrabajoenAlturas;
        this.cantidadCircuitos = cantidadCircuitos;
    }


    @Override
    public double calcularCosto() {
        double costo = TARIFA_BASE;
        if (requiereTrabajoenAlturas) {
            costo += CARGO_ALTURA;
        }
        return costo;
    }

    public boolean isRequiereTrabajoenAlturas() { return requiereTrabajoenAlturas; }
    public int getCantidadCircuitos() { return cantidadCircuitos; }
}
