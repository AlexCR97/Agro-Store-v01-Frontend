package com.example.agrostore01.CapaNegocios.escritores;

import com.example.agrostore01.CapaDatos.repositorios.RepositorioComentarios;
import com.example.agrostore01.CapaEntidades.Comentarios;

public class EscritorComentarios extends Escritor<Comentarios> {

    public static final int OPERACION_COMENTAR = 4;

    private RepositorioComentarios repositorio = new RepositorioComentarios();

    private String idUsuario;
    private String comentario;
    private int idNumProducto;

    public EscritorComentarios(int operacion, Comentarios comentarios) {
        super(operacion, comentarios);
    }

    public EscritorComentarios(int operacion, Comentarios comentarios, Comentarios entidadCambio) {
        super(operacion, comentarios, entidadCambio);
    }

    public EscritorComentarios(int operacion, Comentarios comentarios, String idUsuario, String comentario, int idNumProducto) {
        super(operacion, comentarios);
        this.idUsuario = idUsuario;
        this.comentario = comentario;
        this.idNumProducto = idNumProducto;
    }

    @Override
    public boolean ejecutarCambios() {
        if (operacion == OPERACION_ALTA)
            return false;

        if (operacion == OPERACION_BAJA)
            return false;

        if (operacion == OPERACION_CAMBIO)
            return false;

        if (operacion == OPERACION_COMENTAR) {
            java.util.Date date = new java.util.Date();
            java.sql.Date now = new java.sql.Date(date.getTime());
            return repositorio.comentarPublicacion(idUsuario, comentario, now, null, idNumProducto);
        }

        return false;
    }
}
