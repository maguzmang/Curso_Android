package com.hame.maguzman.formularioventas.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by maguzman on 25/09/2017.
 */

public class BasedeDatos extends SQLiteOpenHelper
{
    private SQLiteDatabase  bd;
    public  static final String nombreBase = "formulario.db3";
    public  static final int versionBase = 1;

    private static final String tipoAutoincrementable = " INTEGER PRIMARY KEY AUTOINCREMENT";
    private static final String crearTabla = " CREATE TABLE IF NOT EXISTS ";
    private static final String tipoTexto = " TEXT";
    private static final String tipoEntero  = " INTEGER";
    private static final String tipoDecimal = " DOUBLE";
    private static final String tipoBooleano = " BOOLEAN";
    private static final String tipoFecha = " DATETIME";
    private static final String coma = " ,";
    private static final String abreParentesis = "( ";
    private static final String cierraParentesis = " )";
    private static final String puntoYComa = ";";

    /*
     * Creación tabla Usuario
     */
    public static final String tabla_Usuario = " tblUsuario ";
    public static final String usuario_IdUsuario = " idUsuario ";
    public static final String usuario_Nombre = " nombre ";
    public static final String usuario_Password = " password ";
    public static final String
    crear_Tabla_Usuario =   crearTabla + tabla_Usuario + abreParentesis + usuario_IdUsuario +
                            tipoTexto + coma + usuario_Nombre + tipoTexto + coma + usuario_Password +
                            tipoTexto + cierraParentesis + puntoYComa;

    /*
     * Creación tabla Equipo
     */
    public static final String tabla_Equipo = " tblEquipo ";
    public static final String equipo_IdEquipo = " idEquipo ";
    public static final String equipo_Modelo = " modelo ";
    public static final String equipo_Serie = " serie ";
    public static final String
    crear_Tabla_Equipo =    crearTabla + tabla_Equipo + abreParentesis + equipo_IdEquipo + tipoTexto + coma +
                            equipo_Modelo + tipoTexto + coma + equipo_Serie + tipoTexto + cierraParentesis + puntoYComa;

    /*
     *Creación tabla Formulario
     */
    public static final String tabla_Formulario = " tblFormulario ";
    public static final String formulario_numFormulario = " numFormulario ";
    public static final String formulario_idUsuario = " idUsuario ";
    public static final String formulario_fecha = " fecha ";
    public static final String formulario_PosX = " posX ";
    public static final String formulario_PosY = " posY ";
    public static final String
    crear_Tabla_Formulario =    crearTabla + tabla_Formulario + abreParentesis + formulario_numFormulario + tipoTexto + coma +
                                formulario_idUsuario + tipoTexto + coma + formulario_fecha + tipoFecha + coma +
                                formulario_PosX + tipoDecimal + coma + formulario_PosY + cierraParentesis + puntoYComa;

    /*
     *Creación tabla Detalle Formulario
     */
    public static final String tabla_Detalle_Formulario = " tblDetalleFormulario ";
    public static final String detalleFormulario_NumFormulario = " numFormulario ";
    public static final String detalleFormulario_NumPregunta = " numPregunta ";
    public static final String detalleFormulario_Respuesta = " respuesta ";
    public static final String
    crear_Tabla_Detalle_Formulario =    crearTabla + tabla_Detalle_Formulario + abreParentesis +
                                        detalleFormulario_NumFormulario + tipoTexto + coma +
                                        detalleFormulario_NumPregunta + tipoEntero + coma +
                                        detalleFormulario_Respuesta + tipoTexto + cierraParentesis + puntoYComa;

    /*
     *Creación tabla Imagen Formulario
     */
    public static final String tabla_Imagen_Formulario = " tblImagenFormulario ";
    public static final String imagenFormulario_NumFormulario = " numFormulario ";
    public static final String imagenFormulario_IdxImagen = " idx ";
    public static final String imagenFormulario_Ubicacion = " ubicacion ";
    public static final String
    crear_Tabla_Imagen_Formulario =     crearTabla + tabla_Imagen_Formulario + abreParentesis +
                                        imagenFormulario_NumFormulario + tipoTexto + coma +
                                        imagenFormulario_IdxImagen + tipoEntero + coma +
                                        imagenFormulario_Ubicacion + tipoTexto + cierraParentesis + puntoYComa;

    /*
     *Creación tabla Parametro
     */
    public static final String tabla_Parametro = " tblParametro ";
    public static final String parametro_CodParametro = " codParametro ";
    public static final String parametro_Nombre = " nombre ";
    public static final String parametro_Valor = " valor ";
    public static final String
    crear_Tabla_Parametro =     crearTabla + tabla_Parametro + abreParentesis + parametro_CodParametro + tipoEntero + coma +
                                parametro_Nombre + tipoTexto + coma + parametro_Valor + cierraParentesis + puntoYComa;

    /*
     *Creación tabla Correlativo
     */
    public static final String tabla_Correlativo = " tblCorrelativo ";
    public static final String correlativo_Tipo_Formulario = " tipoFormulario ";
    public static final String correlativo_IdEquipo = " idEquipo ";
    public static final String correlativo_Serie = " serie ";
    public static final String correlativo_Numero = " numero ";
    public static final String
    crear_tabla_Correlativo =   crearTabla + tabla_Correlativo + abreParentesis + correlativo_Tipo_Formulario + tipoEntero + coma +
                                correlativo_IdEquipo + tipoTexto + coma + correlativo_Serie + tipoTexto + coma +
                                correlativo_Numero + tipoEntero + cierraParentesis + puntoYComa;

    public BasedeDatos(Context context)
    {
        super(context, BasedeDatos.nombreBase, null, BasedeDatos.versionBase);
    }

    public void  openReadableDB()
    {
        bd = this.getReadableDatabase();
    }

    public void  openWriteableDB()
    {
        bd = this.getWritableDatabase();
    }

    public void closeDB()
    {
        if(bd!= null)
        {
            bd.close();
        }
    }


    @Override
    public  void onOpen(SQLiteDatabase db)
    {
        super.onOpen(db);
        if (!db.isReadOnly())
        {
            db.execSQL("PRAGMA foreing_keys=ON; ");
        }
    }

    @Override
    public void onCreate(SQLiteDatabase baseDatos)
    {
        baseDatos.execSQL(crear_Tabla_Usuario);
        baseDatos.execSQL(crear_Tabla_Equipo);
        baseDatos.execSQL(crear_Tabla_Formulario);
        baseDatos.execSQL(crear_Tabla_Detalle_Formulario);
        baseDatos.execSQL(crear_Tabla_Imagen_Formulario);
        baseDatos.execSQL(crear_Tabla_Parametro);
        baseDatos.execSQL(crear_tabla_Correlativo);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {

    }
}
