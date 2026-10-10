package ar.edu.unahur.obj2.peluqueria;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

import ar.edu.unahur.obj2.peluqueria.categorias.Categoria;
import ar.edu.unahur.obj2.peluqueria.exceptions.ClienteSinTurnosException;

public abstract class Cliente {

    private final String nombre;
    private Categoria categoria;
    private final List<Turno> historialDeTurnos = new ArrayList<>();

    public Cliente(String nombre, Categoria categoria) {
        this.nombre = nombre;
        this.categoria = categoria;
    }

    public String getNombre() {
        return nombre;
    }
    public Categoria getCategoria() {
        return categoria;
    }
    public List<Turno> getHistorialDeTurnos() {
        if(historialDeTurnos.isEmpty()){
            throw new ClienteSinTurnosException();
        }
        return Collections.unmodifiableList(historialDeTurnos);
        }

    public boolean tieneTurnosPrevios() {
        return !historialDeTurnos.isEmpty();
    }

    public void agregarTurno(Turno turno) {
        historialDeTurnos.add(turno);
    }
    public void cambiarCategoria(Categoria nuevaCategoria) {
        nuevaCategoria.esClientePrevio(this);
        this.categoria = nuevaCategoria;
    }
    public double costoTotalTurnos() {
        return historialDeTurnos.stream().mapToDouble(Turno::getImporteTotal).sum();
    }

    }
