package com.hame.maguzman.formularioventas.database;

/**
 * Created by maguzman on 27/09/2017.
 */

public class ClaseImagenFormulario
{
    public String numFormulario;
    public String idx;
    public String ubicacion;

    public ClaseImagenFormulario()
    {

    }

    public ClaseImagenFormulario(String numFormulario, String idx, String ubicacion) {
        this.numFormulario = numFormulario;
        this.idx = idx;
        this.ubicacion = ubicacion;
    }

    public String getNumFormulario() {
        return numFormulario;
    }

    public void setNumFormulario(String numFormulario) {
        this.numFormulario = numFormulario;
    }

    public String getIdx() {
        return idx;
    }

    public void setIdx(String idx) {
        this.idx = idx;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
}
