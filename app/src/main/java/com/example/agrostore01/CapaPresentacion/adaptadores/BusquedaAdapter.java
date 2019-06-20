package com.example.agrostore01.CapaPresentacion.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.agrostore01.AgroUtils;
import com.example.agrostore01.CapaEntidades.vistas.VistaBusquedaProducto;
import com.example.agrostore01.R;

import java.math.BigDecimal;
import java.util.List;

public class BusquedaAdapter extends ArrayAdapter<VistaBusquedaProducto> {

    static class Datos {
        public ImageView imageViewItemBuscar;
        public TextView textViewItemBuscarTitulo;
        public TextView textViewItemBuscarPrecio;
        public TextView textViewItemBuscarLocalidad;
    }

    private  Context context;
    private int layoutResourceId;
    private List<VistaBusquedaProducto> lista;

    public BusquedaAdapter(Context context, int resource, List<VistaBusquedaProducto> objects) {
        super(context, resource, objects);

        this.context = context;
        this.layoutResourceId = resource;
        this.lista = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Datos datos;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(layoutResourceId, parent, false);
            datos = new Datos();

            datos.imageViewItemBuscar = convertView.findViewById(R.id.imageViewItemBuscar);
            datos.textViewItemBuscarTitulo = convertView.findViewById(R.id.textViewItemBuscarTitulo);
            datos.textViewItemBuscarPrecio = convertView.findViewById(R.id.textViewItemBuscarPrecio);
            datos.textViewItemBuscarLocalidad = convertView.findViewById(R.id.textViewItemBuscarLocalidad);

            convertView.setTag(datos);

        } else {
            datos = (Datos) convertView.getTag();
        }

        // Llenar campos del list item
        VistaBusquedaProducto vistaBusquedaProducto = lista.get(position);

        String titulo = vistaBusquedaProducto.getProducto();
        String precio = "$" + vistaBusquedaProducto.getPrecio().setScale(2, BigDecimal.ROUND_HALF_UP) + ", " + vistaBusquedaProducto.getHectareas() + " hectareas";
        String localidad = vistaBusquedaProducto.getCiudad() + ", " + vistaBusquedaProducto.getEstado();

        AgroUtils.setImageViewByteArray(datos.imageViewItemBuscar, vistaBusquedaProducto.getFoto());
        datos.textViewItemBuscarTitulo.setText(titulo);
        datos.textViewItemBuscarPrecio.setText(precio);
        datos.textViewItemBuscarLocalidad.setText(localidad);

        //AgroUtils.setImageViewByteArray(datos.imageViewItemBuscar, vistaBusquedaProducto.getFoto());

        // ano-mes-dia

        return convertView;
    }
}
