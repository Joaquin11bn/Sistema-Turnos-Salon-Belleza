package ar.edu.unahur.obj2.peluqueria;
 
import static org.junit.jupiter.api.Assertions.assertEquals;

 
import org.junit.jupiter.api.Test;
 
import ar.edu.unahur.obj2.peluqueria.categorias.Ocasional;
import ar.edu.unahur.obj2.peluqueria.servicios.CorteDeCabello;
import ar.edu.unahur.obj2.peluqueria.servicios.Servicio;

public class TurnoTest {
    @Test
    public void dadosLosDatosDelTurno_alCrearElTurno_quedanRegistrados() {
        Cliente cliente = new Cliente("pablo", new Ocasional());
        Servicio servicio = new CorteDeCabello("Corte de Cabello", 1000.0, 10);
 
        Turno turno = new Turno(cliente, servicio, 1, 1000.0, "TRN-COR-1");
 

        assertEquals(1, turno.getSesiones());
        assertEquals(1000.0, turno.getImporteTotal(), 0.01);
        assertEquals("TRN-COR-1", turno.getComprobante());
    }    
}
