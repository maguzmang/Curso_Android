package com.hame.maguzman.formularioventas.activitys;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.hame.maguzman.formularioventas.R;
import com.hame.maguzman.formularioventas.clases.ClaseConvertiraBitmap;
import com.hame.maguzman.formularioventas.database.ClaseImagenFormularioBD;

public class FrmImagenCompleta extends AppCompatActivity
{
    ImageView iv_image_complete;

    private Context contexto;

    private AlertDialog dialogYesNo;

    private String imagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frmimagencompleta);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        inicializarComponentes();
    }

    public void inicializarComponentes()
    {
        contexto = this;

        imagen = getIntent().getStringExtra("imagen");
        iv_image_complete = (ImageView) findViewById(R.id.iv_image_complete);

        ClaseConvertiraBitmap cBitmap = new ClaseConvertiraBitmap();
        Bitmap bitmap = cBitmap.escalarBitmap(imagen);
        iv_image_complete.setImageBitmap(bitmap);

        inicializarMensajesAlerta();
    }

    public void salir(View view)
    {
        Intent intento = new Intent();
        this.setResult(FrmImagenCompleta.RESULT_CANCELED, intento);
        finish();
    }

    public void inicializarMensajesAlerta()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.alerta);
        builder.setTitle("Eliminar Imágen");
        builder.setMessage("Esta seguro de eliminar la imágen actual?");
        builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ClaseImagenFormularioBD clsImagen = new ClaseImagenFormularioBD(contexto);
                clsImagen.borrarImagenFormulario(imagen);
                salir(null);
                dialogInterface.cancel();
            }
        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        dialogYesNo = builder.create();
    }

    public void showDialogoYesNo(View view)
    {
        dialogYesNo.show();
    }
}
