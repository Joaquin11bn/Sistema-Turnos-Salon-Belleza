package ar.edu.unahur.obj2.peluqueria;
 
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
 
import ar.edu.unahur.obj2.peluqueria.categorias.Categoria;
import ar.edu.unahur.obj2.peluqueria.categorias.Frecuente;
import ar.edu.unahur.obj2.peluqueria.categorias.Ocasional;
import ar.edu.unahur.obj2.peluqueria.categorias.Vip;
import ar.edu.unahur.obj2.peluqueria.exceptions.CategoriaNoAutorizadaException;
 
public class CategoriaTest {
private Cliente cliente;
 
    @BeforeEach
    void setUp() {
        cliente = new Cliente("Ocasional", new Ocasional());
    }
 
    @Test
    public void dadaUnaCategoriaOcasional_alAplicarElDescuento_noHayDescuento() {
        Categoria categoria = new Ocasional();
 
        assertEquals(1000.0, categoria.descuento(1000.0), 0.01);
    }
 
    @Test
    public void dadaUnaCategoriaFrecuente_alAplicarElDescuento_descuentaDiezPorCiento() {
        Categoria categoria = new Frecuente();
 
        assertEquals(900.0, categoria.descuento(1000.0), 0.01);
    }
 
    @Test
    public void dadaUnaCategoriaVip_alAplicarElDescuento_descuentaVeintePorCiento() {
        Categoria categoria = new Vip();
 
        assertEquals(800.0, categoria.descuento(1000.0), 0.01);
    }
 
    @Test
    public void dadaUnaCategoriaFrecuente_alVerificarUnClienteSinTurnos_noSeLanzaExcepcion() {
        Categoria categoria = new Frecuente();
 
        assertDoesNotThrow(() -> categoria.esClientePrevio(cliente));
    }
 // Excepciones
    @Test
    public void dadaUnaCategoriaVip_alVerificarUnClienteSinTurnos_seLanzaCategoriaNoAutorizadaException() {
        Categoria categoria = new Vip();
 
        assertThrows(CategoriaNoAutorizadaException.class, () -> categoria.esClientePrevio(cliente));
    }
 
    @Test
    public void dadaUnaCategoriaVip_alVerificarUnClienteConTurnos_noSeLanzaExcepcion() {
        cliente.agregarTurno(new Turno(cliente, null, 1, 1000.0, "TRN-COR-1"));
        Categoria categoria = new Vip();
 
        assertDoesNotThrow(() -> categoria.esClientePrevio(cliente));
    }
 
    @Test
    public void dadaUnaNuevaCategoria_alUsarla_noHaceFaltaModificarLasExistentes() {
        Categoria mitadDePrecio = importe -> importe * 0.5;
 
        assertEquals(500.0, mitadDePrecio.descuento(1000.0), 0.01);
    }
}
