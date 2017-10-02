package com.hame.maguzman.formularioventas.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.hame.maguzman.formularioventas.clases.ClaseEncuesta;

import java.util.ArrayList;

/**
 * Created by maguzman on 27/09/2017.
 */

public class ClaseDetEncuestaBD
{
    private SQLiteDatabase bd;
    private BasedeDatos manejoBD;

    public ClaseDetEncuestaBD(Context context)
    {
        manejoBD = new BasedeDatos(context);
    }

    public static ContentValues detEncuestaContentValues(ClaseDetEncuesta detalleEncuesta)
    {
        ContentValues cv = new ContentValues();
        cv.put(BasedeDatos.detalleFormulario_NumFormulario, detalleEncuesta.getNumFormulario());
        cv.put(BasedeDatos.detalleFormulario_NumPregunta, detalleEncuesta.getNumPregunta());
        cv.put(BasedeDatos.detalleFormulario_Respuesta, detalleEncuesta.getRespuesta());
        return  cv;
    }

    public String guardarDetalleEncuesta(ClaseDetEncuesta detalleEncuesta)
    {
        bd = manejoBD.getWritableDatabase();
        bd.insert(BasedeDatos.tabla_Detalle_Formulario, null, detEncuestaContentValues(detalleEncuesta));
        bd.close();
        return  "CORRECTO";
    }

    public ArrayList<ClaseEncuesta> cargarDetalleEncuesta()
    {
        ArrayList<ClaseEncuesta> lEnc;
        bd = manejoBD.getReadableDatabase();

        String query = "select distinct C.numFormulario, C.posX, C.posY, D.numPregunta, D.respuesta, I.idx, I.ubicacion from tblFormulario C inner join tblDetalleFormulario D on C.numFormulario = D.numFormulario inner join tblImagenFormulario I on C.numFormulario = I.numFormulario and I.idx = 0 order by  C.numFormulario, D.numPregunta asc";
        Cursor cDetEncuesta = bd.rawQuery(query, null);

        lEnc = new ArrayList<>();
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
                        enc.setImagen(cDetEncuesta.getString(6));
                        break;
                    case 2:
                        enc.setDireccion(cDetEncuesta.getString(4));
                        break;
                    case 3:
                        enc.setNit(cDetEncuesta.getString(4));
                        break;
                    case 4:
                        enc.setTelefono(cDetEncuesta.getString(4));
                        break;
                    case 5:
                        enc.setCorreo(cDetEncuesta.getString(4));
                        break;
                    case 6:
                        enc.setContacto(cDetEncuesta.getString(4));
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

    public ClaseUsuario buscarUsuario(String idusuario, String password)
    {
        ClaseUsuario usr = new ClaseUsuario();
        bd = manejoBD.getReadableDatabase();
        String WHERE = BasedeDatos.usuario_IdUsuario + " = ? and " + BasedeDatos.usuario_Password + " = ?";
        String[] whereArgs = {idusuario, password};
        Cursor cUsuario = bd.query(manejoBD.tabla_Usuario, null, WHERE, whereArgs, null, null, null);
        usr = null;
        if(cUsuario.moveToFirst())
        {
            usr.setIdUSuario(cUsuario.getString(0));
            usr.setNombre(cUsuario.getString(1));
            usr.setPassword(cUsuario.getString(2));
            cUsuario.close();
        }
        return usr;
    }

    public String borrarDetEncuesta(String documento)
    {
        bd = manejoBD.getWritableDatabase();
        String WHERE = manejoBD.detalleFormulario_NumFormulario + " = ?";
        bd.delete(manejoBD.tabla_Detalle_Formulario, WHERE, new String[]{documento});
        bd.close();

        return  "CORRECTO";
    }

    public String borrarEncuesta()
    {
        bd = manejoBD.getWritableDatabase();
        bd.delete(manejoBD.tabla_Detalle_Formulario, null, null);
        bd.close();

        return  "CORRECTO";
    }

    public String actualizarDetalle(ClaseDetEncuesta encuesta)
    {
        bd = manejoBD.getWritableDatabase();
        String WHERE = manejoBD.detalleFormulario_NumFormulario + "= ? and " + manejoBD.detalleFormulario_NumPregunta + "= ?";
        bd.update(manejoBD.tabla_Detalle_Formulario, detEncuestaContentValues(encuesta), WHERE, new String[]{String.valueOf(encuesta.getNumFormulario()), String.valueOf(encuesta.getNumPregunta())});
        bd.close();

        return  "OK-UPDATE";
    }
}
