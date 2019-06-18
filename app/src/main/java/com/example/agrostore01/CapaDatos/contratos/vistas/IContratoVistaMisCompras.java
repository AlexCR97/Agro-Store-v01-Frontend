package com.example.agrostore01.CapaDatos.contratos.vistas;

import com.example.agrostore01.CapaDatos.contratos.IContrato;
import com.example.agrostore01.CapaEntidades.vistas.VistaMisCompras;

import java.util.List;

public interface IContratoVistaMisCompras extends IContrato<VistaMisCompras> {

    List<VistaMisCompras> seleccionarMisCompras(String idUsuario);

}
