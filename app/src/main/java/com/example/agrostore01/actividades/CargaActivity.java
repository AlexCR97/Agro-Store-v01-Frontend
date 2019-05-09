package com.example.agrostore01.actividades;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.example.agrostore01.R;

public class CargaActivity extends AppCompatActivity {

    // ahista

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carga);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(CargaActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        },1000);
    }

}
