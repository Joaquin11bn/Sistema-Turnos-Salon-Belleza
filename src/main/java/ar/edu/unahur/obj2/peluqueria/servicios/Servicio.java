package ar.edu.unahur.obj2.peluqueria.servicios;

import ar.edu.unahur.obj2.peluqueria.Cliente;
import ar.edu.unahur.obj2.peluqueria.RecargoPorInsumos;
import ar.edu.unahur.obj2.peluqueria.Turno;
import ar.edu.unahur.obj2.peluqueria.exceptions.CantidadInvalidaException;
import ar.edu.unahur.obj2.peluqueria.exceptions.PrecioNegativoException;
import ar.edu.unahur.obj2.peluqueria.servicios.Servicio;

public abstract class Servicio {
    private final String nombre;
    private final Double precioBase;
    private Integer cantidadDeCuposDisponibles;
    private Integer numeroComprobanteEmitidos = 0;


    public String emitirComprobante() {
    numeroComprobanteEmitidos++;
    return formatoComprobante() + numeroComprobanteEmitidos;
    }

    protected Servicio(String nombre, Double precioBase, Integer cantidadDeCuposDisponibles) {
        if (precioBase < 0) {
            throw new PrecioNegativoException();
        }
        this.nombre = nombre;
        this.precioBase = precioBase;
        this.cantidadDeCuposDisponibles = cantidadDeCuposDisponibles;
        
    }   
    public Double calcularImporte(Cliente cliente, Integer sesiones, RecargoPorInsumos recargo) {
        Double subtotal = precioBase * sesiones;
        Double tieneDescuento = cliente.getCategoria().descuento(subtotal);
        return tieneDescuento + recargo.getMontoRecargo();
    }

    public final Turno reservarTurno(Cliente cliente, Integer sesiones, RecargoPorInsumos recargoPorInsumos) {
        if (sesiones <= 0 || (sesiones > 1 && !admiteVariasSesiones())) {
            throw new CantidadInvalidaException();
        }
        Double importe = calcularImporte(cliente, sesiones, recargoPorInsumos);
        String comprobante = emitirComprobante();
        Turno turno = new Turno(cliente, this, sesiones, importe, comprobante);
        agendarTurno(turno);
        agregarAvisoDeRetoque(turno);
        return turno;
    }

    protected void agregarAvisoDeRetoque(Turno turno) {
    }
    
    public void agendarTurno(Turno turno) {
        cantidadDeCuposDisponibles-= turno.getSesiones();
        turno.getCliente().agregarTurno(turno);
    }

    protected abstract boolean admiteVariasSesiones();

    protected abstract String formatoComprobante();

    //public abstract void confirmarTurno();

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
