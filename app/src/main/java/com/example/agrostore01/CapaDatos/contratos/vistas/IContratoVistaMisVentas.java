package com.example.agrostore01.CapaDatos.contratos.vistas;

import com.example.agrostore01.CapaDatos.contratos.IContrato;
import com.example.agrostore01.CapaEntidades.vistas.VistaMisCompras;
import com.example.agrostore01.CapaEntidades.vistas.VistaMisVentas;

import java.util.List;

public interface IContratoVistaMisVentas extends IContrato<VistaMisVentas> {

    List<VistaMisVentas> seleccionarMisVentas(String idUsuario);

}
