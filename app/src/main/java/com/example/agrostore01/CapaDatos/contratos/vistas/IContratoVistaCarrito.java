package com.example.agrostore01.CapaDatos.contratos.vistas;

import com.example.agrostore01.CapaDatos.contratos.IContrato;
import com.example.agrostore01.CapaEntidades.vistas.VistaCarrito;

import java.util.List;

public interface IContratoVistaCarrito extends IContrato<VistaCarrito> {

    List<VistaCarrito> seleccionarMisProductosEnCarrito(String idUsuario);

}
