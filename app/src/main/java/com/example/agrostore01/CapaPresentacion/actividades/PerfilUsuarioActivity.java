package com.example.agrostore01.CapaPresentacion.actividades;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.agrostore01.AgroUtils;
import com.example.agrostore01.CapaEntidades.DetallesUsuario;
import com.example.agrostore01.CapaEntidades.Usuario;
import com.example.agrostore01.R;

public class PerfilUsuarioActivity extends RecieveBundlesActivity {

    private TextView tvNombre, tvDireccion, tvTelefono;
    private RatingBar ratingBarEstrellas;
    private ImageButton ibMisDatos, ibClave, ibReputacion, ibConfiguracionCuenta;

    private Usuario usuario = new Usuario();
    private DetallesUsuario detallesUsuario = new DetallesUsuario();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_usuario);

        recieveBundles(this);

        tvNombre = findViewById(R.id.tvPerfilNombre);
        tvDireccion = findViewById(R.id.tvPerfilDireccion);
        tvTelefono = findViewById(R.id.tvPerfilTelefono);
        ratingBarEstrellas = findViewById(R.id.ratingBarPerfil);

        ibMisDatos = findViewById(R.id.ibMisDatos);
        ibConfiguracionCuenta = findViewById(R.id.ibConfiguracionCuenta);
        ibReputacion = findViewById(R.id.ibReputacion);
        ibClave = findViewById(R.id.ibClave);

        ibMisDatos.setOnClickListener(ibMisDatosListener);
        ibConfiguracionCuenta.setOnClickListener(ibConfiguracionCuentaListener);
        ibClave.setOnClickListener(ibClaveListener);
        ibReputacion.setOnClickListener(ibReputacionListener);

        // Llenar campos
        String nombreCompleto = detallesUsuario.getNombres() + " " + detallesUsuario.getApellidos();
        String direccion = detallesUsuario.getCalle() + ", " + detallesUsuario.getColonia() + ". " +
                detallesUsuario.getEstado() + ", " + detallesUsuario.getPais();

        tvNombre.setText(nombreCompleto);
        tvDireccion.setText(direccion);
        tvTelefono.setText(detallesUsuario.getTelefono());
        ratingBarEstrellas.setRating(detallesUsuario.getEstrellas());
    }

    @Override
    public void recieveBundles(Context context) {
        usuario = getIntent().getParcelableExtra(usuario.getClassName());
        detallesUsuario = getIntent().getParcelableExtra(detallesUsuario.getClassName());

        System.out.println("Recieved: " + usuario);
        System.out.println("Recieved: " + detallesUsuario);
    }

    private final View.OnClickListener ibMisDatosListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(PerfilUsuarioActivity.this, MisDatosActivity.class);
            intent.putExtra(usuario.getClassName(), usuario);
            intent.putExtra(detallesUsuario.getClassName(), detallesUsuario);

            startActivity(intent);
        }
    };

    private final View.OnClickListener ibClaveListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(PerfilUsuarioActivity.this, ClaveActivity.class);
            intent.putExtra(usuario.getClassName(), usuario);
            intent.putExtra(detallesUsuario.getClassName(), detallesUsuario);

            startActivity(intent);
        }
    };

    private final View.OnClickListener ibReputacionListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if (!AgroUtils.verificarSiUsuarioEsProductor(v.getContext(), usuario))
                return;

            Intent intent = new Intent(PerfilUsuarioActivity.this, ReputacionActivity.class);
            intent.putExtra(usuario.getClassName(), usuario);
            intent.putExtra(detallesUsuario.getClassName(), detallesUsuario);

            startActivity(intent);
        }
    };

    private final View.OnClickListener ibConfiguracionCuentaListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if (!AgroUtils.verificarSiUsuarioEsProductor(v.getContext(), usuario))
                return;

            Intent intent = new Intent(PerfilUsuarioActivity.this, ConfigurarCuentaActivity.class);
            intent.putExtra(usuario.getClassName(), usuario);
            intent.putExtra(detallesUsuario.getClassName(), detallesUsuario);

            startActivity(intent);
        }
    };

    public ImageButton getIbMisDatos() {
        return ibMisDatos;
    }
    public ImageButton getIbClave() {
        return ibClave;
    }
    public ImageButton getIbReputacion() {
        return ibReputacion;
    }
    public ImageButton getIbConfiguracionCuenta() {
        return ibConfiguracionCuenta;
    }

}
