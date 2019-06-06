package com.example.agrostore01.CapaPresentacion.actividades;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.agrostore01.CapaDatos.conexiones.BaseDeDatos;
import com.example.agrostore01.CapaEntidades.Usuario;
import com.example.agrostore01.CapaNegocios.lectores.LectorUsuario;
import com.example.agrostore01.R;

public class LoginActivity extends RecieveBundlesActivity {

    private ImageButton ibRegistrarse, ibIniciarSesion, ibRecuperarCuenta;
    private ImageView ivFaq;
    private EditText etCorreoElectronico, etContrasena;

    private Usuario usuario = new Usuario();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        recieveBundles(this);

        ibRegistrarse = findViewById(R.id.imageButton2);
        ibIniciarSesion = findViewById(R.id.imageButton);
        ibRecuperarCuenta = findViewById(R.id.ibRecuperarCuenta);
        ivFaq = findViewById(R.id.imageViewFaqLogin);
        etCorreoElectronico = findViewById(R.id.etCorreoElec);
        etContrasena = findViewById(R.id.etContra);

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
            new VerificarExistenciaDeUsuario().execute();
        }
    };

    @Override
    public void recieveBundles(Context context) {
        usuario = getIntent().getParcelableExtra(usuario.getClassName());
    }

    private class VerificarExistenciaDeUsuario extends AsyncTask<Void, Void, Void> {

        private LectorUsuario lectorUsuario = new LectorUsuario();
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

            if (!exito) {
                Toast.makeText(LoginActivity.this, "Usuario o contrasena incorrecta", Toast.LENGTH_LONG).show();
                return;
            }

            //usuario = new Usuario(nombreUsuario, null, 1, 1, nombreUsuario, contrasena, "ale@live.com");
            usuario = lectorUsuario.getEntidadNombreUsuario(nombreUsuario);
            if (usuario == null) {
                Toast.makeText(LoginActivity.this, "Ocurrio un error al iniciar sesion. Intentelo de nuevo", Toast.LENGTH_LONG).show();
                return;
            }

            System.out.println("Login user is " + usuario);

            Intent intent = new Intent(LoginActivity.this, BarraActivity.class);
            intent.putExtra(usuario.getClassName(), usuario);
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
            usuario = new Usuario("root", null, 1, 1, "root", "pass", "ale@live.com");

            Intent intent = new Intent(LoginActivity.this, BarraActivity.class);
            intent.putExtra(usuario.getClassName(), usuario);
            startActivity(intent);
            finish();
        }
    };

    public ImageButton getIbRegistrarse() { return ibRegistrarse; }
    public ImageButton getIbIniciarSesion() { return ibIniciarSesion; }
    public EditText getEtCorreoElectronico() { return etCorreoElectronico; }
    public EditText getEtContrasema() { return etContrasena; }
}
