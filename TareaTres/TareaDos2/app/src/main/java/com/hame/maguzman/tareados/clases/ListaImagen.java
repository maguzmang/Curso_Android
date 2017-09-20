package com.hame.maguzman.tareados.clases;

/**
 * Created by maguzman on 19/09/2017.
 */

public class ListaImagen
{
    private String nombreImagen;
    private int idImagen;

    public ListaImagen(String nombreImagen, int idImagen)
    {
        this.nombreImagen = nombreImagen;
        this.idImagen = idImagen;
    }

    public String getNombreImagen() {return nombreImagen;}

    public int getIdImagen() {
        return idImagen;
    }
}
