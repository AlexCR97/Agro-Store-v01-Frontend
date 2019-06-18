package com.example.agrostore01.CapaNegocios.escritores;

import com.example.agrostore01.CapaDatos.repositorios.RepositorioCarrito;
import com.example.agrostore01.CapaEntidades.Carrito;

public class EscritorCarrito extends Escritor<Carrito> {

    public static final int OPERACION_AGREGAR_PRODUCTO = 4;

    private RepositorioCarrito repositorio = new RepositorioCarrito();
    private int idNumProducto;
    private String idUsuario;
    private int cantidad;

    public EscritorCarrito(int operacion, Carrito carrito) {
        super(operacion, carrito);
    }

    public EscritorCarrito(int operacion, Carrito carrito, Carrito entidadCambio) {
        super(operacion, carrito, entidadCambio);
    }

    public EscritorCarrito(int operacion, Carrito carrito, int idNumProducto, String idUsuario, int cantidad) {
        super(operacion, carrito);
        this.idNumProducto = idNumProducto;
        this.idUsuario = idUsuario;
        this.cantidad = cantidad;
    }

    @Override
    public boolean ejecutarCambios() {
        if (operacion == OPERACION_ALTA)
            return repositorio.alta(entidad);

        if (operacion == OPERACION_BAJA)
            return repositorio.baja(entidad.getIdCar());

        if (operacion == OPERACION_CAMBIO)
            return repositorio.cambio(entidad.getIdCar(),entidadCambio);

        if (operacion == OPERACION_AGREGAR_PRODUCTO)
            return repositorio.agregarProductoACarrito(idNumProducto, idUsuario, cantidad);

        return false;
    }
}
