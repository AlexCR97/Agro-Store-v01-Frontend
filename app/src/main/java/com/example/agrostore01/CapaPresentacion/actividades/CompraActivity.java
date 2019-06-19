package com.example.agrostore01.CapaPresentacion.actividades;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agrostore01.AgroMensajes;
import com.example.agrostore01.AgroUtils;
import com.example.agrostore01.CapaEntidades.DetallesUsuario;
import com.example.agrostore01.CapaEntidades.Usuario;
import com.example.agrostore01.CapaEntidades.vistas.VistaCompra;
import com.example.agrostore01.CapaNegocios.escritores.Escritor;
import com.example.agrostore01.CapaNegocios.escritores.EscritorCarrito;
import com.example.agrostore01.CapaNegocios.escritores.EscritorCompraUsuario;
import com.example.agrostore01.R;

import java.util.ArrayList;

public class CompraActivity extends RecieveBundlesActivity {

    private TextView tvDireccion;
    private TextView tvNombreRecibe;
    private TextView tvNumeroCompra;
    private TextView tvNumeroCliente;
    private TextView tvNumeroRastreo;
    private Button bComprar;

    private Usuario usuario = new Usuario();
    private DetallesUsuario detallesUsuario = new DetallesUsuario();
    private ArrayList<VistaCompra> compras = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compra);

        recieveBundles(this);

        tvDireccion = findViewById(R.id.tvCompraDireccion);
        tvNombreRecibe = findViewById(R.id.tvCompraNombreRecibe);
        tvNumeroCompra = findViewById(R.id.tvCompraNumeroCompra);
        tvNumeroCliente = findViewById(R.id.tvCompraNumeroCliente);
        tvNumeroRastreo = findViewById(R.id.tvCompraNumeroRastreo);
        bComprar = findViewById(R.id.bComprar);

        bComprar.setOnClickListener(bComprarOnClick);

        // Llenar campos
        String direccion = String.format(
                "%s %s Col. %s, %s, %s",
                detallesUsuario.getCp(),
                detallesUsuario.getCalle(),
                detallesUsuario.getColonia(),
                detallesUsuario.getCuidad(),
                detallesUsuario.getEstado()
        );
        String nombreRecibe = detallesUsuario.getNombres() + " " + detallesUsuario.getApellidos();
        String numeroCompra = "#" + AgroUtils.generarNumeroAleatorio(1, 1000);
        String numeroCliente = "#" + AgroUtils.generarNumeroAleatorio(1, 1000);
        String numeroRastreo = AgroUtils.generarIdAleatorio(8);

        tvDireccion.setText(direccion);
        tvNombreRecibe.setText(nombreRecibe);
        tvNumeroCompra.setText(numeroCompra);
        tvNumeroCliente.setText(numeroCliente);
        tvNumeroRastreo.setText(numeroRastreo);
    }

    @Override
    public void recieveBundles(Context context) {
        usuario = getIntent().getParcelableExtra(usuario.getClassName());
        detallesUsuario = getIntent().getParcelableExtra(detallesUsuario.getClassName());
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

            new QuitarProductos().execute();
        }
    }

    private class QuitarProductos extends AsyncTask<Void, Void, Void> {

        private boolean exito;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            for (VistaCompra compra : compras) {
                EscritorCarrito escritorCarrito = new EscritorCarrito(
                        EscritorCarrito.OPERACION_QUITAR_PRODUCTO,
                        null,
                        compra.getIdNumProducto(),
                        usuario.getIdUsuario()
                );
                exito = escritorCarrito.ejecutarCambios();
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

            Toast.makeText(CompraActivity.this, "Se han quitado los productos de tu carrito", Toast.LENGTH_LONG).show();

            CompraActivity.this.finish();
        }
    }
}






















