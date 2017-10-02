package com.hame.maguzman.formularioventas.database;

/**
 * Created by maguzman on 27/09/2017.
 */

public class ClaseUsuario
{
    public String idUSuario;
    public String nombre;
    public String password;

    public ClaseUsuario()
    {

    }

    public ClaseUsuario(String idUSuario, String nombre, String password)
    {
        this.idUSuario = idUSuario;
        this.nombre = nombre;
        this.password = password;
    }

    public String getIdUSuario() {
        return idUSuario;
    }

    public void setIdUSuario(String idUSuario) {
        this.idUSuario = idUSuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
