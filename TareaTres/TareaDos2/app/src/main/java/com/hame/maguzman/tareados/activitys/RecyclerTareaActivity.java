package com.hame.maguzman.tareados.activitys;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import com.hame.maguzman.tareados.R;
import com.hame.maguzman.tareados.adapters.ItemRecyclerAdapterTarea;
import com.hame.maguzman.tareados.clases.ListaImagen;
import com.hame.maguzman.tareados.clases.ListaImagenParcelable;

import java.util.ArrayList;
import java.util.List;

public class RecyclerTareaActivity extends Activity
{
    List<ListaImagen> listado;
    private RecyclerView recyclerView;
    private ItemRecyclerAdapterTarea itemAdapter;
    private Context context;
    int IMAGEN_SELECCIONADA = 3;

    Boolean RETORNAR_INFO = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_tarea);

        context = this;
        inicializarComponentes();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_tarea);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                agregarInformacion();
            }
        });
    }

    public void inicializarComponentes()
    {
        listado = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.rv_item_list_tarea);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 3);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        listado.add(new ListaImagen(getResources().getString(R.string.cbx_atm), R.drawable.atm));
        listado.add(new ListaImagen(getResources().getString(R.string.cbx_bag), R.drawable.bag));
        listado.add(new ListaImagen(getResources().getString(R.string.cbx_basket), R.drawable.basket));
        listado.add(new ListaImagen(getResources().getString(R.string.cbx_box), R.drawable.box));
        listado.add(new ListaImagen(getResources().getString(R.string.cbx_briefcase), R.drawable.briefcase));
        listado.add(new ListaImagen(getResources().getString(R.string.cbx_calculator), R.drawable.calculator));

        itemAdapter = new ItemRecyclerAdapterTarea(listado, context);

        recyclerView.setAdapter(itemAdapter);
    }

    public void agregarInformacion()
    {
        Intent intento = new Intent(RecyclerTareaActivity.this, SelectedImageActivity.class);
        intento.putExtra("RegresaInfo", RETORNAR_INFO);
        startActivityForResult(intento, IMAGEN_SELECCIONADA);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if ((resultCode == Activity.RESULT_OK) && (requestCode == IMAGEN_SELECCIONADA)) {
            ListaImagenParcelable lst;
            lst = data.getParcelableExtra("Recibe");
            listado.add(new ListaImagen(lst.getNombreImagen(), lst.getIdImagen()));
            itemAdapter = new ItemRecyclerAdapterTarea(listado, context);
            recyclerView.setAdapter(itemAdapter);
        }
    }

}
