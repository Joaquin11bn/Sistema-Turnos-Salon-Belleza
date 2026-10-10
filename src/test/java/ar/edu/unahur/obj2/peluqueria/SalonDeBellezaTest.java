package ar.edu.unahur.obj2.peluqueria;
 
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import static org.junit.jupiter.api.Assertions.assertTrue;
 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
 
import ar.edu.unahur.obj2.peluqueria.categorias.Ocasional;
import ar.edu.unahur.obj2.peluqueria.servicios.CorteDeCabello;
import ar.edu.unahur.obj2.peluqueria.servicios.Servicio;
 

public class SalonDeBellezaTest {
   private RecargoPorInsumos recargoPorInsumos;
    private SalonDeBelleza salon;
    private Cliente cliente;
    private Servicio corteDeCabello;
 
    @BeforeEach
    void setUp() {
        recargoPorInsumos = new RecargoPorInsumos(0.0);
        salon = new SalonDeBelleza(recargoPorInsumos);
        cliente = new Cliente("Jorge", new Ocasional());
        corteDeCabello = new CorteDeCabello("Corte de Cabello", 1000.0, 10);
    }
 
    @Test
    public void dadoUnRecargoPorInsumos_alCrearElSalon_elSalonLoConserva() {
        assertSame(recargoPorInsumos, salon.getRecargoPorInsumos());
    }
 
    @Test
    public void dadoUnSalonNuevo_alConsultarLosTurnosConfirmados_hayCero() {
        assertEquals(0, salon.cantidadDeTurnosConfirmados());
        assertTrue(salon.getTurnosConfirmados().isEmpty());
    }
 
    @Test
    public void dadoUnTurnoConfirmado_alConsultarElSalon_cuentaUnTurno() {
        salon.confirmarTurno(cliente, corteDeCabello, 1);
 
        assertEquals(1, salon.cantidadDeTurnosConfirmados());
    }
 
    @Test
    public void dadoUnTurnoConfirmado_alRevisarSusDatos_coincidenConLosDeLaReserva() {
        Turno turno = salon.confirmarTurno(cliente, corteDeCabello, 1);
 
        assertSame(cliente, turno.getCliente());
        assertSame(corteDeCabello, turno.getServicio());
        assertEquals(1, turno.getSesiones());
        assertTrue(turno.getComprobante().startsWith("TRN-COR"));
    }
 
    @Test
    public void dadoUnClienteOcasionalSinRecargo_alConfirmarUnTurno_pagaElPrecioBase() {
        Turno turno = salon.confirmarTurno(cliente, corteDeCabello, 1);
 
        assertEquals(1000.0, turno.getImporteTotal(), 0.01);
    }
 
    @Test
    public void dadoUnRecargoPorInsumos_alConfirmarUnTurno_sumaElRecargoAlImporte() {
        recargoPorInsumos.setMontoRecargo(50.0);
 
        Turno turno = salon.confirmarTurno(cliente, corteDeCabello, 1);
 
        assertEquals(1050.0, turno.getImporteTotal(), 0.01);
    }
 
    @Test
    public void dadoUnRecargoActualizado_alConfirmarOtroTurno_seCobraElRecargoNuevo() {
        recargoPorInsumos.setMontoRecargo(100.0);
        Turno primero = salon.confirmarTurno(cliente, corteDeCabello, 1);
 
        recargoPorInsumos.setMontoRecargo(300.0);
        Turno segundo = salon.confirmarTurno(cliente, corteDeCabello, 1);
 
        assertEquals(1100.0, primero.getImporteTotal(), 0.01);
        assertEquals(1300.0, segundo.getImporteTotal(), 0.01);
    }
}
