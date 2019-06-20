package com.example.agrostore01.CapaDatos.repositorios;
import com.example.agrostore01.CapaDatos.contratos.IContrato;
import com.example.agrostore01.CapaDatos.contratos.IContratoProductoTerreno;
import com.example.agrostore01.CapaDatos.contratos.IContratoRelacion;
import com.example.agrostore01.CapaEntidades.ProductoTerreno;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;

public class RepositorioProductoTerreno extends Repositorio implements IContrato<ProductoTerreno> {

    public RepositorioProductoTerreno() {
        this.sqlAlta = "insert into ProductoTerreno values (?, ?, ?, ?, ?, ?)";
        this.sqlBaja = "delete from ProductoTerreno where IDNumProducto = ?";
        this.sqlCambio = "update ProductoTerreno set " +
                "IDNumProducto = ?, " +
                "IDProducto = ?, " +
                "IDTerreno = ?, " +
                "Precio = ?, " +
                "Hectareas = ?, " +
                "Descripcion = ? " +
                "where IDNumProducto= ?";
        this.sqlSeleccionarId = "select * from ProductoTerreno where IDNumProducto = ?";
        this.sqlSeleccionarTodo = "select * from ProductoTerreno";
    }

    @Override
    public boolean alta(ProductoTerreno e) {
        parametros = new ArrayList<>();
        parametros.add(e.getIdNumProduc());
        parametros.add(e.getIdProducto());
        parametros.add(e.getIdTerreno());
        parametros.add(e.getPrecio());
        parametros.add(e.getHectareas());
        parametros.add(e.getDescripcion());
        return ejecutarConsulta(sqlAlta);
    }

    @Override
    public boolean baja(Object id) {
        parametros = new ArrayList<>();
        parametros.add(id);
        return ejecutarConsulta(sqlBaja);
    }

    @Override
    public boolean cambio(Object id, ProductoTerreno e) {
        parametros = new ArrayList<>();
        parametros.add(e.getIdNumProduc());
        parametros.add(e.getIdProducto());
        parametros.add(e.getIdTerreno());
        parametros.add(e.getPrecio());
        parametros.add(e.getHectareas());
        parametros.add(e.getDescripcion());
        parametros.add(id);
        return ejecutarConsulta(sqlCambio);
    }

    @Override
    public ProductoTerreno seleccionarId(Object id) {
        parametros = new ArrayList<>();
        parametros.add(id);

        resultado = ejecutarLectura(sqlSeleccionarId);

        try {
            resultado.next();

            long idnumprod= resultado.getLong("IDNumProducto");
            long idproducto = resultado.getLong("IDProducto");
            long idterreno =resultado.getLong("IDTerreno");
            BigDecimal precio = resultado.getBigDecimal("Precio");
            int hectareas = resultado.getInt("Hectareas");
            String descripcion = resultado.getString("Descripcion");

            return new ProductoTerreno(idnumprod, idproducto, idterreno, precio, hectareas, descripcion);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        finally {
            try { if (resultado != null) resultado.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (sentencia != null) sentencia.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (bd.getConexion() != null) bd.getConexion().close(); } catch (Exception e) { e.printStackTrace(); }
        }
    }

    @Override
    public ArrayList<ProductoTerreno> seleccionarTodo() {
        parametros = new ArrayList<>();

        resultado = ejecutarLectura(sqlSeleccionarTodo);
        ArrayList<ProductoTerreno> productoTerrenos = new ArrayList<>();

        try {
            while (resultado.next()) {
                long idnumprod= resultado.getLong("IDNumProducto");
                long idproducto = resultado.getLong("IDProducto");
                long idterreno =resultado.getLong("IDTerreno");
                BigDecimal precio = resultado.getBigDecimal("Precio");
                int hectareas = resultado.getInt("Hectareas");
                String descripcion = resultado.getString("Descripcion");

                productoTerrenos.add(new ProductoTerreno(idnumprod, idproducto, idterreno, precio, hectareas, descripcion));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        finally {
            try { if (resultado != null) resultado.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (sentencia != null) sentencia.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (bd.getConexion() != null) bd.getConexion().close(); } catch (Exception e) { e.printStackTrace(); }
        }
        return productoTerrenos;
    }

}
