package com.example.agrostore01.CapaDatos.contratos;

import com.example.agrostore01.CapaEntidades.Producto;

import java.util.List;

public interface IContratoProducto extends IContrato<Producto> {

    List<Integer> seleccionarIdProductosConCategoria(String categoria);
    List<Integer> seleccionarIdProductosConNombre(String nombre);
    List<Integer> detallesDeLosProductos(String IDProductoTerreno);

}
