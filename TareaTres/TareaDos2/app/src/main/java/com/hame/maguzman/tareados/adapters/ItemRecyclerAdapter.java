package com.hame.maguzman.tareados.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hame.maguzman.tareados.R;
import com.hame.maguzman.tareados.holders.ItemHolder;

/**
 * Created by maguzman on 19/09/2017.
 */

public class ItemRecyclerAdapter extends RecyclerView.Adapter<ItemHolder>
{
    private int [] imageResources;
    private String [] labels;
    private Context context;

    public ItemRecyclerAdapter(int [] imageResources, String [] labels, Context context)
    {
        this.imageResources = imageResources;
        this.labels = labels;
        this.context = context;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        LayoutInflater li = LayoutInflater.from(context);
        View view = li.inflate(R.layout.item_recycler, parent, false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position)
    {
        holder.setData(imageResources[position], labels[position]);
    }

    @Override
    public int getItemCount()
    {
        return labels.length;
    }
}
