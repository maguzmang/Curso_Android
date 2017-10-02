package com.hame.maguzman.formularioventas.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by maguzman on 27/09/2017.
 */

public class ClaseParametrosBD
{
    private SQLiteDatabase bd;
    private BasedeDatos manejoBD;

    public ClaseParametrosBD(Context context)
    {
        manejoBD = new BasedeDatos(context);
    }

    public static ContentValues parametroContentValues(ClaseParametros parametros)
    {
        ContentValues cv = new ContentValues();
        cv.put(BasedeDatos.parametro_CodParametro, parametros.getCodParametro());
        cv.put(BasedeDatos.parametro_Nombre, parametros.getNombre());
        cv.put(BasedeDatos.parametro_Valor, parametros.getValor());
        return  cv;
    }

    public String guardarParametros(ClaseParametros parametros)
    {
        bd = manejoBD.getWritableDatabase();
        bd.insert(BasedeDatos.tabla_Parametro, null, parametroContentValues(parametros));
        bd.close();
        return  "CORRECTO";
    }

    public ArrayList<ClaseParametros> cargarParametros()
    {
        ArrayList<ClaseParametros> lPar = new ArrayList<>();
        bd = manejoBD.getReadableDatabase();
        Cursor cParametro = bd.query(BasedeDatos.tabla_Parametro, null, null, null, null, null, null);

        lPar = null;
        if(cParametro.moveToFirst())
        {
            do
            {
                ClaseParametros par = new ClaseParametros();
                par.setCodParametro(cParametro.getString(0));
                par.setNombre(cParametro.getString(1));
                par.setValor(cParametro.getString(2));
                lPar.add(par);
            }
            while (cParametro.moveToNext());
            cParametro.close();
        }
        bd.close();

        return  lPar;
    }

    public ClaseParametros buscarParametro(String codparametro)
    {
        ClaseParametros par;
        bd = manejoBD.getReadableDatabase();
        String WHERE = BasedeDatos.parametro_CodParametro + " = ?";
        String[] whereArgs = {codparametro};
        Cursor cParametro = bd.query(manejoBD.tabla_Parametro, null, WHERE, whereArgs, null, null, null);

        par = null;
        if(cParametro.moveToFirst())
        {
            par = new ClaseParametros();
            par.setCodParametro(cParametro.getString(0));
            par.setNombre(cParametro.getString(1));
            par.setValor(cParametro.getString(2));
            cParametro.close();
        }
        return par;
    }

    public String borrarParametro(String codparametro)
    {
        bd = manejoBD.getWritableDatabase();
        String WHERE = manejoBD.parametro_CodParametro + " = ? ";
        String[] whereArgs = {codparametro};
        bd.delete(manejoBD.tabla_Parametro, WHERE, whereArgs);
        bd.close();

        return  "CORRECTO";
    }

    public String borrarParametros()
    {
        bd = manejoBD.getWritableDatabase();
        bd.delete(manejoBD.tabla_Parametro, null, null);
        bd.close();

        return  "CORRECTO";
    }

    public String actualizarParametros(ClaseParametros parametros)
    {
        bd = manejoBD.getWritableDatabase();
        String WHERE = manejoBD.parametro_CodParametro + "= ?";
        bd.update(manejoBD.tabla_Usuario, parametroContentValues(parametros), WHERE, new String[]{String.valueOf(parametros.getCodParametro())});
        bd.close();

        return  "OK-UPDATE";
    }
}
