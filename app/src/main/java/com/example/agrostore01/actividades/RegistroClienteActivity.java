package com.example.agrostore01.actividades;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.EditText;


import com.example.agrostore01.R;

import java.util.Calendar;

public class RegistroClienteActivity extends AppCompatActivity implements View.OnClickListener{
    ImageButton ibRegistrar,ibFecha;
    String sFecha;
    TextView tvFecha;

    EditText etNombre, etContrasena, etConfirmarContrasena, etCorreoElectronico, etCorreoRespaldo, etDireccion;
    private int dia, mes, anno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_cliente);

        ibRegistrar = (ImageButton) findViewById(R.id.ibRegistrar);
        ibFecha = (ImageButton) findViewById(R.id.ibFecha);

        tvFecha = (TextView) findViewById(R.id.tvFecha);

        ibFecha.setOnClickListener((View.OnClickListener) this);
        ibRegistrar.setOnClickListener((View.OnClickListener) this);

        etNombre = (EditText) findViewById(R.id.etNombre);
        etContrasena = (EditText) findViewById(R.id.etContra);
        etConfirmarContrasena = (EditText) findViewById(R.id.etConfirmarContra);
        etCorreoElectronico = (EditText) findViewById(R.id.etCorreoElec);
        etCorreoRespaldo = (EditText) findViewById(R.id.etCorreoRespaldo);


    }

    public ImageButton getIbRegistrar() {
        return ibRegistrar;
    }

    public ImageButton getIbFecha() {
        return ibFecha;
    }

    public String getsFecha() {
        return sFecha;
    }

    public TextView getTvFecha() {
        return tvFecha;
    }

    public EditText getEtNombre() {
        return etNombre;
    }

    public EditText getEtContrasena() {
        return etContrasena;
    }

    public EditText getEtConfirmarContrasena() {
        return etConfirmarContrasena;
    }

    public EditText getEtCorreoElectronico() {
        return etCorreoElectronico;
    }

    public EditText getEtCorreoRespaldo() {
        return etCorreoRespaldo;
    }

    public EditText getEtDireccion() {
        return etDireccion;
    }

    @Override
    public void onClick(View v) {
        if (v == ibFecha) {
            final Calendar c = Calendar.getInstance();
            dia = c.get(Calendar.DAY_OF_MONTH);
            mes = c.get(Calendar.MONTH);
            anno = c.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    sFecha = (dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    tvFecha.setText(sFecha);
                    tvFecha.setVisibility(View.VISIBLE);
                }
            }
                , dia, mes, anno);
            datePickerDialog.show();
        }
        if (v == ibRegistrar) {
            Intent intent= new Intent(RegistroClienteActivity.this,BarraActivity.class);
            startActivity(intent);
        }


    }
}