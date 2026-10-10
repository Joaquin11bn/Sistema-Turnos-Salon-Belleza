package ar.edu.unahur.obj2.peluqueria;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ar.edu.unahur.obj2.peluqueria.servicios.Servicio;

public class SalonDeBelleza {
    private final List<Turno> turnosConfirmados = new ArrayList<>();
    private final RecargoPorInsumos recargoPorInsumos;


    public SalonDeBelleza(RecargoPorInsumos recargoPorInsumos) {
        this.recargoPorInsumos = recargoPorInsumos;
    }

    public RecargoPorInsumos getRecargoPorInsumos() {
        return recargoPorInsumos;
    }

    public Turno confirmarTurno(Cliente cliente, Servicio servicio, Integer sesiones) {
        double importe = servicio.calcularImporte(cliente, sesiones, recargoPorInsumos);
        String comprobante = servicio.emitirComprobante();
        Turno turno = servicio.reservarTurno(cliente, sesiones, recargoPorInsumos);
        turnosConfirmados.add(turno);
        return turno;
    }

    public Integer cantidadDeTurnosConfirmados() {
        return turnosConfirmados.size();
    }
    
    
    public List<Turno> getTurnosConfirmados() {
        return Collections.unmodifiableList(turnosConfirmados);
    }
}
