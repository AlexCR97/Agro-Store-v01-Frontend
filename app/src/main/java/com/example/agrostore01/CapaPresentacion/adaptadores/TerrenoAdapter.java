package com.example.agrostore01.CapaPresentacion.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.agrostore01.CapaEntidades.Terreno;
import com.example.agrostore01.R;

import java.util.List;

public class TerrenoAdapter extends ArrayAdapter<Terreno> {

    static class Datos {
        ImageView ivTerreno;
        TextView tvNombreTerreno;
        TextView tvTamano;
        TextView tvTipo;
    }

    private Context context;
    private int layoutResourceId;
    private List<Terreno> lista;

    public TerrenoAdapter(Context context, int resource, List<Terreno> objects) {
        super(context, resource, objects);

        this.context = context;
        this.layoutResourceId = resource;
        this.lista = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Datos data;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(layoutResourceId, parent, false);
            data = new Datos();

            data.ivTerreno = convertView.findViewById(R.id.imageViewItemTerreno);
            data.tvNombreTerreno = convertView.findViewById(R.id.textViewItemNombreTerreno);
            data.tvTamano = convertView.findViewById(R.id.textViewItemTamano);
            data.tvTipo = convertView.findViewById(R.id.textViewItemTipoTerreno);

            convertView.setTag(data);

        } else {
            data = (Datos) convertView.getTag();
        }

        Terreno terreno = lista.get(position);

        String nombreTerreno = terreno.getNombre();
        String tamano = terreno.getTamaño() + " " + terreno.getMedida();
        String tipo = terreno.getTipo();

        data.tvNombreTerreno.setText(nombreTerreno);
        data.tvTamano.setText(tamano);
        data.tvTipo.setText(tipo);

        return convertView;
    }
}
