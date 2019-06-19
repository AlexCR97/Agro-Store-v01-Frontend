package com.example.agrostore01.CapaPresentacion.actividades;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.agrostore01.CapaEntidades.Usuario;
import com.example.agrostore01.CapaEntidades.vistas.VistaMisVentas;
import com.example.agrostore01.CapaNegocios.lectores.vistas.LectorMisVentas;
import com.example.agrostore01.R;
import com.example.agrostore01.CapaPresentacion.adaptadores.MisVentasAdapter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MisVentasActivity extends RecieveBundlesActivity {

    private ListView listViewMisVentas;
private ProgressDialog dialog;
    private List<VistaMisVentas> misVentas = new ArrayList<>();

    private Usuario usuario = new Usuario();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_ventas);

        recieveBundles(this);

        listViewMisVentas = findViewById(R.id.listViewMisVentas);
        listViewMisVentas.setOnItemClickListener(listViewMisVentasListener);
        dialog = new ProgressDialog(MisVentasActivity.this);

        dialog.setTitle("Cargando ventas");
        dialog.setMessage("Espere un momento");
        dialog.show();

        new ObtenerMisVentas().execute();
    }

    @Override
    public void recieveBundles(Context context) {
        usuario = getIntent().getParcelableExtra(usuario.getClassName());
    }

    private class ObtenerMisVentas extends AsyncTask<Void, Void, Void> {

        private LectorMisVentas lectorMisCompras = new LectorMisVentas();
        private boolean exito;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            misVentas = lectorMisCompras.getMisVentas(usuario.getIdUsuario());
            exito = misVentas != null;

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(MisVentasActivity.this);

            if (!exito) {
                dialog.cancel();
                alertDialog.setTitle("Advertencia")
                        .setMessage("Hubo un error al obtener tus ventas. Verifica tu conexion a Internet e intentelo de nuevo")
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        });
                alertDialog.show();

                //Toast.makeText(MisVentasActivity.this,"Hubo un error al obtener tus ventas. Verifica tu conexion a Internet e intentelo de nuevo",Toast.LENGTH_LONG).show();
                return;
            }

            /**
             * private int numeroDeVenta;
             *     private BigDecimal precio;
             *     private String producto;
             *     private String fecha;
             */

            misVentas.add(new VistaMisVentas(1, new BigDecimal(5000), "Claras", "97-11-13"));
            misVentas.add(new VistaMisVentas(1, new BigDecimal(5000), "Claras", "97-11-13"));
            misVentas.add(new VistaMisVentas(1, new BigDecimal(5000), "Claras", "97-11-13"));
            misVentas.add(new VistaMisVentas(1, new BigDecimal(5000), "Claras", "97-11-13"));
            misVentas.add(new VistaMisVentas(1, new BigDecimal(5000), "Claras", "97-11-13"));
            misVentas.add(new VistaMisVentas(1, new BigDecimal(5000), "Claras", "97-11-13"));
            misVentas.add(new VistaMisVentas(1, new BigDecimal(5000), "Claras", "97-11-13"));
            misVentas.add(new VistaMisVentas(1, new BigDecimal(5000), "Claras", "97-11-13"));

            MisVentasAdapter adaptador = new MisVentasAdapter(MisVentasActivity.this, R.layout.list_item_mis_ventas, misVentas);
            listViewMisVentas.setAdapter(adaptador);
            dialog.cancel();
        }
    }

    private AdapterView.OnItemClickListener listViewMisVentasListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(MisVentasActivity.this, EstadoVentaActivity.class);
            intent.putExtra(usuario.getClassName(), usuario);

            startActivity(intent);
        }
    };

}
