package com.example.agrostore01.CapaDatos.repositorios.vistas;

import com.example.agrostore01.CapaDatos.contratos.vistas.IContratoVistaComentarios;
import com.example.agrostore01.CapaDatos.repositorios.Repositorio;
import com.example.agrostore01.CapaEntidades.vistas.VistaComentarios;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class RepositorioVistaComentarios extends Repositorio implements IContratoVistaComentarios {

    private String sqlProcSeleccionarComentarios;

    public RepositorioVistaComentarios() {
        this.sqlProcSeleccionarComentarios = "{ call PROC_ESP_COMENTARIOS(?) }";
    }

    @Override
    public List<VistaComentarios> seleccionarComentarios(int idProductoTerreno) {
        parametros = new ArrayList<>();
        parametros.add(idProductoTerreno);

        try {
            List<VistaComentarios> comentarios = new ArrayList<>();

            resultado = ejecutarProcedimientoConSalida(sqlProcSeleccionarComentarios);

            while (resultado.next()) {
                String nombreUsuario = resultado.getString("Nombre");
                String apellidoUsuario = resultado.getString("Apellido");
                String comentario = resultado.getString("Comentario");
                String respuesta = resultado.getString("Respueta");
                Date fecha = resultado.getDate("Fecha");

                comentarios.add(new VistaComentarios(nombreUsuario, apellidoUsuario, comentario, respuesta, fecha));
            }
            return comentarios;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean alta(VistaComentarios e) {
        return false;
    }

    @Override
    public boolean baja(Object id) {
        return false;
    }

    @Override
    public boolean cambio(Object id, VistaComentarios e) {
        return false;
    }

    @Override
    public VistaComentarios seleccionarId(Object id) {
        return null;
    }

    @Override
    public ArrayList<VistaComentarios> seleccionarTodo() {
        return null;
    }
}
