package com.example.agrostore01.CapaDatos.contratos;

import com.example.agrostore01.CapaEntidades.CompraUsuario;
import com.example.agrostore01.CapaEntidades.vistas.VistaMisCompras;

import java.util.List;

public interface IContratoComprasUsuario extends IContratoRelacion<CompraUsuario> {
    List<VistaMisCompras> selecccionarMisCompras(String idUsuario);

}
