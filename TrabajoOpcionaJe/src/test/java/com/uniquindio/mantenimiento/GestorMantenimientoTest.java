package com.uniquindio.mantenimiento;

import com.uniquindio.mantenimiento.model.*;
import com.uniquindio.mantenimiento.service.GestorMantenimiento;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/*
 * ╔═════════════════════════════════════════════════════════════════════════╗
 * ║                        TABLA                                            ║
 * ╠════╦══════════════════════════╦══════════════════╦══════════════════════╣
 * ║ #  ║ Método bajo prueba       ║ Entrada          ║ Resultado esperado   ║
 * ╠════╬══════════════════════════╬══════════════════╬══════════════════════╣
 * ║ 1  ║ calcularCostoServicio    ║ Electricidad sin ║ $50.000              ║
 * ║    ║                          ║ alturas          ║                      ║
 * ╠════╬══════════════════════════╬══════════════════╬══════════════════════╣
 * ║ 2  ║ calcularCostoServicio    ║ Electricidad con ║ $70.000              ║
 * ║    ║                          ║ alturas          ║                      ║
 * ╠════╬══════════════════════════╬══════════════════╬══════════════════════╣
 * ║ 3  ║ calcularCostoServicio    ║ Fontanería sin   ║ $40.000              ║
 * ║    ║                          ║ corte de agua    ║                      ║
 * ╠════╬══════════════════════════╬══════════════════╬══════════════════════╣
 * ║ 4  ║ calcularCostoServicio    ║ Fontanería con   ║ $50.000              ║
 * ║    ║                          ║ corte de agua    ║                      ║
 * ╠════╬══════════════════════════╬══════════════════╬══════════════════════╣
 * ║ 5  ║ calcularCostoServicio    ║ Carpintería sin  ║ $60.000              ║
 * ║    ║                          ║ herr. pesada     ║                      ║
 * ╠════╬══════════════════════════╬══════════════════╬══════════════════════╣
 * ║ 6  ║ calcularCostoServicio    ║ Carpintería con  ║ $75.000              ║
 * ║    ║                          ║ herr. pesada     ║                      ║
 * ╠════╬══════════════════════════╬══════════════════╬══════════════════════╣
 * ║ 7  ║ calcularCostoServicio    ║ servicio nulo    ║ NullPointerException ║
 * ╠════╬══════════════════════════╬══════════════════╬══════════════════════╣
 * ║ 8  ║ registrarTecnico         ║ técnico válido   ║ lista contiene 1     ║
 * ╠════╬══════════════════════════╬══════════════════╬══════════════════════╣
 * ║ 9  ║ registrarTecnico         ║ técnico duplicado║ IllegalArgument      ║
 * ╠════╬══════════════════════════╬══════════════════╬══════════════════════╣
 * ║ 10 ║ registrarTecnico         ║ técnico nulo     ║ NullPointerException ║
 * ╠════╬══════════════════════════╬══════════════════╬══════════════════════╣
 * ║ 11 ║ registrarTecnico         ║ ID vacío         ║ IllegalArgument      ║
 * ╠════╬══════════════════════════╬══════════════════╬══════════════════════╣
 * ║ 12 ║ obtenerServiciosPor      ║ dirección con 2  ║ lista de 2 servicios ║
 * ║    ║ Vivienda                 ║ órdenes          ║                      ║
 * ╠════╬══════════════════════════╬══════════════════╬══════════════════════╣
 * ║ 13 ║ obtenerServiciosPor      ║ dirección sin    ║ lista vacía          ║
 * ║    ║ Vivienda                 ║ órdenes          ║                      ║
 * ╠════╬══════════════════════════╬══════════════════╬══════════════════════╣
 * ║ 14 ║ obtenerServiciosPor      ║ dirección nula   ║ IllegalArgument      ║
 * ║    ║ Vivienda                 ║                  ║                      ║
 * ╠════╬══════════════════════════╬══════════════════╬══════════════════════╣
 * ║ 15 ║ actualizarEstadoServicio ║ ID válido        ║ estado = "en_progreso"║
 * ╠════╬══════════════════════════╬══════════════════╬══════════════════════╣
 * ║ 16 ║ actualizarEstadoServicio ║ ID válido        ║ estado = "completado" ║
 * ╠════╬══════════════════════════╬══════════════════╬══════════════════════╣
 * ║ 17 ║ actualizarEstadoServicio ║ ID inexistente   ║ IllegalArgument      ║
 * ╠════╬══════════════════════════╬══════════════════╬══════════════════════╣
 * ║ 18 ║ actualizarEstadoServicio ║ estado nulo      ║ IllegalArgument      ║
 * ╠════╬══════════════════════════╬══════════════════╬══════════════════════╣
 * ║ 19 ║ calcularCostoTotalViv.   ║ vivienda con 3   ║ suma correcta        ║
 * ║    ║                          ║ servicios        ║                      ║
 * ╠════╬══════════════════════════╬══════════════════╬══════════════════════╣
 * ║ 20 ║ calcularCostoTotalViv.   ║ vivienda sin     ║ 0.0                  ║
 * ║    ║                          ║ órdenes          ║                      ║
 * ╠════╬══════════════════════════╬══════════════════╬══════════════════════╣
 * ║ 21 ║ calcularCostoTotalViv.   ║ dirección nula   ║ IllegalArgument      ║
 * ╚════╩══════════════════════════╩══════════════════╩══════════════════════╝
 */
@DisplayName("Pruebas del sistema de mantenimiento UniQuindio")
class GestorMantenimientoTest {

    private GestorMantenimiento gestor;
    private Tecnico tecnico1;
    private Tecnico tecnico2;
    private Vivienda vivienda1;
    private Vivienda vivienda2;

    @BeforeEach
    void setUp() {
        gestor = new GestorMantenimiento();

        tecnico1 = new Tecnico("Carlos Pérez", "CC-001", "Certificado RETIE", 5);
        tecnico2 = new Tecnico("Ana Gómez",   "CC-002", "Certificado ICONTEC", 3);

        vivienda1 = new Vivienda("Calle 10 # 5-20", "Apartamento", 3);
        vivienda2 = new Vivienda("Carrera 8 # 12-40", "Casa", 5);

        gestor.registrarTecnico(tecnico1);
        gestor.registrarTecnico(tecnico2);
        gestor.registrarVivienda(vivienda1);
        gestor.registrarVivienda(vivienda2);
    }

    // ══════════════════════════════════════════════════════════════════════════
    // PRUEBAS — Método a) calcularCostoServicio
    // ══════════════════════════════════════════════════════════════════════════

    @Test
    @DisplayName("Prueba 1 — Electricidad sin alturas → $50.000")
    void calcularCosto_electricidadSinAlturas_retornaTarifaBase() {
        Servicio servicio = new ServicioElectricidad("E01", "Revisión panel", 60, false, 3);
        assertEquals(50_000.0, gestor.calcularCostoServicio(servicio),
                "Sin trabajo en alturas el costo debe ser $50.000");
    }

    @Test
    @DisplayName("Prueba 2 — Electricidad con alturas → $70.000")
    void calcularCosto_electricidadConAlturas_agregaCargoAlturas() {
        Servicio servicio = new ServicioElectricidad("E02", "Instalación en techo", 90, true, 2);
        assertEquals(70_000.0, gestor.calcularCostoServicio(servicio),
                "Con trabajo en alturas el costo debe ser $70.000");
    }

    @Test
    @DisplayName("Prueba 3 — Fontanería sin corte de agua → $40.000")
    void calcularCosto_fontaneriaSinCorteAgua_retornaTarifaBase() {
        Servicio servicio = new ServicioFontaneria("F01", "Revisión tuberías", 45,
                false, ServicioFontaneria.TipoMaterial.PVC);
        assertEquals(40_000.0, gestor.calcularCostoServicio(servicio));
    }

    @Test
    @DisplayName("Prueba 4 — Fontanería con corte de agua → $50.000")
    void calcularCosto_fontaneriaConCorteAgua_agregaCargo() {
        Servicio servicio = new ServicioFontaneria("F02", "Cambio de tubo principal", 120,
                true, ServicioFontaneria.TipoMaterial.COBRE);
        assertEquals(50_000.0, gestor.calcularCostoServicio(servicio));
    }

    @Test
    @DisplayName("Prueba 5 — Carpintería sin herramienta pesada → $60.000")
    void calcularCosto_carpinteriaSinHerrPesada_retornaTarifaBase() {
        Servicio servicio = new ServicioCarpinteria("C01", "Reparación puerta", 60, false, false);
        assertEquals(60_000.0, gestor.calcularCostoServicio(servicio));
    }

    @Test
    @DisplayName("Prueba 6 — Carpintería con herramienta pesada → $75.000")
    void calcularCosto_carpinteriaConHerrPesada_agregaCargo() {
        Servicio servicio = new ServicioCarpinteria("C02", "Trabajo estructural", 180, true, true);
        assertEquals(75_000.0, gestor.calcularCostoServicio(servicio));
    }

    @Test
    @DisplayName("Prueba 7 — Servicio nulo → NullPointerException")
    void calcularCosto_servicioNulo_lanzaExcepcion() {
        assertThrows(NullPointerException.class,
                () -> gestor.calcularCostoServicio(null));
    }

    // ══════════════════════════════════════════════════════════════════════════
    // PRUEBAS — Método b) registrarTecnico
    // ══════════════════════════════════════════════════════════════════════════

    @Test
    @DisplayName("Prueba 8 — Registro de técnico válido → aparece en la lista")
    void registrarTecnico_tecnicoNuevo_seAgregaALaLista() {
        Tecnico tecnico3 = new Tecnico("Luis Torres", "CC-003", "Cert. Gas", 7);
        gestor.registrarTecnico(tecnico3);
        assertTrue(gestor.getTecnicos().contains(tecnico3),
                "El técnico recién registrado debe estar en la lista");
    }

    @Test
    @DisplayName("Prueba 9 — Técnico duplicado → IllegalArgumentException")
    void registrarTecnico_tecnicoDuplicado_lanzaExcepcion() {
        // tecnico1 ya fue registrado en setUp
        Tecnico duplicado = new Tecnico("Carlos P.", "CC-001", "Otro cert.", 2);
        assertThrows(IllegalArgumentException.class,
                () -> gestor.registrarTecnico(duplicado),
                "No debe permitir técnicos con el mismo número de identificación");
    }

    @Test
    @DisplayName("Prueba 10 — Técnico nulo → NullPointerException")
    void registrarTecnico_nulo_lanzaExcepcion() {
        assertThrows(NullPointerException.class,
                () -> gestor.registrarTecnico(null));
    }

    @Test
    @DisplayName("Prueba 11 — Crear técnico con ID vacío → IllegalArgumentException")
    void crearTecnico_idVacio_lanzaExcepcion() {
        assertThrows(IllegalArgumentException.class,
                () -> new Tecnico("Juan Díaz", "", "Cert.", 1));
    }

    // ══════════════════════════════════════════════════════════════════════════
    // PRUEBAS — Método c) obtenerServiciosPorVivienda
    // ══════════════════════════════════════════════════════════════════════════

    @Test
    @DisplayName("Prueba 12 — Vivienda con 2 órdenes → retorna 2 servicios")
    void obtenerServiciosPorVivienda_dosOrdenes_retornaLista() {
        ServicioElectricidad sElec = new ServicioElectricidad("E10", "Panel", 60, false, 2);
        ServicioFontaneria sFon = new ServicioFontaneria("F10", "Tubería", 45,
                false, ServicioFontaneria.TipoMaterial.PVC);

        OrdenServicio o1 = new OrdenServicio("ORD-001", tecnico1, vivienda1, sElec);
        OrdenServicio o2 = new OrdenServicio("ORD-002", tecnico2, vivienda1, sFon);
        gestor.registrarOrden(o1);
        gestor.registrarOrden(o2);

        List<Servicio> servicios = gestor.obtenerServiciosPorVivienda("Calle 10 # 5-20");
        assertEquals(2, servicios.size(),
                "Deben retornarse exactamente 2 servicios para la vivienda");
    }

    @Test
    @DisplayName("Prueba 13 — Vivienda sin órdenes → lista vacía")
    void obtenerServiciosPorVivienda_sinOrdenes_retornaListaVacia() {
        List<Servicio> servicios = gestor.obtenerServiciosPorVivienda("Carrera 8 # 12-40");
        assertTrue(servicios.isEmpty(),
                "La lista debe estar vacía si no hay órdenes para esa vivienda");
    }

    @Test
    @DisplayName("Prueba 14 — Dirección nula → IllegalArgumentException")
    void obtenerServiciosPorVivienda_direccionNula_lanzaExcepcion() {
        assertThrows(IllegalArgumentException.class,
                () -> gestor.obtenerServiciosPorVivienda(null));
    }

    // ══════════════════════════════════════════════════════════════════════════
    // PRUEBAS — Método d) actualizarEstadoServicio
    // ══════════════════════════════════════════════════════════════════════════

    @Test
    @DisplayName("Prueba 15 — Actualizar a 'en_progreso' → estado cambia correctamente")
    void actualizarEstado_enProgreso_estadoCambia() {
        ServicioElectricidad sElec = new ServicioElectricidad("E20", "Cableado", 90, false, 4);
        OrdenServicio orden = new OrdenServicio("ORD-010", tecnico1, vivienda1, sElec);
        gestor.registrarOrden(orden);

        gestor.actualizarEstadoServicio("E20", "en_progreso");

        assertEquals("en_progreso", sElec.getEstado(),
                "El estado del servicio debe ser 'en_progreso'");
    }

    @Test
    @DisplayName("Prueba 16 — Actualizar a 'completado' → estado cambia correctamente")
    void actualizarEstado_completado_estadoCambia() {
        ServicioCarpinteria sCarp = new ServicioCarpinteria("C20", "Piso", 120, false, true);
        OrdenServicio orden = new OrdenServicio("ORD-011", tecnico2, vivienda2, sCarp);
        gestor.registrarOrden(orden);

        gestor.actualizarEstadoServicio("C20", "completado");

        assertEquals("completado", sCarp.getEstado());
    }

    @Test
    @DisplayName("Prueba 17 — ID de servicio inexistente → IllegalArgumentException")
    void actualizarEstado_idInexistente_lanzaExcepcion() {
        assertThrows(IllegalArgumentException.class,
                () -> gestor.actualizarEstadoServicio("NO-EXISTE", "completado"));
    }

    @Test
    @DisplayName("Prueba 18 — Estado nulo → IllegalArgumentException")
    void actualizarEstado_estadoNulo_lanzaExcepcion() {
        ServicioElectricidad sElec = new ServicioElectricidad("E30", "Tomacorrientes", 30, false, 1);
        OrdenServicio orden = new OrdenServicio("ORD-020", tecnico1, vivienda1, sElec);
        gestor.registrarOrden(orden);

        assertThrows(IllegalArgumentException.class,
                () -> gestor.actualizarEstadoServicio("E30", null));
    }

    // ══════════════════════════════════════════════════════════════════════════
    // PRUEBAS — Método e) calcularCostoTotalVivienda
    // ══════════════════════════════════════════════════════════════════════════

    @Test
    @DisplayName("Prueba 19 — Vivienda con 3 servicios → suma correcta")
    void calcularCostoTotal_tresServicios_sumaCorrectamente() {
        // Electricidad sin alturas: $50.000
        // Fontanería con corte:     $50.000
        // Carpintería con pesada:   $75.000
        // Total esperado:          $175.000
        ServicioElectricidad sElec = new ServicioElectricidad("E40", "Panel", 60, false, 2);
        ServicioFontaneria   sFon  = new ServicioFontaneria("F40", "Tubería", 45,
                true, ServicioFontaneria.TipoMaterial.COBRE);
        ServicioCarpinteria  sCarp = new ServicioCarpinteria("C40", "Estructura", 180, true, true);

        gestor.registrarOrden(new OrdenServicio("ORD-030", tecnico1, vivienda1, sElec));
        gestor.registrarOrden(new OrdenServicio("ORD-031", tecnico1, vivienda1, sFon));
        gestor.registrarOrden(new OrdenServicio("ORD-032", tecnico2, vivienda1, sCarp));

        double total = gestor.calcularCostoTotalVivienda("Calle 10 # 5-20");
        assertEquals(175_000.0, total, 0.001,
                "El costo total de los 3 servicios debe ser $175.000");
    }

    @Test
    @DisplayName("Prueba 20 — Vivienda sin órdenes → costo total 0")
    void calcularCostoTotal_sinOrdenes_retornaCero() {
        double total = gestor.calcularCostoTotalVivienda("Carrera 8 # 12-40");
        assertEquals(0.0, total, 0.001,
                "Una vivienda sin órdenes debe tener costo total de $0");
    }

    @Test
    @DisplayName("Prueba 21 — Dirección nula → IllegalArgumentException")
    void calcularCostoTotal_direccionNula_lanzaExcepcion() {
        assertThrows(IllegalArgumentException.class,
                () -> gestor.calcularCostoTotalVivienda(null));
    }
}
