package ar.edu.unahur.obj2.peluqueria;
 
import static org.junit.jupiter.api.Assertions.assertEquals;
 
import org.junit.jupiter.api.Test;
 
public class RecargoPorInsumosTest {
 
    @Test
    public void dadoUnMontoValido_alCrearElRecargo_seRegistra() {
        RecargoPorInsumos recargo = new RecargoPorInsumos(100.0);
 
        assertEquals(100.0, recargo.getMontoRecargo(), 0.01);
    }
}