package com.example.agrostore01.CapaDatos.repositorios;

import com.example.agrostore01.CapaDatos.contratos.IContratoUsuario;
import com.example.agrostore01.CapaEntidades.DetallesUsuario;
import com.example.agrostore01.CapaEntidades.Usuario;

import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

public class RepositorioUsuario extends Repositorio implements IContratoUsuario {

    private String sqlSeleccionarNombreUsuario;
    private String sqlProcActualizarContrasena;
    private String sqlProcActualizarDatos;
    private String sqlProcConfirmarContrasena;
    private String sqlProcConfirmarExistencia;
    private String sqlProcSeleccionarContrasena;
    private String sqlProcRegistrarUsuario;

    public RepositorioUsuario(){
        this.sqlAlta = "insert into Usuario values (?, ?, ?, ?, ?)";
        this.sqlBaja = "delete from Usuario where IDUsuario = ?";
        this.sqlCambio = "update Usuario set " +
                "IDUsuario = ?, " +
                "Contraseña = ?, " +
                "IDTipo = ?, " +
                "IDDetalles = ?, " +
                "Foto = ?, " +
                "Correo = ? " +
                "where IDUsuario = ?";
        this.sqlSeleccionarId = "select * from Usuario where IDUsuario = ?";
        this.sqlSeleccionarTodo = "select * from Usuario";
        this.sqlSeleccionarNombreUsuario = "select * from [Usuario] where [Usuario].Usuario = ?";

        this.sqlProcActualizarContrasena = "{ call PROC_ESP_NUEVA_CONTRASEÑA(?, ?) }";
        this.sqlProcActualizarDatos = "{ call PROC_ESP_CAMBIO_USUARIO(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) }";
        this.sqlProcConfirmarContrasena = "{ call PROC_USUARIO_CONFIRMAR_CONTRASEÑA(?, ?, ?) }";
        this.sqlProcConfirmarExistencia = "{ call PROC_USUARIO_CONFIRMAR_EXISTENCIA(?, ?, ?) }";
        this.sqlProcSeleccionarContrasena = "{ call PROC_USUARIO_RETURN_CONTRASEÑA(?, ?) }";
        this.sqlProcRegistrarUsuario = "{ call PROC_ESP_ALTA_USER(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) }";
    }

    @Override
    public boolean alta(Usuario e) {
        parametros = new ArrayList<>();
        parametros.add(e.getIdUsuario());
        parametros.add(e.getContrasenaUsuario());
        parametros.add(e.getIdTipo());
        parametros.add(e.getIdDetalles());
        parametros.add(e.getFoto());
        parametros.add(e.getCorreo());
        return ejecutarConsulta(sqlAlta);
    }

    @Override
    public boolean baja(Object id) {
        parametros = new ArrayList<>();
        parametros.add(id);
        return ejecutarConsulta(sqlBaja);
    }

    @Override
    public boolean cambio(Object id, Usuario e) {
        parametros = new ArrayList<>();
        parametros.add(e.getIdUsuario());
        parametros.add(e.getContrasenaUsuario());
        parametros.add(e.getIdTipo());
        parametros.add(e.getIdDetalles());
        parametros.add(e.getFoto());
        parametros.add(e.getCorreo());
        parametros.add(id);
        return ejecutarConsulta(sqlCambio);
    }

    @Override
    public Usuario seleccionarId(Object id) {
        parametros = new ArrayList<>();
        parametros.add(id);

        resultado = ejecutarLectura(sqlSeleccionarId);

        try {
            resultado.next();
            String idUsuario = resultado.getString("IDUsuario");
            byte[] foto = resultado.getBytes("Foto");
            int idtipo = resultado.getInt("IDTipo");
            long iddetalles= resultado.getLong("IDDetalles");
            String usuario = resultado.getString("Usuario");
            String contraseña= resultado.getString("Contraseña");
            String correo = resultado.getString("Correo");
            return new Usuario(idUsuario, foto, idtipo, iddetalles, usuario, contraseña, correo);
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
    public ArrayList<Usuario> seleccionarTodo() {
        parametros = new ArrayList<>();

        resultado = ejecutarLectura(sqlSeleccionarTodo);
        ArrayList<Usuario> usuarios = new ArrayList<>();

        try {
            while (resultado.next()) {
                String idUsuario = resultado.getString("IDUsuario");
                byte[] foto = resultado.getBytes("Foto");
                int idtipo = resultado.getInt("IDTipo");
                long iddetalles = resultado.getLong("IDDetalles");
                String usuario = resultado.getString("Usuario");
                String contrasena = resultado.getString("Contraseña");
                String correo = resultado.getString("Correo");
                usuarios.add(new Usuario(idUsuario, foto, idtipo, iddetalles, usuario, contrasena, correo));
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
        return usuarios;
    }

    @Override
    public Usuario seleccionarNombreUsuario(String nombreUsuario) {
        parametros = new ArrayList<>();
        parametros.add(nombreUsuario);

        resultado = ejecutarLectura(sqlSeleccionarNombreUsuario);

        try {
            resultado.next();
            String idUsuario = resultado.getString("IDUsuario");
            byte[] foto = resultado.getBytes("Foto");
            int idtipo = resultado.getInt("IDTipo");
            long iddetalles= resultado.getLong("IDDetalles");
            String usuario = resultado.getString("Usuario");
            String contraseña = resultado.getString("Contraseña");
            String correo = resultado.getString("Correo");
            return new Usuario(idUsuario, foto, idtipo, iddetalles, usuario, contraseña, correo);
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
    public boolean confirmarContrasena(String idUsuario, String contrasena) {
        parametros = new ArrayList<>();
        parametrosDeSalida = new ArrayList<>();

        parametros.add(idUsuario);
        parametros.add(contrasena);
        parametrosDeSalida.add(Types.BOOLEAN);

        resultado = ejecutarProcedimientoConSalida(sqlProcConfirmarContrasena);
        try {
            if (resultado != null)
                while (resultado.next()) {}

            boolean success = procedimiento.getBoolean(3);

            System.out.println("The output paramter is: " + success);
            return success;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean confirmarExistencia(String idUsuario, String contrasena) {
        parametros = new ArrayList<>();
        parametrosDeSalida = new ArrayList<>();

        parametros.add(idUsuario);
        parametros.add(contrasena);
        parametrosDeSalida.add(Types.BOOLEAN);

        resultado = ejecutarProcedimientoConSalida(sqlProcConfirmarExistencia);
        try {
            if (resultado != null)
                while (resultado.next()) {}

            boolean success = procedimiento.getBoolean(3);

            System.out.println("The output paramter is: " + success);
            return success;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String seleccionarContrasena(String idUsuario) {
        parametros = new ArrayList<>();
        parametrosDeSalida = new ArrayList<>();

        parametros.add(idUsuario);
        parametrosDeSalida.add(Types.VARCHAR);

        resultado = ejecutarProcedimientoConSalida(sqlProcSeleccionarContrasena);
        try {
            if (resultado != null)
                while (resultado.next()) {}

            String contrasena = procedimiento.getString(2);

            System.out.println("The output paramter is: " + contrasena);
            return contrasena;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean registrarUsuario(Usuario usuario, DetallesUsuario detallesUsuario) {
        parametros = new ArrayList<>();

        parametros.add(detallesUsuario.getNombres());
        parametros.add(detallesUsuario.getApellidos());
        parametros.add(detallesUsuario.getCalle());
        parametros.add(detallesUsuario.getColonia());
        parametros.add(detallesUsuario.getEstado());
        parametros.add(detallesUsuario.getPais());
        parametros.add(detallesUsuario.getCp());
        parametros.add(detallesUsuario.getEscrituraOPermiso());
        parametros.add(detallesUsuario.getEstrellas());
        parametros.add(detallesUsuario.getRfc());
        parametros.add(detallesUsuario.getFirmaElectronica());
        parametros.add(detallesUsuario.getCuidad());
        parametros.add(detallesUsuario.getFechaNac());
        parametros.add(detallesUsuario.getTelefono());

        parametros.add(usuario.getIdUsuario());
        parametros.add(usuario.getUsuario());
        parametros.add(usuario.getContrasenaUsuario());
        parametros.add(usuario.getCorreo());
        parametros.add(usuario.getFoto());
        parametros.add(usuario.getIdTipo());

        try {
            return ejecutarProcedimiento(sqlProcRegistrarUsuario);
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean actualizarContrasena(String idUsuario, String nuevaContrasena) {
        parametros = new ArrayList<>();
        parametros.add(idUsuario);
        parametros.add(nuevaContrasena);

        try {
            return ejecutarProcedimiento(sqlProcActualizarContrasena);
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean actualizarDatos(Usuario usuario, DetallesUsuario detallesUsuario) {
        parametros = new ArrayList<>();

        parametros.add(usuario.getIdDetalles());
        parametros.add(detallesUsuario.getNombres());
        parametros.add(detallesUsuario.getApellidos());
        parametros.add(detallesUsuario.getCalle());
        parametros.add(detallesUsuario.getColonia());
        parametros.add(detallesUsuario.getEstado());
        parametros.add(detallesUsuario.getPais());
        parametros.add(detallesUsuario.getCp());
        parametros.add(detallesUsuario.getEscrituraOPermiso());
        parametros.add(detallesUsuario.getEstrellas());
        parametros.add(detallesUsuario.getRfc());
        parametros.add(detallesUsuario.getFirmaElectronica());
        parametros.add(detallesUsuario.getCuidad());
        parametros.add(detallesUsuario.getFechaNac());
        parametros.add(detallesUsuario.getTelefono());

        parametros.add(usuario.getIdUsuario());
        parametros.add(usuario.getCorreo());
        parametros.add(usuario.getFoto());

        try {
            return ejecutarProcedimiento(sqlProcActualizarDatos);
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
