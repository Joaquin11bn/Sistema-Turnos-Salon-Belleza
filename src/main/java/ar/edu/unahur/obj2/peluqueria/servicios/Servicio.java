package ar.edu.unahur.obj2.peluqueria.servicios;

import ar.edu.unahur.obj2.peluqueria.Cliente;
import ar.edu.unahur.obj2.peluqueria.Turno;
import ar.edu.unahur.obj2.peluqueria.exceptions.PrecioNegativoException;
import ar.edu.unahur.obj2.peluqueria.servicios.Servicio;

public abstract class Servicio {
    private final String nombre;
    private final Double precioBase;
    private Integer cantidadDeCuposDisponibles;
    private Integer numeroComprobanteEmitidos;

    protected abstract boolean admiteVariasSesiones();

    private String emitirComprobante() {
        numeroComprobanteEmitidos++;
        return formatoComprobante();
    }

    protected abstract String formatoComprobante();

    public abstract void confirmarTurno();

    protected Servicio(String nombre, Double precioBase, Integer cantidadDeCuposDisponibles) {
        if (precioBase < 0) {
            throw new PrecioNegativoException();
        }
        this.nombre = nombre;
        this.precioBase = precioBase;
        this.cantidadDeCuposDisponibles = cantidadDeCuposDisponibles;
        
    }   

    public final Turno reservarTurno(Cliente cliente, Integer sesiones, RecargaInsumos recargaInsumos) {
        Double importe = calcularImporte(cliente, sesiones, recargaInsumos);
        String comprobante = emitirComprobante();
        Turno turno = new Turno(cliente, this, sesiones, importe, comprobante);
        agendarTurno(turno);
        return turno;
    }

    public void agendarTurno(Turno turno) {
        cantidadDeCuposDisponibles-= turno.getSesiones();
        turno.getCliente().agregarTurno(turno);
    }
    public Double getPrecioBase() {
        return precioBase;
    }

    public Integer getCantidadDeCuposDisponibles() {
        return cantidadDeCuposDisponibles;
    }

    public String getNombre() {
        return nombre;
    }


}
