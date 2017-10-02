package com.hame.maguzman.formularioventas.database;

/**
 * Created by maguzman on 27/09/2017.
 */

public class ClaseParametros
{
    public String codParametro;
    public String nombre;
    public String valor;

    public ClaseParametros()
    {

    }

    public ClaseParametros(String codParametro, String nombre, String valor)
    {
        this.codParametro = codParametro;
        this.nombre = nombre;
        this.valor = valor;
    }

    public String getCodParametro() {
        return codParametro;
    }

    public void setCodParametro(String codParametro) {
        this.codParametro = codParametro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
