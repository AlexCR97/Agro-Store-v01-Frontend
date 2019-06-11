package com.example.agrostore01.CapaDatos.contratos;

import com.example.agrostore01.CapaEntidades.CompraUsuario;
import com.example.agrostore01.CapaEntidades.Venta;
import com.example.agrostore01.CapaEntidades.vistas.VistaVentasUsuario;

import java.util.List;

public interface IContratoVentasUsuario extends IContratoRelacion<Venta> {

    List<VistaVentasUsuario> seleccionarMisVentas(String idUsuario);


}
