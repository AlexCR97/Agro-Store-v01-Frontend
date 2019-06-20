package com.example.agrostore01.CapaDatos.repositorios.vistas;

import com.example.agrostore01.CapaDatos.contratos.vistas.IContratoVistaPublicacion;
import com.example.agrostore01.CapaDatos.repositorios.Repositorio;
import com.example.agrostore01.CapaEntidades.vistas.VistaPublicacion;

import java.util.ArrayList;

public class RepositorioVistaPublicacion extends Repositorio implements IContratoVistaPublicacion {

    private String sqlProcPublicarProducto;

    public RepositorioVistaPublicacion() {
        this.sqlProcPublicarProducto = "{ call PROC_ALTA_ProductoTerreno(?, ?, ?, ?, ?, ?, ?) }";
    }

    @Override
    public boolean publicarProducto(VistaPublicacion vistaPublicacion) {
        parametros = new ArrayList<>();

        parametros.add(vistaPublicacion.getIdProducto());
        parametros.add(vistaPublicacion.getIdTerreno());
        parametros.add(vistaPublicacion.getPrecio());
        parametros.add(vistaPublicacion.getDescripcion());
        parametros.add(vistaPublicacion.getTemporada());
        parametros.add(vistaPublicacion.getFoto());
        parametros.add(vistaPublicacion.getTituloPublicacion());

        try {
            return ejecutarProcedimiento(sqlProcPublicarProducto);
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean alta(VistaPublicacion e) {
        return false;
    }

    @Override
    public boolean baja(Object id) {
        return false;
    }

    @Override
    public boolean cambio(Object id, VistaPublicacion e) {
        return false;
    }

    @Override
    public VistaPublicacion seleccionarId(Object id) {
        return null;
    }

    @Override
    public ArrayList<VistaPublicacion> seleccionarTodo() {
        return null;
    }
}
