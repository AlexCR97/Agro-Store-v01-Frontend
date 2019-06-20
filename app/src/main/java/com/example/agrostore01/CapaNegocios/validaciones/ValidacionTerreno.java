package com.example.agrostore01.CapaNegocios.validaciones;

import com.example.agrostore01.CapaEntidades.Terreno;

public class ValidacionTerreno extends Validacion<Terreno> {
    public ValidacionTerreno(Terreno terreno) {
        super(terreno);
    }

    public boolean validarIdTerreno(){
        System.out.println("Validating Terreno.IdTerreno " + entidad.getIdTerreno());
        if (entidad.getIdTerreno() <= -1)
            return false;

        return true;
    }

    public boolean validarMedida(){
        System.out.println("Validating Terreno.Medida " + entidad.getMedida());
        if (entidad.getMedida() == null)
            return false;

        if (entidad.getMedida().isEmpty())
            return false;

        return true;
    }
    public boolean validarTamano(){
        System.out.println("Validating Terreno.Tamano " + entidad.getTamaño());
        if (entidad.getTamaño() < 10000)
            return false;

        return true;
    }
    public boolean validarTipo(){
        System.out.println("Validating Terreno.Tipo " +entidad.getTipo() );
        if (entidad.getTipo() == null)
            return  false;

        if (entidad.getTipo().isEmpty())
            return false;

        return true;
    }

    @Override
    public boolean validar() {
        return validarIdTerreno() && validarMedida() && validarTamano() && validarTipo();
    }
}
