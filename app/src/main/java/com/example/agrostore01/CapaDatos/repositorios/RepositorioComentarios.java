package com.example.agrostore01.CapaDatos.repositorios;

import com.example.agrostore01.CapaDatos.contratos.IContratoComentario;
import com.example.agrostore01.CapaDatos.contratos.IContratoRelacion;
import com.example.agrostore01.CapaEntidades.Comentarios;

import java.sql.Date;
import java.util.ArrayList;

public class RepositorioComentarios extends Repositorio implements IContratoComentario {

    private String sqlProcComentarPublicacion;

    public RepositorioComentarios() {
        this.sqlAlta="inser into Comentarios values (?, ?, ?, ?, ?)";
        this.sqlBaja="delete from Comentarios where IDComentarios = ?";
        this.sqlCambio="update Comentarios set " +
                "IDComentarios = ?," +
                "IDUsuario = ?," +
                "Comentario = ?, " +
                "Fecha = ?," +
                "Respuesta = ?" +
                "where IDComentarios= ?";
        this.sqlSeleccionarId="select * from Comentarios where IDComentarios = ?";
        this.sqlSeleccionarTodo="select * from Comentarios";

        this.sqlProcComentarPublicacion = "{ call PROC_ALTA_Comentarios(?, ?, ?, ?, ?) }";
    }

    @Override
    public boolean alta(Comentarios e) {
        return false;
    }

    @Override
    public boolean baja(Object id) {
        return false;
    }

    @Override
    public boolean cambio(Object id, Comentarios e) {
        return false;
    }

    @Override
    public Comentarios seleccionarId(Object id) {
        return null;
    }

    @Override
    public ArrayList<Comentarios> seleccionarTodo() {
        return null;
    }

    @Override
    public boolean comentarPublicacion(String idUsuario, String comentario, Date fecha, String respuesta, int idNumProducto) {
        parametros = new ArrayList<>();
        parametros.add(idUsuario);
        parametros.add(comentario);
        parametros.add(fecha);
        parametros.add(respuesta);
        parametros.add(idNumProducto);

        try {
            return ejecutarProcedimiento(sqlProcComentarPublicacion);
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
