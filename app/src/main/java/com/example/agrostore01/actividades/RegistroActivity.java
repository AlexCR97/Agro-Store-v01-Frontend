package com.example.agrostore01.actividades;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.agrostore01.R;
public class RegistroActivity extends AppCompatActivity {
    ImageButton ibProductor, ibCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        ibProductor=(ImageButton)findViewById(R.id.imageButton3);
        ibCliente=(ImageButton)findViewById(R.id.imageButton4);

        ibProductor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(RegistroActivity.this,RegistroClienteActivity.class);
                startActivity(intent);
            }
        });

        ibCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(RegistroActivity.this,RegistroProductorActivity.class);
                startActivity(intent);
            }
        });
    }
}
