package com.hame.maguzman.tareados.clases;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by maguzman on 20/09/2017.
 */

public class ListaImagenParcelable implements Parcelable
{
    private String nombreImagen;
    private int idImagen;

    public ListaImagenParcelable(String nombreImagen, int idImagen)
    {
        this.nombreImagen = nombreImagen;
        this.idImagen = idImagen;
    }

    protected ListaImagenParcelable(Parcel in) {
        nombreImagen = in.readString();
        idImagen = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombreImagen);
        dest.writeInt(idImagen);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<ListaImagenParcelable> CREATOR = new Parcelable.Creator<ListaImagenParcelable>() {
        @Override
        public ListaImagenParcelable createFromParcel(Parcel in) {
            return new ListaImagenParcelable(in);
        }

        @Override
        public ListaImagenParcelable[] newArray(int size) {
            return new ListaImagenParcelable[size];
        }
    };

    public String getNombreImagen() {
        return nombreImagen;
    }

    public void setNombreImagen(String nombreImagen) {
        this.nombreImagen = nombreImagen;
    }

    public int getIdImagen() {
        return idImagen;
    }

    public void setIdImagen(int idImagen) {
        this.idImagen = idImagen;
    }
}