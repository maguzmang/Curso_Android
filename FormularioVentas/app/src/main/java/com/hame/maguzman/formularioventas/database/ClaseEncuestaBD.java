package com.hame.maguzman.formularioventas.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hame.maguzman.formularioventas.clases.ClaseEncuesta;

import java.util.ArrayList;

/**
 * Created by maguzman on 27/09/2017.
 */

public class ClaseEncuestaBD
{
    private SQLiteDatabase bd;
    private BasedeDatos manejoBD;

    public ClaseEncuestaBD(Context context)
    {
        manejoBD = new BasedeDatos(context);
    }

    public static ContentValues encuestaContentValues(ClaseEncuestas encuesta)
    {
        ContentValues cv = new ContentValues();
        cv.put(BasedeDatos.formulario_numFormulario, encuesta.getNumFormulario());
        cv.put(BasedeDatos.formulario_idUsuario, encuesta.getIdUsurio());
        cv.put(BasedeDatos.formulario_fecha, encuesta.getFecha());
        cv.put(BasedeDatos.formulario_PosX, encuesta.getPosX());
        cv.put(BasedeDatos.formulario_PosY, encuesta.getPosY());
        return  cv;
    }

    public String guardarEncuesta(ClaseEncuestas encuesta)
    {
        bd = manejoBD.getWritableDatabase();
        bd.insert(BasedeDatos.tabla_Formulario, null, encuestaContentValues(encuesta));
        bd.close();
        return  "CORRECTO";
    }

    public ArrayList<ClaseEncuesta> cargarEncuesta()
    {
        ArrayList<ClaseEncuesta> lEnc;
        bd = manejoBD.getReadableDatabase();
        Cursor cDetEncuesta = bd.rawQuery("select " +
                "C.numFormulario as 'formulario' , C.posX as 'posX', C.posY as 'posY', " +
                "D.numPregunta as 'nPregunta', D.respuesta as 'respuesta', " +
                "I.idx as 'nImagen', I.ubicacion as 'ubicacion' " +
                "from tblCabecera C " +
                "inner join tblDetalle D " +
                "on C.numFormulario = D.numFormulario " +
                "inner join tblImagen I " +
                "on C.numFormulario = I.numFormulario " +
                "order by C.numFormulario, D.numPregunta asc", null);

        lEnc = null;
        ClaseEncuesta enc = null;
        if(cDetEncuesta.moveToFirst())
        {
            do
            {
                switch (cDetEncuesta.getInt(3))
                {
                    case 1:
                        enc = new ClaseEncuesta();
                        enc.setDocumento(cDetEncuesta.getString(0));
                        enc.setPosX(cDetEncuesta.getString(1));
                        enc.setPosY(cDetEncuesta.getString(2));
                        enc.setNombre(cDetEncuesta.getString(4));
                        enc.setImagen(cDetEncuesta.getString(5));
                        break;
                    case 2:
                        enc.setDireccion(cDetEncuesta.getString(2));
                        break;
                    case 3:
                        enc.setNit(cDetEncuesta.getString(2));
                        break;
                    case 4:
                        enc.setTelefono(cDetEncuesta.getString(2));
                        break;
                    case 5:
                        enc.setCorreo(cDetEncuesta.getString(2));
                        break;
                    case 6:
                        enc.setContacto(cDetEncuesta.getString(2));
                        lEnc.add(enc);
                        break;
                }
            }
            while (cDetEncuesta.moveToNext());
            cDetEncuesta.close();
        }
        bd.close();

        return lEnc;
    }

    public String borrarEncuesta(String documento)
    {
        bd = manejoBD.getWritableDatabase();
        String WHERE = manejoBD.formulario_numFormulario + " = ?";
        bd.delete(manejoBD.tabla_Formulario, WHERE, new String[]{documento});
        bd.close();

        return  "CORRECTO";
    }

    public String borrarEncuesta()
    {
        bd = manejoBD.getWritableDatabase();
        bd.delete(manejoBD.tabla_Formulario, null, null);
        bd.close();

        return  "CORRECTO";
    }

    public String actualizarDetalle(ClaseEncuestas encuesta)
    {
        bd = manejoBD.getWritableDatabase();
        String WHERE = manejoBD.formulario_numFormulario + "= ? ";
        bd.update(manejoBD.tabla_Formulario, encuestaContentValues(encuesta), WHERE, new String[]{String.valueOf(encuesta.getNumFormulario())});
        bd.close();

        return  "OK-UPDATE";
    }
}
