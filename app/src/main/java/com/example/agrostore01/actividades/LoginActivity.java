package com.example.agrostore01.actividades;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;


import com.example.agrostore01.R;
public class LoginActivity extends AppCompatActivity {
    ImageButton ibInicioSesion, ibIniciar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ibInicioSesion=(ImageButton)findViewById(R.id.imageButton2);
        ibIniciar=(ImageButton)findViewById(R.id.imageButton);

        ibInicioSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(LoginActivity.this,RegistroActivity.class);
                startActivity(intent);
            }
        });

        ibIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(LoginActivity.this,BarraActivity.class);
                startActivity(intent);
            }
        });
    }
}
