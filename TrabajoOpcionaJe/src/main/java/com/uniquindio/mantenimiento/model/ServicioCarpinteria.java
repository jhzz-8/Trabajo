package com.uniquindio.mantenimiento.model;

public class ServicioCarpinteria extends Servicio {

    private static final double TARIFA_BASE = 60_000.0;
    private static final double CARGO_HERRAMIENTA_PESADA = 15_000.0;

    private final boolean esEstructural;
    private final boolean requiereHerramientaPesada;

    public ServicioCarpinteria(String id, String descripcion, int duracionEstimadaMinutos,
                                boolean esEstructural, boolean requiereHerramientaPesada) {
        super(id, descripcion, duracionEstimadaMinutos);
        this.esEstructural = esEstructural;
        this.requiereHerramientaPesada = requiereHerramientaPesada;
    }

    @Override
    public double calcularCosto() {
        double costo = TARIFA_BASE;
        if (requiereHerramientaPesada) {
            costo += CARGO_HERRAMIENTA_PESADA;
        }
        return costo;
    }

    public boolean isEsEstructural() { return esEstructural; }
    public boolean isRequiereHerramientaPesada() { return requiereHerramientaPesada; }
}
