package com.example.agrostore01;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.agrostore01.CapaEntidades.DetallesUsuario;
import com.example.agrostore01.CapaEntidades.Usuario;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class AgroUtils {

    public static Usuario getTestUser() {
        return new Usuario(
                "TestUserId",
                null,
                1,
                1,
                "TestUserName",
                "TestPassword",
                "Test@Mail.com"
        );
    }

    public static DetallesUsuario getTestDetalles() {
        return new DetallesUsuario(
                1,
                "Pablo Alejandro",
                "Castillo Ramirez",
                "Tulipanes",
                "Union Burocratica #1",
                "Tamaulipas",
                "Mexico",
                89810,
                null,
                5,
                null,
                null,
                "El Mante",
                "97-11-13",
                "8311146563"
        );
    }

    public static byte[] bitmapToByteArray(Bitmap bmp) {
        if (bmp == null)
            return null;

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }

    public static Bitmap byteArrayToBitmap(byte[] image) {
        System.out.println("Converting byte array to bitmap");

        if (image == null) {
            System.out.println("byte array was null. Returning null");
            return null;
        }

        Bitmap bmp = BitmapFactory.decodeByteArray(image, 0, image.length);

        if (bmp == null) {
            System.out.println("Conversion failed. Returning null");
            return null;
        }

        System.out.println("Conversion successful! Returning bitmap");

        return bmp;
    }

    public static void setImageViewByteArray(ImageView imageView, byte[] image) {
        System.out.println("Setting image view byte array");

        if (image == null) {
            System.out.println("byte array was null. Returning null");
            return;
        }

        Bitmap bmp = byteArrayToBitmap(image);

        if (bmp == null) {
            System.out.println("Got a null bitmap. Returning");
            return;
        }

        System.out.println("About to set image...");
        imageView.setImageBitmap(Bitmap.createScaledBitmap(bmp, imageView.getWidth(), imageView.getHeight(), false));
        System.out.println("Just set image!");
    }

    public static int generarNumeroAleatorio(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    public static String generarIdAleatorio(int tamano) {
        char[] numeros = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        char[] letras = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',};
        StringBuilder id = new StringBuilder();

        while (tamano-- > 0) {
            int operacion = ThreadLocalRandom.current().nextInt(0, 2);

            switch (operacion) {
                case 0: {
                    int indice = ThreadLocalRandom.current().nextInt(0, numeros.length);
                    char caracter = numeros[indice];
                    id.append(caracter);
                }

                case 1: {
                    int indice = ThreadLocalRandom.current().nextInt(0, letras.length);
                    char caracter = letras[indice];
                    id.append(caracter);
                }
            }
        }
        return id.toString();
    }

    @SuppressLint("ClickableViewAccessibility")
    public static void setListViewScrollInsideScrollView(ListView listView) {
        listView.setOnTouchListener(new View.OnTouchListener() {
            // Setting on Touch Listener for handling the touch inside ScrollView
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Disallow the touch request for parent scroll on touch of child view
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null)
            return;

        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        int totalHeight = 0;
        View view = null;

        for (int i = 0; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, listView);
            if (i == 0)
                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, ViewGroup.LayoutParams.WRAP_CONTENT));

            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

    public static void setListViewShowableAmountOfItems(ListView listView, int showableAmountOfItems) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null)
            return;

        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        int totalHeight = 0;
        View view = null;

        for (int i = 0; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, listView);
            if (i == 0)
                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, ViewGroup.LayoutParams.WRAP_CONTENT));

            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = (totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1))) * showableAmountOfItems;
        listView.setLayoutParams(params);
        listView.requestLayout();
    }

    public static void mostrarDialogo(Context context, String titulo, String mensaje) {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);

        alertDialog.setTitle(titulo)
                .setMessage(mensaje)
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    public static boolean verificarSiUsuarioEsProductor(Context context, Usuario usuario) {
        if (usuario.getIdTipo() != AgroTipoUsuarios.PRODUCTOR) {
            mostrarDialogo(context,"Función no disponible","Esta función solo está disponible para productores");
            return false;
        }
        return true;
    }

    public static List<Integer> getDifferentIds(List<Integer> ids) {
        Set<Integer> found = new HashSet<>(ids);
        return new ArrayList<>(found);
    }

    public static void setViewMargin(View view, int left, int top, int right, int bottom) {
        if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            p.setMargins(left, top, right, bottom);
            view.requestLayout();
        }
    }

}
