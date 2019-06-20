package com.example.agrostore01.CapaNegocios.escritores.vistas;

import com.example.agrostore01.CapaDatos.repositorios.vistas.RepositorioVistaPublicacion;
import com.example.agrostore01.CapaEntidades.vistas.VistaPublicacion;
import com.example.agrostore01.CapaNegocios.escritores.Escritor;

public class EscritorVistaPublicacion extends Escritor<VistaPublicacion> {

    public static final int OPERACION_PUBLICAR_PRODUCTO = 4;

    private RepositorioVistaPublicacion repositorio = new RepositorioVistaPublicacion();

    public EscritorVistaPublicacion(int operacion, VistaPublicacion vistaPublicacion) {
        super(operacion, vistaPublicacion);
    }

    public EscritorVistaPublicacion(int operacion, VistaPublicacion vistaPublicacion, VistaPublicacion entidadCambio) {
        super(operacion, vistaPublicacion, entidadCambio);
    }

    @Override
    public boolean ejecutarCambios() {

        if (operacion == OPERACION_ALTA)
            repositorio.alta(entidad);

        if (operacion == OPERACION_BAJA)
            return repositorio.baja(entidad.getIdTerreno());

        if (operacion == OPERACION_CAMBIO)
            return repositorio.cambio(entidad.getIdTerreno(),entidadCambio);

        if (operacion == OPERACION_PUBLICAR_PRODUCTO)
            return repositorio.publicarProducto(entidad);

        return false;
    }
}
