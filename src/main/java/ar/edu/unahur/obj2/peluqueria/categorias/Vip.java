package ar.edu.unahur.obj2.peluqueria.categorias;

import ar.edu.unahur.obj2.peluqueria.Cliente;
import ar.edu.unahur.obj2.peluqueria.exceptions.CategoriaNoAutorizadaException;

public class Vip implements Categoria {

    private final Double porcentajeDescuento = 0.20;

    @Override 
    public Double descuento(Double importe) {
        return importe * porcentajeDescuento;
    }
    
    public void esClientePrevio(Cliente cliente) {
        if (!cliente.tieneTurnosPrevios()) {
            throw new CategoriaNoAutorizadaException();
        }
    }
}
