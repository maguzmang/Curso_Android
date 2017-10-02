package com.hame.maguzman.formularioventas.clases;

import android.os.Parcel;
import android.os.Parcelable;


/**
 * Created by maguzman on 27/09/2017.
 */

public class ClaseEncuesta implements Parcelable {
    public String documento;
    public String nombre;
    public String direccion;
    public String nit;
    public String telefono;
    public String correo;
    public String contacto;
    public String posX;
    public String posY;
    public String imagen;

    public ClaseEncuesta()
    {

    }

    protected ClaseEncuesta(Parcel in) {
        documento = in.readString();
        nombre = in.readString();
        direccion = in.readString();
        nit = in.readString();
        telefono = in.readString();
        correo = in.readString();
        contacto = in.readString();
        posX = in.readString();
        posY = in.readString();
        imagen = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(documento);
        dest.writeString(nombre);
        dest.writeString(direccion);
        dest.writeString(nit);
        dest.writeString(telefono);
        dest.writeString(correo);
        dest.writeString(contacto);
        dest.writeString(posX);
        dest.writeString(posY);
        dest.writeString(imagen);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<ClaseEncuesta> CREATOR = new Parcelable.Creator<ClaseEncuesta>() {
        @Override
        public ClaseEncuesta createFromParcel(Parcel in) {
            return new ClaseEncuesta(in);
        }

        @Override
        public ClaseEncuesta[] newArray(int size) {
            return new ClaseEncuesta[size];
        }
    };

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getPosX() {
        return posX;
    }

    public void setPosX(String posX) {
        this.posX = posX;
    }

    public String getPosY() {
        return posY;
    }

    public void setPosY(String posY) {
        this.posY = posY;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }
}