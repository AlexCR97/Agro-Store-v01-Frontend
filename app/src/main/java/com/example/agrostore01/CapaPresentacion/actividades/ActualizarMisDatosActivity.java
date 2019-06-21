package com.example.agrostore01.CapaPresentacion.actividades;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.EditText;
import android.widget.Toast;

import com.example.agrostore01.AgroMensajes;
import com.example.agrostore01.CapaEntidades.DetallesUsuario;
import com.example.agrostore01.CapaEntidades.Usuario;
import com.example.agrostore01.CapaNegocios.escritores.EscritorUsuario;
import com.example.agrostore01.CapaNegocios.validaciones.ValidacionDetalles;
import com.example.agrostore01.CapaNegocios.validaciones.ValidacionUsuario;
import com.example.agrostore01.R;

public class ActualizarMisDatosActivity extends RecieveBundlesActivity {

    private EditText etNombres, etApellidos, etEmail, etTelefono;
    private EditText etCalle, etColonia, etCiudad, etCodigoPostal, etEstado, etPais;
    private ImageButton ibGuardar;

    private Usuario usuario = new Usuario();
    private DetallesUsuario detallesUsuario = new DetallesUsuario();
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_mis_datos);

        recieveBundles(this);

        etNombres = findViewById(R.id.etActualizarNombre);
        etApellidos = findViewById(R.id.etActualizarApellidos);
        etEmail = findViewById(R.id.tvEmail);
        etTelefono = findViewById(R.id.tvTelefono);

        etCalle = findViewById(R.id.etActualizarCalle);
        etColonia = findViewById(R.id.etActualizarColonia);
        etCiudad = findViewById(R.id.etActualizarCiudad);
        etCodigoPostal = findViewById(R.id.etActualizarCp);
        etEstado = findViewById(R.id.etActualizarEstado);
        etPais = findViewById(R.id.etActualizarPais);

        ibGuardar = findViewById(R.id.ibActualizarGuardar);
        ibGuardar.setOnClickListener(ibGuardarListener);

        // Llenar campos
        etNombres.setText(detallesUsuario.getNombres());
        etApellidos.setText(detallesUsuario.getApellidos());
        etEmail.setText(usuario.getCorreo());
        etTelefono.setText(detallesUsuario.getTelefono());

        etCalle.setText(detallesUsuario.getCalle());
        etColonia.setText(detallesUsuario.getColonia());
        etCiudad.setText(detallesUsuario.getCuidad());
        etCodigoPostal.setText(String.valueOf(detallesUsuario.getCp()));
        etEstado.setText(detallesUsuario.getEstado());
        etPais.setText(detallesUsuario.getPais());
    }

    @Override
    public void recieveBundles(Context context) {
        usuario = getIntent().getParcelableExtra(usuario.getClassName());
        detallesUsuario = getIntent().getParcelableExtra(detallesUsuario.getClassName());
    }

    private final View.OnClickListener ibGuardarListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dialog = new ProgressDialog(ActualizarMisDatosActivity.this);

            dialog.setTitle("Actualizando");
            dialog.setMessage("Espere un momento");
            dialog.show();
            new ActualizarDatos().execute();
        }
    };

    private class ActualizarDatos extends AsyncTask<Void, Void, Void> {

        private Usuario usuarioTemp;
        private DetallesUsuario detallesUsuarioTemp;

        private String mensajeError;
        private boolean exito;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            String nombres = etNombres.getText().toString();
            String apellidos = etApellidos.getText().toString();
            String email = etEmail.getText().toString();
            String telefono = (etTelefono.getText().toString().isEmpty())? null : etTelefono.getText().toString();

            usuarioTemp = new Usuario(
                    usuario.getIdUsuario(),
                    usuario.getFoto(),
                    usuario.getIdTipo(),
                    usuario.getIdDetalles(),
                    usuario.getUsuario(),
                    usuario.getContrasenaUsuario(),
                    email
            );

            String calle = etCalle.getText().toString();
            String colonia = etColonia.getText().toString();
            String ciudad = etCiudad.getText().toString();
            String codigoPostal = etCodigoPostal.getText().toString();
            String estado = etEstado.getText().toString();
            String pais = etPais.getText().toString();

            detallesUsuarioTemp = new DetallesUsuario(
                    nombres, apellidos,
                    calle, colonia, estado, pais,
                    (codigoPostal.isEmpty())? -1 : Integer.valueOf(codigoPostal),
                    detallesUsuario.getEscrituraOPermiso(),
                    detallesUsuario.getEstrellas(),
                    detallesUsuario.getRfc(),
                    detallesUsuario.getFirmaElectronica(),
                    ciudad,
                    detallesUsuario.getFechaNac(),
                    telefono
            );
        }

        @Override
        protected Void doInBackground(Void... voids) {

            ValidacionUsuario validacionUsuario = new ValidacionUsuario(usuarioTemp);
            if (!validacionUsuario.validar()) {
                mensajeError = AgroMensajes.ERROR_VALIDACION_USUARIO;
                exito = false;
                return null;
            }

            ValidacionDetalles validacionDetalles = new ValidacionDetalles(detallesUsuarioTemp);

            if (!validacionDetalles.validarTelefono()) {
                mensajeError = AgroMensajes.ERROR_NUMERO_TELEFONO;
                exito = false;
                return null;
            }

            if (!validacionDetalles.validar()) {
                mensajeError = AgroMensajes.ERROR_VALIDACION_DETALLES_USUARIO;
                exito = false;
                return null;
            }

            EscritorUsuario escritorUsuario = new EscritorUsuario(EscritorUsuario.OPERACION_ACTUALIZAR_DATOS, usuarioTemp, detallesUsuarioTemp);

            exito = escritorUsuario.ejecutarCambios();

            if (!exito) {
                mensajeError = AgroMensajes.ERROR_INTERNET;
                return null;
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            dialog.cancel();

            AlertDialog.Builder alertDialog = new AlertDialog.Builder(ActualizarMisDatosActivity.this);
            if (!exito) {
                alertDialog.setTitle("Advertencia")
                        .setMessage(mensajeError)
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                alertDialog.show();
                return;
            }

            usuario.setCorreo(usuarioTemp.getCorreo());
            detallesUsuario.setNombres(detallesUsuarioTemp.getNombres());
            detallesUsuario.setApellidos(detallesUsuarioTemp.getApellidos());
            detallesUsuario.setCalle(detallesUsuarioTemp.getCalle());
            detallesUsuario.setColonia(detallesUsuarioTemp.getColonia());
            detallesUsuario.setEstado(detallesUsuarioTemp.getEstado());
            detallesUsuario.setPais(detallesUsuarioTemp.getPais());
            detallesUsuario.setCp(detallesUsuarioTemp.getCp());
            detallesUsuario.setCuidad(detallesUsuarioTemp.getCuidad());
            detallesUsuario.setTelefono(detallesUsuarioTemp.getTelefono());

            Intent intent = new Intent(ActualizarMisDatosActivity.this, MisDatosActivity.class);
            intent.putExtra(usuario.getClassName(), usuario);
            intent.putExtra(detallesUsuario.getClassName(), detallesUsuario);

            startActivity(intent);
            finish();
        }
    }

}



















