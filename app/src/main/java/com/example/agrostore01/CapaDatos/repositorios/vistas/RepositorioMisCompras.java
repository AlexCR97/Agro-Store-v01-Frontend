package com.example.agrostore01.CapaDatos.repositorios.vistas;

import com.example.agrostore01.CapaDatos.contratos.vistas.IContratoVistaMisCompras;
import com.example.agrostore01.CapaDatos.repositorios.Repositorio;
import com.example.agrostore01.CapaEntidades.vistas.VistaMisCompras;

import java.util.ArrayList;
import java.util.List;

public class RepositorioMisCompras extends Repositorio implements IContratoVistaMisCompras {

    private String sqlProcMisCompras;

    public RepositorioMisCompras() {
        this.sqlProcMisCompras = "{ call PROC_ESP_MISCOMPRAS(?) }";
    }

    @Override
    public List<VistaMisCompras> seleccionarMisCompras(String idUsuario) {
        try {
            parametros = new ArrayList<>();
            parametros.add(idUsuario);

            List<VistaMisCompras> misCompras = new ArrayList<>();

            resultado = ejecutarProcedimientoConSalida(sqlProcMisCompras);

            while (resultado.next()) {
                int numeroDeCompra = resultado.getInt("Numero de Compra");
                int numeroDeLote = resultado.getInt("Numero de lote");
                boolean estado = resultado.getBoolean("Estado");
                String tiempo = resultado.getString("Tiempo");

                misCompras.add(new VistaMisCompras(numeroDeCompra, numeroDeLote, estado, tiempo));
            }

            return misCompras;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean alta(VistaMisCompras e) {
        return false;
    }

    @Override
    public boolean baja(Object id) {
        return false;
    }

    @Override
    public boolean cambio(Object id, VistaMisCompras e) {
        return false;
    }

    @Override
    public VistaMisCompras seleccionarId(Object id) {
        return null;
    }

    @Override
    public ArrayList<VistaMisCompras> seleccionarTodo() {
        return null;
    }
}
