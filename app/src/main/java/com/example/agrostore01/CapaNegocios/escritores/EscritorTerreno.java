package com.example.agrostore01.CapaNegocios.escritores;

import com.example.agrostore01.CapaDatos.repositorios.RepositorioTerreno;
import com.example.agrostore01.CapaEntidades.Terreno;

public class EscritorTerreno extends  Escritor<Terreno> {

    public static final int OPERACION_AGREGAR_TERRENO = 4;

    private RepositorioTerreno repositorio = new RepositorioTerreno();
    private String idUsuario;

    public EscritorTerreno(int operacion, Terreno terreno) {
        super(operacion, terreno);
    }

    public EscritorTerreno(int operacion, Terreno terreno, Terreno entidadCambio) {
        super(operacion, terreno, entidadCambio);
    }

    public EscritorTerreno(int operacion, Terreno terreno, String idUsuario) {
        super(operacion, terreno);
        this.idUsuario = idUsuario;
    }

    @Override
    public boolean ejecutarCambios() {

        if (operacion == OPERACION_ALTA)
            repositorio.alta(entidad);

        if (operacion == OPERACION_BAJA)
            return repositorio.baja(entidad.getIdTerreno());

        if (operacion == OPERACION_CAMBIO)
            return repositorio.cambio(entidad.getIdTerreno(),entidadCambio);

        if (operacion == OPERACION_AGREGAR_TERRENO)
            return repositorio.agregarNuevoTerreno(idUsuario, entidad.getTamaño(), entidad.getMedida(), entidad.getTipo(), entidad.getNombre());

        return false;

    }
}
