package com.example.agrostore01.CapaNegocios.lectores.vistas;

import com.example.agrostore01.CapaDatos.repositorios.vistas.RepositorioVistaMiCarrito;
import com.example.agrostore01.CapaEntidades.vistas.VistaCarrito;
import com.example.agrostore01.CapaNegocios.lectores.Lector;

import java.util.ArrayList;
import java.util.List;

public class LectorVistaCarrito extends Lector<VistaCarrito> {

    private RepositorioVistaMiCarrito repositorio = new RepositorioVistaMiCarrito();

    @Override
    public VistaCarrito getEntidadId(Object id) {
        return null;
    }

    @Override
    public ArrayList<VistaCarrito> getEntidades() {
        return null;
    }

    public List<VistaCarrito> getMisProductosEnCarrito(String idUsuario) {
        return repositorio.seleccionarMisProductosEnCarrito(idUsuario);
    }
}
