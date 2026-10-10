package ar.edu.unahur.obj2.peluqueria.exceptions;

public class CategoriaNoAutorizadaException extends RuntimeException {
    public CategoriaNoAutorizadaException() {
        super("El cliente no está autorizado para este servicio.");
    }

}
