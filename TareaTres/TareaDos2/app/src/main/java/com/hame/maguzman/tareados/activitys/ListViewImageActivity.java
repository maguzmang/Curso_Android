package com.hame.maguzman.tareados.activitys;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.hame.maguzman.tareados.R;
import com.hame.maguzman.tareados.clases.CuadroDialogo;

public class ListViewImageActivity extends AppCompatActivity implements AdapterView.OnItemClickListener
{
    private Context contexto;
    private ListView lvListado;
    private String [] lstOpciones;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_image);

        contexto = this;

        inicializarComponentes();
    }

    public void inicializarComponentes()
    {
        lstOpciones = getResources().getStringArray(R.array.selected_options);

        lvListado = (ListView) findViewById(R.id.lv_imagenes);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lstOpciones);
        lvListado.setAdapter(adapter);
        lvListado.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l)
    {
        TextView tvTemp = (TextView) view;
        int imgSel = 0;
        switch(position)
        {
            case 0:
                imgSel = R.drawable.atm;
                break;
            case 1:
                imgSel = R.drawable.bag;
                break;
            case 2:
                imgSel = R.drawable.basket;
                break;
            case 3:
                imgSel = R.drawable.box;
                break;
            case 4:
                imgSel = R.drawable.briefcase;
                break;
            case 5:
                imgSel = R.drawable.calculator;
                break;
        }
        new CuadroDialogo(contexto, R.layout.toast_layout, (ViewGroup) findViewById(R.id.lytLayout), imgSel, tvTemp.getText().toString());
    }
}
