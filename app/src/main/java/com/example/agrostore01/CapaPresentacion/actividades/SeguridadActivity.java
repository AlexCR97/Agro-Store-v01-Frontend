package com.example.agrostore01.CapaPresentacion.actividades;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.agrostore01.CapaEntidades.DetallesUsuario;
import com.example.agrostore01.CapaEntidades.Usuario;
import com.example.agrostore01.CapaNegocios.escritores.EscritorUsuario;
import com.example.agrostore01.R;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class SeguridadActivity extends RecieveBundlesActivity {

    private ImageButton ibCambiar;
    private EditText etClave, etConfirmarClave;
    private ProgressDialog dialog;
    private Usuario usuario = new Usuario();
    private DetallesUsuario detallesUsuario = new DetallesUsuario();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seguridad);

        recieveBundles(this);

        ibCambiar = findViewById(R.id.ibCambiar);
        etClave = findViewById(R.id.etClave);
        etConfirmarClave = findViewById(R.id.etConfirmarClave);

        ibCambiar.setOnClickListener(ibCambiarListener);
    }

    @Override
    public void recieveBundles(Context context) {
        usuario = getIntent().getParcelableExtra(usuario.getClassName());
        detallesUsuario = getIntent().getParcelableExtra(detallesUsuario.getClassName());
    }

    private final View.OnClickListener ibCambiarListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dialog = new ProgressDialog(SeguridadActivity.this);

            dialog.setTitle("Actualizando contraseña");
            dialog.setMessage("Espere un momento");
            dialog.show();

            new ActualizarContrasena().execute();
        }
    };

    private class ActualizarContrasena extends AsyncTask<Void, Void, Void>{

        private final String ERROR_CONTRASENAS_INCORRECTAS = "Las contrasenas no coinciden. Intentelo de nuevo";
        private final String ERROR_CONEXION_INTERNET = "Verifique su conexion a Internet e intentelo de nuevo";

        private String contrasena1;
        private String contrasena2;
        private String mensajeError;
        private boolean exito;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            contrasena1 = etClave.getText().toString();
            contrasena2 = etConfirmarClave.getText().toString();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            if(!contrasena1.equals(contrasena2)) {
                mensajeError = ERROR_CONTRASENAS_INCORRECTAS;
                exito = false;
                return null;
            }

            usuario.setContrasenaUsuario(contrasena1);
            EscritorUsuario escritorUsuario = new EscritorUsuario(EscritorUsuario.OPERACION_ACTUALIZAR_CONTRASENA, usuario);
            exito = escritorUsuario.ejecutarCambios();

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(SeguridadActivity.this);

            if (!exito) {
                dialog.cancel();
                alertDialog.setTitle("Advertencia")
                        .setMessage(mensajeError)
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        });
                alertDialog.show();
                //Toast.makeText(SeguridadActivity.this, mensajeError, Toast.LENGTH_LONG).show();
                return;
            }
            dialog.cancel();
            alertDialog.setTitle("")
                    .setMessage("La contrasena ha sido actualizada")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(SeguridadActivity.this, PerfilUsuarioActivity.class);
                            intent.putExtra(usuario.getClassName(), usuario);
                            intent.putExtra(detallesUsuario.getClassName(), detallesUsuario);
                            startActivity(intent);
                            finish();

                        }
                    });
            alertDialog.show();
            //Toast.makeText(SeguridadActivity.this, "La contrasena ha sido actualizada", Toast.LENGTH_LONG).show();

        }

    }

    public ImageButton getIbCambiar() {
        return ibCambiar;
    }
    public EditText getEtClave() { return etClave; }
    public EditText getEtConfirmarClave() {
        return etConfirmarClave;
    }



}
