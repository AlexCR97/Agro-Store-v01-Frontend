package com.example.agrostore01.CapaPresentacion.actividades;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;

import com.example.agrostore01.CapaEntidades.DetallesUsuario;
import com.example.agrostore01.CapaEntidades.Usuario;
import com.example.agrostore01.R;

import java.math.BigDecimal;

public class FiltrosActivity extends RecieveBundlesActivity {

    // Tipos de busqueda

    public static final String TIPO_BUSQUEDA = "TipoBusqueda";

    public static final String BUSQUEDA_CATEGORIA = "BusquedaCategoria";
    public static final String BUSQUEDA_CATEGORIA_HORTALIZAS = "Hortalizas";
    public static final String BUSQUEDA_CATEGORIA_SEMILLAS = "Semillas";
    public static final String BUSQUEDA_CATEGORIA_CARNES = "Carnes";
    public static final String BUSQUEDA_CATEGORIA_LACTEOS = "Lacteos";

    public static final String BUSQUEDA_NOMBRE_PRODUCTO = "BusquedaNombreProducto";

    public static final String BUSQUEDA_FILTRO = "BusquedaFiltros";
    public static final String FILTRO_PRECIO_MIN = "FiltroPrecioMin";
    public static final String FILTRO_PRECIO_MAX = "FiltroPrecioMax";
    public static final String FILTRO_PRODUCTO = "FiltroProducto";
    public static final String FILTRO_CATEGORIA = "FiltroCategoria";
    public static final String FILTRO_CATEGORIA_HORTALIZAS = "Hortalizas";
    public static final String FILTRO_CATEGORIA_SEMILLAS = "Semillas";
    public static final String FILTRO_CATEGORIA_CARNES = "Carnes";
    public static final String FILTRO_CATEGORIA_LACTEOS = "Lacteos";
    public static final String FILTRO_TEMPORADA = "FiltroTemporada";
    public static final String FILTRO_TEMPORADA_INVIERNO = "Invierno";
    public static final String FILTRO_TEMPORADA_OTONO = "Otono";
    public static final String FILTRO_TEMPORADA_PRIMAVERA = "Primavera";
    public static final String FILTRO_TEMPORADA_VERANO = "Verano";
    public static final String FILTRO_PAIS = "FiltroPais";
    public static final String FILTRO_ESTADO = "FiltroEstado";
    public static final String FILTRO_ESTRELLAS = "FiltroEstrellas";
    public static final String FILTRO_TIEMPO_COSECHA = "FiltroTiempoCosecha";
    public static final String FILTRO_TIEMPO_COSECHA_MENOS_MES = "-mes";
    public static final String FILTRO_TIEMPO_COSECHA_MENOS_ANIO = "-anio";
    public static final String FILTRO_TIEMPO_COSECHA_MAS_ANIO = "+anio";

    private String filtroCategoria;
    private String filtroTemporada;
    private String filtroTiempoCosecha;

    private EditText etPrecioMin, etPrecioMax;
    private ImageView ivCategoriaLacteo, ivCategoriaCarne, ivCategoriaSemilla, ivCategoriaHortaliza;
    private ImageView ivTemporadaPrimavera, ivTemporadaVerano, ivTemporadaOtono, ivTemporadaInvierno;
    private Spinner sPais, sEstado;
    private ImageView ivTiempoMenosMes, ivTiempoMenosAnio, ivTiempoMasAnio;
    private RatingBar rbEstrellas;
    private Button bAplicarFiltros;

    private Usuario usuario = new Usuario();
    private DetallesUsuario detallesUsuario = new DetallesUsuario();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtros);

        etPrecioMin = findViewById(R.id.etFiltroPrecioMin);
        etPrecioMax = findViewById(R.id.etFiltroPrecioMax);
        ivCategoriaLacteo = findViewById(R.id.ivFiltroCategoriaLacteo);
        ivCategoriaCarne = findViewById(R.id.ivFiltroCategoriaCarne);
        ivCategoriaSemilla = findViewById(R.id.ivFiltroCategoriaSemilla);
        ivCategoriaHortaliza = findViewById(R.id.ivFiltroCategoriaHortaliza);
        ivTemporadaInvierno = findViewById(R.id.ivFiltroTemporadaInvierno);
        ivTemporadaOtono = findViewById(R.id.ivFiltroTemporadaOtono);
        ivTemporadaPrimavera = findViewById(R.id.ivFiltroTemporadaPrimavera);
        ivTemporadaVerano = findViewById(R.id.ivFiltroTemporadaVerano);
        ivTiempoMenosMes = findViewById(R.id.ivFiltroTiempoMenosMes);
        ivTiempoMenosAnio = findViewById(R.id.ivFiltroTiempoMenosAnio);
        ivTiempoMasAnio = findViewById(R.id.ivFiltroTiempoMasAnio);
        rbEstrellas = findViewById(R.id.rbFiltroReputacion);
        bAplicarFiltros = findViewById(R.id.bAplicarFiltros);

        ivCategoriaHortaliza.setOnClickListener(ivCategoriaListener);
        ivCategoriaSemilla.setOnClickListener(ivCategoriaListener);
        ivCategoriaCarne.setOnClickListener(ivCategoriaListener);
        ivCategoriaLacteo.setOnClickListener(ivCategoriaListener);

        ivTemporadaVerano.setOnClickListener(ivTemporadaListener);
        ivTemporadaPrimavera.setOnClickListener(ivTemporadaListener);
        ivTemporadaOtono.setOnClickListener(ivTemporadaListener);
        ivTemporadaInvierno.setOnClickListener(ivTemporadaListener);

        ivTiempoMenosMes.setOnClickListener(ivTiempoCosechaListener);
        ivTiempoMenosAnio.setOnClickListener(ivTiempoCosechaListener);
        ivTiempoMasAnio.setOnClickListener(ivTiempoCosechaListener);

        bAplicarFiltros.setOnClickListener(bAplicarFiltrosListener);

        String[] paises = new String[] {"México"};
        ArrayAdapter<String> adapterPais = new ArrayAdapter<>(this, R.layout.list_item_spinner, paises);
        sPais = findViewById(R.id.sFiltroPais);
        sPais.setAdapter(adapterPais);

        String[] estados = new String[] {"TODOS", "Aguascalientes","Baja California","Baja California Sur","Campeche","Coahuila de Zaragoza","Colima"
                ,"Chiapas","Chihuahua","Distrito Federal","Durango","Guanajuato","Guerrero","Hidalgo","Jalisco","México","Michoacán de Ocampo"
                ,"Morelos","Nayarit","Nuevo León","Oaxaca","Puebla","Querétaro","Quintana Roo","San Luis Potosí","Sinaloa","Sonora","Tabasco"
                ,"Tamaulipas","Tlaxcala","Veracruz de Ignacio de la Llave","Yucatán","Zacatecas"};
        ArrayAdapter<String> adapterEstado = new ArrayAdapter<>(this, R.layout.list_item_spinner, estados);
        sEstado = findViewById(R.id.sFiltroEstado);
        sEstado.setAdapter(adapterEstado);
    }

    @Override
    public void recieveBundles(Context context) {
        usuario = getIntent().getParcelableExtra(usuario.getClassName());
        detallesUsuario = getIntent().getParcelableExtra(detallesUsuario.getClassName());
    }

    private final View.OnClickListener ivCategoriaListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {

                case R.id.ivFiltroCategoriaCarne: {
                    if (filtroCategoria == null)
                        filtroCategoria = FILTRO_CATEGORIA_CARNES;

                    else if (filtroCategoria.equals(FILTRO_CATEGORIA_CARNES))
                        filtroCategoria = null;

                    else
                        filtroCategoria = FILTRO_CATEGORIA_CARNES;

                    break;
                }

                case R.id.ivFiltroCategoriaHortaliza: {
                    if (filtroCategoria == null)
                        filtroCategoria = FILTRO_CATEGORIA_HORTALIZAS;

                    else if (filtroCategoria.equals(FILTRO_CATEGORIA_HORTALIZAS))
                        filtroCategoria = null;

                    else
                        filtroCategoria = FILTRO_CATEGORIA_HORTALIZAS;

                    break;
                }

                case R.id.ivFiltroCategoriaLacteo: {
                    if (filtroCategoria == null)
                        filtroCategoria = FILTRO_CATEGORIA_LACTEOS;

                    else if (filtroCategoria.equals(FILTRO_CATEGORIA_LACTEOS))
                        filtroCategoria = null;

                    else
                        filtroCategoria = FILTRO_CATEGORIA_LACTEOS;

                    break;
                }

                case R.id.ivFiltroCategoriaSemilla: {
                    if (filtroCategoria == null)
                        filtroCategoria = FILTRO_CATEGORIA_SEMILLAS;

                    else if (filtroCategoria.equals(FILTRO_CATEGORIA_SEMILLAS))
                        filtroCategoria = null;

                    else
                        filtroCategoria = FILTRO_CATEGORIA_SEMILLAS;

                    break;
                }
            }
        }
    };

    private final View.OnClickListener ivTemporadaListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {

                case R.id.ivFiltroTemporadaInvierno: {
                    if (filtroTemporada == null)
                        filtroTemporada = FILTRO_TEMPORADA_INVIERNO;

                    else if (filtroTemporada.equals(FILTRO_TEMPORADA_INVIERNO))
                        filtroTemporada = null;

                    else
                        filtroTemporada = FILTRO_TEMPORADA_INVIERNO;

                    break;
                }

                case R.id.ivFiltroTemporadaOtono: {
                    if (filtroTemporada == null)
                        filtroTemporada = FILTRO_TEMPORADA_OTONO;

                    else if (filtroTemporada.equals(FILTRO_TEMPORADA_OTONO))
                        filtroTemporada = null;

                    else
                        filtroTemporada = FILTRO_TEMPORADA_OTONO;

                    break;
                }

                case R.id.ivFiltroTemporadaPrimavera: {
                    if (filtroTemporada == null)
                        filtroTemporada = FILTRO_TEMPORADA_PRIMAVERA;

                    else if (filtroTemporada.equals(FILTRO_TEMPORADA_PRIMAVERA))
                        filtroTemporada = null;

                    else
                        filtroTemporada = FILTRO_TEMPORADA_PRIMAVERA;

                    break;
                }

                case R.id.ivFiltroTemporadaVerano: {
                    if (filtroTemporada == null)
                        filtroTemporada = FILTRO_TEMPORADA_VERANO;

                    else if (filtroTemporada.equals(FILTRO_TEMPORADA_VERANO))
                        filtroTemporada = null;

                    else
                        filtroTemporada = FILTRO_TEMPORADA_VERANO;

                    break;
                }
            }
        }
    };

    private final View.OnClickListener ivTiempoCosechaListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {

                case R.id.ivFiltroTiempoMenosMes: {
                    if (filtroTiempoCosecha == null)
                        filtroTiempoCosecha = FILTRO_TIEMPO_COSECHA_MENOS_MES;

                    else if (filtroTiempoCosecha.equals(FILTRO_TIEMPO_COSECHA_MENOS_MES))
                        filtroTiempoCosecha = null;

                    else
                        filtroTiempoCosecha = FILTRO_TIEMPO_COSECHA_MENOS_MES;

                    break;
                }

                case R.id.ivFiltroTiempoMenosAnio: {
                    if (filtroTiempoCosecha == null)
                        filtroTiempoCosecha = FILTRO_TIEMPO_COSECHA_MENOS_ANIO;

                    else if (filtroTiempoCosecha.equals(FILTRO_TIEMPO_COSECHA_MENOS_ANIO))
                        filtroTiempoCosecha = null;

                    else
                        filtroTiempoCosecha = FILTRO_TIEMPO_COSECHA_MENOS_ANIO;

                    break;
                }

                case R.id.ivFiltroTiempoMasAnio: {
                    if (filtroTiempoCosecha == null)
                        filtroTiempoCosecha = FILTRO_TIEMPO_COSECHA_MAS_ANIO;

                    else if (filtroTiempoCosecha.equals(FILTRO_TIEMPO_COSECHA_MAS_ANIO))
                        filtroTiempoCosecha = null;

                    else
                        filtroTiempoCosecha = FILTRO_TIEMPO_COSECHA_MAS_ANIO;

                    break;
                }
            }
        }
    };

    private final View.OnClickListener bAplicarFiltrosListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String precioMinStr = etPrecioMin.getText().toString();
            String precioMaxStr = etPrecioMax.getText().toString();
            long precioMin = (precioMinStr.isEmpty())? -1 : Long.valueOf(precioMinStr);
            long precioMax = (precioMaxStr.isEmpty())? -1 : Long.valueOf(precioMaxStr);

            String pais = sPais.getSelectedItem().toString();
            String estado = sEstado.getSelectedItem().toString();

            float estrellas = rbEstrellas.getRating();

            System.out.println("Los filtros son:\n" +
                    "Precio min: " + precioMin + "\n" +
                    "Precio max: " + precioMax + "\n" +
                    "Categoria: " + filtroCategoria + "\n" +
                    "Temporada: " + filtroTemporada + "\n" +
                    "Locacion: " + estado + ", " + pais + "\n" +
                    "Tiempo cosecha: " + filtroTiempoCosecha + "\n" +
                    "Estrellas: " + estrellas);

            Intent intent = new Intent(FiltrosActivity.this, BuscarActivity.class);

            intent.putExtra(usuario.getClassName(), usuario);
            intent.putExtra(detallesUsuario.getClassName(), detallesUsuario);
            intent.putExtra(TIPO_BUSQUEDA, BUSQUEDA_FILTRO);
            intent.putExtra(FILTRO_PRECIO_MIN, precioMin);
            intent.putExtra(FILTRO_PRECIO_MAX, precioMax);
            intent.putExtra(FILTRO_CATEGORIA, filtroCategoria);
            intent.putExtra(FILTRO_TEMPORADA, filtroTemporada);
            intent.putExtra(FILTRO_PAIS, pais);
            intent.putExtra(FILTRO_ESTADO, estado);
            intent.putExtra(FILTRO_ESTRELLAS, estrellas);

            startActivity(intent);
        }
    };

}
