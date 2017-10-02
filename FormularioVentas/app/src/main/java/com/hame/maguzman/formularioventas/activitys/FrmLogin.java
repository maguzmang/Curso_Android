package com.hame.maguzman.formularioventas.activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.hame.maguzman.formularioventas.R;
import com.hame.maguzman.formularioventas.database.ClaseUsuarioBD;

public class FrmLogin extends Activity
{
    private EditText et_Usuario;
    private EditText et_Password;

    private TextInputLayout til_Usuario;
    private TextInputLayout til_Password;

    private ProgressBar pb_Avance;

    private Handler mHandler = null;

    private Boolean btnPresionado;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frmlogin);

        inicializarComponentes();
    }

    public void inicializarComponentes()
    {
        et_Usuario = findViewById(R.id.et_usuario);
        et_Password = findViewById(R.id.et_password);

        til_Usuario = findViewById(R.id.til_usuario);
        til_Password = findViewById(R.id.til_password);

        pb_Avance = findViewById(R.id.pb_avance);
        pb_Avance.setVisibility(View.GONE);

        til_Usuario.setErrorEnabled(false);
        til_Password.setErrorEnabled(false);

        btnPresionado = false;
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

        til_Usuario.setErrorEnabled(false);
        til_Password.setErrorEnabled(false);
        if(et_Usuario.length() == 0)
        {
            hay_Error = true;
            til_Usuario.setError("El campo usuario no puede estar en blanco");
        }
        if((!hay_Error)&&(et_Password.length() == 0))
        {
            hay_Error = true;
            til_Password.setError("El campo contraseña no puede estar en blanco");
        }

        if(!hay_Error)
        {
            ClaseUsuarioBD BD = new ClaseUsuarioBD(this);
            if((et_Usuario.getText().toString().compareToIgnoreCase("sysadmin") == 0)&&(et_Password.getText().toString().compareToIgnoreCase("1234") == 0))
            {
                hay_Error = false;
            }
            else
            {
                if(BD.buscarUsuario(et_Usuario.getText().toString(), et_Password.getText().toString())!= null)
                {
                    hay_Error = false;
                }
                else
                {
                    hay_Error = true;
                    ocultarProgreso();
                    Toast.makeText(this, "Usuario o password incorrectos", Toast.LENGTH_LONG).show();
                }
            }

            if(!hay_Error)
            {
                mHandler = new Handler();
                mHandler.postDelayed(temporizador, 3_000);
            }
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
        Intent intento = new Intent(FrmLogin.this, FrmListaInformacion.class);
        startActivity(intento);

        et_Password.setText("");
        et_Usuario.setText("");
        et_Usuario.requestFocus();
        ocultarProgreso();
    }

    public void ocultarProgreso()
    {
        btnPresionado = false;
        pb_Avance.setVisibility(View.GONE);
    }
}
