package ar.edu.unahur.obj2.peluqueria;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import ar.edu.unahur.obj2.peluqueria.servicios.CorteDeCabello;
import ar.edu.unahur.obj2.peluqueria.servicios.Servicio;

public class CorteDeCabelloTest {

    @Test 
    public void dadoPrecioBaseYCuposValidos_alCrearElServicio_quedanRegistrados() {
        Double precioBase = 1000.0;
        Integer cantidadDeCuposDisponibles = 10;
        Servicio corteDeCabello = new CorteDeCabello("Corte de Cabello", precioBase, cantidadDeCuposDisponibles);

        assertEquals(precioBase, corteDeCabello.getPrecioBase());
        assertEquals(1000.0, corteDeCabello.getPrecioBase(), 0.01);
        assertEquals(10, corteDeCabello.getCantidadDeCuposDisponibles());
    }
}
