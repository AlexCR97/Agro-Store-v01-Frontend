package com.example.agrostore01.CapaPresentacion.actividades;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;


import com.example.agrostore01.CapaEntidades.Terreno;
import com.example.agrostore01.CapaEntidades.Usuario;
import com.example.agrostore01.CapaNegocios.escritores.Escritor;
import com.example.agrostore01.CapaNegocios.escritores.EscritorTerreno;
import com.example.agrostore01.CapaNegocios.validaciones.ValidacionTerreno;
import com.example.agrostore01.R;

public class AgregarTerrenoActivity extends RecieveBundlesActivity {

    private EditText etNombre, etTamanoAlto, etTamanoAncho, etMedida;
    private Spinner sTipoTerreno;
    private ImageButton ibAceptar;
    private ProgressDialog dialog;

    private Usuario usuario = new Usuario();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_terreno);

        recieveBundles(this);

        etNombre = findViewById(R.id.tvNombre);
        etTamanoAlto = findViewById(R.id.etTerrenoTamanoAlto);
        etTamanoAncho = findViewById(R.id.etTerrenoTamanoAncho);
        etMedida = findViewById(R.id.etTerrenoMedida);
        sTipoTerreno = findViewById(R.id.sTipoTerreno);
        ibAceptar = findViewById(R.id.ibAceptar);

        String[] datos = new String[] {"Suelo arenoso", "Suelo calizo", "Suelo limoso", "Suelo humífero",
                "Suelo arcilloso", "Suelo pedregoso", "Suelo de turba", "Suelo salino"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.list_item_spinner, datos);
        sTipoTerreno.setAdapter(adapter);

        ibAceptar.setOnClickListener(ibAceptarListener);

    }

    @Override
    public void recieveBundles(Context context) {
        usuario = getIntent().getParcelableExtra(usuario.getClassName());
    }

    private final View.OnClickListener ibAceptarListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            dialog = new ProgressDialog(AgregarTerrenoActivity.this);

            dialog.setTitle("Agregando");
            dialog.setMessage("Espere un momento");
            dialog.show();

            new AgregarTerreno().execute();
        }
    };

    private class AgregarTerreno extends AsyncTask<Void, Void, Void> {

        private final String ERROR_VALIDACION = "Verifica que hayas ingresado correctamente los datos de tu terreno";
        private final String ERROR_INTERNET = "Verifica tu conexion a Internet e intentalo de nuevo";
        private String mensajeError;

        private Terreno terrenoTemp;
        private boolean exito;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            String nombreTerreno = etNombre.getText().toString();
            String tamanoAncho = etTamanoAncho.getText().toString();
            String tamanoAlto = etTamanoAlto.getText().toString();
            String medida = etMedida.getText().toString();
            String tipoTerreno = (String) sTipoTerreno.getSelectedItem();

            terrenoTemp = new Terreno(
                    0,
                    (tamanoAlto.isEmpty() || tamanoAncho.isEmpty()) ?
                            -1 :
                            Integer.parseInt(tamanoAlto) * Integer.parseInt(tamanoAncho),
                    medida,
                    tipoTerreno
            );
        }

        @Override
        protected Void doInBackground(Void... voids) {

            ValidacionTerreno validacionTerreno = new ValidacionTerreno(terrenoTemp);
            if (!validacionTerreno.validar()) {
                mensajeError = ERROR_VALIDACION;
                exito = false;
                return null;
            }

            EscritorTerreno escritorTerreno = new EscritorTerreno(
                    EscritorTerreno.OPERACION_AGREGAR_TERRENO,
                    terrenoTemp,
                    usuario.getIdUsuario()
            );

            exito = escritorTerreno.ejecutarCambios();

            if (!exito) {
                mensajeError = ERROR_INTERNET;
                return null;
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(AgregarTerrenoActivity.this);

            if (!exito) {
                dialog.cancel();
                alertDialog.setTitle("Advertencia")
                        .setMessage(mensajeError)
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        });
                alertDialog.show();
                //Toast.makeText(AgregarTerrenoActivity.this, mensajeError, Toast.LENGTH_LONG).show();
                return;
            }
            dialog.cancel();

            alertDialog.setTitle("Advertencia")
                    .setMessage("Tu terreno ha sido agregado")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });
            alertDialog.show();

            //Toast.makeText(AgregarTerrenoActivity.this, "Tu terreno ha sido agregado", Toast.LENGTH_LONG).show();

            Intent intent = new Intent(AgregarTerrenoActivity.this, MiTerrenoActivity.class);
            intent.putExtra(usuario.getClassName(), usuario);

            startActivity(intent);
            finish();
        }
    }

}
