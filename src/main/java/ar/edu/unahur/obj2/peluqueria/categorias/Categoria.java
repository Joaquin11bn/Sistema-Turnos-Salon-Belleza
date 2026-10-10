package ar.edu.unahur.obj2.peluqueria.categorias;

import ar.edu.unahur.obj2.peluqueria.Cliente;

public interface Categoria {

    Double descuento(Double importe);
    default void esClientePrevio(Cliente cliente) {
       
    }
}
