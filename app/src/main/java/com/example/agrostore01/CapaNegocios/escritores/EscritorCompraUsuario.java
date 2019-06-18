package com.example.agrostore01.CapaNegocios.escritores;

import com.example.agrostore01.CapaDatos.repositorios.RepositorioCompraUsuario;
import com.example.agrostore01.CapaEntidades.CompraUsuario;
import com.example.agrostore01.CapaEntidades.vistas.VistaCompra;

public class EscritorCompraUsuario extends EscritorRelacion<CompraUsuario> {

    public static final int OPERACION_REALIZAR_COMPRA = 4;

    private RepositorioCompraUsuario repositorio = new RepositorioCompraUsuario();
    private VistaCompra compra;

    public EscritorCompraUsuario(int operacion, CompraUsuario compraUsuario) {
        super(operacion, compraUsuario);
    }

    public EscritorCompraUsuario(int operacion, CompraUsuario compraUsuario, CompraUsuario entidadCambio) {
        super(operacion, compraUsuario, entidadCambio);
    }

    public EscritorCompraUsuario(int operacion, CompraUsuario compraUsuario, VistaCompra compra) {
        super(operacion, compraUsuario);
        this.compra = compra;
    }

    @Override
    public boolean ejecutarCambios() {
        if (operacion == OPERACION_ALTA)
            return repositorio.alta(entidad);

        if (operacion == OPERACION_BAJA)
            return repositorio.baja(entidad.getIdProductoComprado());

        if (operacion == OPERACION_CAMBIO)
            return repositorio.cambio(entidad,entidadCambio);

        if (operacion == OPERACION_REALIZAR_COMPRA)
            return repositorio.realizarCompra(compra);

        return false;
    }
}
