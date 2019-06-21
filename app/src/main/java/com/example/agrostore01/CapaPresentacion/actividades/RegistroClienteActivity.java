package com.example.agrostore01.CapaPresentacion.actividades;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;

import com.example.agrostore01.AgroMensajes;
import com.example.agrostore01.AgroTipoUsuarios;
import com.example.agrostore01.AgroUtils;
import com.example.agrostore01.CapaEntidades.DetallesUsuario;
import com.example.agrostore01.CapaEntidades.Usuario;
import com.example.agrostore01.CapaNegocios.escritores.EscritorUsuario;
import com.example.agrostore01.CapaNegocios.validaciones.ValidacionDetalles;
import com.example.agrostore01.CapaNegocios.validaciones.ValidacionUsuario;
import com.example.agrostore01.R;

import java.util.Calendar;
import java.util.Vector;

public class RegistroClienteActivity extends AppCompatActivity {

    private EditText etUsuario, etNombres, etApellidos, etContrasena, etConfirmarContrasena, etCorreoElectronico, etNumTel, etCorreoRespaldo;
    private EditText etCalle, etColonia, etCiudad, etCodigoPostal;
    private ImageButton ibFecha, ibRegistrar;
    private CheckBox cbTerminos;
    private String sFecha;
    private TextView tvFecha;
    private int dia, mes, anno;
    private Spinner sEstado, sPais,sDia,sMes, sAnio;
    private ProgressDialog dialog;

    private Usuario usuario = new Usuario();
    private DetallesUsuario detallesUsuario = new DetallesUsuario();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_registro_cliente);

        etUsuario = findViewById(R.id.etRegistroClienteUsuario);
        etNombres = findViewById(R.id.etRegistroClienteNombres);
        etApellidos = findViewById(R.id.etRegistroClienteApellido);
        etContrasena = findViewById(R.id.etRegistroClienteContrasena);
        etConfirmarContrasena = findViewById(R.id.etRegistroClienteConfirmarContrasena);
        etCorreoElectronico = findViewById(R.id.etRegistroClienteNombreUsuario);
        etNumTel = findViewById(R.id.etTelefono2);
        etCorreoRespaldo = findViewById(R.id.etRegistroClienteCorreoRespaldo);
        sEstado = findViewById(R.id.sEstado);
        sPais = findViewById(R.id.sPais);

        sDia = findViewById(R.id.sDia);
        sMes = findViewById(R.id.sMes);
        sAnio = findViewById(R.id.sAnio);

        etCalle = findViewById(R.id.etRegistroClienteCalle);
        etColonia = findViewById(R.id.etRegistroClienteColonia);
        etCiudad = findViewById(R.id.etRegistroClienteCiudad);
        etCodigoPostal = findViewById(R.id.etRegistroClienteCodigoPostal);

        ibFecha = findViewById(R.id.ibRegistroClienteFechaNac);
        ibRegistrar = findViewById(R.id.ibRegistroClienteRegistrar);
        tvFecha = findViewById(R.id.tvFecha);
        cbTerminos = findViewById(R.id.cbRegistroClienteTerminos);

        ibFecha.setOnClickListener(ibFechaListener);
        ibRegistrar.setOnClickListener(ibRegistrarListener);

        String[] paises = new String[] {"México"};
        ArrayAdapter<String> adapterPais = new ArrayAdapter<>(this, R.layout.list_item_spinner, paises);
        sPais.setAdapter(adapterPais);

        String[] estados = new String[] {"Aguascalientes","Baja California","Baja California Sur","Campeche","Coahuila de Zaragoza","Colima"
                ,"Chiapas","Chihuahua","Distrito Federal","Durango","Guanajuato","Guerrero","Hidalgo","Jalisco","México","Michoacán de Ocampo"
                ,"Morelos","Nayarit","Nuevo León","Oaxaca","Puebla","Querétaro","Quintana Roo","San Luis Potosí","Sinaloa","Sonora","Tabasco"
                ,"Tamaulipas","Tlaxcala","Veracruz de Ignacio de la Llave","Yucatán","Zacatecas"};
        ArrayAdapter<String> adapterEstado = new ArrayAdapter<>(this, R.layout.list_item_spinner, estados);
        sEstado.setAdapter(adapterEstado);

        Vector<String> dias = new Vector<String>();
        dias.add("Día");
        for (int i=1;i<32;i++){
            dias.add(""+i);
        }

        ArrayAdapter<String> adapterDias = new ArrayAdapter<>(this, R.layout.list_item_spinner, dias);
        sDia.setAdapter(adapterDias);

        String[] meses = new String[] {"Mes","Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        ArrayAdapter<String> adapterMeses = new ArrayAdapter<>(this, R.layout.list_item_spinner, meses);
        sMes.setAdapter(adapterMeses);


        //String[] anios = new String[] {"Año"};
        Vector<String> anios = new Vector<String>();
        anios.add("Año");
        for (int i=1920;i<2002;i++){
            anios.add(""+i);
        }

        ArrayAdapter<String> adapterAnio = new ArrayAdapter<>(this, R.layout.list_item_spinner, anios);
        sAnio.setAdapter(adapterAnio);


    }


    private final View.OnClickListener ibFechaListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final Calendar c = Calendar.getInstance();
            dia = c.get(Calendar.DAY_OF_MONTH);
            mes = c.get(Calendar.MONTH);
            anno = c.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(v.getContext(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    sFecha = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                    tvFecha.setText(sFecha);
                    tvFecha.setVisibility(View.VISIBLE);
                }
            } ,anno, mes, dia);
            datePickerDialog.show();
        }
    };

    private final View.OnClickListener ibRegistrarListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dialog = new ProgressDialog(RegistroClienteActivity.this);

            dialog.setTitle("Registrando");
            dialog.setMessage("Espere un momento");
            dialog.show();
            new VerificarRegistro().execute();
        }
    };

    private class VerificarRegistro extends AsyncTask<Void, Void, Void> {

        private final String ERROR_TERMINOS_Y_CONDICIONES = "Debes aceptar los terminos y condiciones";
        private final String ERROR_CONTRASENAS_DIFERENTES = "Las contrasenas no coinciden";
        private final String ERROR_DATOS_USUARIO = "Verifica que hayas ingresado correctamente tus datos de usuario";
        private final String ERROR_DATOS_DETALLES = "Verifica que hayas ingresado correctamente tu domicilio";
        private final String ERROR_TRANSACCION = "Ocurrio un error al realizar el registro. Compruebe su conexion a Internet e intentelo de nuevo";

        private boolean exito;
        private String mensajeError;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // Datos del usuario
            String idUsuario = "User" + etUsuario.getText().toString();
            byte[] foto = null;
            int idTipo = AgroTipoUsuarios.CLIENTE;
            long idDetalles = 0;
            String nombreUsuario = etUsuario.getText().toString();
            String contrasena = etContrasena.getText().toString();
            String confirmarContrasena = etConfirmarContrasena.getText().toString();
            String correoElectronico = etCorreoElectronico.getText().toString();

            // Detalles del usuario
            String nombres = etNombres.getText().toString();
            String apellidos = etApellidos.getText().toString();
            String calle = etCalle.getText().toString();
            String colonia = etColonia.getText().toString();
            String estado = sEstado.getSelectedItem().toString();
            String pais = sPais.getSelectedItem().toString();
            String codigoPostal = etCodigoPostal.getText().toString();
            int cp = codigoPostal.isEmpty()? 0 : Integer.parseInt(codigoPostal);
            String escrituraOPermiso = "";
            float estrellas = 0;
            String rfc = "";
            String firmaElectronica = "";
            String ciudad = etCiudad.getText().toString();
            String fechaNac = sFecha;
            String telefono = (etNumTel.getText().toString().isEmpty())? null : etNumTel.getText().toString();

            if (!cbTerminos.isChecked()) {
                mensajeError = ERROR_TERMINOS_Y_CONDICIONES;
                exito = false;
                return;
            }

            if (!contrasena.equals(confirmarContrasena)) {
                mensajeError = ERROR_CONTRASENAS_DIFERENTES;
                exito = false;
                return;
            }

            usuario = new Usuario(idUsuario, foto, idTipo, idDetalles, nombreUsuario, contrasena, correoElectronico);
            detallesUsuario = new DetallesUsuario(nombres, apellidos, calle, colonia, estado, pais, cp, escrituraOPermiso, estrellas, rfc, firmaElectronica, ciudad, fechaNac, telefono);

            ValidacionUsuario validacionUsuario = new ValidacionUsuario(usuario);
            boolean validarUsuario = validacionUsuario.validar();

            ValidacionDetalles validacionDetalles = new ValidacionDetalles(detallesUsuario);
            boolean validarDetalles = validacionDetalles.validar();

            if (!validarUsuario) {
                mensajeError = ERROR_DATOS_USUARIO;
                exito = false;
                return;
            }

            if (!validacionDetalles.validarTelefono()) {
                mensajeError = AgroMensajes.ERROR_NUMERO_TELEFONO;
                exito = false;
                return;
            }

            if (!validarDetalles) {
                mensajeError = ERROR_DATOS_DETALLES;
                exito = false;
                return;
            }

            exito = true;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            if (!exito)
                return null;

            EscritorUsuario escritorUsuario = new EscritorUsuario(
                    EscritorUsuario.OPERACION_REGISTRAR_USUARIO,
                    usuario,
                    detallesUsuario
            );

            exito = escritorUsuario.ejecutarCambios();

            if (!exito)
                mensajeError = ERROR_TRANSACCION;

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(RegistroClienteActivity.this);
            dialog.cancel();

            if (!exito) {
                alertDialog.setTitle("Advertencia")
                        .setMessage(mensajeError)
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                alertDialog.show();
                return;
            }

            AgroUtils.mostrarDialogo(RegistroClienteActivity.this, "¡Bienvenido a Agro Store!", "¡Tu cuenta ha sido creada!");

            // Espera por 2 segundos
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {}
            }, 2000);

            Intent intent = new Intent(RegistroClienteActivity.this, BarraActivity.class);
            intent.putExtra(usuario.getClassName(), usuario);
            intent.putExtra(detallesUsuario.getClassName(), detallesUsuario);

            startActivity(intent);
            finish();
        }
    }

    public ImageButton getIbRegistrar() { return ibRegistrar; }
    public ImageButton getIbFecha() { return ibFecha; }
    public String getFecha() { return sFecha; }
    public TextView getTvFecha() { return tvFecha; }
    public EditText getEtNombres() { return etNombres; }
    public EditText getEtContrasena() { return etContrasena; }
    public EditText getEtConfirmarContrasena() { return etConfirmarContrasena; }
    public EditText getEtCorreoElectronico() { return etCorreoElectronico; }
    public EditText getEtCorreoRespaldo() { return etCorreoRespaldo; }
}
