package ar.edu.unahur.obj2.peluqueria.categorias;


public class Ocasional implements Categoria {


    @Override
    public Double descuento(Double importe) {
        return importe;
    }


}
