package ar.edu.unahur.obj2.peluqueria.servicios;
import java.util.List;

import ar.edu.unahur.obj2.peluqueria.AvisoRetoque;
import ar.edu.unahur.obj2.peluqueria.Turno;

import java.util.Collections;
import java.time.LocalDate;


public class Coloracion extends Servicio {
    private final Integer avisoDeRetoque = 30;
    private java.util.List<AvisoRetoque> avisosDeRetoque = new java.util.ArrayList<>();
    
    public Coloracion(String nombre, Double precioBase, Integer cantidadDeCuposDisponibles) {
        super("Coloración", precioBase, cantidadDeCuposDisponibles);
    }

    public List<AvisoRetoque> getAvisosDeRetoque() {
        return Collections.unmodifiableList(avisosDeRetoque);
    }

    @Override
    protected String formatoComprobante() {
        return "TRN-COL-";
    }

    @Override
    protected boolean admiteVariasSesiones() {
        return false;
    }  

    @Override 
    protected void agregarAvisoDeRetoque(Turno turno) {;
        avisosDeRetoque.add(new AvisoRetoque(turno, LocalDate.now().plusDays(avisoDeRetoque)));
    }
}
