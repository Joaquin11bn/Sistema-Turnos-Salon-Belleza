package ar.edu.unahur.obj2.peluqueria.servicios;

import java.util.List;

import ar.edu.unahur.obj2.peluqueria.exceptions.PrecioNegativoException;

public class TratamientoCapilar extends Servicio {
    private Double precioBase;
    private Integer cantidadDeCuposDisponibles;
    private java.util.List<reservarTurno> reservarTurnos; 
    private String nombre = "Tratamiento Capilar";

    
    public TratamientoCapilar(String nombre, Double precioBase, Integer cantidadDeCuposDisponibles) {
        super(nombre, precioBase, cantidadDeCuposDisponibles);
    }

    @Override   
    public Double precioBase() {
        if (precioBase <= 0){
            throw new PrecioNegativoException(); 
        }
        return precioBase;
    }

}
