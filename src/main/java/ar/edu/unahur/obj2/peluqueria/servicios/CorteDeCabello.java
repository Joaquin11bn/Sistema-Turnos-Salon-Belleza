package ar.edu.unahur.obj2.peluqueria.servicios;

public class CorteDeCabello extends Servicio {
    
    public CorteDeCabello(String nombre, Double precioBase, Integer cantidadDeCuposDisponibles) {
        super("Corte de Cabello", precioBase, cantidadDeCuposDisponibles);
    }
    @Override 
    protected String formatoComprobante() {
        return "TRN-COR-";
    }
    @Override 
    protected boolean admiteVariasSesiones() {
        return false;
    }
}
