package ar.edu.unahur.obj2.peluqueria;

import java.time.LocalDate;

public class AvisoRetoque {

    private final Turno turno;
    private final LocalDate fecha;

    public AvisoRetoque(Turno turno, LocalDate fecha) {
        this.turno = turno;
        this.fecha = fecha;
    }

    public Turno getTurno() {
        return turno;
    }

    public LocalDate getFecha() {
        return fecha;
    }
}