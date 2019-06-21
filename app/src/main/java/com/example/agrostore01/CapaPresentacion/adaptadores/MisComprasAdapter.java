package com.example.agrostore01.CapaPresentacion.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.agrostore01.CapaEntidades.vistas.VistaMisCompras;
import com.example.agrostore01.R;

import java.util.List;

public class MisComprasAdapter extends ArrayAdapter<VistaMisCompras> {

    static class Datos {
        ImageView ivMisCompras;
        TextView tvNumeroCompra;
        TextView tvNumeroLote;
        TextView tvEstado;
        TextView tvFecha;
    }

    private  Context context;
    private int layoutResourceId;
    private List<VistaMisCompras> items;

    public MisComprasAdapter(Context context, int resource, List<VistaMisCompras> items) {
        super(context, resource, items);

        this.context = context;
        this.layoutResourceId = resource;
        this.items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Datos data = null;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(layoutResourceId, parent, false);
            data = new Datos();

            data.ivMisCompras = convertView.findViewById(R.id.ivMisCompras);
            data.tvNumeroCompra = convertView.findViewById(R.id.tvMisComprasNumeroPedido);
            data.tvNumeroLote = convertView.findViewById(R.id.tvMisComprasNumeroLote);
            data.tvEstado = convertView.findViewById(R.id.tvMisComprasEstado);
            data.tvFecha = convertView.findViewById(R.id.tvMisComprasFecha);

            convertView.setTag(data);

        } else {
            data = (Datos) convertView.getTag();
        }

        VistaMisCompras misCompra = items.get(position);

        String noCompra = "#" + misCompra.getNumeroDeCompra();
        String noLote = "#" + misCompra.getNumeroDeLote();
        String estado = (misCompra.isEstado())? "Completado" : "No completado";
        String fecha = misCompra.getTiempo();

        data.tvNumeroCompra.setText(noCompra);
        data.tvNumeroLote.setText(noLote);
        data.tvEstado.setText(estado);
        data.tvFecha.setText(fecha);

        return convertView;
    }
}
