package com.hame.maguzman.parcelableapp.vistas;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.hame.maguzman.parcelableapp.clases.Alumno;
import com.hame.maguzman.parcelableapp.clases.Clase;
import com.hame.maguzman.parcelableapp.R;

import java.util.List;

public class FrmDatos extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frmdatos);

        Clase clsClase = getIntent().getParcelableExtra("clsClase");

        Log.e("Curso", clsClase.getNombre());
        Log.e("Descripcion", clsClase.getDescripcion());

        List<Alumno> listaAlumnos;
        listaAlumnos = clsClase.getAlumnos();

        for(int i = 0; i < listaAlumnos.size(); i++)
        {
            Log.e("Dato " + i, listaAlumnos.get(i).getNombre() + " " + listaAlumnos.get(i).getApellido());

        }
    }
}
