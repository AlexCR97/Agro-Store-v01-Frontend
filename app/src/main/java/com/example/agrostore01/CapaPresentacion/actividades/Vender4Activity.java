package com.example.agrostore01.CapaPresentacion.actividades;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agrostore01.AgroMensajes;
import com.example.agrostore01.CapaEntidades.DetallesUsuario;
import com.example.agrostore01.CapaEntidades.Producto;
import com.example.agrostore01.CapaEntidades.Terreno;
import com.example.agrostore01.CapaEntidades.Usuario;
import com.example.agrostore01.CapaEntidades.vistas.VistaPublicacion;
import com.example.agrostore01.CapaNegocios.escritores.vistas.EscritorVistaPublicacion;
import com.example.agrostore01.CapaNegocios.lectores.LectorProducto;
import com.example.agrostore01.CapaNegocios.lectores.LectorTerreno;
import com.example.agrostore01.CapaNegocios.validaciones.vistas.ValidacionVistaPublicacion;
import com.example.agrostore01.R;

import java.math.BigDecimal;
import java.util.List;

public class Vender4Activity extends RecieveBundlesActivity {

    private EditText etTitulo;
    private EditText etDescripcion;
    private Spinner sProductos;
    private TextView tvPrecioTonelada;
    private TextView tvPrecioKilogramo;
    private EditText etPrecio;
    private Spinner sTerrenos;
    private TextView tvTamanoMedida;
    private TextView tvTipoSuelo;
    private Button buttonSiguiente;

    private Usuario usuario = new Usuario();
    private DetallesUsuario detallesUsuario = new DetallesUsuario();
    private VistaPublicacion vistaPublicacion = new VistaPublicacion();

    private List<Producto> productosList;
    private List<Terreno> terrenoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vender4);

        recieveBundles(this);

        etTitulo = findViewById(R.id.etVenderTitulo);
        etDescripcion = findViewById(R.id.etVenderDescripcion);
        sProductos = findViewById(R.id.sVenderProductos);
        tvPrecioTonelada = findViewById(R.id.tvVenderPrecioTonelada);
        tvPrecioKilogramo = findViewById(R.id.tvVenderPrecioKilogramo);
        etPrecio = findViewById(R.id.etVenderPrecio);
        sTerrenos = findViewById(R.id.sVenderTerrenos);
        tvTamanoMedida = findViewById(R.id.tvVenderTamanoMedida);
        tvTipoSuelo = findViewById(R.id.tvVenderTipoSuelo);
        buttonSiguiente = findViewById(R.id.buttonSiguienteVender4);

        sProductos.setOnItemSelectedListener(sProductosOnSelected);
        sTerrenos.setOnItemSelectedListener(sTerrenosOnSelected);
        buttonSiguiente.setOnClickListener(buttonSiguienteOnClick);

        new ObtenerProductos().execute();
        new ObtenerMisTerrenos().execute();
    }

    @Override
    public void recieveBundles(Context context) {
        usuario = getIntent().getParcelableExtra(usuario.getClassName());
        detallesUsuario = getIntent().getParcelableExtra(detallesUsuario.getClassName());
        vistaPublicacion = getIntent().getParcelableExtra(vistaPublicacion.getClassName());
    }

    private class ObtenerProductos extends AsyncTask<Void, Void, Void> {

        private String[] productos;
        private boolean exito;

        @Override
        protected Void doInBackground(Void... voids) {
            LectorProducto lectorProducto = new LectorProducto();
            productosList = lectorProducto.getEntidades();

            if (productosList == null) {
                exito = false;
                return null;
            }

            productos = new String[productosList.size()];

            int i = 0;
            for (Producto producto : productosList)
                productos[i++] = producto.getProducto();

            exito = true;

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            if (!exito) {
                Toast.makeText(Vender4Activity.this, AgroMensajes.ERROR_INTERNET, Toast.LENGTH_LONG).show();
                return;
            }

            ArrayAdapter<String> adapterProductos = new ArrayAdapter<>(
                    Vender4Activity.this,
                    R.layout.list_item_spinner,
                    productos
            );
            sProductos.setAdapter(adapterProductos);
        }
    }

    private class ObtenerMisTerrenos extends AsyncTask<Void, Void, Void> {

        private String[] terrenos;
        private boolean exito;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            LectorTerreno lectorTerreno = new LectorTerreno();
            terrenoList = lectorTerreno.getMisTerrenos(usuario.getIdUsuario());

            if (terrenoList == null) {
                exito = false;
                return null;
            }

            terrenos = new String[terrenoList.size()];

            int i = 0;
            for (Terreno terreno : terrenoList)
                terrenos[i++] = String.valueOf(terreno.getIdTerreno());

            exito = true;

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            if (!exito) {
                Toast.makeText(Vender4Activity.this,AgroMensajes.ERROR_INTERNET, Toast.LENGTH_LONG).show();
                return;
            }

            ArrayAdapter<String> adapterTerrenos = new ArrayAdapter<>(
                    Vender4Activity.this,
                    R.layout.list_item_spinner,
                    terrenos
            );
            sTerrenos.setAdapter(adapterTerrenos);
        }
    }

    private final AdapterView.OnItemSelectedListener sProductosOnSelected = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if (productosList == null)
                return;

            Producto producto = productosList.get(position);

            String precioTonelada = "$" + producto.getPrecioTonelada().setScale(2, BigDecimal.ROUND_HALF_EVEN);
            String precioKilogramo = "$" + producto.getPrecioKilogramo().setScale(2, BigDecimal.ROUND_HALF_EVEN);

            tvPrecioTonelada.setText(precioTonelada);
            tvPrecioKilogramo.setText(precioKilogramo);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {}
    };

    private final AdapterView.OnItemSelectedListener sTerrenosOnSelected = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if (terrenoList == null)
                return;

            Terreno terreno = terrenoList.get(position);

            String tamanoMedida = terreno.getTamaño() + " " + terreno.getMedida();
            String tipoSuelo = terreno.getTipo();

            tvTamanoMedida.setText(tamanoMedida);
            tvTipoSuelo.setText(tipoSuelo);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {}
    };

    private final View.OnClickListener buttonSiguienteOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            new PublicarProducto().execute();
        }
    };

    private long getIdFromProducto(String producto) {
        for (Producto p : productosList)
            if (p.getProducto().equals(producto))
                return p.getIdProducto();

        return -1;
    }

    private long getIdFromTerreno(String idTerreno) {
        for (Terreno t : terrenoList)
            if (t.getIdTerreno() == Long.valueOf(idTerreno))
                return t.getIdTerreno();

        return -1;
    }

    private class PublicarProducto extends AsyncTask<Void, Void, Void> {

        private String mensajeError;
        private boolean exito;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            String precio = etPrecio.getText().toString();

            if (precio.isEmpty()) {
                mensajeError = AgroMensajes.ERROR_PUBLICACION_INCORRECTA;
                exito = false;
                return;
            }

            String tituloPublicacion = etTitulo.getText().toString();
            String descripcion = etDescripcion.getText().toString();
            String producto = sProductos.getSelectedItem().toString();
            String terreno = sTerrenos.getSelectedItem().toString();

            long idProducto = getIdFromProducto(producto);
            long idTerreno = getIdFromTerreno(terreno);

            vistaPublicacion.setTituloPublicacion(tituloPublicacion);
            vistaPublicacion.setDescripcion(descripcion);
            vistaPublicacion.setIdProducto(idProducto);
            vistaPublicacion.setPrecio(new BigDecimal(precio));
            vistaPublicacion.setIdTerreno(idTerreno);

            ValidacionVistaPublicacion validacionVistaPublicacion = new ValidacionVistaPublicacion(vistaPublicacion);

            exito = validacionVistaPublicacion.validar();

            if (!exito)
                mensajeError = AgroMensajes.ERROR_PUBLICACION_INCORRECTA;
        }

        @Override
        protected Void doInBackground(Void... voids) {

            if (!exito)
                return null;

            EscritorVistaPublicacion escritorVistaPublicacion = new EscritorVistaPublicacion(
                    EscritorVistaPublicacion.OPERACION_PUBLICAR_PRODUCTO,
                    vistaPublicacion
            );

            exito = escritorVistaPublicacion.ejecutarCambios();

            if (!exito)
                mensajeError = AgroMensajes.ERROR_INTERNET;

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            if (!exito) {
                Toast.makeText(Vender4Activity.this, mensajeError, Toast.LENGTH_LONG).show();
                return;
            }

            Intent intent = new Intent(Vender4Activity.this, BarraActivity.class);
            intent.putExtra(usuario.getClassName(), usuario);
            intent.putExtra(detallesUsuario.getClassName(), detallesUsuario);
            startActivity(intent);
            finish();

            Toast.makeText(Vender4Activity.this, AgroMensajes.PRODUCTO_PUBLICADO, Toast.LENGTH_LONG).show();
        }
    }

}
