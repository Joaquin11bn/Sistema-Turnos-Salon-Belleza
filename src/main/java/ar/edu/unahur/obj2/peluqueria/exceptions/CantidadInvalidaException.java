package ar.edu.unahur.obj2.peluqueria.exceptions;

public class CantidadInvalidaException extends RuntimeException {
    public CantidadInvalidaException() {
        super("La cantidad de cupos disponibles no puede ser negativa.");
    }

}
