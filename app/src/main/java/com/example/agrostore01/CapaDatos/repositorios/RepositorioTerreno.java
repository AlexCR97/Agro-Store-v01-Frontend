package com.example.agrostore01.CapaDatos.repositorios;

import com.example.agrostore01.CapaDatos.contratos.IContratoTerreno;
import com.example.agrostore01.CapaEntidades.Terreno;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RepositorioTerreno extends Repositorio implements IContratoTerreno {

    private String sqlProcAgregarNuevoTerreno;
    private String sqlProcSeleccionarMisTerrenos;

    public RepositorioTerreno(){
        this.sqlAlta = "insert into Terreno values (?, ?, ?)";
        this.sqlBaja = "delete from Terreno where IDTerreno = ?";
        this.sqlCambio = "update Terreno set " +
                "IDTerreno = ?," +
                "Tamaño = ?, " +
                "Medida = ?, " +
                "Tipo = ?, " +
                "where IDTerreno = ?";
        this.sqlSeleccionarId = "select * from Terreno where IDTerreno = ?";
        this.sqlSeleccionarTodo = "select * from Terreno";

        this.sqlProcAgregarNuevoTerreno = "{ call PROC_ALTA_Terreno(?, ?, ?, ?, ?) }";
        this.sqlProcSeleccionarMisTerrenos = "{ call PROC_ESP_MISTEERENOS(?) }";
    }

    @Override
    public boolean alta(Terreno e) {
        parametros= new ArrayList<>();
        parametros.add(e.getTamaño());
        parametros.add(e.getMedida());
        parametros.add(e.getTipo());
        return ejecutarConsulta(sqlAlta);
    }

    @Override
    public boolean baja(Object id) {
        parametros = new ArrayList<>();
        parametros.add(id);
        return ejecutarConsulta(sqlBaja);
    }

    @Override
    public boolean cambio(Object id, Terreno e) {
        parametros= new ArrayList<>();
        parametros.add(e.getIdTerreno());
        parametros.add(e.getTamaño());
        parametros.add(e.getMedida());
        parametros.add(e.getTipo());
        parametros.add(id);
        return ejecutarConsulta(sqlCambio);
    }

    @Override
    public Terreno seleccionarId(Object id) {
        parametros = new ArrayList<>();
        parametros.add(id);

        resultado = ejecutarLectura(sqlSeleccionarId);

        try {
            resultado.next();
            long idTerreno = resultado.getLong("IDTerreno");
            int tamaño = resultado.getInt("Tamaño");
            String medida = resultado.getString("Medida");
            String tipo = resultado.getString("Tipo");
            String nombre = resultado.getString("Nombre");
            return new Terreno(idTerreno,tamaño,medida,tipo, nombre);
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
    public ArrayList<Terreno> seleccionarTodo() {
        parametros = new ArrayList<>();

        resultado = ejecutarLectura(sqlSeleccionarTodo);
        ArrayList<Terreno> terrenos = new ArrayList<>();

        try {
            while (resultado.next()) {
                long idTerreno = resultado.getLong("IDTerreno");
                int tamaño = resultado.getInt("Tamaño");
                String medida = resultado.getString("Medida");
                String tipo = resultado.getString("Tipo");
                String nombre = resultado.getString("Nombre");
                terrenos.add(new Terreno(idTerreno,tamaño,medida,tipo, nombre));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        finally {
            try { if (resultado != null) resultado.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (sentencia != null) sentencia.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (bd.getConexion() != null) bd.getConexion().close(); } catch (Exception e) { e.printStackTrace(); }
        }
        return terrenos;
    }

    @Override
    public boolean agregarNuevoTerreno(String idUsuario, int tamano, String medida, String tipo, String nombreTerreno) {
        parametros = new ArrayList<>();
        parametros.add(idUsuario);
        parametros.add(tamano);
        parametros.add(medida);
        parametros.add(tipo);
        parametros.add(nombreTerreno);

        try {
            return ejecutarProcedimiento(sqlProcAgregarNuevoTerreno);
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Terreno> seleccionarMisTerrenos(String idUsuario) {
        parametros = new ArrayList<>();
        parametros.add(idUsuario);

        try {
            resultado = ejecutarProcedimientoConSalida(sqlProcSeleccionarMisTerrenos);
            List<Terreno> terrenos = new ArrayList<>();

            while (resultado.next()) {
                long idTerreno = resultado.getLong("IDTerreno");
                String medida = resultado.getString("Medida");
                int tamano = resultado.getInt("Tamaño");
                String tipo = resultado.getString("Tipo");
                String nombre = resultado.getString("Nombre");

                terrenos.add(new Terreno(idTerreno, tamano, medida, tipo, nombre));
            }
            return terrenos;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
