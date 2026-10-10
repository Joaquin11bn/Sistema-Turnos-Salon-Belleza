package ar.edu.unahur.obj2.peluqueria;
 
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
 
import java.time.LocalDate;
 
import org.junit.jupiter.api.Test;
 
import ar.edu.unahur.obj2.peluqueria.categorias.Ocasional;
 
public class AvisoRetoqueTest {
 
    @Test
    public void dadoUnTurnoYUnaFecha_alCrearElAviso_quedanRegistrados() {
        Cliente cliente = new Cliente("Andres", new Ocasional());
        Turno turno = new Turno(cliente, null, 1, 1000.0, "TRN-COL-1");
        LocalDate fecha = LocalDate.of(2026, 11, 8);
 
        AvisoRetoque aviso = new AvisoRetoque(turno, fecha);
 
        assertSame(turno, aviso.getTurno());
        assertEquals(fecha, aviso.getFecha());
    }
}
