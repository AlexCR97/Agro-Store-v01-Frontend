package com.example.agrostore01.adaptadores;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.agrostore01.R;

import java.util.ArrayList;
public class TerrenoAdapter extends ArrayAdapter {

    static class Datos {
        public ImageView imageViewItemTerreno;
        public TextView textViewItemNombreTerreno;
        public TextView textViewItemTamano;
        public TextView textViewItemMedidas;

        public TextView textViewItemTipoTerreno;

        public LinearLayout linearLayoutItemTerreno;
    }

    private  Context context;
    private int layoutResourceId;
    private ArrayList lista;

    public TerrenoAdapter(Context context, int resource, Object[] objects) {
        super(context, resource, objects);

        this.context = context;
        this.layoutResourceId = resource;
        this.lista = lista;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Datos data = null;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(layoutResourceId, parent, false);
            data = new Datos();

            data.imageViewItemTerreno = convertView.findViewById(R.id.imageViewItemTerreno);
            data.textViewItemNombreTerreno = convertView.findViewById(R.id.textViewItemNombreTerreno);
            data.textViewItemTamano = convertView.findViewById(R.id.textViewItemTamano);
            data.textViewItemMedidas = convertView.findViewById(R.id.textViewItemMedidas);
            data.linearLayoutItemTerreno = convertView.findViewById(R.id.linearLayoutItemTerreno);
            data.textViewItemTipoTerreno = convertView.findViewById(R.id.textViewItemTipoTerreno);

            convertView.setTag(data);

        } else {
            data = (Datos) convertView.getTag();
        }

        return convertView;
    }
}
