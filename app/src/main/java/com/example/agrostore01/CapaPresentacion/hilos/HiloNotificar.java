package com.example.agrostore01.CapaPresentacion.hilos;

import android.os.AsyncTask;

import com.example.agrostore01.CapaNegocios.escritores.EscritorNotificaciones;

public class HiloNotificar extends AsyncTask<Void, Void, Void> {

    private long idNumProducto;
    private String detalle;

    private boolean exito;

    public HiloNotificar(long idNumProducto, String detalle) {
        super();

        this.idNumProducto = idNumProducto;
        this.detalle = detalle;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        if (idNumProducto <= -1) {
            exito = false;
            return;
        }

        if (detalle == null) {
            exito = false;
            return;
        }

        if (detalle.isEmpty()) {
            exito = false;
            return;
        }

        exito = true;
    }

    @Override
    protected Void doInBackground(Void... voids) {

        if (!exito)
            return null;

        EscritorNotificaciones escritor = new EscritorNotificaciones(EscritorNotificaciones.OPERACION_NOTIFICAR, idNumProducto, detalle);
        exito = escritor.ejecutarCambios();

        return null;
    }

}
