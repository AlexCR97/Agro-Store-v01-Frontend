package com.example.agrostore01.CapaPresentacion.actividades;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.EditText;
import android.widget.Toast;


import com.example.agrostore01.CapaEntidades.DetallesUsuario;
import com.example.agrostore01.CapaEntidades.Usuario;
import com.example.agrostore01.CapaNegocios.lectores.LectorUsuario;
import com.example.agrostore01.R;

public class ClaveActivity extends RecieveBundlesActivity {

    private ImageButton ibSeguridad;
    private EditText etClave;
private ProgressDialog dialog;
    private Usuario usuario = new Usuario();
    private DetallesUsuario detallesUsuario = new DetallesUsuario();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clave);

        recieveBundles(this);

        ibSeguridad = findViewById(R.id.ibAceptar);
        etClave = findViewById(R.id.etClave);

        ibSeguridad.setOnClickListener(ibSeguridadListener);
    }

    private class VerificarContrasena extends AsyncTask<Void, Void, Void> {

        private LectorUsuario lectorUsuario = new LectorUsuario();
        private String contrasena;
        boolean exito;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            contrasena = etClave.getText().toString();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            exito = lectorUsuario.confirmarContrasena(usuario.getIdUsuario(), contrasena);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(ClaveActivity.this);

            if (exito) {
                Intent intent = new Intent(ClaveActivity.this, SeguridadActivity.class);
                intent.putExtra(usuario.getClassName(), usuario);
                intent.putExtra(detallesUsuario.getClassName(), detallesUsuario);

                startActivity(intent);
            } else {
                dialog.cancel();
                alertDialog.setTitle("Advertencia")
                        .setMessage("La contrasena es incorrecta")
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                alertDialog.show();
                //Toast.makeText(ClaveActivity.this, "La contrasena es incorrecta", Toast.LENGTH_LONG).show();
            }
        }

    }

    private final View.OnClickListener ibSeguridadListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dialog = new ProgressDialog(ClaveActivity.this);

            dialog.setTitle("Cargando");
            dialog.setMessage("Espere un momento");
            dialog.show();

            new VerificarContrasena().execute();
        }
    };

    @Override
    public void recieveBundles(Context context) {
        usuario = getIntent().getParcelableExtra(usuario.getClassName());
        detallesUsuario = getIntent().getParcelableExtra(detallesUsuario.getClassName());
    }

    public ImageButton getIbSeguridad() {
        return ibSeguridad;
    }
    public EditText getEtClave() {
        return etClave;
    }
}
