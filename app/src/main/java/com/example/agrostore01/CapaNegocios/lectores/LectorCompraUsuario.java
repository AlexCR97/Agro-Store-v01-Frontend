package com.example.agrostore01.CapaNegocios.lectores;

import com.example.agrostore01.CapaDatos.repositorios.RepositorioCompraUsuario;
import com.example.agrostore01.CapaEntidades.CompraUsuario;
import com.example.agrostore01.CapaEntidades.vistas.VistaMisCompras;

import java.util.ArrayList;
import java.util.List;

public class LectorCompraUsuario extends LectorRelacion<CompraUsuario> {
    private RepositorioCompraUsuario repositorio = new RepositorioCompraUsuario();
    @Override
    public ArrayList<CompraUsuario> getEntidadesId(Object id) {
        return repositorio.seleccionarTodosId(id);
    }

    @Override
    public CompraUsuario getEntidadId(Object id) {
         return repositorio.seleccionarId(id);
    }

    @Override
    public ArrayList<CompraUsuario> getEntidades() {
        return repositorio.seleccionarTodo();

    }
    public List<VistaMisCompras> getMisCompras(String idUsuario) {
        return repositorio.selecccionarMisCompras(idUsuario);
    }
}
