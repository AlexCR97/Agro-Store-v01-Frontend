package com.example.agrostore01.CapaNegocios.escritores;

import com.example.agrostore01.CapaDatos.repositorios.RepositorioProductoTerreno;
import com.example.agrostore01.CapaEntidades.ProductoTerreno;

public class EscritorProductoTerreno extends EscritorRelacion<ProductoTerreno> {

    private RepositorioProductoTerreno repositorio = new RepositorioProductoTerreno();

    public EscritorProductoTerreno(int operacion, ProductoTerreno productoTerreno) {
        super(operacion, productoTerreno);
    }

    public EscritorProductoTerreno(int operacion, ProductoTerreno productoTerreno, ProductoTerreno entidadCambio) {
        super(operacion, productoTerreno, entidadCambio);
    }

    @Override
    public boolean ejecutarCambios() {

        if (operacion == OPERACION_ALTA)
            repositorio.alta(entidad);

        if (operacion == OPERACION_BAJA)
            return repositorio.baja(entidad.getIdTerreno());

        if (operacion == OPERACION_CAMBIO)
            return repositorio.cambio(entidad.getIdTerreno(),entidadCambio);

        return false;

    }

}
