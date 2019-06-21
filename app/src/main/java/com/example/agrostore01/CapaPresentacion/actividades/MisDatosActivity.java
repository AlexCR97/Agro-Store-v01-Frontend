package com.example.agrostore01.CapaPresentacion.actividades;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.agrostore01.CapaEntidades.DetallesUsuario;
import com.example.agrostore01.CapaEntidades.Usuario;
import com.example.agrostore01.R;

public class MisDatosActivity extends RecieveBundlesActivity {

    private TextView tvNombre, tvUsuario, tvEmail, tvTelefono;
    private TextView tvCalle, tvColonia, tvCiudad, tvCodigoPostal, tvEstado, tvPais;
    private ImageButton ibActualizar;

    private Usuario usuario = new Usuario();
    private DetallesUsuario detallesUsuario = new DetallesUsuario();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_datos);

        recieveBundles(this);

        tvNombre = findViewById(R.id.tvNombre);
        tvUsuario = findViewById(R.id.tvUsuario);
        tvEmail = findViewById(R.id.tvEmail);
        tvTelefono = findViewById(R.id.tvTelefono);

        tvCalle = findViewById(R.id.tvCalle);
        tvColonia = findViewById(R.id.tvColonia);
        tvCiudad = findViewById(R.id.tvCiudad);
        tvCodigoPostal = findViewById(R.id.tvCp);
        tvEstado = findViewById(R.id.tvEstado);
        tvPais = findViewById(R.id.tvPais);

        ibActualizar = findViewById(R.id.ibActualizar);
        ibActualizar.setOnClickListener(ibActualizarListener);

        // Llenar campos
        llenarCampos();
    }

    @Override
    protected void onResume() {
        super.onResume();
        llenarCampos();
    }

    @Override
    public void recieveBundles(Context context) {
        usuario = getIntent().getParcelableExtra(usuario.getClassName());
        detallesUsuario = getIntent().getParcelableExtra(detallesUsuario.getClassName());

        System.out.println("Recieved: " + usuario);
        System.out.println("Recieved: " + detallesUsuario);
    }

    private final View.OnClickListener ibActualizarListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MisDatosActivity.this, ActualizarMisDatosActivity.class);
            intent.putExtra(usuario.getClassName(), usuario);
            intent.putExtra(detallesUsuario.getClassName(), detallesUsuario);

            startActivity(intent);
        }
    };

    private void llenarCampos() {
        String nombre = detallesUsuario.getNombres() + " " + detallesUsuario.getApellidos();
        tvNombre.setText(nombre);
        tvUsuario.setText(usuario.getUsuario());
        tvEmail.setText(usuario.getCorreo());
        tvTelefono.setText(detallesUsuario.getTelefono());

        tvCalle.setText(detallesUsuario.getCalle());
        tvColonia.setText(detallesUsuario.getColonia());
        tvCiudad.setText(detallesUsuario.getCuidad());
        tvCodigoPostal.setText(String.valueOf(detallesUsuario.getCp()));
        tvEstado.setText(detallesUsuario.getEstado());
        tvPais.setText(detallesUsuario.getPais());
    }

}
