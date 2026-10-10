package ar.edu.unahur.obj2.peluqueria.exceptions;

/**
 * ClienteSinTurnosException
 */
public class ClienteSinTurnosException extends RuntimeException {
    public ClienteSinTurnosException() {
        super("El cliente no tiene turnos asignados.");
    }

}
