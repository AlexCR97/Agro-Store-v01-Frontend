package com.example.agrostore01.CapaNegocios.escritores;

import com.example.agrostore01.CapaDatos.repositorios.RepositorioNotificaciones;
import com.example.agrostore01.CapaEntidades.Notificaciones;

public class EscritorNotificaciones extends Escritor<Notificaciones> {

    public static final int OPERACION_NOTIFICAR = 4;

    private RepositorioNotificaciones repositorio = new RepositorioNotificaciones();

    private long idNumProducto;
    private String detalle;

    public EscritorNotificaciones(int operacion, Notificaciones notificaciones) {
        super(operacion, notificaciones);
    }

    public EscritorNotificaciones(int operacion, Notificaciones notificaciones, Notificaciones entidadCambio) {
        super(operacion, notificaciones, entidadCambio);
    }

    public EscritorNotificaciones(int operacion, long idNumProducto, String detalle) {
        super(operacion, null);

        this.idNumProducto = idNumProducto;
        this.detalle = detalle;
    }

    @Override
    public boolean ejecutarCambios() {

        if (operacion == OPERACION_ALTA)
            repositorio.alta(entidad);

        if (operacion == OPERACION_BAJA)
            return repositorio.baja(entidad.getIdNotificacion());

        if (operacion == OPERACION_CAMBIO)
            return repositorio.cambio(entidad.getIdNotificacion(),entidadCambio);

        if (operacion == OPERACION_NOTIFICAR)
            return repositorio.agregarNotificaion(idNumProducto, detalle);

        return false;
    }
}
