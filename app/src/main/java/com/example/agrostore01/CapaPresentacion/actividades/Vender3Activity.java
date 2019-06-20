package com.example.agrostore01.CapaPresentacion.actividades;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.agrostore01.AgroCodigos;
import com.example.agrostore01.AgroUtils;
import com.example.agrostore01.CapaEntidades.DetallesUsuario;
import com.example.agrostore01.CapaEntidades.Usuario;
import com.example.agrostore01.CapaEntidades.vistas.VistaPublicacion;
import com.example.agrostore01.R;

public class Vender3Activity extends RecieveBundlesActivity {

    private ImageView ivFoto;
    private ImageView ivEliminar;
    private TextView tvCamara;
    private TextView tvGaleria;
    private Button buttonSiguiente;

    private Usuario usuario = new Usuario();
    private DetallesUsuario detallesUsuario = new DetallesUsuario();
    private VistaPublicacion vistaPublicacion = new VistaPublicacion();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vender3);

        recieveBundles(this);

        ivFoto = findViewById(R.id.ivFotoProducto);
        ivEliminar = findViewById(R.id.ivFotoProductoEliminar);
        tvCamara = findViewById(R.id.tvFotoProducto);
        tvGaleria = findViewById(R.id.tvFotoProductoGaleria);
        buttonSiguiente = findViewById(R.id.buttonSiguienteVender3);

        tvCamara.setOnClickListener(tvCamaraOnClick);
        tvGaleria.setOnClickListener(tvGaleriaOnClick);
        ivEliminar.setOnClickListener(ivEliminarOnClick);
        buttonSiguiente.setOnClickListener(buttonSiguienteOnClick);
    }

    @Override
    public void recieveBundles(Context context) {
        usuario = getIntent().getParcelableExtra(usuario.getClassName());
        detallesUsuario = getIntent().getParcelableExtra(detallesUsuario.getClassName());
        vistaPublicacion = getIntent().getParcelableExtra(vistaPublicacion.getClassName());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != RESULT_OK)
            return;

        if (data == null)
            return;

        switch (requestCode) {

            case AgroCodigos.TOMAR_FOTOGRAFIA: {
                Bundle bundle = data.getExtras();
                if (bundle == null)
                    return;

                Bitmap bitmap = (Bitmap) bundle.get("data");
                if (bitmap == null)
                    return;

                ivFoto.setImageBitmap(bitmap);
                break;
            }

            case AgroCodigos.ELEGIR_DESDE_GALERIA: {
                Uri imagen = data.getData();
                ivFoto.setImageURI(imagen);
                break;
            }
        }
    }

    private View.OnClickListener tvCamaraOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, AgroCodigos.TOMAR_FOTOGRAFIA);
        }
    };

    private View.OnClickListener tvGaleriaOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            // Version de Android es menor que Marshmellow
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                // Permiso no concedido, solicitarlo
                if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                    String[] permisos = { Manifest.permission.READ_EXTERNAL_STORAGE };
                    requestPermissions(permisos, AgroCodigos.CONCEDER_PERMISO);
                }

                // Permiso concedido
                else {
                    elegirImagenDesdeGaleria();
                }
            }

            // Version de Android es mayor que Marshmellow
            else {
                elegirImagenDesdeGaleria();
            }
        }
    };

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == AgroCodigos.CONCEDER_PERMISO) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                elegirImagenDesdeGaleria();
            }
        }
    }

    private void elegirImagenDesdeGaleria() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, AgroCodigos.ELEGIR_DESDE_GALERIA);
    }

    private View.OnClickListener ivEliminarOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ivFoto.setImageResource(R.drawable.ic_insert_photo_black_24dp);
        }
    };

    private View.OnClickListener buttonSiguienteOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Bitmap bitmap = ((BitmapDrawable) ivFoto.getDrawable()).getBitmap();
            byte[] foto = AgroUtils.bitmapToByteArray(bitmap);

            vistaPublicacion.setFoto(foto);

            Intent intent = new Intent(Vender3Activity.this, Vender4Activity.class);
            intent.putExtra(usuario.getClassName(), usuario);
            intent.putExtra(detallesUsuario.getClassName(), detallesUsuario);
            intent.putExtra(vistaPublicacion.getClassName(), vistaPublicacion);
            startActivity(intent);
        }
    };

}
