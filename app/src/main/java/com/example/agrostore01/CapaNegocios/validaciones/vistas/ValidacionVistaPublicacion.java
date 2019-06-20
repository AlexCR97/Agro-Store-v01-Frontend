package com.example.agrostore01.CapaNegocios.validaciones.vistas;

import com.example.agrostore01.CapaEntidades.vistas.VistaPublicacion;
import com.example.agrostore01.CapaNegocios.validaciones.Validacion;

public class ValidacionVistaPublicacion extends Validacion<VistaPublicacion> {

    public ValidacionVistaPublicacion(VistaPublicacion vistaPublicacion) {
        super(vistaPublicacion);
    }

    @Override
    public boolean validar() {

        boolean exito = validarDescripcion() && validarFoto() && validarHectareas() &&
                validarIdProducto() && validarIdTerreno() && validarPrecio()
                && validarTemporada() && validarTituloPublicacion();

        if (!exito) {
            System.out.println("Validacion de publicacion fallo :(");
            return false;
        }

        return true;
    }

    public boolean validarTituloPublicacion() {
        if (entidad.getTituloPublicacion() == null)
            return false;

        if (entidad.getTituloPublicacion().isEmpty())
            return false;

        return true;
    }

    public boolean validarIdProducto() {
        if (entidad.getIdProducto() <= -1)
            return false;

        return true;
    }

    public boolean validarIdTerreno() {
        if (entidad.getIdTerreno() <= -1)
            return false;

        return true;
    }

    public boolean validarPrecio() {
        return true;
    }

    public boolean validarHectareas() {
        if (entidad.getHectareas() <= -1)
            return false;

        return true;
    }

    public boolean validarDescripcion() {
        if (entidad.getDescripcion() == null)
            return false;

        if (entidad.getDescripcion().isEmpty())
            return false;

        return true;
    }

    public boolean validarTemporada() {
        if (entidad.getTemporada() == null)
            return false;

        if (entidad.getTemporada().isEmpty())
            return false;

        return true;
    }

    public boolean validarFoto() {
        return true;
    }
}
