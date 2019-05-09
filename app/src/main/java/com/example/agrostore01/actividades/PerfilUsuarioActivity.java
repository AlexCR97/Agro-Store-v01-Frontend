package com.example.agrostore01.actividades;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.agrostore01.R;
public class PerfilUsuarioActivity extends AppCompatActivity {
    ImageButton ibMisDatos, ibClave, ibReputacion, ibConfiguracionCuenta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_usuario);

        ibMisDatos=(ImageButton)findViewById(R.id.ibMisDatos);
        ibConfiguracionCuenta=(ImageButton)findViewById(R.id.ibConfiguracionCuenta);
        ibReputacion=(ImageButton)findViewById(R.id.ibReputacion);
        ibClave=(ImageButton)findViewById(R.id.ibClave);

        ibMisDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(PerfilUsuarioActivity.this,MisDatosActivity.class);
                startActivity(intent);
            }
        });

        ibConfiguracionCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(PerfilUsuarioActivity.this,ConfigurarCuentaActivity.class);
                startActivity(intent);
            }
        });

        ibClave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(PerfilUsuarioActivity.this,ClaveActivity.class);
                startActivity(intent);
            }
        });

        ibReputacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(PerfilUsuarioActivity.this,ReputacionActivity.class);
                startActivity(intent);
            }
        });
    }
}
