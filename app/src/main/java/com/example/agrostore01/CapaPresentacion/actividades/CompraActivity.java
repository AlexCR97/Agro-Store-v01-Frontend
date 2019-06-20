package com.example.agrostore01.CapaPresentacion.actividades;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.EditText;

import com.example.agrostore01.AgroMensajes;
import com.example.agrostore01.AgroUtils;
import com.example.agrostore01.CapaEntidades.DetallesUsuario;
import com.example.agrostore01.CapaEntidades.Usuario;
import com.example.agrostore01.CapaEntidades.vistas.VistaCompra;
import com.example.agrostore01.CapaNegocios.escritores.Escritor;
import com.example.agrostore01.CapaNegocios.escritores.EscritorCarrito;
import com.example.agrostore01.CapaNegocios.escritores.EscritorCompraUsuario;
import com.example.agrostore01.R;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;

public class CompraActivity extends RecieveBundlesActivity {

    private EditText etCodigoPostal,etCalle, etColonia,etCiudad,etNombre,etApellido;
    private Spinner sEstado, sPais;
    private Button bComprar,bDireccionExtra;
    private Dialog dDireccion;

    private TextView tvDireccion;
    private TextView tvNombreRecibe;
    private TextView tvNumeroCompra;
    private TextView tvNumeroCliente;
    private TextView tvNumeroRastreo;

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
        bDireccionExtra = findViewById(R.id.bDireccionExtra);

        bComprar.setOnClickListener(bComprarOnClick);

        bDireccionExtra.setOnClickListener(bDireccionExtraOnClick);

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

    private final View.OnClickListener bDireccionExtraOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

AlertDialog.Builder mBuilder = new AlertDialog.Builder(CompraActivity.this);
View mView = getLayoutInflater().inflate(R.layout.layout_dialog,null);
mBuilder.setTitle("Dirección");
            etCodigoPostal = mView.findViewById(R.id.etCodigoPostal);
            etCalle = mView.findViewById(R.id.etCalle);
            etColonia = mView.findViewById(R.id.etColonia);
            etCiudad = mView.findViewById(R.id.etCiudad);
            sEstado = mView.findViewById(R.id.sEstado);
            sPais = mView.findViewById(R.id.sPais);
            etNombre = mView.findViewById(R.id.etNombre);
            etApellido = mView.findViewById(R.id.etApellido);

            ArrayAdapter<String> adapterPais = new ArrayAdapter<String>(CompraActivity.this,
                    android.R.layout.simple_spinner_item,
                    getResources().getStringArray(R.array.paisList));

            adapterPais.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sPais.setAdapter(adapterPais);

            ArrayAdapter<String> adapterEstado = new ArrayAdapter<String>(CompraActivity.this,
                    android.R.layout.simple_spinner_item,
                    getResources().getStringArray(R.array.estadoList));

            adapterEstado.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sEstado.setAdapter(adapterEstado);
            mBuilder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String codigopostal = etCodigoPostal.getText().toString();
                    String calle = etCalle.getText().toString();
                    String colonia = etColonia.getText().toString();
                    String ciudad = etCiudad.getText().toString();
                    String estado = sEstado.getSelectedItem().toString();
                    String pais = sPais.getSelectedItem().toString();
                    String nombre = etNombre.getText().toString();
                    String apellido = etApellido.getText().toString();

                }
            }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });

            mBuilder.setView(mView);
            AlertDialog dialog = mBuilder.create();
            dialog.show();

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
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(CompraActivity.this);

            if (!exito) {

                alertDialog.setTitle("Advertencia")
                        .setMessage(AgroMensajes.ERROR_INTERNET)
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        });
                alertDialog.show();

                //Toast.makeText(CompraActivity.this, AgroMensajes.ERROR_INTERNET, Toast.LENGTH_LONG).show();
                return;
            }

            alertDialog.setTitle("")
                    .setMessage(AgroMensajes.COMPRA_REALIZADA)
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });
            alertDialog.show();
          
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






















