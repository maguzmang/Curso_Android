package com.hame.maguzman.formularioventas.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.hame.maguzman.formularioventas.clases.ClaseImagen;

import java.util.ArrayList;

/**
 * Created by maguzman on 27/09/2017.
 */

public class ClaseImagenFormularioBD
{
    private SQLiteDatabase bd;
    private BasedeDatos manejoBD;

    public ClaseImagenFormularioBD(Context context)
    {
        manejoBD = new BasedeDatos(context);
    }

    public static ContentValues imagenFormularioContentValues(ClaseImagenFormulario imagen)
    {
        ContentValues cv = new ContentValues();
        cv.put(BasedeDatos.imagenFormulario_NumFormulario, imagen.getNumFormulario());
        cv.put(BasedeDatos.imagenFormulario_IdxImagen, imagen.getIdx());
        cv.put(BasedeDatos.imagenFormulario_Ubicacion, imagen.getUbicacion());
        return  cv;
    }

    public String guardarImagenFormulario(ClaseImagenFormulario imagen)
    {
        bd = manejoBD.getWritableDatabase();
        bd.insert(BasedeDatos.tabla_Imagen_Formulario, null, imagenFormularioContentValues(imagen));
        bd.close();
        return  "CORRECTO";
    }

    public ArrayList<ClaseImagenFormulario> cargarImagenFormulario(String documento)
    {
        ArrayList<ClaseImagenFormulario> lImg;
        bd = manejoBD.getReadableDatabase();
        String WHERE = manejoBD.imagenFormulario_NumFormulario + " = ?";
        String[] whereArgs = {documento};
        Cursor cImagen = bd.query(BasedeDatos.tabla_Imagen_Formulario, null, WHERE, whereArgs, null, null, null);

        lImg = null;
        if(cImagen.moveToFirst())
        {
            lImg = new ArrayList<>();
            do
            {
                ClaseImagenFormulario img = new ClaseImagenFormulario();
                img.setNumFormulario(cImagen.getString(0));
                img.setIdx(cImagen.getString(1));
                img.setUbicacion(cImagen.getString(2));
                lImg.add(img);
            }
            while (cImagen.moveToNext());
            cImagen.close();
        }
        bd.close();

        return  lImg;
    }

    public String borrarImagenFormulario(String ubicacion)
    {
        bd = manejoBD.getWritableDatabase();
        String WHERE = manejoBD.imagenFormulario_Ubicacion + " = ?";
        String[] whereArgs = {ubicacion};
        bd.delete(manejoBD.tabla_Imagen_Formulario, WHERE, whereArgs);
        bd.close();

        return  "CORRECTO";
    }

    public String borrarImagenFormularioDocumento(String documento)
    {
        bd = manejoBD.getWritableDatabase();
        String WHERE = manejoBD.imagenFormulario_NumFormulario + " = ?";
        String[] whereArgs = {documento};
        bd.delete(manejoBD.tabla_Imagen_Formulario, WHERE, whereArgs);
        bd.close();

        return  "CORRECTO";
    }

    public String borrarImagenesFormulario()
    {
        bd = manejoBD.getWritableDatabase();
        bd.delete(manejoBD.tabla_Imagen_Formulario, null, null);
        bd.close();

        return  "CORRECTO";
    }

    public String actualizarImagenesFormulario(ClaseImagenFormulario imagenes)
    {
        bd = manejoBD.getWritableDatabase();
        String WHERE = manejoBD.parametro_CodParametro + "= ?";
        bd.update(manejoBD.tabla_Imagen_Formulario, imagenFormularioContentValues(imagenes), WHERE, new String[]{String.valueOf(imagenes.getNumFormulario())});
        bd.close();

        return  "OK-UPDATE";
    }
}
