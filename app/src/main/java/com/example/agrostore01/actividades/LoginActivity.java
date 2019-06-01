package com.example.agrostore01.actividades;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.EditText;


import com.example.agrostore01.R;
public class LoginActivity extends AppCompatActivity {

    private ImageButton ibInicioSesion, ibIniciar, ibRecuperarCuenta;
    private EditText etCorreoElectronico, etContrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ibInicioSesion=(ImageButton)findViewById(R.id.imageButton2);
        ibIniciar=(ImageButton)findViewById(R.id.imageButton);
        ibRecuperarCuenta=(ImageButton)findViewById(R.id.ibRecuperarCuenta);
        etCorreoElectronico=(EditText) findViewById(R.id.etCorreoElec);
        etContrasena=(EditText) findViewById(R.id.etContra);

        ibInicioSesion.setOnClickListener(ibInicioSesionListener);
        ibIniciar.setOnClickListener(ibIniciarListener);

    }

    private final View.OnClickListener ibInicioSesionListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent= new Intent(LoginActivity.this,RegistroActivity.class);
            startActivity(intent);
        }
    };

    private final View.OnClickListener ibIniciarListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent= new Intent(LoginActivity.this,BarraActivity.class);
            startActivity(intent);
        }
    };

    public ImageButton getIbInicioSesion() {
        return ibInicioSesion;
    }

    public ImageButton getIbIniciar() {
        return ibIniciar;
    }

    public EditText getEtCorreoElectronico() {
        return etCorreoElectronico;
    }

    public EditText getEtContrasema() {
        return etContrasena;
    }
}
