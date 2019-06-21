package com.example.agrostore01.CapaDatos.repositorios.vistas;

import com.example.agrostore01.CapaDatos.contratos.vistas.IContratoVistaBusquedaProducto;
import com.example.agrostore01.CapaDatos.repositorios.Repositorio;
import com.example.agrostore01.CapaEntidades.vistas.VistaBusquedaProducto;

import java.math.BigDecimal;
import java.util.ArrayList;

public class RepositorioVistaBusquedaProducto extends Repositorio implements IContratoVistaBusquedaProducto {

    private String sqlProcDetallesProducto;

    public RepositorioVistaBusquedaProducto() {
        this.sqlProcDetallesProducto = "{ call PROC_ESP_PRODUCTO_COMPLETO(?) }";
    }

    @Override
    public boolean alta(VistaBusquedaProducto e) {
        return false;
    }

    @Override
    public boolean baja(Object id) {
        return false;
    }

    @Override
    public boolean cambio(Object id, VistaBusquedaProducto e) {
        return false;
    }

    @Override
    public VistaBusquedaProducto seleccionarId(Object id) {
        return null;
    }

    @Override
    public ArrayList<VistaBusquedaProducto> seleccionarTodo() {
        return null;
    }

    @Override
    public VistaBusquedaProducto seleccionarEntidadIdProducto(Object idProducto) {
        try {
            parametros = new ArrayList<>();
            parametros.add(idProducto);

            resultado = ejecutarProcedimientoConSalida(sqlProcDetallesProducto);

            resultado.next();

            /**
             * ProductoTerreno.[Nombre Publicacion]
             */

            String producto = resultado.getString("Producto");
            String nombre = resultado.getString("Nombre");
            String apellido = resultado.getString("Apellido");
            byte[] foto = resultado.getBytes("Foto");
            BigDecimal precio = resultado.getBigDecimal("Precio");
            int hectareas = resultado.getInt("Hectareas");
            String descripcion = resultado.getString("Descripcion");
            String estado = resultado.getString("Estado");
            String ciudad = resultado.getString("Ciudad");
            String tituloPublicacion = resultado.getString("Nombre Publicacion");

            return new VistaBusquedaProducto(producto, nombre, apellido, foto, precio, hectareas, descripcion, estado, ciudad, tituloPublicacion);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
