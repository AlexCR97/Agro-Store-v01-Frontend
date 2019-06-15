package com.example.agrostore01.CapaPresentacion.actividades;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MotionEvent;
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
import com.example.agrostore01.CapaEntidades.Usuario;
import com.example.agrostore01.CapaEntidades.vistas.VistaBusquedaProducto;
import com.example.agrostore01.CapaEntidades.vistas.VistaComentarios;
import com.example.agrostore01.CapaNegocios.lectores.vistas.LectorVistaBusquedaProducto;
import com.example.agrostore01.CapaNegocios.lectores.vistas.LectorVistaComentario;
import com.example.agrostore01.CapaPresentacion.adaptadores.ComentariosAdapter;
import com.example.agrostore01.R;

import java.math.BigDecimal;
import java.util.List;

public class DetallesProductoActivity extends RecieveBundlesActivity {

    private ImageView ivImagenProducto;
    private TextView tvTitulo, tvPrecio, tvLocalidad;
    private RatingBar rbEstrellas;
    private ImageView ivAgregarProducto, ivQuitarProducto;
    private Button bComprar;
    private TextView tvVendedor, tvProducto, tvDescripcion;
    private EditText etComentario;
    private ImageButton ibComentar;
    private ListView lvComentarios;

    private Usuario usuario = new Usuario();
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
        ivAgregarProducto = findViewById(R.id.ivDetalleProductoAgregar);
        ivQuitarProducto = findViewById(R.id.ivDetalleProductoQuitar);
        bComprar = findViewById(R.id.bDetalleProductoComprar);
        tvVendedor = findViewById(R.id.tvDetallesProductoVendedor);
        tvProducto = findViewById(R.id.tvDetallesProductoNombreProducto);
        tvDescripcion = findViewById(R.id.tvDetallesProductoDescripcion);
        etComentario = findViewById(R.id.etComentario);
        ibComentar = findViewById(R.id.ibComentar);
        lvComentarios = findViewById(R.id.lvComentarios);

        // Arreglar listview dentro de scrollview
        //AgroUtils.setListViewScrollInsideScrollView(lvComentarios);
        //AgroUtils.setListViewHeightBasedOnChildren(lvComentarios);
        //AgroUtils.setListViewShowableAmountOfItems(lvComentarios, 4);

        new LlenarCampos().execute();
        new ObtenerComentarios().execute();
    }

    @Override
    public void recieveBundles(Context context) {
        usuario = getIntent().getParcelableExtra(usuario.getClassName());
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
            String precio = "$" + vistaProducto.getPrecio().setScale(2, BigDecimal.ROUND_HALF_UP) + ", " + vistaProducto.getHectareas() + " hectareas";
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

            if (!exito) {
                Toast.makeText(DetallesProductoActivity.this, AgroMensajes.ERROR_INTERNET, Toast.LENGTH_LONG).show();
                return;
            }

            for (int i = 0; i < 10; i++)
                comentarios.add(new VistaComentarios());

            ComentariosAdapter adapter = new ComentariosAdapter(DetallesProductoActivity.this, R.layout.list_item_comentario, comentarios);
            lvComentarios.setAdapter(adapter);
        }
    }

    private final View.OnClickListener ivAgregarProductoOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

    private final View.OnClickListener ivQuitarProductoOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

    private final View.OnClickListener bComprarOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

}
