package com.example.agrostore01.CapaDatos.contratos;

import com.example.agrostore01.CapaEntidades.Carrito;

public interface IContratoCarrito extends IContratoRelacion<Carrito> {

    boolean agregarProductoACarrito(int idNumProducto, String idUsuario, int cantidad);
    boolean quitarProductoDeCarrito(int idNumProducto, String idUsuario);

}
