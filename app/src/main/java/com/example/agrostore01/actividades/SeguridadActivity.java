package com.example.agrostore01.actividades;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.agrostore01.R;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class SeguridadActivity extends AppCompatActivity {

    private ImageButton ibCambiar;
    private EditText etClave, etConfirmarClave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seguridad);

        ibCambiar = (ImageButton) findViewById(R.id.ibCambiar);
        etClave = (EditText) findViewById(R.id.etClave);
        etConfirmarClave = (EditText) findViewById(R.id.etConfirmarClave);

        ibCambiar.setOnClickListener(ibCambiarListener);
    }

    private final View.OnClickListener ibCambiarListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent= new Intent(SeguridadActivity.this,BarraActivity.class);
            startActivity(intent);
        }
    };

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
