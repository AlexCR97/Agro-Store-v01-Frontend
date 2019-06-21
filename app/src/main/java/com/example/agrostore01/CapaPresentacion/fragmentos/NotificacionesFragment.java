package com.example.agrostore01.CapaPresentacion.fragmentos;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.agrostore01.CapaEntidades.Notificaciones;
import com.example.agrostore01.CapaEntidades.Usuario;
import com.example.agrostore01.CapaNegocios.lectores.LectorNotificaciones;
import com.example.agrostore01.CapaPresentacion.actividades.BarraActivity;
import com.example.agrostore01.CapaPresentacion.actividades.BuscarActivity;
import com.example.agrostore01.CapaPresentacion.actividades.RecieveBundlesFragment;
import com.example.agrostore01.R;
import com.example.agrostore01.CapaPresentacion.actividades.CompraActivity;
import com.example.agrostore01.CapaPresentacion.adaptadores.NotificacionesAdapter;

import java.util.List;

public class NotificacionesFragment extends RecieveBundlesFragment {

    private ListView listViewNotificaciones;
private ProgressDialog dialog;
    private Usuario usuario = new Usuario();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_notificaciones, container, false);

        listViewNotificaciones = vista.findViewById(R.id.listViewNotificaciones);

        recieveBundles(vista.getContext());
        dialog = new ProgressDialog(getActivity());

        dialog.setTitle("Cargando");
        dialog.setMessage("Espere un momento");
        dialog.show();

        new ObtenerNotificaciones().execute();

        return vista;
    }

    @Override
    public void recieveBundles(Context context) {
        usuario = getArguments().getParcelable(usuario.getClassName());
    }

    private class ObtenerNotificaciones extends AsyncTask<Void, Void, Void> {

        private final LectorNotificaciones lectorNotificaciones = new LectorNotificaciones();
        private List<String> notificaciones;
        private boolean exito;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            notificaciones = lectorNotificaciones.getNotificaciones(usuario.getIdUsuario());
            exito = notificaciones != null;
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());

            if (!exito) {
                dialog.cancel();
                alertDialog.setTitle("Advertencia")
                        .setMessage("Hubo un error al obtener las notificaciones. Compruebe su conexion a Internet e intentelo de nuevo")
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        });
                alertDialog.show();
                //Toast.makeText(NotificacionesFragment.this.getContext(),"Hubo un error al obtener las notificaciones. Compruebe su conexion a Internet e intentelo de nuevo",Toast.LENGTH_LONG).show();
                return;
            }

            NotificacionesAdapter adapter = new NotificacionesAdapter(
                    NotificacionesFragment.this.getContext(),
                    R.layout.list_item_notificaciones,
                    notificaciones
            );
            listViewNotificaciones.setAdapter(adapter);
            dialog.cancel();
        }

    }

}
