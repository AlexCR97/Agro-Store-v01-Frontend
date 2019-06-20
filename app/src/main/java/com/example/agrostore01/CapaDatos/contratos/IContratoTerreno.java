package com.example.agrostore01.CapaDatos.contratos;

import com.example.agrostore01.CapaEntidades.Terreno;

import java.util.List;

public interface IContratoTerreno extends IContrato<Terreno> {

    boolean agregarNuevoTerreno(String idUsuario, int tamano, String medida, String tipo, String nombreTerreno);
    List<Terreno> seleccionarMisTerrenos(String idUsuario);

}
