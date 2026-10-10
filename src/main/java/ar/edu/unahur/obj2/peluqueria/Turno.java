
package ar.edu.unahur.obj2.peluqueria;
import ar.edu.unahur.obj2.peluqueria.servicios.Servicio;




public class Turno {

    private final Cliente cliente;
    private final Servicio servicio;
    private final Integer sesiones;
    private final Double importeTotal;
    private final String comprobante;

    public Turno(Cliente cliente, Servicio servicio, Integer sesiones, Double importeTotal, String comprobante) {
        this.cliente = cliente;
        this.servicio = servicio;
        this.sesiones = sesiones;
        this.importeTotal = importeTotal;
        this.comprobante = comprobante;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public Integer getSesiones() {
        return sesiones;
    }

    public Double getImporteTotal() {
        return importeTotal;
    }

    public String getComprobante() {
        return comprobante;
    }
}
