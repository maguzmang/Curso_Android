package com.hame.maguzman.formularioventas.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by maguzman on 27/09/2017.
 */

public class ClaseCorrelativoBD
{
    private SQLiteDatabase bd;
    private BasedeDatos manejoBD;

    public ClaseCorrelativoBD(Context context)
    {
        manejoBD = new BasedeDatos(context);
    }

    public static ContentValues correlativoContentValues(ClaseCorrelativo correlativo)
    {
        ContentValues cv = new ContentValues();
        cv.put(BasedeDatos.correlativo_Tipo_Formulario, correlativo.getTipoFormulario());
        cv.put(BasedeDatos.correlativo_IdEquipo, correlativo.getIdEquipo());
        cv.put(BasedeDatos.correlativo_Serie, correlativo.getSerie());
        cv.put(BasedeDatos.correlativo_Numero, correlativo.getNumero());
        return  cv;
    }

    public String guardarCorrelativo(ClaseCorrelativo correlativo)
    {
        bd = manejoBD.getWritableDatabase();
        bd.insert(BasedeDatos.tabla_Correlativo, null, correlativoContentValues(correlativo));
        bd.close();
        return  "CORRECTO";
    }

    public ArrayList<ClaseCorrelativo> cargarCorrelativo()
    {
        ArrayList<ClaseCorrelativo> lCor = new ArrayList<>();
        bd = manejoBD.getReadableDatabase();
        Cursor cCorrelativo = bd.query(BasedeDatos.tabla_Correlativo, null, null, null, null, null, null);

        lCor = null;
        if(cCorrelativo.moveToFirst())
        {
            do
            {
                ClaseCorrelativo cor = new ClaseCorrelativo();
                cor.setTipoFormulario(cCorrelativo.getString(0));
                cor.setIdEquipo(cCorrelativo.getString(1));
                cor.setSerie(cCorrelativo.getString(2));
                cor.setNumero(cCorrelativo.getString(3));
                lCor.add(cor);
            }
            while (cCorrelativo.moveToNext());
            cCorrelativo.close();
        }
        bd.close();

        return  lCor;
    }

    public ClaseCorrelativo buscarCorrelativo(String idEquipo, String tipoFormulario)
    {
        ClaseCorrelativo cor;
        bd = manejoBD.getReadableDatabase();
        String WHERE = BasedeDatos.correlativo_IdEquipo + " = ? and " + BasedeDatos.correlativo_Tipo_Formulario + " = ?";
        String[] whereArgs = {idEquipo, tipoFormulario};
        Cursor cCorrelativo = bd.query(manejoBD.tabla_Correlativo, null, WHERE, whereArgs, null, null, null);
        cor = null;
        if(cCorrelativo.moveToFirst())
        {
            cor = new ClaseCorrelativo();
            cor.setTipoFormulario(cCorrelativo.getString(0));
            cor.setIdEquipo(cCorrelativo.getString(1));
            cor.setSerie(cCorrelativo.getString(2));
            cor.setNumero(cCorrelativo.getString(3));
            cCorrelativo.close();
        }
        return cor;
    }

    public String borrarCorrelativo(ClaseCorrelativo correlativo)
    {
        bd = manejoBD.getWritableDatabase();
        String WHERE = manejoBD.correlativo_IdEquipo + " = ?";
        bd.delete(manejoBD.tabla_Correlativo, WHERE, new String[]{String.valueOf(correlativo.getIdEquipo())});
        bd.close();

        return  "CORRECTO";
    }

    public String borrarCorrelativo()
    {
        bd = manejoBD.getWritableDatabase();
        bd.delete(manejoBD.tabla_Correlativo, null, null);
        bd.close();

        return  "CORRECTO";
    }

    public String actualizarCorrelativo(ClaseCorrelativo correlativo)
    {
        bd = manejoBD.getWritableDatabase();
        String WHERE = manejoBD.correlativo_IdEquipo + "= ? and " + manejoBD.correlativo_Serie + " = ? and " + manejoBD.correlativo_Tipo_Formulario + " = ?";
        bd.update(manejoBD.tabla_Correlativo, correlativoContentValues(correlativo), WHERE, new String[]{String.valueOf(correlativo.getIdEquipo()), String.valueOf(correlativo.getSerie()), String.valueOf(correlativo.getTipoFormulario())});
        bd.close();

        return  "OK-UPDATE";
    }
}
