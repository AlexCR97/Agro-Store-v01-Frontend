package com.example.agrostore01.CapaPresentacion.actividades;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.agrostore01.AgroUtils;
import com.example.agrostore01.CapaDatos.conexiones.BaseDeDatos;
import com.example.agrostore01.CapaEntidades.DetallesUsuario;
import com.example.agrostore01.CapaEntidades.Usuario;
import com.example.agrostore01.CapaNegocios.lectores.LectorDetalles;
import com.example.agrostore01.CapaNegocios.lectores.LectorUsuario;
import com.example.agrostore01.R;

public class LoginActivity extends AppCompatActivity {

    private ImageButton ibRegistrarse, ibIniciarSesion, ibRecuperarCuenta;
    private ImageView ivFaq,ivCorreo, ivContra;
    private EditText etCorreoElectronico, etContrasena;
    private ProgressBar pbIconoCarga;
    private Usuario usuario = new Usuario();
    private DetallesUsuario detallesUsuario = new DetallesUsuario();
    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ibRegistrarse = findViewById(R.id.imageButton2);
        ibIniciarSesion = findViewById(R.id.imageButton);
        ibRecuperarCuenta = findViewById(R.id.ibRecuperarCuenta);
        ivFaq = findViewById(R.id.imageViewFaqLogin);
        etCorreoElectronico = findViewById(R.id.etRegistroClienteNombreUsuario);
        etContrasena = findViewById(R.id.etContra);
        ivContra = findViewById(R.id.ivContrasena);
        ivCorreo = findViewById(R.id.ivCorreo);
        pbIconoCarga = findViewById(R.id.pbIconoCarga);

        ibRegistrarse.setOnClickListener(ibRegistrarseListener);
        ibIniciarSesion.setOnClickListener(ibIniciarSesionListener);
        ibRecuperarCuenta.setOnClickListener(ibRecuperarCuentaListener);
        ivFaq.setOnClickListener(ivFaqOnClickListener);
    }

    private final View.OnClickListener ibRegistrarseListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(LoginActivity.this, RegistroActivity.class);
            startActivity(intent);
        }
    };

    private final View.OnClickListener ibIniciarSesionListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            android.app.AlertDialog.Builder alertDialog = new android.app.AlertDialog.Builder(LoginActivity.this);

            if (etCorreoElectronico.getText().toString().trim().equalsIgnoreCase("")){
                alertDialog.setTitle("Advertencia")
                        .setMessage("Ingrese un correo electronico")
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        });
                alertDialog.show();

                //Toast.makeText(LoginActivity.this, "Ingrese un correo electronico", Toast.LENGTH_LONG).show();

            }else{
                if(etContrasena.getText().toString().trim().equalsIgnoreCase("")){
                    //Toast.makeText(LoginActivity.this, "Ingrese la contraseña", Toast.LENGTH_LONG).show();
                    alertDialog.setTitle("Advertencia")
                            .setMessage("Ingrese la contraseña")
                            .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                }
                            });
                    alertDialog.show();
                }else{
                    ibRegistrarse.setVisibility(View.INVISIBLE);
                    ibIniciarSesion.setVisibility(View.INVISIBLE);
                    ibRecuperarCuenta.setVisibility(View.INVISIBLE);
                    etCorreoElectronico.setVisibility(View.INVISIBLE);
                    etContrasena.setVisibility(View.INVISIBLE);
                    ivContra.setVisibility(View.INVISIBLE);
                    ivCorreo.setVisibility(View.INVISIBLE);
                    pbIconoCarga.setVisibility(View.VISIBLE);
                    new VerificarExistenciaDeUsuario().execute();
                }
            }

        }
    };

    private class VerificarExistenciaDeUsuario extends AsyncTask<Void, Void, Void> {

        private LectorUsuario lectorUsuario = new LectorUsuario();
        private LectorDetalles lectorDetalles = new LectorDetalles();
        private String nombreUsuario;
        private String contrasena;
        private boolean exito;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            nombreUsuario = etCorreoElectronico.getText().toString();
            contrasena = etContrasena.getText().toString();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            exito = lectorUsuario.confirmarExistencia(nombreUsuario, contrasena);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            android.app.AlertDialog.Builder alertDialog = new android.app.AlertDialog.Builder(LoginActivity.this);

            if (!exito) {
                pbIconoCarga.setVisibility(View.INVISIBLE);

                ibRegistrarse.setVisibility(View.VISIBLE);
                ibIniciarSesion.setVisibility(View.VISIBLE);
                ibRecuperarCuenta.setVisibility(View.VISIBLE);
                etCorreoElectronico.setVisibility(View.VISIBLE);
                etContrasena.setVisibility(View.VISIBLE);
                ivContra.setVisibility(View.VISIBLE);
                ivCorreo.setVisibility(View.VISIBLE);
                //Toast.makeText(LoginActivity.this, "Usuario o contrasena incorrecta", Toast.LENGTH_LONG).show();
                alertDialog.setTitle("Advertencia")
                        .setMessage("Usuario o contrasena incorrecta")
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        });
                alertDialog.show();

                return;
            }

            usuario = lectorUsuario.getEntidadNombreUsuario(nombreUsuario);
            if (usuario == null) {
                pbIconoCarga.setVisibility(View.INVISIBLE);

                ibRegistrarse.setVisibility(View.VISIBLE);
                ibIniciarSesion.setVisibility(View.VISIBLE);
                ibRecuperarCuenta.setVisibility(View.VISIBLE);
                etCorreoElectronico.setVisibility(View.VISIBLE);
                etContrasena.setVisibility(View.VISIBLE);
                ivContra.setVisibility(View.VISIBLE);
                ivCorreo.setVisibility(View.VISIBLE);
                //Toast.makeText(LoginActivity.this, "Ocurrio un error al iniciar sesion. Intentelo de nuevo", Toast.LENGTH_LONG).show();
                alertDialog.setTitle("Advertencia")
                        .setMessage("Ocurrio un error al iniciar sesion. Intentelo de nuevo")
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        });
                alertDialog.show();
                return;
            }
            pbIconoCarga.setVisibility(View.INVISIBLE);
            ibRegistrarse.setVisibility(View.INVISIBLE);
            ibIniciarSesion.setVisibility(View.INVISIBLE);
            ibRecuperarCuenta.setVisibility(View.INVISIBLE);
            etCorreoElectronico.setVisibility(View.INVISIBLE);
            etContrasena.setVisibility(View.INVISIBLE);
            ivContra.setVisibility(View.INVISIBLE);
            ivCorreo.setVisibility(View.INVISIBLE);

            detallesUsuario = lectorDetalles.getEntidadId(usuario.getIdDetalles());

            System.out.println("Login user is " + usuario);
            System.out.println("Login user details are " + detallesUsuario);

            Intent intent = new Intent(LoginActivity.this, BarraActivity.class);
            intent.putExtra(usuario.getClassName(), usuario);
            intent.putExtra(detallesUsuario.getClassName(), detallesUsuario);
            startActivity(intent);
            finish();
        }
    }

    private final View.OnClickListener ibRecuperarCuentaListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            BaseDeDatos bd = new BaseDeDatos();
            boolean success = bd.getConexion() != null;
            if (!success)
                Toast.makeText(v.getContext(), "Connection failed :(", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(v.getContext(), "Connection successful! :D", Toast.LENGTH_SHORT).show();
        }
    };

    private final View.OnClickListener ivFaqOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            usuario = AgroUtils.getTestUser();
            detallesUsuario = AgroUtils.getTestDetalles();

            Intent intent = new Intent(LoginActivity.this, BarraActivity.class);
            intent.putExtra(usuario.getClassName(), usuario);
            intent.putExtra(detallesUsuario.getClassName(), detallesUsuario);
            startActivity(intent);
            finish();
        }
    };

    public ImageButton getIbRegistrarse() { return ibRegistrarse; }
    public ImageButton getIbIniciarSesion() { return ibIniciarSesion; }
    public EditText getEtCorreoElectronico() { return etCorreoElectronico; }
    public EditText getEtContrasema() { return etContrasena; }
}
