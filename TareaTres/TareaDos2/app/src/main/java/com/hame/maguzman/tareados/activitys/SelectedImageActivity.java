package com.hame.maguzman.tareados.activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.hame.maguzman.tareados.R;
import com.hame.maguzman.tareados.clases.ListaImagenParcelable;

public class SelectedImageActivity extends Activity
{
    private Spinner sOptions;
    private ImageView imgSelected;
    private LinearLayout frmBotones;

    private Boolean Regresa;
    private ListaImagenParcelable imgParcel;
    private int pos;
    private int image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_image);

        Regresa = getIntent().getBooleanExtra("RegresaInfo", false);

        inicializarComponentes();
    }
    public void inicializarComponentes()
    {
        sOptions = (Spinner) findViewById(R.id.s_opcion);
        final String[] options = getResources().getStringArray(R.array.selected_options);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, options);
        sOptions.setAdapter(adapter);

        frmBotones = (LinearLayout)findViewById(R.id.frmbotones);
        imgSelected = (ImageView) findViewById(R.id.iv_selected);

        if(Regresa)
        {
            frmBotones.setVisibility(View.VISIBLE);
        }
        else
        {
            frmBotones.setVisibility(View.GONE);
        }

        sOptions.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                pos = position;
                switch (position)
                {
                    case 0:
                        imgSelected.setImageResource(R.drawable.atm);
                        image = R.drawable.atm;
                        break;
                    case 1:
                        imgSelected.setImageResource(R.drawable.bag);
                        image = R.drawable.bag;
                        break;
                    case 2:
                        imgSelected.setImageResource(R.drawable.basket);
                        image = R.drawable.basket;
                        break;
                    case 3:
                        imgSelected.setImageResource(R.drawable.box);
                        image = R.drawable.box;
                        break;
                    case 4:
                        imgSelected.setImageResource(R.drawable.briefcase);
                        image = R.drawable.briefcase;
                        break;
                    case 5:
                        imgSelected.setImageResource(R.drawable.calculator);
                        image = R.drawable.calculator;
                        break;
                }
                imgParcel = new ListaImagenParcelable(options[pos], image);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void Aceptar(View view)
    {
        Intent intento = new Intent();
        intento.putExtra("Recibe", imgParcel);
        this.setResult(MainActivity.RESULT_OK, intento);
        finish();
    }

    public void Cancelar(View view)
    {
        Intent intento = new Intent();
        this.setResult(MainActivity.RESULT_CANCELED, intento);
        finish();
    }
}
