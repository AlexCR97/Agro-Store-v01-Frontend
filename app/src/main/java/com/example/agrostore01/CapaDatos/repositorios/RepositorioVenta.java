package com.example.agrostore01.CapaDatos.repositorios;

import com.example.agrostore01.CapaDatos.contratos.IContratoRelacion;
import com.example.agrostore01.CapaEntidades.Venta;

import java.util.ArrayList;

public class RepositorioVenta extends Repositorio implements IContratoRelacion<Venta> {

    public RepositorioVenta(){
        this.sqlAlta = "insert into Venta values (?, ?, ?)";
        this.sqlBaja = "delete from Venta where IDVenta = ?";
        this.sqlCambio = "update Venta set " +
                "IDVenta = ?," +
                "IDUsuarioAgricultor = ?, " +
                "IDUsuarioCliente = ?, " +
                "IDDetalle = ? " +
                "where IDVenta = ?";
        this.sqlSeleccionarId = "select * from Venta where IDVenta = ?";
        this.sqlSeleccionarTodo = "select * from Venta";

    }
    @Override
    public boolean alta(Venta e) {
        parametros = new ArrayList<>();
        //parametros.add(e.getIdVenta());
        parametros.add(e.getIdUsuarioAgricultor());
        parametros.add(e.getIdUsuarioCliente());
        parametros.add(e.getIdDetalle());
        return ejecutarConsulta(sqlAlta);
    }

    @Override
    public boolean baja(Object id) {
        parametros = new ArrayList<>();
        parametros.add(id);
        return ejecutarConsulta(sqlBaja);
    }

    @Override
    public boolean cambio(Object id, Venta e) {
        parametros = new ArrayList<>();
        parametros.add(e.getIdVenta());
        parametros.add(e.getIdUsuarioAgricultor());
        parametros.add(e.getIdUsuarioCliente());
        parametros.add(e.getIdDetalle());
        parametros.add(id);
        return ejecutarConsulta(sqlCambio);
    }

    @Override
    public Venta seleccionarId(Object id) {
        parametros = new ArrayList<>();
        parametros.add(id);

        resultado = ejecutarLectura(sqlSeleccionarId);

        try {
            resultado.next();
            long idVenta = resultado.getLong("IDVenta");
            String idUsuarioAgricultor = resultado.getString("IDUsuarioAgricultor");
            String idUsuarioCliente= resultado.getString("IDUsuarioCliente");
            long idDetalle = resultado.getLong("IDDetalle");

            return new Venta(idVenta,idUsuarioAgricultor,idUsuarioCliente,idDetalle);
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
    public ArrayList<Venta> seleccionarTodo() {
        parametros = new ArrayList<>();

        resultado = ejecutarLectura(sqlSeleccionarTodo);
        ArrayList<Venta> ventas = new ArrayList<>();

        try {
            while (resultado.next()) {
                long idVenta = resultado.getLong("IDVenta");
                String idUsuarioAgricultor = resultado.getString("IDUsuarioAgricultor");
                String idUsuarioCliente= resultado.getString("IDUsuarioCliente");
                long idDetalle = resultado.getLong("IDDetalle");
                ventas.add(new Venta(idVenta,idUsuarioAgricultor,idUsuarioCliente,idDetalle));
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
        return ventas;
    }

    @Override
    public boolean bajaEspecifica(Venta e) {
        return false;
    }

    @Override
    public ArrayList<Venta> seleccionarTodosId(Object id) {
        parametros = new ArrayList<>();
        parametros.add(id);

        resultado = ejecutarLectura(sqlSeleccionarId);
        ArrayList<Venta> ventas = new ArrayList<>();

        try {
            while (resultado.next()) {
                long idVenta = resultado.getLong("IDVenta");
                String idUsuarioAgricultor = resultado.getString("IDUsuarioAgricultor");
                String idUsuarioCliente= resultado.getString("IDUsuarioCliente");
                long idDetalle = resultado.getLong("IDDetalle");
                ventas.add(new Venta(idVenta,idUsuarioAgricultor,idUsuarioCliente,idDetalle));
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
        return ventas;
    }
}
