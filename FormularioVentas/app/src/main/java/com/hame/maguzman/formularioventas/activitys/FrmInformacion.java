package com.hame.maguzman.formularioventas.activitys;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.hame.maguzman.formularioventas.R;
import com.hame.maguzman.formularioventas.clases.ClaseEncuesta;
import com.hame.maguzman.formularioventas.database.ClaseDetEncuestaBD;
import com.hame.maguzman.formularioventas.database.ClaseEncuestaBD;

import java.util.regex.Pattern;

public class FrmInformacion extends AppCompatActivity implements OnMapReadyCallback
{
    private GoogleMap mMap;

    private ClaseEncuesta encuesta;

    private Boolean esNuevo = true;

    private TextInputLayout til_NombreCliente;
    private EditText et_NombreCliente;
    private TextInputLayout til_DireccionCliente;
    private EditText et_DireccionCliente;
    private TextInputLayout til_NitCliente;
    private EditText et_NitCliente;
    private TextInputLayout til_TelefonoCliente;
    private EditText et_TelefonoCliente;
    private TextInputLayout til_CorreoCliente;
    private EditText et_CorreoCliente;
    private TextInputLayout til_ContactoCliente;
    private EditText et_ContactoCliente;

    private String posY = "-90.2500000";
    private String posX = "15.5000000";

    private String direccion = "";

    private static final int LISTADO_FOTOS = 1;

    private AlertDialog dialogYesNo;
    private AlertDialog dialogOk;

    private Context contexto;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frminformacion);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        encuesta = getIntent().getParcelableExtra("encuestaSel");

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        inicializarComponentes();
    }

    public void inicializarComponentes()
    {
        contexto = this;
        til_NombreCliente = (TextInputLayout) findViewById(R.id.til_nombre_cliente);
        et_NombreCliente = (EditText) findViewById(R.id.et_nombre_cliente);
        til_DireccionCliente = (TextInputLayout) findViewById(R.id.til_direccion_cliente);
        et_DireccionCliente = (EditText) findViewById(R.id.et_direccion_cliente);
        til_NitCliente = (TextInputLayout) findViewById(R.id.til_nit_cliente);
        et_NitCliente = (EditText) findViewById(R.id.et_nit_cliente);
        til_TelefonoCliente = (TextInputLayout) findViewById(R.id.til_telefono_cliente);
        et_TelefonoCliente = (EditText) findViewById(R.id.et_telefono_cliente);
        til_CorreoCliente = (TextInputLayout) findViewById(R.id.til_correo_cliente);
        et_CorreoCliente = (EditText) findViewById(R.id.et_correo_cliente);
        til_ContactoCliente = (TextInputLayout) findViewById(R.id.til_contacto_cliente);
        et_ContactoCliente = (EditText) findViewById(R.id.et_contacto_cliente);

        inicializarMensajesAlerta();

        if(encuesta != null)
        {
            et_NombreCliente.setText(encuesta.getNombre());
            et_DireccionCliente.setText(encuesta.getDireccion());
            direccion = encuesta.getDireccion();
            et_NitCliente.setText(encuesta.getNit());
            et_TelefonoCliente.setText(encuesta.getTelefono());
            et_CorreoCliente.setText(encuesta.getCorreo());
            et_ContactoCliente.setText(encuesta.getContacto());
            posX = encuesta.getPosX();
            posY = encuesta.getPosY();
            esNuevo = false;
        }
        habilitarTextos(esNuevo);
    }

    public void inicializarMensajesAlerta()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.alerta);
        builder.setTitle("Eliminar Registros");
        builder.setMessage("No se puede eliminar un registro que no está almacenado");
        builder.setNeutralButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        dialogOk = builder.create();

        builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.alerta);
        builder.setTitle("Eliminar Registros");
        builder.setMessage("Esta seguro de eliminar el registro actual?");
        builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ClaseDetEncuestaBD claseDetEncuestaBD = new ClaseDetEncuestaBD(contexto);
                ClaseEncuestaBD claseEncuestaBD = new ClaseEncuestaBD(contexto);

                claseDetEncuestaBD.borrarDetEncuesta(encuesta.getDocumento());
                claseEncuestaBD.borrarEncuesta(encuesta.getDocumento());

                Toast.makeText(contexto, "El registro con número " + encuesta.getDocumento() + " fué eliminado ", Toast.LENGTH_LONG).show();
                aceptar();
                dialogInterface.cancel();
            }
        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        dialogYesNo = builder.create();
    }

    public void seleccionaEditar(View view)
    {
        habilitarTextos(true);
    }

    public void habilitarTextos(Boolean habilita)
    {
        et_NombreCliente.setEnabled(habilita);
        et_DireccionCliente.setEnabled(habilita);
        et_NitCliente.setEnabled(habilita);
        et_TelefonoCliente.setEnabled(habilita);
        et_CorreoCliente.setEnabled(habilita);
        et_ContactoCliente.setEnabled(habilita);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng posicionEncuesta = new LatLng(Double.parseDouble(posX), Double.parseDouble(posY));
        mMap.addMarker(new MarkerOptions().position(posicionEncuesta).title(direccion));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(posicionEncuesta));
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public void pantallaFotos()
    {
        Intent intento = new Intent(FrmInformacion.this, FrmListaFotos.class);
        intento.putExtra("encuestaSel", encuesta);
        intento.putExtra("esNuevo", esNuevo);
        startActivityForResult(intento, LISTADO_FOTOS);
    }

    public void validarCampos(View view)
    {
        Boolean hay_Error = false;

        til_NombreCliente.setErrorEnabled(false);
        til_DireccionCliente.setErrorEnabled(false);
        til_NitCliente.setErrorEnabled(false);
        til_TelefonoCliente.setErrorEnabled(false);
        til_CorreoCliente.setErrorEnabled(false);
        til_ContactoCliente.setErrorEnabled(false);

        if(et_NombreCliente.getText().toString().replace(" ", "").length() == 0)
        {
            hay_Error = true;
            til_NombreCliente.setError("El campo Nombre de Cliente no puede estar en blanco");
        }
        if((!hay_Error)&&(et_DireccionCliente.getText().toString().replace(" ", "").length() == 0))
        {
            hay_Error = true;
            til_DireccionCliente.setError("El campo Direccion del Cliente no puede estar en blanco");
        }
        if((!hay_Error)&&(et_NitCliente.getText().toString().replace(" ", "").length() == 0))
        {
            hay_Error = true;
            til_NitCliente.setError("El campo Nit del Cliente no puede estar en blanco");
        }
        if((!hay_Error)&&(et_TelefonoCliente.getText().toString().replace(" ", "").length() == 0))
        {
            hay_Error = true;
            til_TelefonoCliente.setError("El campo Teléfono del Cliente no puede estar en blanco");
        }
        if((!hay_Error)&&(et_CorreoCliente.getText().toString().replace(" ", "").length() == 0))
        {
            hay_Error = true;
            til_CorreoCliente.setError("El campo Correo del Cliente no puede estar en blanco");
        }
        else
        {
            if((!hay_Error)&&(!validarCorreo(et_CorreoCliente.getText().toString())))
            {
                hay_Error = true;
                til_CorreoCliente.setError("El formato en el campo Correo del Cliente no es válido");
            }
        }
        if((!hay_Error)&&(et_ContactoCliente.getText().toString().replace(" ", "").length() == 0))
        {
            hay_Error = true;
            til_ContactoCliente.setError("El campo Nombre del contacto no puede estar en blanco");
        }

        if(!hay_Error)
        {
            if(esNuevo)
            {
                encuesta = new ClaseEncuesta();
            }
            encuesta.setNombre(et_NombreCliente.getText().toString());
            encuesta.setDireccion(et_DireccionCliente.getText().toString());
            encuesta.setNit(et_NitCliente.getText().toString());
            encuesta.setTelefono(et_TelefonoCliente.getText().toString());
            encuesta.setCorreo(et_CorreoCliente.getText().toString());
            encuesta.setContacto(et_ContactoCliente.getText().toString());

            pantallaFotos();
        }
    }

    private boolean validarCorreo(String email)
    {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK)
        {
            switch (requestCode)
            {
                case LISTADO_FOTOS:
                        aceptar();
                    break;
            }
        }
    }

    public void showDialogoYesNo(View view)
    {
        if(!esNuevo)
        {
            dialogYesNo.show();
        }
        else
        {
            dialogOk.show();
        }
    }

    public void aceptar()
    {
        Intent intento = new Intent();
        this.setResult(FrmListaFotos.RESULT_OK, intento);
        finish();
    }

    public void salir(View view)
    {
        Intent intento = new Intent();
        this.setResult(FrmInformacion.RESULT_CANCELED, intento);
        finish();
    }
}
