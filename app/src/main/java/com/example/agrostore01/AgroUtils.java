package com.example.agrostore01;

import android.annotation.SuppressLint;
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
                "97-11-13"
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
        if (image == null)
            return null;

        return BitmapFactory.decodeByteArray(image, 0, image.length);
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

    public static void setImageViewByteArray(ImageView imageView, byte[] image) {
        if (image == null)
            return;

        Bitmap bmp = byteArrayToBitmap(image);
        if (bmp == null)
            return;

        imageView.setImageBitmap(Bitmap.createScaledBitmap(bmp, imageView.getWidth(), imageView.getHeight(), false));
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

}
