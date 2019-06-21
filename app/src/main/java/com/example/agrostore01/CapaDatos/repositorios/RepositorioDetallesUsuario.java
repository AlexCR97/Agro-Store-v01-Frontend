package com.example.agrostore01.CapaDatos.repositorios;

import com.example.agrostore01.CapaDatos.contratos.IContrato;
import com.example.agrostore01.CapaDatos.contratos.IContratoDetallesUsuario;
import com.example.agrostore01.CapaEntidades.DetallesUsuario;
import com.example.agrostore01.CapaEntidades.Usuario;

import java.sql.SQLException;
import java.util.ArrayList;

public class RepositorioDetallesUsuario extends Repositorio implements IContratoDetallesUsuario {

    private String sqlProcMostrarDetallesUsuario;

    public RepositorioDetallesUsuario() {
        this.sqlAlta = "insert into DetallesUsuario values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        this.sqlBaja = "delete from DetallesUsuario where IDDetalles = ?";
        this.sqlCambio = "update DetallesUsuario set" +
                "IDDetalles = ?," +
                "Nombre = ?," +
                "Apellido = ?," +
                "Calle = ?," +
                "Colonia = ?," +
                "Estado = ?," +
                "Pais = ?," +
                "CP = ?," +
                "EscrituraPermiso = ?," +
                "ESTRELLAS = ?," +
                "RFC = ?," +
                "FirmaElectronica  = ?," +
                "Ciudad = ?, " +
                "Fecha = ? " +
                "where IDDetalles = ?";
        this.sqlSeleccionarId = "select * from DetallesUsuario where IDDetalles = ?";
        this.sqlSeleccionarTodo = "select * from DetallesUsuario";

        this.sqlProcMostrarDetallesUsuario = "{ call PROC_MOSTRAR_DetallesUsuario(?) }";
    }

    @Override
    public boolean alta(DetallesUsuario e) {
        parametros = new ArrayList<>();
        //parametros.add(e.getIdDetalles());
        parametros.add(e.getNombres());
        parametros.add(e.getApellidos());
        parametros.add(e.getCalle());
        parametros.add(e.getColonia());
        parametros.add(e.getEstado());
        parametros.add(e.getPais());
        parametros.add(e.getCp());
        parametros.add(e.getEscrituraOPermiso());
        parametros.add(e.getEstrellas());
        parametros.add(e.getRfc());
        parametros.add(e.getFirmaElectronica());
        parametros.add(e.getCuidad());
        parametros.add(e.getFechaNac());
        return ejecutarConsulta(sqlAlta);
    }

    @Override
    public boolean baja(Object id) {
        parametros = new ArrayList<>();
        parametros.add(id);
        return ejecutarConsulta(sqlBaja);
    }

    @Override
    public boolean cambio(Object id, DetallesUsuario e) {
        parametros = new ArrayList<>();
        parametros.add(e.getIdDetalles());
        parametros.add(e.getNombres());
        parametros.add(e.getApellidos());
        parametros.add(e.getCalle());
        parametros.add(e.getColonia());
        parametros.add(e.getEstado());
        parametros.add(e.getPais());
        parametros.add(e.getCp());
        parametros.add(e.getEscrituraOPermiso());
        parametros.add(e.getEstrellas());
        parametros.add(e.getRfc());
        parametros.add(e.getFirmaElectronica());
        parametros.add(e.getCuidad());
        parametros.add(e.getFechaNac());
        parametros.add(id);
        return ejecutarConsulta(sqlCambio);
    }

    @Override
    public DetallesUsuario seleccionarId(Object id) {
        parametros = new ArrayList<>();
        parametros.add(id);

        resultado = ejecutarLectura(sqlSeleccionarId);

        try {
            resultado.next();

            long IDDetalles = resultado.getLong("IDDetalles");
            String Nombre = resultado.getString("Nombre");
            String Apellido = resultado.getString("Apellido");
            String Calle = resultado.getString("Calle");
            String Colonia =resultado.getString("Colonia");
            String Estado = resultado.getString("Estado");
            String Pais = resultado.getString("Pais");
            int CP = resultado.getInt("CP");
            String EscrituraPermiso = resultado.getString("EscrituraPermiso");
            float ESTRELLAS = resultado.getFloat("ESTRELLAS");
            String RFC = resultado.getString("RFC");
            String FirmaElectronica = resultado.getString("FirmaElectronica");
            String Ciudad = resultado.getString("Ciudad");
            String Fecha = resultado.getString("Fecha");
            String Telefono = resultado.getString("Telefono");

            return new DetallesUsuario(IDDetalles, Nombre, Apellido, Calle, Colonia, Estado, Pais, CP, EscrituraPermiso, ESTRELLAS, RFC, FirmaElectronica, Ciudad, Fecha, Telefono);
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        finally {
            try { if (resultado != null) resultado.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (sentencia != null) sentencia.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (bd.getConexion() != null) bd.getConexion().close(); } catch (SQLException e) { e.printStackTrace(); }
        }
    }

    @Override
    public ArrayList<DetallesUsuario> seleccionarTodo() {
        parametros = new ArrayList<>();

        resultado = ejecutarLectura(sqlSeleccionarTodo);
        ArrayList<DetallesUsuario> detallesUsuarios = new ArrayList<>();

        try {
            while (resultado.next()) {
                long IDDetalles = resultado.getLong("IDDetalles");
                String Nombre = resultado.getString("Nombre");
                String Apellido = resultado.getString("Apellido");
                String Calle = resultado.getString("Calle");
                String Colonia =resultado.getString("Colonia");
                String Estado = resultado.getString("Estado");
                String Pais = resultado.getString("Pais");
                int CP =resultado.getInt("CP");
                String EscrituraPermiso = resultado.getString("EscrituraPermiso");
                float ESTRELLAS = resultado.getFloat("ESTRELLAS");
                String RFC = resultado.getString("RFC");
                String FirmaElectronica = resultado.getString("FirmaElectronica");
                String Ciudad = resultado.getString("Ciudad");
                String Fecha = resultado.getString("Fecha");
                String Telefono = resultado.getString("Telefono");

                detallesUsuarios.add(new DetallesUsuario(IDDetalles, Nombre, Apellido, Calle, Colonia, Estado, Pais, CP, EscrituraPermiso, ESTRELLAS, RFC, FirmaElectronica, Ciudad, Fecha, Telefono));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        finally {
            try { if (resultado != null) resultado.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (sentencia != null) sentencia.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (bd.getConexion() != null) bd.getConexion().close(); } catch (SQLException e) { e.printStackTrace(); }
        }
        return detallesUsuarios;
    }

    @Override
    public DetallesUsuario mostrarDetallesUsuario(String idUsuario) {
        parametros = new ArrayList<>();
        parametros.add(idUsuario);

        resultado = ejecutarProcedimientoConSalida(sqlProcMostrarDetallesUsuario);

        try {
            resultado.next();

            long IDDetalles = resultado.getLong("IDDetalles");
            String Nombre = resultado.getString("Nombre");
            String Apellido = resultado.getString("Apellido");
            String Calle = resultado.getString("Calle");
            String Colonia =resultado.getString("Colonia");
            String Estado = resultado.getString("Estado");
            String Pais = resultado.getString("Pais");
            int CP = resultado.getInt("CP");
            String EscrituraPermiso = resultado.getString("EscrituraPermiso");
            float ESTRELLAS = resultado.getFloat("ESTRELLAS");
            String RFC = resultado.getString("RFC");
            String FirmaElectronica = resultado.getString("FirmaElectronica");
            String Ciudad = resultado.getString("Ciudad");
            String Fecha = resultado.getString("Fecha");
            String Telefono = resultado.getString("Telefono");

            return new DetallesUsuario(IDDetalles, Nombre, Apellido, Calle, Colonia, Estado, Pais, CP, EscrituraPermiso, ESTRELLAS, RFC, FirmaElectronica, Ciudad, Fecha, Telefono);
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
}

