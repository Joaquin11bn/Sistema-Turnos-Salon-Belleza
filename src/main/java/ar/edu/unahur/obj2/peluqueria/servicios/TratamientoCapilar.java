package ar.edu.unahur.obj2.peluqueria.servicios;


public class TratamientoCapilar extends Servicio {
   
    public TratamientoCapilar(String nombre, Double precioBase, Integer cantidadDeCuposDisponibles) {
        super("Tratamiento Capilar", precioBase, cantidadDeCuposDisponibles);
    }

    @Override
    protected String formatoComprobante() {
        return "TRN-TRA-";
    }

    @Override
    protected boolean admiteVariasSesiones() {
        return true;
    }


   

}
