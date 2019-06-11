package com.example.agrostore01.CapaDatos.repositorios;


import com.example.agrostore01.CapaDatos.contratos.IContratoComprasUsuario;
import com.example.agrostore01.CapaEntidades.CompraUsuario;
import com.example.agrostore01.CapaEntidades.vistas.VistaMisCompras;

import java.util.ArrayList;
import java.util.List;

public class RepositorioCompraUsuario extends Repositorio implements IContratoComprasUsuario {

    private String sqlProcSeleccionarMiscompras;
    public RepositorioCompraUsuario() {
        this.sqlAlta = "insert into CompraUsuario values (?,?)";
        this.sqlBaja = "delete from CompraUSuario where IDUsuario = ?";
        this.sqlCambio = "update CompraUSuario set" +
                "IDUsuario  = ?," +
                "IDProductoComprado= ?" +
                "where IDUsuario = ?";
        this.sqlSeleccionarId = "select * from CompraUSuario where IDUsuario = ?";
        this.sqlSeleccionarTodo = "select * from CompraUSuario";
        this.sqlProcSeleccionarMiscompras= " { call PROC_ESP_MISCOMPRAS (?)}";


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
    public List<VistaMisCompras> selecccionarMisCompras(String idUsuario) {
        parametros = new ArrayList<>();
        parametros.add(idUsuario);

        List<VistaMisCompras> compras = new ArrayList<>();
        resultado = ejecutarProcedimientoConSalida(sqlProcSeleccionarMiscompras);

        try {
            while(resultado.next()){
                 int numeroDeCompra = resultado.getInt("[Numero de Compra]");
                 int numeroDeLote = resultado.getInt(" [Numero de lote]");
                 boolean estado = resultado.getBoolean("Estado");
                 String tiempo = resultado.getString("Localizacion");
                 compras.add(new VistaMisCompras(numeroDeCompra,numeroDeLote,estado,tiempo));
            }
            return compras;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

