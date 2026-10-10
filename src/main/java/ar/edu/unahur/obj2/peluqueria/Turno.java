
package ar.edu.unahur.obj2.peluqueria;



public class Turno {

    private final Cliente cliente;
    private final Servicio servicio;
    private final int sesiones;
    private final double importeTotal;
    private final String comprobante;

    public Turno(Cliente cliente, Servicio servicio, int sesiones, double importeTotal, String comprobante) {
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

    public int getSesiones() {
        return sesiones;
    }

    public double getImporteTotal() {
        return importeTotal;
    }

    public String getComprobante() {
        return comprobante;
    }
}
