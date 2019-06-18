package com.example.agrostore01.CapaDatos.repositorios.vistas;

import com.example.agrostore01.CapaDatos.contratos.vistas.IContratoVistaMisVentas;
import com.example.agrostore01.CapaDatos.repositorios.Repositorio;
import com.example.agrostore01.CapaEntidades.vistas.VistaMisVentas;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class RepositorioMisVentas extends Repositorio implements IContratoVistaMisVentas {

    private String sqlProcMisVentas;

    public RepositorioMisVentas() {
        this.sqlProcMisVentas = "{ call PROC_ESP_MISVENTAS(?) }";
    }

    @Override
    public List<VistaMisVentas> seleccionarMisVentas(String idUsuario) {
        try {
            parametros = new ArrayList<>();
            parametros.add(idUsuario);

            List<VistaMisVentas> misVentas = new ArrayList<>();

            resultado = ejecutarProcedimientoConSalida(sqlProcMisVentas);

            while (resultado.next()) {
                int numeroDeVenta = resultado.getInt("Numero de Venta");
                BigDecimal precio = resultado.getBigDecimal("Precio");
                String producto = resultado.getString("Producto");
                String fecha = resultado.getString("Fecha");

                misVentas.add(new VistaMisVentas(numeroDeVenta, precio, producto, fecha));
            }
            return misVentas;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean alta(VistaMisVentas e) {
        return false;
    }

    @Override
    public boolean baja(Object id) {
        return false;
    }

    @Override
    public boolean cambio(Object id, VistaMisVentas e) {
        return false;
    }

    @Override
    public VistaMisVentas seleccionarId(Object id) {
        return null;
    }

    @Override
    public ArrayList<VistaMisVentas> seleccionarTodo() {
        return null;
    }
}
