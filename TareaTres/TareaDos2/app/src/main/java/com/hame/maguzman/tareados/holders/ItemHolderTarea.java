package com.hame.maguzman.tareados.holders;

import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hame.maguzman.tareados.R;

/**
 * Created by maguzman on 19/09/2017.
 */

public class ItemHolderTarea extends RecyclerView.ViewHolder
{
    private ImageView ivImagen;
    private TextView tvlLabel;
    private CardView cvBackground;

    public ItemHolderTarea(View itemView)
    {
        super(itemView);

        cvBackground = itemView.findViewById(R.id.cv_item_recycler_tarea);
        ivImagen = itemView.findViewById(R.id.iv_item_image_tarea);
        tvlLabel = itemView.findViewById(R.id.tv_item_label_tarea);
        cvBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, tvlLabel.getText().toString(), Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });
    }

    public void setData(int imageResource, String label)
    {
        ivImagen.setImageResource(imageResource);
        tvlLabel.setText(label);
    }
}
