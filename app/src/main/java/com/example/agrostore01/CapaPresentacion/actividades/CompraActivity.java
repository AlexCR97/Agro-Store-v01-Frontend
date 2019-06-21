package com.example.agrostore01.CapaPresentacion.actividades;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.EditText;

import com.example.agrostore01.AgroMensajes;
import com.example.agrostore01.AgroUtils;
import com.example.agrostore01.CapaEntidades.DetallesUsuario;
import com.example.agrostore01.CapaEntidades.Usuario;
import com.example.agrostore01.CapaEntidades.vistas.VistaCarrito;
import com.example.agrostore01.CapaEntidades.vistas.VistaCompra;
import com.example.agrostore01.CapaNegocios.escritores.EscritorCarrito;
import com.example.agrostore01.CapaNegocios.escritores.EscritorCompraUsuario;
import com.example.agrostore01.CapaPresentacion.hilos.HiloNotificar;
import com.example.agrostore01.R;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CompraActivity extends RecieveBundlesActivity {

    private EditText etCodigoPostal,etCalle, etColonia,etCiudad,etNombre,etApellido;
    private Spinner sEstado, sPais;
    private Button bComprar,bDireccionExtra;

    private TextView tvDireccion;
    private TextView tvNombreRecibe;
    private TextView tvNumeroCompra;
    private TextView tvNumeroCliente;
    private TextView tvNumeroRastreo;

    private TableLayout tTicket;

    private Usuario usuario = new Usuario();
    private DetallesUsuario detallesUsuario = new DetallesUsuario();
    private ArrayList<VistaCarrito> vistaCarritoList = new ArrayList<>();
    private ArrayList<VistaCompra> vistaComprasList = new ArrayList<>();

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
        bDireccionExtra = findViewById(R.id.bDireccionExtra);
        bComprar = findViewById(R.id.bComprar);

        tTicket = findViewById(R.id.tTicket);

        bDireccionExtra.setOnClickListener(bDireccionExtraOnClick);
        bComprar.setOnClickListener(bComprarOnClick);

        // Llenar campos
        llenarCampos(
                String.valueOf(detallesUsuario.getCp()),
                detallesUsuario.getCalle(),
                detallesUsuario.getColonia(),
                detallesUsuario.getCuidad(),
                detallesUsuario.getEstado(),
                detallesUsuario.getNombres(),
                detallesUsuario.getApellidos(),
                String.valueOf(AgroUtils.generarNumeroAleatorio(1, 1000)),
                String.valueOf(AgroUtils.generarNumeroAleatorio(1, 1000)),
                AgroUtils.generarIdAleatorio(8)
        );

        generarTicket();
    }

    @Override
    public void recieveBundles(Context context) {
        usuario = getIntent().getParcelableExtra(usuario.getClassName());
        detallesUsuario = getIntent().getParcelableExtra(detallesUsuario.getClassName());
        vistaComprasList = getIntent().getParcelableArrayListExtra(vistaComprasList.getClass().getSimpleName());
        vistaCarritoList = getIntent().getParcelableArrayListExtra(vistaCarritoList.getClass().getSimpleName() + "1");
    }

    private void llenarCampos(String cp, String calle, String colonia, String ciudad, String estado, String nombreRecibe,
                              String apellidoRecibe, String numeroCompra, String numeroCliente, String numeroRastreo) {

        String direccion = String.format(
                "%s %s Col. %s, %s, %s",
                cp, calle, colonia, ciudad, estado
        );

        String nombre = nombreRecibe + " " + apellidoRecibe;
        numeroCompra = "#" + numeroCompra;
        numeroCliente = "#" + numeroCliente;

        tvDireccion.setText(direccion);
        tvNombreRecibe.setText(nombre);
        tvNumeroCompra.setText(numeroCompra);
        tvNumeroCliente.setText(numeroCliente);
        tvNumeroRastreo.setText(numeroRastreo);
    }

    private void generarTicket() {

        tTicket.removeAllViews();

        BigDecimal subTotal = new BigDecimal(0);

        for (VistaCarrito vistaCarrito : vistaCarritoList) {
            subTotal = subTotal
                    .add(new BigDecimal(vistaCarrito.getPrecio()))
                    .multiply(new BigDecimal(vistaCarrito.getCantidad()));

            String titulo = vistaCarrito.getCantidad() + " " + vistaCarrito.getProducto();
            String precio = new BigDecimal(vistaCarrito.getPrecio()).setScale(2, BigDecimal.ROUND_HALF_EVEN).toString();

            agregarFilaPrecio(titulo, precio);
        }

        agregarFilaSeparador();

        subTotal = subTotal.setScale(2, BigDecimal.ROUND_HALF_EVEN);
        agregarFilaPrecio("SubTotal", "$" + subTotal.toString());

        BigDecimal cuota = subTotal
                .multiply(new BigDecimal(15))
                .divide(new BigDecimal(100), BigDecimal.ROUND_HALF_EVEN)
                .setScale(2, BigDecimal.ROUND_HALF_EVEN);

        BigDecimal total = subTotal
                .add(cuota)
                .setScale(2, BigDecimal.ROUND_HALF_EVEN);

        agregarFilaPrecio("+ Cuota 15%", cuota.toString());
        agregarFilaSeparador();
        agregarFilaPrecio("TOTAL", total.toString());
    }

    private void agregarFilaTitulo(String titulo) {
        TextView tvTitulo = new TextView(this);
        tvTitulo.setText(titulo);
        tvTitulo.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
        tvTitulo.setTextSize(18);
        AgroUtils.setViewMargin(tvTitulo, 5, 5, 5, 5);

        TableRow row = new TableRow(this);
        TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT
        );
        row.setLayoutParams(layoutParams);
        row.addView(tvTitulo);

        tTicket.addView(row);
    }

    private void agregarFilaSeparador() {
        View separador = new View(this);
        separador.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                5
        ));
        separador.setBackgroundColor(Color.LTGRAY);
        AgroUtils.setViewMargin(separador, 10, 10, 10, 10);

        tTicket.addView(separador);
    }

    private void agregarFilaPrecio(String titulo, String precio) {
        TextView tvTitulo = new TextView(this);
        tvTitulo.setText(titulo);
        tvTitulo.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
        tvTitulo.setTextSize(18);
        AgroUtils.setViewMargin(tvTitulo, 5, 5, 5, 5);

        TextView tvPrecio = new TextView(this);
        tvPrecio.setText(precio);
        tvPrecio.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
        tvPrecio.setTextColor(Color.BLACK);
        tvPrecio.setTextSize(18);
        AgroUtils.setViewMargin(tvPrecio, 5, 5, 5, 5);

        TableRow row = new TableRow(this);
        TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT
        );
        row.setLayoutParams(layoutParams);
        row.addView(tvTitulo);
        row.addView(tvPrecio);

        tTicket.addView(row);
    }

    private final View.OnClickListener bComprarOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if (vistaComprasList == null)
                return;

            if (vistaComprasList.isEmpty())
                return;

            new RealizarCompra().execute();
        }
    };

    private final View.OnClickListener bDireccionExtraOnClick = new View.OnClickListener() {
        @Override
        public void onClick(final View v) {

            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
            View builderView = getLayoutInflater().inflate(R.layout.layout_dialog,null);
            builder.setTitle("Nueva dirección");

            etCodigoPostal = builderView.findViewById(R.id.etCodigoPostal);
            etCalle = builderView.findViewById(R.id.etCalle);
            etColonia = builderView.findViewById(R.id.etColonia);
            etCiudad = builderView.findViewById(R.id.etCiudad);
            sEstado = builderView.findViewById(R.id.sEstado);
            sPais = builderView.findViewById(R.id.sPais);
            etNombre = builderView.findViewById(R.id.etNombre);
            etApellido = builderView.findViewById(R.id.etApellido);

            ArrayAdapter<String> adapterPais = new ArrayAdapter<>(
                    v.getContext(),
                    android.R.layout.simple_spinner_item,
                    getResources().getStringArray(R.array.paisList)
            );

            adapterPais.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sPais.setAdapter(adapterPais);

            ArrayAdapter<String> adapterEstado = new ArrayAdapter<>(
                    v.getContext(),
                    android.R.layout.simple_spinner_item,
                    getResources().getStringArray(R.array.estadoList)
            );

            adapterEstado.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sEstado.setAdapter(adapterEstado);

            DialogInterface.OnClickListener dialogInterface = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String cp = etCodigoPostal.getText().toString();
                    String calle = etCalle.getText().toString();
                    String colonia = etColonia.getText().toString();
                    String ciudad = etCiudad.getText().toString();
                    String estado = sEstado.getSelectedItem().toString();
                    String pais = sPais.getSelectedItem().toString();
                    String nombre = etNombre.getText().toString();
                    String apellido = etApellido.getText().toString();

                    if (cp.isEmpty() || calle.isEmpty() || colonia.isEmpty() || ciudad.isEmpty() || nombre.isEmpty() || apellido.isEmpty()) {
                        return;
                    }

                    llenarCampos(
                            cp, calle, colonia, ciudad, estado,
                            nombre, apellido,
                            String.valueOf(AgroUtils.generarNumeroAleatorio(1, 1000)),
                            String.valueOf(AgroUtils.generarNumeroAleatorio(1, 1000)),
                            AgroUtils.generarIdAleatorio(8)
                    );
                }
            };

            builder.setPositiveButton("Confirmar", dialogInterface);
            builder.setView(builderView);

            AlertDialog dialog = builder.create();
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

            for (int i = 0; i < vistaComprasList.size(); i++) {
                VistaCompra compra = vistaComprasList.get(i);
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
                            dialogInterface.dismiss();
                        }
                    })
                    .show();

            // Notificar las vistaComprasList

            List<Integer> idsRepetidos = new ArrayList<>();

            for (VistaCompra vistaCompra : vistaComprasList)
                idsRepetidos.add(vistaCompra.getIdNumProducto());

            List<Integer> idsUnicos = AgroUtils.getDifferentIds(idsRepetidos);

            for (Integer idNumProducto : idsUnicos) {

                String detalle = String.format(
                        "¡El usuario \"%s %s\" ha realizado una compra! Publicacion #%s",
                        detallesUsuario.getNombres(),
                        detallesUsuario.getApellidos(),
                        idNumProducto
                );

                new HiloNotificar(idNumProducto, detalle).execute();
            }

            // Quitar productos del vistaCarritoList del usuario

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

            for (VistaCompra compra : vistaComprasList) {
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

            CompraActivity.this.finish();
        }
    }
}






















