package ar.edu.unahur.obj2.peluqueria.servicios;
import java.util.List;

public class Coloracion extends Servicio {
    private final Integer avisoDeRetoque = 30;
    private java.util.List<AvisoDeRetoque> avisosDeRetoque = new java.util.ArrayList<>();
    
    public Coloracion(String nombre, Double precioBase, Integer cantidadDeCuposDisponibles) {
        super("Coloración", precioBase, cantidadDeCuposDisponibles);
    }

    public List<AvisoDeRetoque> getAvisosDeRetoque() {
        return avisosDeRetoque;
    }

    @Override
    protected String formatoComprobante() {
        return "TRN-COL-";
    }

    @Override
    public void confirmarTurno() {
       
    }
}
