package com.hame.maguzman.formularioventas.activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.hame.maguzman.formularioventas.R;
import com.hame.maguzman.formularioventas.adapter.ItemEncuestaAdapter;
import com.hame.maguzman.formularioventas.clases.ClaseEncuesta;
import com.hame.maguzman.formularioventas.database.ClaseDetEncuestaBD;
import com.hame.maguzman.formularioventas.holder.ItemEncuesta;

import java.util.ArrayList;

public class FrmListaInformacion extends AppCompatActivity
{
    private ImageButton ib_Salir;
    private ImageButton ib_Ajustes;

    private RecyclerView recyclerView;
    private ItemEncuestaAdapter itemEncuestaAdapter;
    private Context context;

    private ArrayList<ClaseEncuesta> clsEncuesta;

    private static final int LISTADO_INFORMACION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frmlistainformacion);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                agregar(null);
            }
        });

        inicializarComponentes();
    }

    public void inicializarComponentes()
    {
        context = this;
        ib_Salir = (ImageButton) findViewById(R.id.ib_salir);
        ib_Ajustes = (ImageButton) findViewById(R.id.ib_ajustes);
        recyclerView = (RecyclerView) findViewById(R.id.rv_encuestas);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 1);
        recyclerView.setLayoutManager(gridLayoutManager);

        cargarDatos();
    }

    public void cargarDatos()
    {
        ClaseDetEncuestaBD detEncuestaBD = new ClaseDetEncuestaBD(context);

        clsEncuesta = detEncuestaBD.cargarDetalleEncuesta();

        if(clsEncuesta != null)
        {
            itemEncuestaAdapter = new ItemEncuestaAdapter(context, detEncuestaBD.cargarDetalleEncuesta());
            recyclerView.setAdapter(itemEncuestaAdapter);
        }
    }

    public void salir(View view)
    {
        finish();
    }

    public void ajustes(View view)
    {
        Intent intento = new Intent(FrmListaInformacion.this, FrmConfiguracion.class);
        startActivity(intento);
    }

    public void agregar(ClaseEncuesta clsEncuesta)
    {
        ClaseEncuesta encuesta = clsEncuesta;
        Intent intento = new Intent(FrmListaInformacion.this, FrmInformacion.class);
        intento.putExtra("encuestaSel", encuesta);
        startActivityForResult(intento, LISTADO_INFORMACION);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK)
        {
            switch (requestCode)
            {
                case LISTADO_INFORMACION:
                    cargarDatos();
                    break;
            }
        }
    }

}
