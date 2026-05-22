package com.uniquindio.mantenimiento.model;

public class ServicioFontaneria extends Servicio {

    private static final double TARIFA_BASE = 40_000.0;
    private static final double CARGO_CORTE_AGUA = 10_000.0;

    public enum TipoMaterial { PVC, COBRE }

    private final boolean requiereCorteAgua;
    private final TipoMaterial tipoMaterial;

    public ServicioFontaneria(String id, String descripcion, int duracionEstimadaMinutos,
                               boolean requiereCorteAgua, TipoMaterial tipoMaterial) {
        super(id, descripcion, duracionEstimadaMinutos);
        if (tipoMaterial == null)
            throw new IllegalArgumentException("El tipo de material no puede ser nulo.");
        this.requiereCorteAgua = requiereCorteAgua;
        this.tipoMaterial = tipoMaterial;
    }

    @Override
    public double calcularCosto() {
        double costo = TARIFA_BASE;
        if (requiereCorteAgua) {
            costo += CARGO_CORTE_AGUA;
        }
        return costo;
    }

    public boolean isRequiereCorteAgua() { return requiereCorteAgua; }
    public TipoMaterial getTipoMaterial() { return tipoMaterial; }
}
