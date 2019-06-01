package com.example.agrostore01.actividades;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.EditText;


import com.example.agrostore01.R;

public class AgregarTerrenoActivity extends AppCompatActivity {

    private ImageButton ibAceptar;
    private Spinner sTipoTerreno;
    private EditText etNombre, etTamano, etMedidasAncho, etMedidasAlto;

    String[] datos = new String[] {"Suelo arenoso", "Suelo calizo", "Suelo limoso", "Suelo humífero", "Suelo arcilloso", "Suelo pedregoso", "Suelo de turba", "Suelo salino"};
    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, datos);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_terreno);

        ibAceptar=(ImageButton)findViewById(R.id.ibAceptar);
        sTipoTerreno = (Spinner) findViewById(R.id.sTipoTerreno);
        etNombre=(EditText)findViewById(R.id.tvNombre);
        etTamano=(EditText)findViewById(R.id.etTamano);
        etMedidasAncho=(EditText)findViewById(R.id.etMedidasAn);
        etMedidasAlto=(EditText)findViewById(R.id.etMedidasAl);

        sTipoTerreno.setAdapter(adapter);

        ibAceptar.setOnClickListener(ibAceptarListener);

    }

    private final View.OnClickListener ibAceptarListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent= new Intent(AgregarTerrenoActivity.this,MiTerrenoActivity.class);
            startActivity(intent);
        }
    };

    public ImageButton getIbAceptar() {
        return ibAceptar;
    }

    public Spinner getsTipoTerreno() {
        return sTipoTerreno;
    }

    public EditText getEtNombre() {
        return etNombre;
    }

    public EditText getEtTamano() {
        return etTamano;
    }

    public EditText getEtMedidasAncho() {
        return etMedidasAncho;
    }

    public EditText getEtMedidasAlto() {
        return etMedidasAlto;
    }
}
