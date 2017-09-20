package com.hame.maguzman.tareados.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hame.maguzman.tareados.R;

public class ActivityText extends AppCompatActivity
{
    private TextView tv_Valor;
    private EditText et_Ingresa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
        Inicializar();
    }

    private void Inicializar() {
        tv_Valor = (TextView) findViewById(R.id.tv_Ingresado);
        et_Ingresa = (EditText) findViewById(R.id.ed_InsertText);
    }

    public void leerTexto(View view) {
        String texto = et_Ingresa.getText().toString();
        if ((texto != null) && (!texto.trim().isEmpty())) {
            tv_Valor.setText("ingresaron: " + texto);
        } else {
            tv_Valor.setText("");
            Toast.makeText(this, getString(R.string.msj_empty_text), Toast.LENGTH_LONG).show();
        }
    }
}
