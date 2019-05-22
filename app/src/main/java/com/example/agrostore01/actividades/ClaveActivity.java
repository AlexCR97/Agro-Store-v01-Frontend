package com.example.agrostore01.actividades;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.EditText;


import com.example.agrostore01.R;

public class ClaveActivity extends AppCompatActivity {
    ImageButton ibSeguridad;
    EditText etClave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clave);

        ibSeguridad=(ImageButton)findViewById(R.id.ibAceptar);

        ibSeguridad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(ClaveActivity.this,SeguridadActivity.class);
                startActivity(intent);
            }
        });

        etClave=(EditText) findViewById(R.id.etClave);

    }

    public ImageButton getIbSeguridad() {
        return ibSeguridad;
    }

    public EditText getEtClave() {
        return etClave;
    }
}
