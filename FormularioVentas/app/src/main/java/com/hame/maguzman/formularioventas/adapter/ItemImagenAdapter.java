package com.hame.maguzman.formularioventas.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hame.maguzman.formularioventas.R;
import com.hame.maguzman.formularioventas.holder.ItemImagen;

import java.util.ArrayList;

/**
 * Created by maguzman on 27/09/2017.
 */

public class ItemImagenAdapter extends RecyclerView.Adapter<ItemImagen>
{
    private Context context;
    private ArrayList<String> datos;

    public ItemImagenAdapter(Context context, ArrayList<String> datos)
    {
        this.context = context;
        this.datos = datos;
    }

    @Override
    public ItemImagen onCreateViewHolder(ViewGroup parent, int viewType)
    {
        LayoutInflater li = LayoutInflater.from(context);
        View view = li.inflate(R.layout.itemimagen, parent, false);
        return new ItemImagen(view);
    }

    @Override
    public void onBindViewHolder(ItemImagen holder, int position)
    {
        holder.setDatos(datos.get(position), context);
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    public void addNew(String imagen){
        if(datos == null)
        {
            datos = new ArrayList<>();
        }
        datos.add(imagen);
        notifyItemInserted(datos.size() - 1);
    }
}
