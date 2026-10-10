package ar.edu.unahur.obj2.peluqueria;

import ar.edu.unahur.obj2.peluqueria.categorias.Frecuente;
import ar.edu.unahur.obj2.peluqueria.categorias.Ocasional;
//import ar.edu.unahur.obj2.peluqueria.categorias.Ocasional;
import ar.edu.unahur.obj2.peluqueria.categorias.Vip;
import ar.edu.unahur.obj2.peluqueria.exceptions.CantidadInvalidaException;
import ar.edu.unahur.obj2.peluqueria.exceptions.PrecioNegativoException;
import ar.edu.unahur.obj2.peluqueria.servicios.Coloracion;
import ar.edu.unahur.obj2.peluqueria.servicios.CorteDeCabello;
import ar.edu.unahur.obj2.peluqueria.servicios.Servicio;
import ar.edu.unahur.obj2.peluqueria.servicios.TratamientoCapilar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class ServicioTest {
    private RecargoPorInsumos recargoPorInsumos;
    private Cliente ocasional;

    @BeforeEach
    void setUp() {
        recargoPorInsumos = new RecargoPorInsumos(0.0);
        ocasional = new Cliente("Ocasional", new Ocasional());
    }


@Test 
public void dadoPrecioBaseYCuposValidos_alCrearElServicio_quedanRegistrados() {
        Double precioBase = 1000.0;
        Integer cantidadDeCuposDisponibles = 10;
        Servicio corteDeCabello = new CorteDeCabello("Corte de Cabello", precioBase, cantidadDeCuposDisponibles);

        //Assert Equals(precioBase, corteDeCabello.getPrecioBase());
        assertEquals(1000.0, corteDeCabello.getPrecioBase(), 0.01);
        assertEquals(10, corteDeCabello.getCantidadDeCuposDisponibles());
    }

// Excepciones 

@Test 
    void dadoPrecioBaseNegativo_alCrearElServicio_seLanzaExcepcion() {
        assertThrows(PrecioNegativoException.class, () -> {
            new CorteDeCabello("Corte de Cabello", -1000.0, 10);
        });
    }
@Test
    public void dadaUnaCantidadDeSesionesCero_alReservarUnTurno_seLanzaCantidadInvalidaException() {
        Servicio corte = new CorteDeCabello("Corte de Cabello", 1000.0, 10);
 
        assertThrows(CantidadInvalidaException.class,
                () -> corte.reservarTurno(ocasional, 0, recargoPorInsumos));
    }
 
    @Test
    public void dadaUnaCantidadDeSesionesNegativa_alReservarUnTurno_seLanzaCantidadInvalidaException() {
        Servicio tratamiento = new TratamientoCapilar("Tratamiento Capilar", 1000.0, 10);
 
        assertThrows(CantidadInvalidaException.class,
                () -> tratamiento.reservarTurno(ocasional, -2, recargoPorInsumos));
    }
 
    @Test
    public void dadaUnaCantidadInvalida_alFallarLaReserva_noSeDescuentanCuposNiSeAsientaElTurno() {
        Servicio corte = new CorteDeCabello("Corte de Cabello", 1000.0, 10);
 
        assertThrows(CantidadInvalidaException.class,
                () -> corte.reservarTurno(ocasional, 0, recargoPorInsumos));
 
        assertEquals(10, corte.getCantidadDeCuposDisponibles());
        assertFalse(ocasional.tieneTurnosPrevios());
    }
 
    @Test
    public void dadoUnServicioDeUnaSolaSesion_alPedirVariasSesiones_seLanzaCantidadInvalidaException() {
        Servicio corte = new CorteDeCabello("Corte de Cabello", 1000.0, 10);
 
        assertThrows(CantidadInvalidaException.class,
                () -> corte.reservarTurno(ocasional, 2, recargoPorInsumos));
    }
 
    // AVISO RETOQUE
 
    @Test
    public void dadaUnaColoracion_alReservarUnTurno_seRegistraUnAvisoDeRetoqueATreintaDias() {
        Coloracion coloracion = new Coloracion("Coloración", 2000.0, 10);
 
        Turno turno = coloracion.reservarTurno(ocasional, 1, recargoPorInsumos);
 
        assertEquals(1, coloracion.getAvisosDeRetoque().size());
        AvisoRetoque aviso = coloracion.getAvisosDeRetoque().get(0);
        assertSame(turno, aviso.getTurno());
        assertEquals(LocalDate.now().plusDays(30), aviso.getFecha());
    }
 
    // NUEVO SERVICIO
 
    static class Manicura extends Servicio {
        Manicura(Double precioBase, Integer cupos) {
            super("Manicura", precioBase, cupos);
        }
 
        @Override
        protected boolean admiteVariasSesiones() {
            return true;
        }
 
        @Override
        protected String formatoComprobante() {
            return "TRN-MAN-";
        }
    }
 
    @Test
    public void dadoUnNuevoServicio_alReservarUnTurno_cumpleElComportamientoComunSinModificarLosExistentes() {
        recargoPorInsumos.setMontoRecargo(10.0);
        Cliente frecuente = new Cliente("Beto", new Frecuente());
        Servicio manicura = new Manicura(500.0, 3);
 
        Turno turno = manicura.reservarTurno(frecuente, 1, recargoPorInsumos);
 
        assertEquals("TRN-MAN-1", turno.getComprobante());
        assertEquals(460.0, turno.getImporteTotal(), 0.01);
        assertEquals(2, manicura.getCantidadDeCuposDisponibles());
        assertEquals(1, frecuente.getHistorialDeTurnos().size());
    }

// historial de turnos y cupos
 
    @Test
    public void dadoUnServicioConCupos_alReservarUnTurno_seDescuentaUnCupo() {
        Servicio corte = new CorteDeCabello("Corte de Cabello", 1000.0, 2);
 
        corte.reservarTurno(ocasional, 1, recargoPorInsumos);
 
        assertEquals(1, corte.getCantidadDeCuposDisponibles());
    }
 
    @Test
    public void dadoUnTratamientoCapilar_alReservarDosSesiones_seDescuentanEsosCupos() {
        Servicio tratamiento = new TratamientoCapilar("Tratamiento Capilar", 1000.0, 10);
 
        tratamiento.reservarTurno(ocasional, 2, recargoPorInsumos);
 
        assertEquals(8, tratamiento.getCantidadDeCuposDisponibles());
    }
 
    @Test
    public void dadoUnTurnoReservado_alConsultarElHistorialDelCliente_quedaAgendado() {
        Servicio corte = new CorteDeCabello("Corte de Cabello", 1000.0, 10);
 
        Turno turno = corte.reservarTurno(ocasional, 1, recargoPorInsumos);
 
        assertEquals(1, ocasional.getHistorialDeTurnos().size());
        assertSame(turno, ocasional.getHistorialDeTurnos().get(0));
    }
// comprobantes con numero
    @Test
    public void dadoUnCorteDeCabello_alReservarUnTurno_elComprobanteEsTRNCOR1() {
        Servicio corte = new CorteDeCabello("Corte de Cabello", 1000.0, 10);
 
        Turno turno = corte.reservarTurno(ocasional, 1, recargoPorInsumos);
 
        assertEquals("TRN-COR-1", turno.getComprobante());
    }
 
    @Test
    public void dadaUnaColoracion_alReservarUnTurno_elComprobanteEsTRNCOL1() {
        Servicio coloracion = new Coloracion("Coloración", 2000.0, 10);
 
        Turno turno = coloracion.reservarTurno(ocasional, 1, recargoPorInsumos);
 
        assertEquals("TRN-COL-1", turno.getComprobante());
    }
 
    @Test
    public void dadoUnTratamientoCapilar_alReservarUnTurno_elComprobanteEsTRNTRA1() {
        Servicio tratamiento = new TratamientoCapilar("Tratamiento Capilar", 1500.0, 10);
 
        Turno turno = tratamiento.reservarTurno(ocasional, 2, recargoPorInsumos);
 
        assertEquals("TRN-TRA-1", turno.getComprobante());
    }


//importes 
 @Test
    public void dadoUnClienteOcacional_alReservarUnCorte_pagaElPrecioBase() {
        Servicio corte = new CorteDeCabello("Corte de Cabello", 100.0, 10);
        recargoPorInsumos.setMontoRecargo(0.0);
        Turno turno = corte.reservarTurno(ocasional, 1, recargoPorInsumos);
 
        assertEquals(100.0, turno.getImporteTotal(), 0.01);
    }
 
    @Test
    public void dadoUnClienteFrecuente_alReservarTresSesionesDeTratamiento_aplicaDescuentoDelDiezPorCiento() {
        Cliente frecuente = new Cliente("Beto", new Frecuente());
        Servicio tratamiento = new TratamientoCapilar("Tratamiento Capilar", 1000.0, 10);
        recargoPorInsumos.setMontoRecargo(0.0);
        Turno turno = tratamiento.reservarTurno(frecuente, 3, recargoPorInsumos);
 
        assertEquals(2700.0, turno.getImporteTotal(), 0.01);
    }
    @Test
    public void dadoUnClienteVip_alReservarUnaColoracion_aplicaDescuentoDelVeintePorCiento() {
        Cliente cliente = new Cliente("Carla", new Ocasional());
        new CorteDeCabello("Corte de Cabello", 100.0, 10).reservarTurno(cliente, 1, recargoPorInsumos);
        cliente.cambiarCategoria(new Vip());
        Servicio coloracion = new Coloracion("Coloración", 1000.0, 10);
 
        Turno turno = coloracion.reservarTurno(cliente, 1, recargoPorInsumos);
 
        assertEquals(800.0, turno.getImporteTotal(), 0.01);
    }


}
