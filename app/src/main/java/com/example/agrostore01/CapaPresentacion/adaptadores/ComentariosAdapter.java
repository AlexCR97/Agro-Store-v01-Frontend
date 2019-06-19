package com.example.agrostore01.CapaPresentacion.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.agrostore01.CapaEntidades.vistas.VistaComentarios;
import com.example.agrostore01.R;

import java.util.List;

public class ComentariosAdapter extends ArrayAdapter<VistaComentarios> {

    public static class Datos {
        TextView tvNombreApellido;
        TextView tvFecha;
        TextView tvComentario;
    }

    private Context context;
    private int layoutResourceId;
    private List<VistaComentarios> comentarios;

    public ComentariosAdapter(Context context, int resource, List<VistaComentarios> objects) {
        super(context, resource, objects);

        this.context = context;
        this.layoutResourceId = resource;
        this.comentarios = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Datos data;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(layoutResourceId, parent, false);
            data = new Datos();

            data.tvComentario = convertView.findViewById(R.id.tvComentarioComentario);
            data.tvFecha = convertView.findViewById(R.id.tvComentarioFecha);
            data.tvNombreApellido = convertView.findViewById(R.id.tvComentarioNombreApellido);

            convertView.setTag(data);

        } else {
            data = (Datos) convertView.getTag();
        }

        VistaComentarios vistaComentario = comentarios.get(position);

        String nombreApellido = vistaComentario.getNombreUsuario() + " " + vistaComentario.getApellidoUsuario();
        String comentario = vistaComentario.getComentario();
        String fecha = vistaComentario.getFecha().toString();

        data.tvNombreApellido.setText(nombreApellido);
        data.tvComentario.setText(comentario);
        data.tvFecha.setText(fecha);

        return convertView;
    }
}
