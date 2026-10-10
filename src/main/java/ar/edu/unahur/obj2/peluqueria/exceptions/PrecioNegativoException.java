package ar.edu.unahur.obj2.peluqueria.exceptions;

/**
 * PrecioNegativoException
 */
public class PrecioNegativoException extends RuntimeException {
    public PrecioNegativoException() {
        super("El precio base no puede ser negativo.");
    }

}
