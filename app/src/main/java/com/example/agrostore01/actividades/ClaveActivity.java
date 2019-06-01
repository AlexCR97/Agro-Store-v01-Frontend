package com.example.agrostore01.actividades;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.EditText;


import com.example.agrostore01.R;

public class ClaveActivity extends AppCompatActivity {

    private ImageButton ibSeguridad;
    private EditText etClave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clave);

        ibSeguridad=(ImageButton)findViewById(R.id.ibAceptar);
        etClave=(EditText) findViewById(R.id.etClave);

        ibSeguridad.setOnClickListener(ibSeguridadListener);

    }

    private final View.OnClickListener ibSeguridadListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent= new Intent(ClaveActivity.this,SeguridadActivity.class);
            startActivity(intent);
        }
    };

    public ImageButton getIbSeguridad() {
        return ibSeguridad;
    }

    public EditText getEtClave() {
        return etClave;
    }
}
