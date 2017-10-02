package com.hame.maguzman.formularioventas.database;

/**
 * Created by maguzman on 27/09/2017.
 */

public class ClaseDetEncuesta
{
    public String numFormulario;
    public String numPregunta;
    public String respuesta;

    public ClaseDetEncuesta()
    {

    }

    public ClaseDetEncuesta(String numFormulario, String numPregunta, String respuesta)
    {
        this.numFormulario = numFormulario;
        this.numPregunta = numPregunta;
        this.respuesta = respuesta;
    }

    public String getNumFormulario() {
        return numFormulario;
    }

    public void setNumFormulario(String numFormulario) {
        this.numFormulario = numFormulario;
    }

    public String getNumPregunta() {
        return numPregunta;
    }

    public void setNumPregunta(String numPregunta) {
        this.numPregunta = numPregunta;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }
}
