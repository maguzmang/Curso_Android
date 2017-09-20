package com.hame.maguzman.tareados.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hame.maguzman.tareados.R;
import com.hame.maguzman.tareados.clases.ListaImagen;
import com.hame.maguzman.tareados.holders.ItemHolder;
import com.hame.maguzman.tareados.holders.ItemHolderTarea;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maguzman on 19/09/2017.
 */

public class ItemRecyclerAdapterTarea extends RecyclerView.Adapter<ItemHolderTarea>
{
    List<ListaImagen> listaImagen;
    private Context context;

    public ItemRecyclerAdapterTarea(List<ListaImagen> listaImagen, Context context)
    {
        this.listaImagen = listaImagen;
        this.context = context;
    }

    @Override
    public ItemHolderTarea onCreateViewHolder(ViewGroup parent, int viewType)
    {
        LayoutInflater li = LayoutInflater.from(context);
        View view = li.inflate(R.layout.item_recycler_tarea, parent, false);
        return new ItemHolderTarea(view);
    }

    @Override
    public void onBindViewHolder(ItemHolderTarea holder, int position)
    {
        holder.setData(listaImagen.get(position).getIdImagen(), listaImagen.get(position).getNombreImagen());
    }

    @Override
    public int getItemCount()
    {
        return listaImagen.size();
    }
}
