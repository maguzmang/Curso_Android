package com.hame.maguzman.formularioventas.activitys;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.util.Log;

import com.hame.maguzman.formularioventas.R;
import com.hame.maguzman.formularioventas.database.BasedeDatos;
import com.hame.maguzman.formularioventas.database.ClaseCorrelativo;
import com.hame.maguzman.formularioventas.database.ClaseCorrelativoBD;
import com.hame.maguzman.formularioventas.database.ClaseParametros;
import com.hame.maguzman.formularioventas.database.ClaseParametrosBD;
import com.hame.maguzman.formularioventas.database.ClaseUsuario;
import com.hame.maguzman.formularioventas.database.ClaseUsuarioBD;

public class FrmSplash extends Activity
{
    private Handler mHandler;
    private Context context;
    private int pantallaAcceso;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frmsplash);

        context = this;
        inicializarComponentes();

        mHandler = new Handler();
        mHandler.postDelayed(temporizador, 5_000);
    }

    public void inicializarComponentes()
    {
        ClaseParametrosBD cParametrosDB = new ClaseParametrosBD(context);
        ClaseCorrelativoBD cCorrelativoBD = new ClaseCorrelativoBD(context);
        ClaseUsuarioBD cUsuarioBD = new ClaseUsuarioBD(context);

        ClaseParametros cParametros = cParametrosDB.buscarParametro("1");
        if(cParametros == null)
        {
            parametrizacion(cParametrosDB, cCorrelativoBD, cUsuarioBD);
        }
        else
        {
            if(cParametros.getValor().compareToIgnoreCase("Y") != 0)
            {
                parametrizacion(cParametrosDB, cCorrelativoBD, cUsuarioBD);
            }
        }

        pantallaAcceso = 1;
    }

    public void parametrizacion(ClaseParametrosBD cParametrosDB, ClaseCorrelativoBD cCorrelativoBD, ClaseUsuarioBD cUsuarioBD)
    {
        cParametrosDB.guardarParametros(new ClaseParametros("1", "Parametrizado", "Y"));
        cParametrosDB.guardarParametros(new ClaseParametros("2", "Equipo", "Hame001"));
        cParametrosDB.guardarParametros(new ClaseParametros("3", "web service", "http://10.4.4.191/wsFormulario/Formulario.asmx"));

        cCorrelativoBD.guardarCorrelativo(new ClaseCorrelativo("1", "Hame001", "EV001", "1"));

        cUsuarioBD.guardarUsuario(new ClaseUsuario("maguzman", "Mario Antonio Guzmán Garzona", "123456"));
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
        Intent intento;
        switch (pantallaAcceso)
        {
            case 1:
                intento = new Intent(FrmSplash.this, FrmLogin.class);
                break;
            case 2:
                intento = new Intent(FrmSplash.this, FrmConfiguracion.class);
                break;
            default:
                intento = new Intent(FrmSplash.this, FrmLogin.class);
                break;
        }
        startActivity(intento);
        finish();
    }
}
