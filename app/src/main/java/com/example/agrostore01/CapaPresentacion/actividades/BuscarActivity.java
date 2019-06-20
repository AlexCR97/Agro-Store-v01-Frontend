package com.example.agrostore01.CapaPresentacion.actividades;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.agrostore01.CapaEntidades.DetallesUsuario;
import com.example.agrostore01.CapaEntidades.Usuario;
import com.example.agrostore01.CapaEntidades.vistas.VistaBusquedaProducto;
import com.example.agrostore01.CapaNegocios.lectores.LectorProducto;
import com.example.agrostore01.CapaNegocios.lectores.vistas.LectorVistaBusquedaProducto;
import com.example.agrostore01.R;
import com.example.agrostore01.CapaPresentacion.adaptadores.BusquedaAdapter;

import java.util.ArrayList;
import java.util.List;

public class BuscarActivity extends RecieveBundlesActivity {

    private SearchView buscador;
    private Button buttonFiltrar;
    private ListView listViewBuscar;

    private Usuario usuario = new Usuario();
    private DetallesUsuario detallesUsuario = new DetallesUsuario();

    // Variables de busqueda
    private String tipoBusqueda;
    private long filtroPrecioMin;
    private long filtroPrecioMax;
    private String filtroProducto;
    private String filtroCategoria;
    private String filtroTemporada;
    private String filtroPais;
    private String filtroEstado;
    private float filtroEstrellas;
    private ProgressDialog dialog;

    // Productos buscados
    private List<Integer> idProductos;
    private List<VistaBusquedaProducto> vistasProductos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);

        recieveBundles(this);

        buscador = findViewById(R.id.searchViewBuscador);
        buscador.setOnQueryTextListener(buscadorListener);

        buttonFiltrar = findViewById(R.id.buttonFiltrar);
        buttonFiltrar.setOnClickListener(buttonFiltrarListener);

        listViewBuscar = findViewById(R.id.listViewBuscar);
        listViewBuscar.setOnItemClickListener(listViewBuscarListener);

        dialog = new ProgressDialog(BuscarActivity.this);

        new RealizarBusqueda().execute();
    }

    @Override
    public void recieveBundles(Context context) {
        Intent intent = getIntent();

        usuario = intent.getParcelableExtra(usuario.getClassName());
        detallesUsuario = intent.getParcelableExtra(detallesUsuario.getClassName());

        if (!intent.hasExtra(FiltrosActivity.TIPO_BUSQUEDA)) {
            return;
        }

        tipoBusqueda = intent.getStringExtra(FiltrosActivity.TIPO_BUSQUEDA);

        switch (tipoBusqueda) {

            case FiltrosActivity.BUSQUEDA_CATEGORIA: {
                filtroCategoria = intent.getStringExtra(FiltrosActivity.BUSQUEDA_CATEGORIA);
                break;
            }

            case FiltrosActivity.BUSQUEDA_NOMBRE_PRODUCTO: {
                filtroProducto = intent.getStringExtra(FiltrosActivity.BUSQUEDA_NOMBRE_PRODUCTO);
                break;
            }

            case FiltrosActivity.BUSQUEDA_FILTRO: {
                filtroPrecioMin = intent.getLongExtra(FiltrosActivity.FILTRO_PRECIO_MIN, -1);
                filtroPrecioMax = intent.getLongExtra(FiltrosActivity.FILTRO_PRECIO_MAX, -1);
                filtroCategoria = intent.getStringExtra(FiltrosActivity.FILTRO_CATEGORIA);
                filtroTemporada = intent.getStringExtra(FiltrosActivity.FILTRO_TEMPORADA);
                filtroPais = intent.getStringExtra(FiltrosActivity.FILTRO_PAIS);
                filtroEstado = intent.getStringExtra(FiltrosActivity.FILTRO_ESTADO);
                filtroEstrellas = intent.getFloatExtra(FiltrosActivity.FILTRO_ESTRELLAS, 0);
                break;
            }

            default: {
                break;
            }
        }
    }

    private final SearchView.OnQueryTextListener buscadorListener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            tipoBusqueda = FiltrosActivity.BUSQUEDA_NOMBRE_PRODUCTO;
            filtroProducto = query;

            new RealizarBusqueda().execute();
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            return false;
        }
    };

    private class RealizarBusqueda extends AsyncTask<Void, Void, Void> {

        private LectorProducto lectorProducto = new LectorProducto();
        private boolean exito;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.setTitle("Buscando producto");
            dialog.setMessage("Espere un momento");
            dialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            switch (tipoBusqueda) {

                case FiltrosActivity.BUSQUEDA_CATEGORIA: {
                    System.out.println("Tipo de tipoBusqueda 'CATEGORIA' '" + filtroCategoria + "'");

                    idProductos = lectorProducto.getIdProductosConCategoria(filtroCategoria);

                    System.out.println("Se obtuvieron los siguientes productos con el filtroCategoria '" + filtroCategoria + "'");
                    System.out.println(idProductos);

                    LectorVistaBusquedaProducto lectorVistaBusquedaProducto = new LectorVistaBusquedaProducto();
                    vistasProductos = new ArrayList<>();
                    for (Integer id : idProductos) {
                        VistaBusquedaProducto producto = lectorVistaBusquedaProducto.getEntidadId(id);
                        vistasProductos.add(producto);
                    }

                    System.out.println("Las vistas para los productos son: ");
                    System.out.println(vistasProductos);

                    break;
                }

                case FiltrosActivity.BUSQUEDA_NOMBRE_PRODUCTO: {
                    System.out.println("Tipo de tipoBusqueda 'NOMBRE PRODUCTO' '" + filtroProducto + "'");

                    idProductos = lectorProducto.getIdProductosConNombre(filtroProducto);

                    System.out.println("Se obtuvieron los siguientes productos con el nombre '" + filtroProducto + "'");
                    System.out.println(idProductos);

                    LectorVistaBusquedaProducto lectorVistaBusquedaProducto = new LectorVistaBusquedaProducto();
                    vistasProductos = new ArrayList<>();
                    for (Integer id : idProductos) {
                        VistaBusquedaProducto producto = lectorVistaBusquedaProducto.getEntidadId(id);
                        vistasProductos.add(producto);
                    }

                    System.out.println("Las vistas para los productos son: ");
                    System.out.println(vistasProductos);

                    break;
                }

                case FiltrosActivity.BUSQUEDA_FILTRO: {
                    System.out.println("Tipo de tipoBusqueda 'FILTROS'");

                    idProductos = lectorProducto.getIdProductosConFiltros(
                            filtroPrecioMin,
                            filtroPrecioMax,
                            "",
                            filtroCategoria,
                            filtroTemporada,
                            filtroPais,
                            filtroEstado,
                            filtroEstrellas
                    );

                    System.out.println("Se obtuvieron los siguientes productos con los filtros especificados");
                    System.out.println(idProductos);

                    LectorVistaBusquedaProducto lectorVistaBusquedaProducto = new LectorVistaBusquedaProducto();
                    vistasProductos = new ArrayList<>();
                    for (Integer id : idProductos) {
                        VistaBusquedaProducto producto = lectorVistaBusquedaProducto.getEntidadId(id);
                        vistasProductos.add(producto);
                    }

                    System.out.println("Las vistas para los productos son: ");
                    System.out.println(vistasProductos);

                    break;
                }

                default: {
                    System.out.println("Ningun tipo de tipoBusqueda detectado");
                }
            }

            exito = vistasProductos != null;
            return null;

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            if (!exito) {
                Toast.makeText(
                        BuscarActivity.this,
                        "Hubo un error en la busqueda de los productos. Verifique su conexion a Internet e intentelo de nuevo.",
                        Toast.LENGTH_LONG
                ).show();
                return;
            }

            if (vistasProductos != null) {
                BusquedaAdapter adaptador = new BusquedaAdapter(BuscarActivity.this, R.layout.list_item_buscar, vistasProductos);
                listViewBuscar.setAdapter(adaptador);
            }

            System.out.println("Terminado el hilo de tipoBusqueda");
            dialog.cancel();
        }
    }

    private final View.OnClickListener buttonFiltrarListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext(), FiltrosActivity.class);
            intent.putExtra(usuario.getClassName(), usuario);
            intent.putExtra(detallesUsuario.getClassName(), detallesUsuario);
            startActivity(intent);
        }
    };

    private AdapterView.OnItemClickListener listViewBuscarListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            System.out.println("Clicked item " + position);
            System.out.println("Item id is " + idProductos.get(position));
            System.out.println("Vista producto is " + vistasProductos.get(position));

            Intent intent = new Intent(view.getContext(), DetallesProductoActivity.class);
            intent.putExtra(usuario.getClassName(), usuario);
            intent.putExtra(detallesUsuario.getClassName(), detallesUsuario);
            intent.putExtra("idProducto", idProductos.get(position));
            intent.putExtra("vistaProducto", vistasProductos.get(position));

            startActivity(intent);
        }
    };

}
