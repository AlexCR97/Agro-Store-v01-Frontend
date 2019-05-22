package com.example.agrostore01.actividades;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.EditText;

import com.example.agrostore01.R;

public class ActualizarMisDatosActivity extends AppCompatActivity {
    EditText etNombre, etUsuario, etEmail, etTelefono, etDireccion;
    ImageButton ibGuardar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_mis_datos);

        ibGuardar=(ImageButton)findViewById(R.id.ibGuardar);


        ibGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(ActualizarMisDatosActivity.this,MisDatosActivity.class);
                startActivity(intent);
            }
        });
        etNombre=(EditText)findViewById(R.id.tvNombre);
        etUsuario=(EditText)findViewById(R.id.tvUsuario);
        etEmail=(EditText)findViewById(R.id.tvEmail);
        etTelefono=(EditText)findViewById(R.id.tvTelefono);
        etDireccion=(EditText)findViewById(R.id.tvDireccion);


    }

    public EditText getEtNombre() {
        return etNombre;
    }

    public EditText getEtUsuario() {
        return etUsuario;
    }

    public EditText getEtEmail() {
        return etEmail;
    }

    public EditText getEtTelefono() {
        return etTelefono;
    }

    public EditText getEtDireccion() {
        return etDireccion;
    }

    public ImageButton getIbGuardar() {
        return ibGuardar;
    }
}
