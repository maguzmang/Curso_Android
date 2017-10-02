package com.hame.maguzman.formularioventas.database;

/**
 * Created by maguzman on 27/09/2017.
 */

public class ClaseEncuestas
{
    public String numFormulario;
    public String idUsurio;
    public String fecha;
    public String posX;
    public String posY;

    public ClaseEncuestas()
    {

    }

    public ClaseEncuestas(String numFormulario, String idUsurio, String fecha, String posX, String posY)
    {
        this.numFormulario = numFormulario;
        this.idUsurio = idUsurio;
        this.fecha = fecha;
        this.posX = posX;
        this.posY = posY;
    }

    public String getNumFormulario() {
        return numFormulario;
    }

    public void setNumFormulario(String numFormulario) {
        this.numFormulario = numFormulario;
    }

    public String getIdUsurio() {
        return idUsurio;
    }

    public void setIdUsurio(String idUsurio) {
        this.idUsurio = idUsurio;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
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
}
