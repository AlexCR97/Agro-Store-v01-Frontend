package com.example.agrostore01.CapaPresentacion.fragmentos;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agrostore01.AgroMensajes;
import com.example.agrostore01.AgroUtils;
import com.example.agrostore01.CapaEntidades.DetallesUsuario;
import com.example.agrostore01.CapaEntidades.Usuario;
import com.example.agrostore01.CapaEntidades.vistas.VistaCarrito;
import com.example.agrostore01.CapaEntidades.vistas.VistaCompra;
import com.example.agrostore01.CapaNegocios.lectores.vistas.LectorVistaCarrito;
import com.example.agrostore01.CapaPresentacion.actividades.RecieveBundlesFragment;
import com.example.agrostore01.R;
import com.example.agrostore01.CapaPresentacion.actividades.CompraActivity;
import com.example.agrostore01.CapaPresentacion.adaptadores.CarritoAdapter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CarritoFragment extends RecieveBundlesFragment {

    private ListView listViewCarrito;
    private TextView tvPrecioTotal;
    private Button buttonComprarCarrito;

    private Usuario usuario = new Usuario();
    private DetallesUsuario detallesUsuario = new DetallesUsuario();
    private List<VistaCarrito> vistaCarritoList;

    ProgressDialog dialog;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_carrito, container, false);

        recieveBundles(vista.getContext());

        listViewCarrito = vista.findViewById(R.id.listViewCarrito);
        tvPrecioTotal = vista.findViewById(R.id.tvCarritoTotal);
        buttonComprarCarrito = vista.findViewById(R.id.buttonCarritoComprar);

        listViewCarrito.setOnItemLongClickListener(listViewCarritoListener);
        buttonComprarCarrito.setOnClickListener(buttonComprarCarritoListener);

        dialog = new ProgressDialog(vista.getContext());

        new ObtenerMisProductosEnCarrito().execute();

        return vista;
    }

    @Override
    public void onResume() {
        super.onResume();

        new ObtenerMisProductosEnCarrito().execute();
    }

    @Override
    public void recieveBundles(Context context) {
        usuario = getArguments().getParcelable(usuario.getClassName());
        detallesUsuario = getArguments().getParcelable(detallesUsuario.getClassName());
    }

    private class ObtenerMisProductosEnCarrito extends AsyncTask<Void, Void, Void> {

        private boolean exito;
        private String mensaje;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            dialog.setTitle("Obteniendo tu carrito");
            dialog.setMessage("Espere un momento");
            dialog.show();

            System.out.println("\n\n\n\nJust initiated async task " + this.getClass().getSimpleName() + "\n\n\n\n");
        }

        @Override
        protected Void doInBackground(Void... voids) {

            LectorVistaCarrito lectorVistaCarrito = new LectorVistaCarrito();
            vistaCarritoList = lectorVistaCarrito.getMisProductosEnCarrito(usuario.getIdUsuario());

            if (vistaCarritoList == null) {
                mensaje = AgroMensajes.ERROR_INTERNET;
                exito = false;
            }

            exito = true;

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            if (!exito) {
                Toast.makeText(CarritoFragment.this.getContext(), mensaje, Toast.LENGTH_LONG).show();
                return;
            }

            CarritoAdapter adapter = new CarritoAdapter(CarritoFragment.this.getContext(), R.layout.list_item_carrito, vistaCarritoList);
            listViewCarrito.setAdapter(adapter);

            System.out.println("\n\n\n\nJust ended async task " + this.getClass().getSimpleName() + "\n\n\n\n");

            dialog.cancel();
            setPrecioTotal();
        }
    }

    private void setPrecioTotal() {
        if (vistaCarritoList == null) {
            tvPrecioTotal.setText("$0");
            return;
        }

        if (vistaCarritoList.isEmpty()) {
            tvPrecioTotal.setText("$0");
            return;
        }

        BigDecimal precioTotal = new BigDecimal(0);

        for (VistaCarrito vistaCarrito : vistaCarritoList)
            precioTotal = precioTotal
                    .add( new BigDecimal(vistaCarrito.getPrecio()))
                    .multiply(new BigDecimal(vistaCarrito.getCantidad()));

        String precio = "$" + precioTotal.setScale(2, BigDecimal.ROUND_HALF_EVEN);

        tvPrecioTotal.setText(precio);
    }

    private final AdapterView.OnItemLongClickListener listViewCarritoListener = new AdapterView.OnItemLongClickListener() {

        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which) {
                        case DialogInterface.BUTTON_POSITIVE: {
                            break;
                        }

                        case DialogInterface.BUTTON_NEGATIVE:
                            dialog.cancel();
                            break;
                    }
                }
            };

            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
            builder.setMessage("Estas seguro de que quieres quitar este producto de tu vistaCarritoList?")
                    .setPositiveButton("Si", dialogClickListener)
                    .setNegativeButton("No", dialogClickListener)
                    .show();

            return false;
        }
    };

    private final View.OnClickListener buttonComprarCarritoListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (vistaCarritoList == null) {
                AgroUtils.mostrarDialogo(v.getContext(), "¡Ups!", AgroMensajes.ERROR_CARRITO_VACIO);
                return;
            }

            if (vistaCarritoList.isEmpty()) {
                AgroUtils.mostrarDialogo(v.getContext(), "¡Ups!", AgroMensajes.ERROR_CARRITO_VACIO);
                return;
            }

            ArrayList<VistaCompra> vistaCompraList = new ArrayList<>();

            for (VistaCarrito vistaCarrito : vistaCarritoList) {
                vistaCompraList.add(new VistaCompra(
                        vistaCarrito.getIdNumProducto(),
                        usuario.getIdUsuario(),
                        vistaCarrito.getCantidad()
                ));
            }

            Intent intent = new Intent(v.getContext(), CompraActivity.class);
            intent.putExtra(usuario.getClassName(), usuario);
            intent.putExtra(detallesUsuario.getClassName(), detallesUsuario);
            intent.putParcelableArrayListExtra(vistaCompraList.getClass().getSimpleName(), vistaCompraList);
            intent.putParcelableArrayListExtra(vistaCarritoList.getClass().getSimpleName() + "1", (ArrayList<VistaCarrito>) vistaCarritoList);

            startActivity(intent);
        }
    };

}
