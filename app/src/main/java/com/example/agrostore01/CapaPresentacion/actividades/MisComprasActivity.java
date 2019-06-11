package com.example.agrostore01.CapaPresentacion.actividades;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.agrostore01.CapaEntidades.Usuario;
import com.example.agrostore01.CapaEntidades.vistas.VistaMisCompras;
import com.example.agrostore01.CapaNegocios.lectores.LectorCompraUsuario;
import com.example.agrostore01.R;
import com.example.agrostore01.CapaPresentacion.adaptadores.MisComprasAdapter;

import java.util.List;

public class MisComprasActivity extends RecieveBundlesActivity {

    private ListView listViewMisCompras;

    private Usuario usuario = new Usuario();
    private List<VistaMisCompras> misCompras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_compras);

        recieveBundles(this);
/*
        Object[] items = new Object[] {1, 2, 3, 4, 5, 6, 7, 8, 9};
        MisComprasAdapter adaptador = new MisComprasAdapter(this, R.layout.list_item_mis_compras, items);
        listViewMisCompras = findViewById(R.id.listViewMisCompras);
        listViewMisCompras.setAdapter(adaptador);
        listViewMisCompras.setOnItemClickListener(listViewMisComprasListener);*/
    }
    private class ObtenerMisCompras extends AsyncTask<Void,Void,Void> {
        private LectorCompraUsuario lectorCompraUsuario = new LectorCompraUsuario();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            misCompras = lectorCompraUsuario.getMisCompras(usuario.getIdUsuario());
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }


    }

    private AdapterView.OnItemClickListener listViewMisComprasListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(MisComprasActivity.this, EstadoCompraActivity.class);
            intent.putExtra(usuario.getClassName(), usuario);

            startActivity(intent);
        }
    };

    @Override
    public void recieveBundles(Context context) {
        usuario = getIntent().getParcelableExtra(usuario.getClassName());
    }

}
