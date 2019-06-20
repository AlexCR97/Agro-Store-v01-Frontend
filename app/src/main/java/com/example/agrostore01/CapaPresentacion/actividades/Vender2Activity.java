package com.example.agrostore01.CapaPresentacion.actividades;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.agrostore01.CapaEntidades.DetallesUsuario;
import com.example.agrostore01.CapaEntidades.ProductoTerreno;
import com.example.agrostore01.CapaEntidades.Usuario;
import com.example.agrostore01.CapaEntidades.vistas.VistaPublicacion;
import com.example.agrostore01.R;

public class Vender2Activity extends RecieveBundlesActivity {

    private ImageView imageViewPrimavera;
    private ImageView imageViewVerano;
    private ImageView imageViewOtonio;
    private ImageView imageViewInvierno;

    private Usuario usuario = new Usuario();
    private DetallesUsuario detallesUsuario = new DetallesUsuario();
    private VistaPublicacion vistaPublicacion = new VistaPublicacion();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vender2);

        recieveBundles(this);

        imageViewPrimavera = findViewById(R.id.imageViewTemporadaPrimavera);
        imageViewVerano = findViewById(R.id.imageViewTemporadaVerano);
        imageViewOtonio = findViewById(R.id.imageViewTemporadaOtonio);
        imageViewInvierno = findViewById(R.id.imageViewTemporadaInvierno);

        imageViewPrimavera.setOnClickListener(imageViewTemporadaOnClick);
        imageViewVerano.setOnClickListener(imageViewTemporadaOnClick);
        imageViewOtonio.setOnClickListener(imageViewTemporadaOnClick);
        imageViewInvierno.setOnClickListener(imageViewTemporadaOnClick);
    }

    @Override
    public void recieveBundles(Context context) {
        usuario = getIntent().getParcelableExtra(usuario.getClassName());
        detallesUsuario = getIntent().getParcelableExtra(detallesUsuario.getClassName());
    }

    private final View.OnClickListener imageViewTemporadaOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String temporada = null;

            switch (v.getId()) {
                case R.id.imageViewTemporadaInvierno: temporada = "Invierno"; break;
                case R.id.imageViewTemporadaOtonio: temporada = "Otoño"; break;
                case R.id.imageViewTemporadaPrimavera: temporada = "Primavera"; break;
                case R.id.imageViewTemporadaVerano: temporada = "Verano"; break;
            }

            if (temporada == null)
                return;

            vistaPublicacion.setTemporada(temporada);

            Intent intent = new Intent(Vender2Activity.this, Vender3Activity.class);
            intent.putExtra(usuario.getClassName(), usuario);
            intent.putExtra(detallesUsuario.getClassName(), detallesUsuario);
            intent.putExtra(vistaPublicacion.getClassName(), vistaPublicacion);
            startActivity(intent);
        }
    };
}
