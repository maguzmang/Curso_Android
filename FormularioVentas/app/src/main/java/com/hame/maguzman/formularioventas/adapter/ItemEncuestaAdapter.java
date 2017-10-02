package com.hame.maguzman.formularioventas.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hame.maguzman.formularioventas.R;
import com.hame.maguzman.formularioventas.clases.ClaseEncuesta;
import com.hame.maguzman.formularioventas.holder.ItemEncuesta;

import java.util.ArrayList;

/**
 * Created by maguzman on 27/09/2017.
 */

public class ItemEncuestaAdapter extends RecyclerView.Adapter<ItemEncuesta>
{
    private Context context;
    private ArrayList<ClaseEncuesta> datos;

    public ItemEncuestaAdapter(Context context, ArrayList<ClaseEncuesta> encuesta)
    {
        this.context = context;
        this.datos = encuesta;
    }

    @Override
    public ItemEncuesta onCreateViewHolder(ViewGroup parent, int viewType)
    {
        LayoutInflater li = LayoutInflater.from(context);
        View view = li.inflate(R.layout.itemencuesta, parent, false);
        return new ItemEncuesta(view);
    }

    @Override
    public void onBindViewHolder(ItemEncuesta holder, int position)
    {
        holder.setDatos(datos.get(position), context);
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }
}
