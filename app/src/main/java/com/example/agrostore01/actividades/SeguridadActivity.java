package com.example.agrostore01.actividades;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.agrostore01.R;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class SeguridadActivity extends AppCompatActivity {
ImageButton ibCambiar;
    EditText etClave, etConfirmarClave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seguridad);

        ibCambiar = (ImageButton) findViewById(R.id.ibCambiar);

        ibCambiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(SeguridadActivity.this,BarraActivity.class);
                startActivity(intent);
            }
        });

        etClave = (EditText) findViewById(R.id.etClave);
        etConfirmarClave = (EditText) findViewById(R.id.etConfirmarClave);


    }

    public ImageButton getIbCambiar() {
        return ibCambiar;
    }

    public EditText getEtClave() {
        return etClave;
    }

    public EditText getEtConfirmarClave() {
        return etConfirmarClave;
    }


}
