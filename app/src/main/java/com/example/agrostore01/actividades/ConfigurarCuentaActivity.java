package com.example.agrostore01.actividades;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.agrostore01.R;
import android.widget.EditText;
import android.widget.ImageButton;

public class ConfigurarCuentaActivity extends AppCompatActivity {
EditText etVerificacionEji, etCuentaBan, etCodigoSat, etDireccionFis, etRFC;
ImageButton ibRecuperarCuenta, ibDarAlta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configurar_cuenta);

        ibRecuperarCuenta=(ImageButton)findViewById(R.id.ibRecuperarCuenta);
        ibDarAlta=(ImageButton)findViewById(R.id.ibDarAlta);

        etVerificacionEji=(EditText) findViewById(R.id.etVerificarEji);
        etCuentaBan=(EditText) findViewById(R.id.etCuentaBan);
        etCodigoSat=(EditText) findViewById(R.id.etSAT);
        etDireccionFis=(EditText) findViewById(R.id.etDireccionFis);
        etRFC=(EditText) findViewById(R.id.etRFC);


    }

    public EditText getEtVerificacionEji() {
        return etVerificacionEji;
    }

    public EditText getEtCuentaBan() {
        return etCuentaBan;
    }

    public EditText getEtCodigoSat() {
        return etCodigoSat;
    }

    public EditText getEtDireccionFis() {
        return etDireccionFis;
    }

    public EditText getEtRFC() {
        return etRFC;
    }

    public ImageButton getIbRecuperarCuenta() {
        return ibRecuperarCuenta;
    }

    public ImageButton getIbDarAlta() {
        return ibDarAlta;
    }
}
