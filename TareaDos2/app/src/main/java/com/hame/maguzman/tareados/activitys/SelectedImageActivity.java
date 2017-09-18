package com.hame.maguzman.tareados.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import com.hame.maguzman.tareados.R;

public class SelectedImageActivity extends AppCompatActivity
{
    private Spinner sOptions;
    private ImageView imgSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_image);

        inicializarComponentes();
    }
    public void inicializarComponentes()
    {
        sOptions = (Spinner) findViewById(R.id.s_opcion);
        String[] options = getResources().getStringArray(R.array.selected_options);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, options);
        sOptions.setAdapter(adapter);

        imgSelected = (ImageView) findViewById(R.id.iv_selected);

        sOptions.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                switch (position)
                {
                    case 0:
                        imgSelected.setImageResource(R.drawable.atm);
                        break;
                    case 1:
                        imgSelected.setImageResource(R.drawable.bag);
                        break;
                    case 2:
                        imgSelected.setImageResource(R.drawable.basket);
                        break;
                    case 3:
                        imgSelected.setImageResource(R.drawable.box);
                        break;
                    case 4:
                        imgSelected.setImageResource(R.drawable.briefcase);
                        break;
                    case 5:
                        imgSelected.setImageResource(R.drawable.calculator);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}
