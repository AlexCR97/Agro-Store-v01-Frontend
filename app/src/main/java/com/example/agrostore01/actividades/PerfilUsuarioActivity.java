package com.example.agrostore01.actividades;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.agrostore01.R;
public class PerfilUsuarioActivity extends AppCompatActivity {

    private ImageButton ibMisDatos, ibClave, ibReputacion, ibConfiguracionCuenta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_usuario);

        ibMisDatos=(ImageButton)findViewById(R.id.ibMisDatos);
        ibConfiguracionCuenta=(ImageButton)findViewById(R.id.ibConfiguracionCuenta);
        ibReputacion=(ImageButton)findViewById(R.id.ibReputacion);
        ibClave=(ImageButton)findViewById(R.id.ibClave);

        ibMisDatos.setOnClickListener(ibMisDatosListener);
        ibConfiguracionCuenta.setOnClickListener(ibConfiguracionCuentaListener);
        ibClave.setOnClickListener(ibClaveListener);
        ibReputacion.setOnClickListener(ibReputacionListener);
    }

    private final View.OnClickListener ibMisDatosListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent= new Intent(PerfilUsuarioActivity.this,MisDatosActivity.class);
            startActivity(intent);
        }
    };

    private final View.OnClickListener ibConfiguracionCuentaListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent= new Intent(PerfilUsuarioActivity.this,ConfigurarCuentaActivity.class);
            startActivity(intent);
        }
    };

    private final View.OnClickListener ibClaveListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent= new Intent(PerfilUsuarioActivity.this,ClaveActivity.class);
            startActivity(intent);
        }
    };

    private final View.OnClickListener ibReputacionListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent= new Intent(PerfilUsuarioActivity.this,ReputacionActivity.class);
            startActivity(intent);
        }
    };

    public ImageButton getIbMisDatos() {
        return ibMisDatos;
    }

    public ImageButton getIbClave() {
        return ibClave;
    }

    public ImageButton getIbReputacion() {
        return ibReputacion;
    }

    public ImageButton getIbConfiguracionCuenta() {
        return ibConfiguracionCuenta;
    }

    public View.OnClickListener getIbMisDatosListener() {
        return ibMisDatosListener;
    }

    public View.OnClickListener getIbConfiguracionCuentaListener() {
        return ibConfiguracionCuentaListener;
    }

    public View.OnClickListener getIbClaveListener() {
        return ibClaveListener;
    }

    public View.OnClickListener getIbReputacionListener() {
        return ibReputacionListener;
    }
}
