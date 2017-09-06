package com.hame.maguzman.parcelableapp.vistas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.hame.maguzman.parcelableapp.clases.Alumno;
import com.hame.maguzman.parcelableapp.clases.Clase;
import com.hame.maguzman.parcelableapp.R;

import java.util.ArrayList;
import java.util.List;

public class FrmPrincipal extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frmprincipal);

        List<Alumno> listaAlumno = new ArrayList<>();
        Clase clsClase = new Clase();
        clsClase.setNombre("Curso de Android");
        clsClase.setDescripcion("curso esta creado para dar nuestros primeros pasos en aplicaciones moviles y crear apps funcionales que podremos probar en nuestros dispositivos.");
        for(int i = 0; i < 5; i++)
        {
            Alumno alumno = new Alumno();
            alumno.setNombre("Nombre " + i);
            alumno.setApellido("Apellido " + i);
            listaAlumno.add(alumno);
        }
        clsClase.setAlumnos(listaAlumno);

        Intent intento = new Intent(FrmPrincipal.this, FrmDatos.class);
        intento.putExtra("clsClase", clsClase);
        startActivity(intento);
    }
}
