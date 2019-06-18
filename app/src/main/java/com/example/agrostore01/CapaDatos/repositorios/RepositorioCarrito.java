package com.example.agrostore01.CapaDatos.repositorios;

import com.example.agrostore01.CapaDatos.contratos.IContrato;
import com.example.agrostore01.CapaDatos.contratos.IContratoCarrito;
import com.example.agrostore01.CapaDatos.contratos.IContratoRelacion;
import com.example.agrostore01.CapaEntidades.Carrito;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;

public class RepositorioCarrito extends Repositorio implements IContratoCarrito {

    private String sqlProcAgregarProducto;

    public RepositorioCarrito() {
        this.sqlAlta="insert into Carrito values (?)";
        this.sqlBaja="delete from Carrito where IDCarrito = ?";
        this.sqlCambio="update Carrito set "+
                "IDUsuario = ?" +
                "where IDCarrito = ?";
        this.sqlSeleccionarId="select * from Carrito where IDCarrito = ?";
        this.sqlSeleccionarTodo="select * from Carrito";

        this.sqlProcAgregarProducto = "{ call PROC_ESP_REALIZARCOMPRA(?, ?, ?) }";
    }

    @Override
    public boolean alta(Carrito e) {
        parametros = new ArrayList<>();
       // parametros.add(e.getIdCar());
        parametros.add(e.getIdUsuario());
        return ejecutarConsulta(sqlAlta);
    }

    @Override
    public boolean baja(Object id) {
        parametros = new ArrayList<>();
        parametros.add(id);
        return ejecutarConsulta(sqlBaja);
    }

    @Override
    public boolean cambio(Object id, Carrito e) {
        parametros= new ArrayList<>();
        parametros.add(e.getIdCar());
        parametros.add(e.getIdUsuario());
        parametros.add(id);
        return ejecutarConsulta(sqlCambio);
    }

    @Override
    public Carrito seleccionarId(Object id) {
        parametros = new ArrayList<>();
        parametros.add(id);

        resultado = ejecutarLectura(sqlSeleccionarId);

        try {
            resultado.next();

            int IDCarrito = resultado.getInt("IDCarrito");
            String IDUsuario = resultado.getString("IDUsuario");

            return new Carrito(IDCarrito,IDUsuario);
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
    public ArrayList<Carrito> seleccionarTodo() {
        parametros = new ArrayList<>();

        resultado = ejecutarLectura(sqlSeleccionarTodo);
        ArrayList<Carrito> carrito = new ArrayList<>();

        try {
            while (resultado.next()) {
                int IDCarrito = resultado.getInt("IDCarrito");
                String IDUsuario = resultado.getString("IDUsuario");
                carrito.add(new Carrito(IDCarrito,IDUsuario));
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
        return carrito;
    }

    @Override
    public boolean bajaEspecifica(Carrito e) {
        return false;
    }

    @Override
    public ArrayList<Carrito> seleccionarTodosId(Object id) {
        parametros = new ArrayList<>();
        parametros.add(id);

        resultado = ejecutarLectura(sqlSeleccionarId);
        ArrayList<Carrito> carrito = new ArrayList<>();

        try {
            while (resultado.next()) {
                int IDCarrito = resultado.getInt("IDCarrito");
                String IDUsuario = resultado.getString("IDUsuario");
                carrito.add(new Carrito(IDCarrito,IDUsuario));
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
        return carrito;
    }

    @Override
    public boolean agregarProductoACarrito(int idNumProducto, String idUsuario, int cantidad) {
        parametros = new ArrayList<>();
        parametros.add(idNumProducto);
        parametros.add(idUsuario);
        parametros.add(cantidad);

        try {
            return ejecutarProcedimiento(sqlProcAgregarProducto);
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
