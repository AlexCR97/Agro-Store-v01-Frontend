package com.example.agrostore01.CapaDatos.repositorios.vistas;

import com.example.agrostore01.CapaDatos.contratos.vistas.IContratoVistaCarrito;
import com.example.agrostore01.CapaDatos.repositorios.Repositorio;
import com.example.agrostore01.CapaEntidades.vistas.VistaCarrito;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class RepositorioVistaMiCarrito extends Repositorio implements IContratoVistaCarrito {

    private String sqlProcMiCarrito;

    public RepositorioVistaMiCarrito() {
        this.sqlProcMiCarrito = "{ call PROC_ESP_MICARRITO(?) }";
    }

    @Override
    public List<VistaCarrito> seleccionarMisProductosEnCarrito(String idUsuario) {
        parametros = new ArrayList<>();
        parametros.add(idUsuario);

        try {
            List<VistaCarrito> productos = new ArrayList<>();

            resultado = ejecutarProcedimientoConSalida(sqlProcMiCarrito);

            /*
            * private int idNumProducto;
            private String producto;
            private int cantidad;
            private BigDecimal precio;
            private byte[] foto;
            */

            while (resultado.next()) {
                int idNumProducto = resultado.getInt("IDNumProducto");
                String producto = resultado.getString("Producto");
                int cantidad = resultado.getInt("Cantidad");
                String precio = resultado.getBigDecimal("Precio").toString();
                byte[] foto = resultado.getBytes("Foto");

                productos.add(new VistaCarrito(idNumProducto, producto, cantidad, precio, foto));
            }
            return productos;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean alta(VistaCarrito e) {
        return false;
    }

    @Override
    public boolean baja(Object id) {
        return false;
    }

    @Override
    public boolean cambio(Object id, VistaCarrito e) {
        return false;
    }

    @Override
    public VistaCarrito seleccionarId(Object id) {
        return null;
    }

    @Override
    public ArrayList<VistaCarrito> seleccionarTodo() {
        return null;
    }
}
