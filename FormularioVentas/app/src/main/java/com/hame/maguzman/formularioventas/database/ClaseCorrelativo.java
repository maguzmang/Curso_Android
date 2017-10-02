package com.hame.maguzman.formularioventas.database;

/**
 * Created by maguzman on 27/09/2017.
 */

public class ClaseCorrelativo
{
    public String tipoFormulario;
    public String idEquipo;
    public String serie;
    public String numero;

    public ClaseCorrelativo()
    {

    }

    public ClaseCorrelativo(String tipoFormulario, String idEquipo, String serie, String numero) {
        this.tipoFormulario = tipoFormulario;
        this.idEquipo = idEquipo;
        this.serie = serie;
        this.numero = numero;
    }

    public String getTipoFormulario() {
        return tipoFormulario;
    }

    public void setTipoFormulario(String tipoFormulario) {
        this.tipoFormulario = tipoFormulario;
    }

    public String getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(String idEquipo) {
        this.idEquipo = idEquipo;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}
