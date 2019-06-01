package com.example.agrostore01.actividades;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.agrostore01.R;
import android.widget.TextView;
import android.widget.ProgressBar;


public class ReputacionActivity extends AppCompatActivity {

    private TextView tvTotalOpe, tvConcretadas, tvCanceladas, tvPositivas, tvNeutrales, tvNegativas;
    private ProgressBar pbReputacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reputacion);

        tvTotalOpe=(TextView)findViewById(R.id.tvTotalOperaciones);
        tvConcretadas=(TextView)findViewById(R.id.tvConcretadas);
        tvCanceladas=(TextView)findViewById(R.id.tvCanceladas);
        tvPositivas=(TextView)findViewById(R.id.tvPositivas);
        tvNeutrales=(TextView)findViewById(R.id.tvNeutrales);
        tvNegativas=(TextView)findViewById(R.id.tvNegativas);
        pbReputacion=(ProgressBar)findViewById(R.id.pbReputacion);


    }


    public TextView getTvTotalOpe() {
        return tvTotalOpe;
    }

    public TextView getTvConcretadas() {
        return tvConcretadas;
    }

    public TextView getTvCanceladas() {
        return tvCanceladas;
    }

    public TextView getTvPositivas() {
        return tvPositivas;
    }

    public TextView getTvNeutrales() {
        return tvNeutrales;
    }

    public TextView getTvNegativas() {
        return tvNegativas;
    }

    public ProgressBar getPbReputacion() {
        return pbReputacion;
    }
}
