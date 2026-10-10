package ar.edu.unahur.obj2.peluqueria.servicios;

public class CorteDeCabello extends Servicio {

    private String nombre = "Corte de Cabello";

    
    public CorteDeCabello(String nombre, Double precioBase, Integer cantidadDeCuposDisponibles) {
        super(nombre, precioBase, cantidadDeCuposDisponibles);
    }
    
}
