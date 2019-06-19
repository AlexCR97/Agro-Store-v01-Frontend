package com.example.agrostore01.CapaDatos.contratos;

import com.example.agrostore01.CapaEntidades.Comentarios;

import java.sql.Date;

public interface IContratoComentario extends IContrato<Comentarios> {

    boolean comentarPublicacion(String idUsuario, String comentario, Date fecha, String respuesta, int idNumProducto);

}
