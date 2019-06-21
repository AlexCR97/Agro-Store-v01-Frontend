package com.example.agrostore01.CapaNegocios.validaciones;

import com.example.agrostore01.CapaEntidades.DetallesUsuario;

public class ValidacionDetalles extends Validacion <DetallesUsuario> {

    public ValidacionDetalles(DetallesUsuario detallesUsuario) {
        super(detallesUsuario);
    }

    @Override
    public boolean validar() {
        return validarIdDetalles() && validarCalle() && validarColonia() &&
                validarCiudad() && validarEstado() && validarPais() &&
                validarCp() && validarEscrituraOPermiso() && validarEstrellas() &&
                validarRfc() && validarFirmaElectronica() && validarNombres() &&
                validarApellidos() && validarFecha() && validarTelefono();
    }

    public boolean validarIdDetalles() {
        System.out.println("Validating Detalles.IdDetalles :" + entidad.getIdDetalles());

        if (entidad.getIdDetalles() <= -1)
            return false;

        return true;
    }

    public boolean validarNombres() {
        System.out.println("Validating Detalles.Nombres :" + entidad.getNombres());

        if (entidad.getNombres() == null)
            return false;

        if (entidad.getNombres().isEmpty())
            return false;

        return true;
    }

    public boolean validarApellidos() {
        System.out.println("Validating Detalles.Apellidos :" + entidad.getApellidos());

        if (entidad.getApellidos() == null)
            return false;

        if (entidad.getApellidos().isEmpty())
            return false;

        return true;
    }

    public boolean validarCalle() {
        System.out.println("Validating Detalles.Calle :" + entidad.getCalle());

        if (entidad.getCalle() == null)
            return false;

        if (entidad.getCalle().isEmpty())
            return false;

        return true;
    }

    public boolean validarColonia() {
        System.out.println("Validating Detalles.Colonia :" + entidad.getColonia());

        if (entidad.getColonia() == null)
            return false;

        if (entidad.getColonia().isEmpty())
            return false;

        return true;
    }

    public boolean validarEstado() {
        System.out.println("Validating Detalles.Estado :" + entidad.getEstado());

        if (entidad.getEstado() == null)
            return false;

        if (entidad.getEstado().isEmpty())
            return false;

        return true;
    }

    public boolean validarPais() {
        System.out.println("Validating Detalles.Pais :" + entidad.getPais());

        if (entidad.getPais() == null)
            return false;

        if (entidad.getPais().isEmpty())
            return false;

        return true;
    }

    public boolean validarCp() {
        System.out.println("Validating Detalles.CodigoPostal :" + entidad.getCp());

        if (entidad.getCp() <= -1)
            return false;

        return true;
    }

    public boolean validarEscrituraOPermiso() {
        System.out.println("Validating Detalles.EscrituraOPermiso :" + entidad.getEscrituraOPermiso());
        return true;
    }

    public boolean validarEstrellas() {
        System.out.println("Validating Detalles.Estrellas :" + entidad.getEstrellas());

        if (entidad.getEstrellas() <= -1)
            return false;

        return true;
    }

    public boolean validarRfc() {
        System.out.println("Validating Detalles.Rfc :" + entidad.getRfc());
        return true;
    }

    public boolean validarFirmaElectronica() {
        System.out.println("Validating Detalles.FirmaElectronica :" + entidad.getFirmaElectronica());
        return true;
    }


    public boolean validarCiudad() {
        System.out.println("Validating Detalles.Ciudad :" + entidad.getCuidad());

        if (entidad.getCuidad() == null)
            return false;

        if (entidad.getCuidad().isEmpty())
            return false;

        return true;
    }

    public boolean validarFecha() {
        System.out.println("Validating Detalles.Fecha :" + entidad.getFechaNac());

        if (entidad.getFechaNac() == null)
            return true;

        String[] datos = entidad.getFechaNac().split("-");

        if (datos.length != 3)
            return false;

        try {
            Integer.parseInt(datos[0]);
            Integer.parseInt(datos[1]);
            Integer.parseInt(datos[2]);
            return true;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean validarTelefono() {
        System.out.println("Validating Detalles.Telefono: " + entidad.getTelefono());

        if (entidad.getTelefono() == null)
            return true;

        if (entidad.getTelefono().isEmpty())
            return false;

        return entidad.getTelefono().length() == 10;
    }

}
