package com.hame.maguzman.formularioventas.activitys;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.hame.maguzman.formularioventas.R;
import com.hame.maguzman.formularioventas.adapter.ItemImagenAdapter;
import com.hame.maguzman.formularioventas.clases.ClaseEncuesta;
import com.hame.maguzman.formularioventas.database.ClaseCorrelativo;
import com.hame.maguzman.formularioventas.database.ClaseCorrelativoBD;
import com.hame.maguzman.formularioventas.database.ClaseDetEncuesta;
import com.hame.maguzman.formularioventas.database.ClaseDetEncuestaBD;
import com.hame.maguzman.formularioventas.database.ClaseEncuestaBD;
import com.hame.maguzman.formularioventas.database.ClaseEncuestas;
import com.hame.maguzman.formularioventas.database.ClaseImagenFormulario;
import com.hame.maguzman.formularioventas.database.ClaseImagenFormularioBD;

import java.io.File;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FrmListaFotos extends AppCompatActivity implements LocationListener, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener
{
    private static final int CAPTURA_DE_IMAGENES = 1;
    private static final int PETICION_CONFIG_UBICACION = 201;
    private static final int IMAGEN_COMPLETA = 101;

    private static final long INTERVAL = 1000 * 10;
    private static final long FASTEST_INTERVAL = 1000 * 5;

    private LocationRequest mLocationRequest;
    private GoogleApiClient mGoogleApiClient;
    private Location mCurrentLocation;

    private LocationRequest locRequest;

    private String mLastUpdateTime;
    private String posY = "-90.2500000";
    private String posX = "15.5000000";

    private int conteo;

    private RecyclerView recyclerView;
    private ItemImagenAdapter itemImagenAdapter;

    private Context context;

    private ClaseEncuesta encuesta;

    private Boolean esNuevo = true;

    private ArrayList<String> itemImageList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frmlistafotos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {
            itemImageList = new ArrayList<>();
            conteo = 0;
        } else {
            itemImageList = savedInstanceState.getStringArrayList("listaFotos");
            conteo = savedInstanceState.getInt("conteo");
        }

        context = this;
        inicializarComponentes();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (camaraDisponible(context)) {
                    abrirCamara();
                } else {
                    Snackbar.make(view, getBaseContext().getString(R.string.no_camara_sistema), Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }
            }
        });
    }

    public void inicializarComponentes()
    {
        encuesta = getIntent().getParcelableExtra("encuestaSel");
        esNuevo = getIntent().getBooleanExtra("esNuevo", true);

        if(isGooglePlayServicesAvailable())
        {
            createLocationRequest();
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addApi(LocationServices.API)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .build();
        }
        else
        {
            Toast.makeText(context, "no se encontraron servicios de google", Toast.LENGTH_LONG).show();
        }

        recyclerView = (RecyclerView) findViewById(R.id.rv_imagenes);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(gridLayoutManager);

        if(!esNuevo)
        {
            ClaseImagenFormularioBD clsImageDB = new ClaseImagenFormularioBD(context);
            ArrayList<ClaseImagenFormulario> imgForm = clsImageDB.cargarImagenFormulario(encuesta.getDocumento());
            if(imgForm.size() > 0)
            {
                itemImageList = new ArrayList<>();
            }
            for(int i = 0; i < imgForm.size(); i++)
            {
                itemImageList.add(imgForm.get(i).getUbicacion());
                conteo = imgForm.size();
            }
            if(itemImageList != null)
            {
                itemImagenAdapter = new ItemImagenAdapter(this, itemImageList);
                recyclerView.setAdapter(itemImagenAdapter);
            }
        }
    }

    public void abrirCamara() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(getTemporal(this)));
            startActivityForResult(takePictureIntent, CAPTURA_DE_IMAGENES);
        }
    }

    private File getTemporal(Context context) {
        final File path = new File(Environment.getExternalStorageDirectory(), context.getPackageName());
        if (!path.exists()) {
            path.mkdir();
        }
        return new File(path, "image"+ encuesta.getNit() + conteo + ".tmp");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case CAPTURA_DE_IMAGENES:
                    File file = getTemporal(this);
                    itemImageList.add(file.getAbsolutePath());
                    itemImagenAdapter = new ItemImagenAdapter(this, itemImageList);
                    recyclerView.setAdapter(itemImagenAdapter);
                    conteo = conteo + 1;
                    break;
            }
        } else {
        }
    }

    private boolean camaraDisponible(Context context) {
        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            return true;
        } else {
            return false;
        }
    }

    public void salir(View view) {
        Intent intento = new Intent();
        this.setResult(FrmListaFotos.RESULT_CANCELED, intento);
        finish();
    }

    public void Aceptar(View view) {
        updateUI();

        if(esNuevo)
        {
            grabarInformacion();
        }
        else
        {
            actualizarInformacion();
        }
        Intent intento = new Intent();
        this.setResult(FrmListaFotos.RESULT_OK, intento);
        finish();
    }

    public void actualizarInformacion()
    {
        ClaseDetEncuestaBD clsDetEncuestaDB = new ClaseDetEncuestaBD(context);
        ClaseImagenFormularioBD clsImgForm = new ClaseImagenFormularioBD(context);

        clsDetEncuestaDB.actualizarDetalle(new ClaseDetEncuesta(encuesta.getDocumento(), "1", encuesta.getNombre()));
        clsDetEncuestaDB.actualizarDetalle(new ClaseDetEncuesta(encuesta.getDocumento(), "2", encuesta.getDireccion()));
        clsDetEncuestaDB.actualizarDetalle(new ClaseDetEncuesta(encuesta.getDocumento(), "3", encuesta.getNit()));
        clsDetEncuestaDB.actualizarDetalle(new ClaseDetEncuesta(encuesta.getDocumento(), "4", encuesta.getTelefono()));
        clsDetEncuestaDB.actualizarDetalle(new ClaseDetEncuesta(encuesta.getDocumento(), "5", encuesta.getCorreo()));
        clsDetEncuestaDB.actualizarDetalle(new ClaseDetEncuesta(encuesta.getDocumento(), "6", encuesta.getContacto()));

        clsImgForm.borrarImagenFormularioDocumento(encuesta.getDocumento());

        for(int i = 0; i < itemImageList.size(); i++)
        {
            clsImgForm.guardarImagenFormulario(new ClaseImagenFormulario(encuesta.getDocumento(), ""+i, itemImageList.get(i)));
        }

        Toast.makeText(context, "Encuesta " + encuesta.getDocumento() + " fué actualizada", Toast.LENGTH_LONG).show();
    }

    public void grabarInformacion()
    {
        ClaseDetEncuestaBD clsDetEncuestaDB = new ClaseDetEncuestaBD(context);
        ClaseEncuestaBD clsEncuestaDB = new ClaseEncuestaBD(context);
        ClaseCorrelativoBD clsCorrelativoBd = new ClaseCorrelativoBD(context);
        ClaseImagenFormularioBD clsImgForm = new ClaseImagenFormularioBD(context);
        ClaseCorrelativo clsCorrelativo;

        clsCorrelativo = clsCorrelativoBd.buscarCorrelativo("Hame001", "1");
        String numero = formatoCorrelativo(clsCorrelativo.getSerie(), clsCorrelativo.getNumero());
        encuesta.setDocumento(numero);

        clsDetEncuestaDB.guardarDetalleEncuesta(new ClaseDetEncuesta(encuesta.getDocumento(), "1", encuesta.getNombre()));
        clsDetEncuestaDB.guardarDetalleEncuesta(new ClaseDetEncuesta(encuesta.getDocumento(), "2", encuesta.getDireccion()));
        clsDetEncuestaDB.guardarDetalleEncuesta(new ClaseDetEncuesta(encuesta.getDocumento(), "3", encuesta.getNit()));
        clsDetEncuestaDB.guardarDetalleEncuesta(new ClaseDetEncuesta(encuesta.getDocumento(), "4", encuesta.getTelefono()));
        clsDetEncuestaDB.guardarDetalleEncuesta(new ClaseDetEncuesta(encuesta.getDocumento(), "5", encuesta.getCorreo()));
        clsDetEncuestaDB.guardarDetalleEncuesta(new ClaseDetEncuesta(encuesta.getDocumento(), "6", encuesta.getContacto()));
        clsEncuestaDB.guardarEncuesta(new ClaseEncuestas(encuesta.getDocumento(), "maguzman", "", posX, posY));

        for(int i = 0; i < itemImageList.size(); i++)
        {
            clsImgForm.guardarImagenFormulario(new ClaseImagenFormulario(encuesta.getDocumento(), ""+i, itemImageList.get(i)));
        }

        String num = ""+(Integer.parseInt(clsCorrelativo.getNumero()) + 1);
        clsCorrelativoBd.actualizarCorrelativo(new ClaseCorrelativo(clsCorrelativo.getTipoFormulario(), clsCorrelativo.getIdEquipo(), clsCorrelativo.getSerie(), num));

        Toast.makeText(context, "Encuesta Guardada con el número " + numero, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    public void onStop() {
        super.onStop();
        mGoogleApiClient.disconnect();
    }

    protected void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(INTERVAL);
        mLocationRequest.setFastestInterval(FASTEST_INTERVAL);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    private boolean isGooglePlayServicesAvailable() {
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (ConnectionResult.SUCCESS == status) {
            return true;
        } else {
            GooglePlayServicesUtil.getErrorDialog(status, this, 0).show();
            return false;
        }
    }

    @Override
    public void onConnected(Bundle bundle) {
        startLocationUpdates();
    }

    protected void startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            locRequest = new LocationRequest();
            locRequest.setInterval(2000);
            locRequest.setFastestInterval(1000);
            locRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

            LocationSettingsRequest locSettingsRequest =
                    new LocationSettingsRequest.Builder()
                            .addLocationRequest(locRequest)
                            .build();

            PendingResult<LocationSettingsResult> result =
                    LocationServices.SettingsApi.checkLocationSettings(mGoogleApiClient, locSettingsRequest);
            result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
                @Override
                public void onResult(LocationSettingsResult locationSettingsResult) {
                    final Status status = locationSettingsResult.getStatus();
                    switch (status.getStatusCode()) {
                        case LocationSettingsStatusCodes.SUCCESS:
                            startLocationUpdates();

                            break;
                        case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                            try {
                                status.startResolutionForResult(FrmListaFotos.this, PETICION_CONFIG_UBICACION);
                            } catch (IntentSender.SendIntentException e) {
                            }

                            break;
                        case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                            break;
                    }
                }
            });
            return;
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
    }

    @Override
    public void onLocationChanged(Location location) {
        mCurrentLocation = location;
        mLastUpdateTime = DateFormat.getTimeInstance().format(new Date());
        updateUI();
    }

    private void updateUI() {
        if (null != mCurrentLocation) {
            String lat = String.valueOf(mCurrentLocation.getLatitude());
            String lng = String.valueOf(mCurrentLocation.getLongitude());
            posX = lat;
            posY = lng;
            Log.v("At Time: ", mLastUpdateTime + "Latitude: " + lat + "Longitude: " + lng +
                    "Accuracy: " + mCurrentLocation.getAccuracy() + "Provider: " + mCurrentLocation.getProvider());
        } else {
            posY = "-90.2500000";
            posX = "15.5000000";
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopLocationUpdates();
    }

    protected void stopLocationUpdates() {
        LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mGoogleApiClient.isConnected()) {
            startLocationUpdates();
        }
    }

    public String formatoCorrelativo(String serie, String numero)
    {
        String documento;

        switch (numero.length())
        {
            case 1:
                documento = "0000"+numero;
                break;
            case 2:
                documento = "000"+numero;
                break;
            case 3:
                documento = "00"+numero;
                break;
            case 4:
                documento = "0"+numero;
                break;
            default:
                documento = numero;
                break;
        }
        documento = serie + "-" + documento;

        return documento;
    }

    @Override
    protected void onRestart()
    {
        super.onRestart();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle bundle)
    {
        bundle.putStringArrayList("listaFotos", itemImageList);
        bundle.putInt("conteo", conteo);
        super.onSaveInstanceState(bundle);

    }

    public void imagenCompleta(String pathImagen)
    {
        Intent intento = new Intent(FrmListaFotos.this, FrmImagenCompleta.class);
        intento.putExtra("imagen", pathImagen);
        startActivityForResult(intento, IMAGEN_COMPLETA);
    }
}
