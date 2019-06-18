package com.example.agrostore01.CapaPresentacion.actividades;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.agrostore01.AgroMensajes;
import com.example.agrostore01.CapaEntidades.DetallesUsuario;
import com.example.agrostore01.CapaEntidades.Usuario;
import com.example.agrostore01.CapaEntidades.vistas.VistaCompra;
import com.example.agrostore01.CapaNegocios.escritores.Escritor;
import com.example.agrostore01.CapaNegocios.escritores.EscritorCompraUsuario;
import com.example.agrostore01.R;

import java.util.ArrayList;

public class CompraActivity extends RecieveBundlesActivity {

    private Button bComprar;

    private Usuario usuario = new Usuario();
    private ArrayList<VistaCompra> compras = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compra);

        recieveBundles(this);

        bComprar = findViewById(R.id.bComprar);

        bComprar.setOnClickListener(bComprarOnClick);
    }

    @Override
    public void recieveBundles(Context context) {
        usuario = getIntent().getParcelableExtra(usuario.getClassName());
        compras = getIntent().getParcelableArrayListExtra(compras.getClass().getSimpleName());

        System.out.println("Recieved compras");
        for (VistaCompra compra : compras)
            System.out.println(compra);
    }

    private final View.OnClickListener bComprarOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if (compras == null)
                return;

            if (compras.isEmpty())
                return;

            new RealizarCompra().execute();
        }
    };

    private class RealizarCompra extends AsyncTask<Void, Void, Void> {

        private boolean exito;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            for (VistaCompra compra : compras) {
                EscritorCompraUsuario escritorCompraUsuario = new EscritorCompraUsuario(
                        EscritorCompraUsuario.OPERACION_REALIZAR_COMPRA,
                        null,
                        compra
                );
                exito = escritorCompraUsuario.ejecutarCambios();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            if (!exito) {
                Toast.makeText(CompraActivity.this, AgroMensajes.ERROR_INTERNET, Toast.LENGTH_LONG).show();
                return;
            }

            Toast.makeText(CompraActivity.this, AgroMensajes.COMPRA_REALIZADA, Toast.LENGTH_LONG).show();
        }
    }

}






















