package com.example.agrostore01.CapaPresentacion.actividades;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agrostore01.AgroMensajes;
import com.example.agrostore01.AgroUtils;
import com.example.agrostore01.CapaEntidades.DetallesUsuario;
import com.example.agrostore01.CapaEntidades.Usuario;
import com.example.agrostore01.CapaEntidades.vistas.VistaBusquedaProducto;
import com.example.agrostore01.CapaEntidades.vistas.VistaComentarios;
import com.example.agrostore01.CapaEntidades.vistas.VistaCompra;
import com.example.agrostore01.CapaNegocios.escritores.EscritorCarrito;
import com.example.agrostore01.CapaNegocios.escritores.EscritorComentarios;
import com.example.agrostore01.CapaNegocios.lectores.vistas.LectorVistaBusquedaProducto;
import com.example.agrostore01.CapaNegocios.lectores.vistas.LectorVistaComentario;
import com.example.agrostore01.CapaPresentacion.adaptadores.ComentariosAdapter;
import com.example.agrostore01.R;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DetallesProductoActivity extends RecieveBundlesActivity {

    private ImageView ivImagenProducto;
    private TextView tvTitulo, tvPrecio, tvLocalidad;
    private RatingBar rbEstrellas;
    private EditText etCantidad;
    private ImageView ivAgregarProducto, ivQuitarProducto;
    private Button bComprar;
    private TextView tvVendedor, tvProducto, tvDescripcion;
    private EditText etComentario;
    private ImageButton ibComentar;
    private ListView lvComentarios;

    private ProgressDialog dialog;

    private Usuario usuario = new Usuario();
    private DetallesUsuario detallesUsuario = new DetallesUsuario();

    private int idProducto;
    private VistaBusquedaProducto vistaProducto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_producto);

        recieveBundles(this);

        ivImagenProducto = findViewById(R.id.imageViewItemDetallesProducto);
        tvTitulo = findViewById(R.id.textViewItemDetallesTitulo);
        tvPrecio = findViewById(R.id.textViewItemDetallesPrecio);
        tvLocalidad = findViewById(R.id.textViewItemDetallesLocalidad);
        rbEstrellas = findViewById(R.id.rbItemDetallesEstrellas);
        etCantidad = findViewById(R.id.etDetalleProductoCantidad);
        ivAgregarProducto = findViewById(R.id.ivDetalleProductoAgregar);
        ivQuitarProducto = findViewById(R.id.ivDetalleProductoQuitar);
        bComprar = findViewById(R.id.bDetalleProductoComprar);
        tvVendedor = findViewById(R.id.tvDetallesProductoVendedor);
        tvProducto = findViewById(R.id.tvDetallesProductoNombreProducto);
        tvDescripcion = findViewById(R.id.tvDetallesProductoDescripcion);
        etComentario = findViewById(R.id.etComentario);
        ibComentar = findViewById(R.id.ibComentar);
        lvComentarios = findViewById(R.id.lvComentarios);

        etCantidad.addTextChangedListener(etCantidadWatcher);
        ivAgregarProducto.setOnClickListener(ivAgregarProductoOnClick);
        ivQuitarProducto.setOnClickListener(ivQuitarProductoOnClick);
        bComprar.setOnClickListener(bComprarOnClick);
        ibComentar.setOnClickListener(ibComentarOnClick);

        dialog = new ProgressDialog(DetallesProductoActivity.this);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);

        dialog.setTitle("Cargando producto");
        dialog.setMessage("Espere un momento");
        dialog.show();

        // Arreglar listview dentro de scrollview
        AgroUtils.setListViewScrollInsideScrollView(lvComentarios);
        AgroUtils.setListViewShowableAmountOfItems(lvComentarios, 4);

        new LlenarCampos().execute();
        new ObtenerComentarios().execute();
    }

    @Override
    public void recieveBundles(Context context) {
        usuario = getIntent().getParcelableExtra(usuario.getClassName());
        detallesUsuario = getIntent().getParcelableExtra(detallesUsuario.getClassName());

        idProducto = getIntent().getIntExtra("idProducto", -1);
    }

    private class LlenarCampos extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            System.out.println("Just started thread");
        }

        @Override
        protected Void doInBackground(Void... voids) {

            System.out.println("Executing thread");

            LectorVistaBusquedaProducto lectorVistaBusquedaProducto = new LectorVistaBusquedaProducto();
            vistaProducto = lectorVistaBusquedaProducto.getEntidadId(idProducto);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            String titulo = vistaProducto.getProducto();
            String precio = "$" + vistaProducto.getPrecio().setScale(2, BigDecimal.ROUND_HALF_UP) +
                    ", " + vistaProducto.getHectareas() + " hectareas";
            String localidad = vistaProducto.getCiudad() + ", " + vistaProducto.getEstado();
            String vendedor = vistaProducto.getNombreUsuario() + " " + vistaProducto.getApellidosUsuario();
            String descripcion = vistaProducto.getDescripcion();

            tvTitulo.setText(titulo);
            tvPrecio.setText(precio);
            tvLocalidad.setText(localidad);
            tvVendedor.setText(vendedor);
            tvDescripcion.setText(descripcion);

            System.out.println("Just finished thread");
        }
    }

    private class ObtenerComentarios extends AsyncTask<Void, Void, Void> {

        private LectorVistaComentario lectorVistaComentario = new LectorVistaComentario();
        private List<VistaComentarios> comentarios;
        private boolean exito;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            lectorVistaComentario = new LectorVistaComentario();
            comentarios = lectorVistaComentario.getComentarios(idProducto);

            exito = comentarios != null;

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(DetallesProductoActivity.this);

            if (!exito) {
                alertDialog.setTitle("Advertencia")
                        .setMessage(AgroMensajes.ERROR_INTERNET)
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        });
                alertDialog.show();

                //Toast.makeText(DetallesProductoActivity.this, AgroMensajes.ERROR_INTERNET, Toast.LENGTH_LONG).show();
                return;
            }

            ComentariosAdapter adapter = new ComentariosAdapter(DetallesProductoActivity.this, R.layout.list_item_comentario, comentarios);
            lvComentarios.setAdapter(adapter);
            dialog.cancel();
        }
    }

    private final TextWatcher etCantidadWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String cantidadString = etCantidad.getText().toString();
            int cantidad = cantidadString.isEmpty()? 0 : Integer.valueOf(cantidadString);
            int max = vistaProducto.getHectareas();

            if (cantidad > max)
                etCantidad.setText(String.valueOf(max));
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private final View.OnClickListener ivAgregarProductoOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            new AgregarProducto().execute();
        }
    };

    private class AgregarProducto extends AsyncTask<Void, Void, Void> {

        private boolean exito;
        private String mensajeError;

        private int idNumProducto;
        private String idUsuario;
        private int cantidad;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            idNumProducto = idProducto;
            idUsuario = usuario.getIdUsuario();
            String cantidadTemp = etCantidad.getText().toString();
            cantidad = cantidadTemp.isEmpty() ? -1 : Integer.parseInt(cantidadTemp);
        }

        @Override
        protected Void doInBackground(Void... voids) {

            if (cantidad <= 0) {
                mensajeError = AgroMensajes.ERROR_CANTIDAD_INVALIDA;
                exito = false;
                return null;
            }

            EscritorCarrito escritorCarrito = new EscritorCarrito(
                    EscritorCarrito.OPERACION_AGREGAR_PRODUCTO,
                    null,
                    idNumProducto,
                    idUsuario,
                    cantidad
            );

            exito = escritorCarrito.ejecutarCambios();

            if (!exito) {
                mensajeError = AgroMensajes.ERROR_INTERNET;
                exito = false;
                return null;
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(DetallesProductoActivity.this);

            if (!exito) {
                alertDialog.setTitle("Advertencia")
                        .setMessage(mensajeError)
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        });
                alertDialog.show();
                //Toast.makeText(DetallesProductoActivity.this, mensajeError, Toast.LENGTH_LONG).show();
                return;
            }
            alertDialog.setTitle("Advertencia")
                    .setMessage("Se ha anadido este producto a tu carrito")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });
            alertDialog.show();

            //Toast.makeText(DetallesProductoActivity.this, "Se ha anadido este producto a tu carrito", Toast.LENGTH_LONG).show();
        }
    }

    private final View.OnClickListener ivQuitarProductoOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            new QuitarProducto().execute();
        }
    };

    private class QuitarProducto extends AsyncTask<Void, Void, Void> {

        private boolean exito;
        private String mensajeError;

        private int idNumProducto;
        private String idUsuario;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            idNumProducto = idProducto;
            idUsuario = usuario.getIdUsuario();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            EscritorCarrito escritorCarrito = new EscritorCarrito(
                    EscritorCarrito.OPERACION_QUITAR_PRODUCTO,
                    null,
                    idNumProducto,
                    idUsuario
            );

            exito = escritorCarrito.ejecutarCambios();

            if (!exito) {
                mensajeError = AgroMensajes.ERROR_INTERNET;
                exito = false;
                return null;
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            if (!exito) {
                Toast.makeText(DetallesProductoActivity.this, mensajeError, Toast.LENGTH_LONG).show();
                return;
            }

            Toast.makeText(DetallesProductoActivity.this, "Se ha quitado este producto de tu carrito", Toast.LENGTH_LONG).show();
        }
    }

    private final View.OnClickListener bComprarOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            String cantidadTemp = etCantidad.getText().toString();

            VistaCompra vistaCompra = new VistaCompra(
                    idProducto,
                    usuario.getIdUsuario(),
                    cantidadTemp.isEmpty() ? -1 : Integer.parseInt(cantidadTemp)
            );

            if (vistaCompra.getCantidad() <= 0) {
                Toast.makeText(DetallesProductoActivity.this, AgroMensajes.ERROR_CANTIDAD_INVALIDA, Toast.LENGTH_LONG).show();
                return;
            }

            ArrayList<VistaCompra> compras = new ArrayList<>();
            compras.add(vistaCompra);

            Intent intent = new Intent(DetallesProductoActivity.this, CompraActivity.class);
            intent.putExtra(usuario.getClassName(), usuario);
            intent.putExtra(detallesUsuario.getClassName(), detallesUsuario);
            intent.putParcelableArrayListExtra(compras.getClass().getSimpleName(), compras);

            startActivity(intent);
        }
    };

    private final View.OnClickListener ibComentarOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            new ComentarPublicacion().execute();
        }
    };

    private class ComentarPublicacion extends AsyncTask<Void, Void, Void> {

        private String idUsuario;
        private String comentario;
        private int idNumProducto;

        private boolean exito;
        private String mensajeError;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            comentario = etComentario.getText().toString();
            if (comentario.isEmpty()) {
                mensajeError = AgroMensajes.ERROR_COMENTARIO_VACIO;
                return;
            }

            idUsuario = usuario.getIdUsuario();
            idNumProducto = idProducto;

            exito = true;
        }

        @Override
        protected Void doInBackground(Void... voids) {

            if (!exito)
                return null;

            EscritorComentarios escritorComentarios = new EscritorComentarios(
                    EscritorComentarios.OPERACION_COMENTAR,
                    null,
                    idUsuario,
                    comentario,
                    idNumProducto
            );

            exito = escritorComentarios.ejecutarCambios();

            if (!exito)
                mensajeError = AgroMensajes.ERROR_INTERNET;

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            if (!exito) {
                Toast.makeText(DetallesProductoActivity.this, mensajeError, Toast.LENGTH_LONG).show();
                return;
            }

            Toast.makeText(DetallesProductoActivity.this, AgroMensajes.COMENTARIO_PUBLICADO, Toast.LENGTH_LONG).show();

            new ObtenerComentarios().execute();
        }
    }

}

















