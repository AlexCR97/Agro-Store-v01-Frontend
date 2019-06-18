package com.example.agrostore01.CapaDatos.repositorios;

import com.example.agrostore01.CapaDatos.contratos.IContratoCompraUsuario;
import com.example.agrostore01.CapaEntidades.CompraUsuario;
import com.example.agrostore01.CapaEntidades.vistas.VistaCompra;

import java.util.ArrayList;

public class RepositorioCompraUsuario extends Repositorio implements IContratoCompraUsuario {

    private String sqlProcRealizarCompra;

    public RepositorioCompraUsuario() {
        this.sqlAlta = "insert into CompraUsuario values (?, ?)";
        this.sqlBaja = "delete from CompraUSuario where IDUsuario = ?";
        this.sqlCambio = "update CompraUSuario set" +
                "IDUsuario  = ?," +
                "IDProductoComprado= ?" +
                "where IDUsuario = ?";
        this.sqlSeleccionarId = "select * from CompraUSuario where IDUsuario = ?";
        this.sqlSeleccionarTodo = "select * from CompraUSuario";

        this.sqlProcRealizarCompra = "{ call PROC_ESP_COMPRA(?, ?, ?) }";
    }

    @Override
    public boolean alta(CompraUsuario e) {
       parametros = new ArrayList<>();
       // parametros.add(e.getIdUsuario());
        parametros.add(e.getIdProductoComprado());
        return ejecutarConsulta(sqlAlta);
    }

    @Override
    public boolean baja(Object id) {
        parametros = new ArrayList<>();
        parametros.add(id);
        return ejecutarConsulta(sqlBaja);
    }

    @Override
    public boolean cambio(Object id, CompraUsuario e) {
        parametros = new ArrayList<>();
        // parametros.add(e.getIdUsuario());
        parametros.add(e.getIdProductoComprado());
        return ejecutarConsulta(sqlCambio);
    }

    @Override
    public CompraUsuario seleccionarId(Object id) {
        parametros = new ArrayList<>();
        parametros.add(id);

        resultado = ejecutarLectura(sqlSeleccionarId);

        try {
            resultado.next();

            String idUsuario = resultado.getString("IDUsuario");
            long idProductocomprado = resultado.getLong("IDProductoComprado");
            return new CompraUsuario(idUsuario,idProductocomprado);
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
    public ArrayList<CompraUsuario> seleccionarTodo() {
        parametros = new ArrayList<>();

        resultado = ejecutarLectura(sqlSeleccionarTodo);
        ArrayList<CompraUsuario> compraUsuarios = new ArrayList<>();

        try {
            while (resultado.next()) {
                String idUsuario = resultado.getString("IDUsuario");
                long idProductocomprado = resultado.getLong("IDProductoComprado");
                compraUsuarios.add(new CompraUsuario(idUsuario,idProductocomprado));
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
        return compraUsuarios;
    }


    @Override
    public boolean bajaEspecifica(CompraUsuario e) {
        return false;
    }

    @Override
    public ArrayList<CompraUsuario> seleccionarTodosId(Object id) {
        parametros = new ArrayList<>();
        parametros.add(id);

        resultado = ejecutarLectura(sqlSeleccionarId);
        ArrayList<CompraUsuario> compraUsuarios = new ArrayList<>();

        try {
            while (resultado.next()) {
                String idUsuario = resultado.getString("IDUsuario");
                long idProductocomprado = resultado.getLong("IDProductoComprado");
                compraUsuarios.add(new CompraUsuario(idUsuario,idProductocomprado));
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
        return compraUsuarios;
    }

    @Override
    public boolean realizarCompra(VistaCompra compra) {
        parametros = new ArrayList<>();
        parametros.add(compra.getIdNumProducto());
        parametros.add(compra.getIdUsuario());
        parametros.add(compra.getCantidad());

        try {
            return ejecutarProcedimiento(sqlProcRealizarCompra);
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

