package com.example.agrostore01.CapaDatos.contratos;

import com.example.agrostore01.CapaEntidades.CompraUsuario;
import com.example.agrostore01.CapaEntidades.vistas.VistaCompra;

public interface IContratoCompraUsuario extends IContratoRelacion<CompraUsuario> {

    boolean realizarCompra(VistaCompra compra);

}
