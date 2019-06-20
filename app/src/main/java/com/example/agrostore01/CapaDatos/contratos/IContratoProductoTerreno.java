package com.example.agrostore01.CapaDatos.contratos;

import com.example.agrostore01.CapaEntidades.ProductoTerreno;

public interface IContratoProductoTerreno extends IContrato<ProductoTerreno> {

    boolean publicarProducto(ProductoTerreno productoTerreno, String tituloPublicacion, String temporada, byte[] foto);

}
