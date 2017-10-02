package com.hame.maguzman.formularioventas.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by maguzman on 27/09/2017.
 */

public class ClaseUsuarioBD
{
    private SQLiteDatabase bd;
    private BasedeDatos manejoBD;

    public  ClaseUsuarioBD(Context context)
    {
        manejoBD = new BasedeDatos(context);
    }

    public static ContentValues usuarioContentValues(ClaseUsuario usuario)
    {
        ContentValues cv = new ContentValues();
        cv.put(BasedeDatos.usuario_IdUsuario, usuario.getIdUSuario());
        cv.put(BasedeDatos.usuario_Nombre, usuario.getNombre());
        cv.put(BasedeDatos.usuario_Password, usuario.getPassword());
        return  cv;
    }

    public String guardarUsuario(ClaseUsuario usuario)
    {
        bd = manejoBD.getWritableDatabase();
        bd.insert(BasedeDatos.tabla_Usuario, null, usuarioContentValues(usuario));
        bd.close();
        return  "CORRECTO";
    }

    public ArrayList<ClaseUsuario> cargarUsuarios()
    {
        ArrayList<ClaseUsuario> lUsr = new ArrayList<>();
        bd = manejoBD.getReadableDatabase();
        Cursor cUsuario = bd.query(BasedeDatos.tabla_Usuario, null, null, null, null, null, null);

        lUsr = null;
        if(cUsuario.moveToFirst())
        {
            do
            {
                ClaseUsuario usr = new ClaseUsuario();
                usr.setIdUSuario(cUsuario.getString(0));
                usr.setNombre(cUsuario.getString(1));
                usr.setPassword(cUsuario.getString(2));
                lUsr.add(usr);
            }
            while (cUsuario.moveToNext());
            cUsuario.close();
        }
        bd.close();

        return  lUsr;
    }

    public ClaseUsuario buscarUsuario(String idusuario, String password)
    {
        ClaseUsuario usr;
        bd = manejoBD.getReadableDatabase();
        String WHERE = BasedeDatos.usuario_IdUsuario + " = ? and " + BasedeDatos.usuario_Password + " = ?";
        String[] whereArgs = {idusuario, password};
        Cursor cUsuario = bd.query(manejoBD.tabla_Usuario, null, WHERE, whereArgs, null, null, null);
        usr = null;
        if(cUsuario.moveToFirst())
        {
            usr = new ClaseUsuario();
            usr.setIdUSuario(cUsuario.getString(0));
            usr.setNombre(cUsuario.getString(1));
            usr.setPassword(cUsuario.getString(2));
            cUsuario.close();
        }
        return usr;
    }

    public String borrarUsuario(ClaseUsuario usr)
    {
        bd = manejoBD.getWritableDatabase();
        String WHERE = manejoBD.usuario_IdUsuario + " = ?";
        bd.delete(manejoBD.tabla_Usuario, WHERE, new String[]{String.valueOf(usr.getIdUSuario())});
        bd.close();

        return  "CORRECTO";
    }

    public String borrarUsuarios()
    {
        bd = manejoBD.getWritableDatabase();
        bd.delete(manejoBD.tabla_Usuario, null, null);
        bd.close();

        return  "CORRECTO";
    }

    public String actualizarUsuario(ClaseUsuario usr)
    {
        bd = manejoBD.getWritableDatabase();
        String WHERE = manejoBD.usuario_IdUsuario + "= ?";
        bd.update(manejoBD.tabla_Usuario, usuarioContentValues(usr), WHERE, new String[]{String.valueOf(usr.getIdUSuario())});
        bd.close();

        return  "OK-UPDATE";
    }
}
