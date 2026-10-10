package ar.edu.unahur.obj2.peluqueria;
 
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
 
import ar.edu.unahur.obj2.peluqueria.categorias.Categoria;
import ar.edu.unahur.obj2.peluqueria.categorias.Frecuente;
import ar.edu.unahur.obj2.peluqueria.categorias.Ocasional;
import ar.edu.unahur.obj2.peluqueria.categorias.Vip;
import ar.edu.unahur.obj2.peluqueria.exceptions.CategoriaNoAutorizadaException;
import ar.edu.unahur.obj2.peluqueria.exceptions.ClienteSinTurnosException;

public class ClienteTest {
  private Cliente cliente;
    private Categoria categoriaInicial;
 
    @BeforeEach
    void setUp() {
        categoriaInicial = new Ocasional();
        cliente = new Cliente("OBJETS", categoriaInicial);
    }
 
    private Turno unTurno(double importe) {
        return new Turno(cliente, null, 1, importe, "TRN-COR-1");
    }
 
    @Test
    public void dadoUnNombreYUnaCategoria_alCrearElCliente_quedanRegistrados() {
        assertEquals("OBJETS", cliente.getNombre());
        assertSame(categoriaInicial, cliente.getCategoria());
    }
 
    @Test
    public void dadoUnClienteSinTurnos_alConsultarSiTieneTurnosPrevios_esFalso() {
        assertFalse(cliente.tieneTurnosPrevios());
    }
 
    @Test
    public void dadoUnClienteSinTurnos_alPedirElHistorial_seLanzaClienteSinTurnosException() {
        assertThrows(ClienteSinTurnosException.class, () -> cliente.getHistorialDeTurnos());
    }
 
    @Test
    public void dadoUnClienteSinTurnos_alConsultarElCostoTotal_esCero() {
        assertEquals(0.0, cliente.costoTotalTurnos(), 0.01);
    }
 
    @Test
    public void dadoUnTurnoAgregado_alConsultarElHistorial_contieneEseTurno() {
        Turno turno = unTurno(1000.0);
 
        cliente.agregarTurno(turno);
 
        assertTrue(cliente.tieneTurnosPrevios());
        assertEquals(1, cliente.getHistorialDeTurnos().size());
        assertSame(turno, cliente.getHistorialDeTurnos().get(0));
    }
 
    @Test
    public void dadoVariosTurnos_alConsultarElCostoTotal_sumaLosImportes() {
        cliente.agregarTurno(unTurno(1000.0));
        cliente.agregarTurno(unTurno(500.0));
 
        assertEquals(1500.0, cliente.costoTotalTurnos(), 0.01);
    }
 @Test
    public void dadoUnClienteOcasional_alCambiarloAFrecuente_cambiaLaCategoriaYConservaElHistorial() {
        cliente.agregarTurno(unTurno(1000.0));
        Categoria frecuente = new Frecuente();
 
        cliente.cambiarCategoria(frecuente);
 
        assertSame(frecuente, cliente.getCategoria());
        assertEquals(1, cliente.getHistorialDeTurnos().size());
    }
 
    @Test
    public void dadoUnClienteSinTurnos_alCambiarloAVip_seLanzaCategoriaNoAutorizadaException() {
        assertThrows(CategoriaNoAutorizadaException.class,
                () -> cliente.cambiarCategoria(new Vip()));
    }
}
