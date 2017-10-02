package com.hame.maguzman.formularioventas.activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.hame.maguzman.formularioventas.R;
import com.hame.maguzman.formularioventas.database.ClaseParametros;
import com.hame.maguzman.formularioventas.database.ClaseParametrosBD;

public class FrmConfiguracion extends AppCompatActivity
{
    private EditText et_IdEquipo;
    private EditText et_WebService;

    private TextInputLayout til_IdEquipo;
    private TextInputLayout til_WebService;

    private ProgressBar pb_Avance;

    private Context contexto;

    private Handler mHandler = null;

    private Boolean btnPresionado;

    private ClaseParametrosBD clsParametro;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frmconfiguracion);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        contexto = this;

        inicializarComponentes();
    }

    public void inicializarComponentes()
    {
        et_IdEquipo = (EditText) findViewById(R.id.et_id_equipo);
        et_WebService = (EditText) findViewById(R.id.et_web_service);

        til_IdEquipo = (TextInputLayout) findViewById(R.id.til_id_equipo);
        til_WebService = (TextInputLayout) findViewById(R.id.til_web_service);

        pb_Avance = (ProgressBar) findViewById(R.id.pb_avance);
        pb_Avance.setVisibility(View.GONE);

        til_IdEquipo.setErrorEnabled(false);
        til_WebService.setErrorEnabled(false);

        btnPresionado = false;

        clsParametro = new ClaseParametrosBD(this);
        et_IdEquipo.setText(clsParametro.buscarParametro("2").getValor());

        clsParametro.buscarParametro("3");
        et_WebService.setText(clsParametro.buscarParametro("3").getValor());
    }

    public void aceptar(View view)
    {
        if(!btnPresionado)
        {
            btnPresionado = true;
            pb_Avance.setVisibility(View.VISIBLE);
            validarDatos();
        }
    }

    public void validarDatos()
    {
        Boolean hay_Error = false;

        til_IdEquipo.setErrorEnabled(false);
        til_WebService.setErrorEnabled(false);
        if(et_IdEquipo.length() == 0)
        {
            hay_Error = true;
            til_IdEquipo.setError("El campo identificador del Equipo no puede estar en blanco");
        }
        if((!hay_Error)&&(et_WebService.length() == 0))
        {
            hay_Error = true;
            til_WebService.setError("El campo Web Service no puede estar en blanco");
        }

        if(!hay_Error)
        {
            clsParametro.borrarParametro("2");
            clsParametro.borrarParametro("3");

            clsParametro.guardarParametros(new ClaseParametros("2", "Equipo", et_IdEquipo.getText().toString()));
            clsParametro.guardarParametros(new ClaseParametros("3", "web service", et_WebService.getText().toString()));

            mHandler = new Handler();
            mHandler.postDelayed(temporizador, 3_000);
        }
        else
        {
            ocultarProgreso();
        }
    }

    private Runnable temporizador = new Runnable()
    {
        @Override
        public void run()
        {
            cambioPantalla();
        }
    };

    private void cambioPantalla()
    {
        salir(null);

        ocultarProgreso();
    }

    public void ocultarProgreso()
    {
        btnPresionado = false;
        pb_Avance.setVisibility(View.GONE);
    }

    public void salir(View view)
    {
        finish();
    }
}
