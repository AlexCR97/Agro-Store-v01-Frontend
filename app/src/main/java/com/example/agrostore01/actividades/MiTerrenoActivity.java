package com.example.agrostore01.actividades;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ImageButton;


import com.example.agrostore01.R;
import com.example.agrostore01.adaptadores.TerrenoAdapter;

public class MiTerrenoActivity extends AppCompatActivity {

    private ImageButton ibAgregarTerreno;
    private ListView listViewTerrenos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mi_terreno);

        ibAgregarTerreno = findViewById(R.id.ibAgregar);
        listViewTerrenos = findViewById(R.id.listViewTerreno);

        Object[] items = new Object[] {1,2,3,4,5,6,7,8,9,10};
        TerrenoAdapter adaptador = new TerrenoAdapter(this, R.layout.list_item_terreno, items);
        listViewTerrenos.setAdapter(adaptador);

        ibAgregarTerreno.setOnClickListener(ibAgregarTerrenoListener);
    }

    private final View.OnClickListener ibAgregarTerrenoListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent= new Intent(MiTerrenoActivity.this, AgregarTerrenoActivity.class);
            startActivity(intent);
        }
    };

    public ImageButton getIbAgregarTerreno() {
        return ibAgregarTerreno;
    }

    public ListView getListViewTerrenos() {
        return listViewTerrenos;
    }
}
