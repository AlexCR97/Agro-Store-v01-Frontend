package com.example.agrostore01.CapaDatos.contratos.vistas;

import com.example.agrostore01.CapaDatos.contratos.IContrato;
import com.example.agrostore01.CapaEntidades.vistas.VistaPublicacion;

public interface IContratoVistaPublicacion extends IContrato<VistaPublicacion> {

    boolean publicarProducto(VistaPublicacion vistaPublicacion);

}
