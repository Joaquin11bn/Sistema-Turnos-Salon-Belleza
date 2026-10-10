package ar.edu.unahur.obj2.peluqueria.categorias;

public class Frecuente implements Categoria {

    private final Double porcentajeDescuento = 0.10;

    @Override 
    public Double descuento(Double importe) {
        return importe * (1 - porcentajeDescuento);
    }
}
