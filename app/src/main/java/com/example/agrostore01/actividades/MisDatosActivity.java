package com.example.agrostore01.actividades;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;


import com.example.agrostore01.R;

public class MisDatosActivity extends AppCompatActivity {

    private ImageButton ibActualizar;
    private TextView tvNombre, tvUsuario, tvEmail, tvTelefono, tvDireccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_datos);

        ibActualizar=(ImageButton)findViewById(R.id.ibActualizar);
        tvNombre=(TextView) findViewById(R.id.tvNombre);
        tvUsuario=(TextView)findViewById(R.id.tvUsuario);
        tvEmail=(TextView)findViewById(R.id.tvEmail);
        tvTelefono=(TextView)findViewById(R.id.tvTelefono);
        tvDireccion=(TextView)findViewById(R.id.tvDireccion);

        ibActualizar.setOnClickListener(ibActualizarListener);
    }

    private final View.OnClickListener ibActualizarListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent= new Intent(MisDatosActivity.this,ActualizarMisDatosActivity.class);
            startActivity(intent);
        }
    };

    public ImageButton getIbActualizar() {
        return ibActualizar;
    }

    public TextView getTvNombre() {
        return tvNombre;
    }

    public TextView getTvUsuario() {
        return tvUsuario;
    }

    public TextView getTvEmail() {
        return tvEmail;
    }

    public TextView getTvTelefono() {
        return tvTelefono;
    }

    public TextView getTvDireccion() {
        return tvDireccion;
    }
}
