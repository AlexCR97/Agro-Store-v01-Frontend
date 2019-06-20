package com.example.agrostore01.CapaPresentacion.actividades;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agrostore01.CapaEntidades.DetallesUsuario;
import com.example.agrostore01.CapaEntidades.Usuario;
import com.example.agrostore01.R;
import com.example.agrostore01.CapaPresentacion.fragmentos.BuscarFragment;
import com.example.agrostore01.CapaPresentacion.fragmentos.CarritoFragment;
import com.example.agrostore01.CapaPresentacion.fragmentos.DashboardFragment;
import com.example.agrostore01.CapaPresentacion.fragmentos.NotificacionesFragment;

public class BarraActivity extends RecieveBundlesActivity implements NavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView navegador;

    private DashboardFragment dashboardFragment = new DashboardFragment();
    private BuscarFragment buscarFragment = new BuscarFragment();
    private CarritoFragment carritoFragment = new CarritoFragment();
    private NotificacionesFragment notificacionesFragment = new NotificacionesFragment();

    private Usuario usuario = new Usuario();
    private DetallesUsuario detallesUsuario = new DetallesUsuario();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barra);

        recieveBundles(this);

        // DRAWER //////////////////////////////////////////////////////////////////////////////////
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        );
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Set header text
        String nombreApellido = detallesUsuario.getNombres() + " " + detallesUsuario.getApellidos();

        View headerView = navigationView.getHeaderView(0);
        TextView tvHeader = headerView.findViewById(R.id.tvBarraNombreUsuario);
        tvHeader.setText(nombreApellido);

        // NAVIGATION //////////////////////////////////////////////////////////////////////////////

        navegador = findViewById(R.id.navigation);
        navegador.setOnNavigationItemSelectedListener(navegadorListener);

        // Bundles de los fragmentos
        Bundle bundleDashboard = new Bundle();
        bundleDashboard.putParcelable(usuario.getClassName(), usuario);
        dashboardFragment.setArguments(bundleDashboard);

        Bundle bundleBuscar = new Bundle();
        bundleBuscar.putParcelable(usuario.getClassName(), usuario);
        bundleBuscar.putParcelable(detallesUsuario.getClassName(), detallesUsuario);
        buscarFragment.setArguments(bundleBuscar);

        Bundle bundleCarrito = new Bundle();
        bundleCarrito.putParcelable(usuario.getClassName(), usuario);
        bundleCarrito.putParcelable(detallesUsuario.getClassName(), detallesUsuario);
        carritoFragment.setArguments(bundleCarrito);

        Bundle bundleNotificaciones = new Bundle();
        bundleNotificaciones.putParcelable(usuario.getClassName(), usuario);
        notificacionesFragment.setArguments(bundleNotificaciones);

        // Fragmento for defecto
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, dashboardFragment).commit();
    }

    @Override
    public void recieveBundles(Context context) {
        usuario = getIntent().getParcelableExtra(usuario.getClassName());
        detallesUsuario = getIntent().getParcelableExtra(detallesUsuario.getClassName());
        Toast.makeText(context, "¡Has iniciado sesion! :D\n\n" + usuario +"\n\n" + detallesUsuario, Toast.LENGTH_LONG).show();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navegadorListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment fragmento = null;

            switch (menuItem.getItemId()) {
                case R.id.navigation_home:
                    fragmento = dashboardFragment;
                    break;
                case R.id.navigation_buscar:
                    fragmento = buscarFragment;
                    break;
                case R.id.navigation_carrito:
                    fragmento = carritoFragment;
                    break;
                case R.id.navigation_notificaciones:
                    fragmento = notificacionesFragment;
                    break;
            }

            if (fragmento == null)
                return false;

            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, fragmento).commit();

            return true;
        }
    };

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.barra, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Intent intent;

        if (id == R.id.perfil) {
            intent = new Intent(BarraActivity.this, PerfilUsuarioActivity.class);
            intent.putExtra(usuario.getClassName(), usuario);
            intent.putExtra(detallesUsuario.getClassName(), detallesUsuario);
            startActivity(intent);
        }

        else if (id == R.id.miterreno){
            intent = new Intent(BarraActivity.this, MiTerrenoActivity.class);
            intent.putExtra(usuario.getClassName(), usuario);
            startActivity(intent);
        }

        else if (id == R.id.compras) {
            intent = new Intent(BarraActivity.this, MisComprasActivity.class);
            intent.putExtra(usuario.getClassName(), usuario);
            startActivity(intent);
        }

        else if (id == R.id.ventas) {
            intent = new Intent(BarraActivity.this, MisVentasActivity.class);
            intent.putExtra(usuario.getClassName(), usuario);
            startActivity(intent);
        }

        else if (id == R.id.vender) {
            intent = new Intent(BarraActivity.this, Vender2Activity.class);
            intent.putExtra(usuario.getClassName(), usuario);
            intent.putExtra(detallesUsuario.getClassName(), detallesUsuario);
            startActivity(intent);
        }

        else if (id == R.id.faq) {

        }

        else if (id == R.id.cerrarsesion) {
            intent = new Intent(BarraActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();

        } else if (id == R.id.ventana) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
