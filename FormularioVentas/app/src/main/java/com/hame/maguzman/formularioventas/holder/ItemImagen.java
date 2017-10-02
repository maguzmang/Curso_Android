package com.hame.maguzman.formularioventas.holder;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.hame.maguzman.formularioventas.R;
import com.hame.maguzman.formularioventas.activitys.FrmListaFotos;
import com.hame.maguzman.formularioventas.clases.ClaseConvertiraBitmap;

/**
 * Created by maguzman on 27/09/2017.
 */

public class ItemImagen extends RecyclerView.ViewHolder
{
    private ImageView iv_Item_Image;

    private Context context;

    private String imagen;

    public ItemImagen(View itemView)
    {
        super(itemView);

        iv_Item_Image = itemView.findViewById(R.id.iv_item_image);
        iv_Item_Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((FrmListaFotos) context).imagenCompleta(imagen);
            }
        });
    }

    public void setDatos(String ubicacionImg, Context context)
    {
        this.context = context;
        this.imagen = ubicacionImg;
        ClaseConvertiraBitmap cBitmap = new ClaseConvertiraBitmap();
        Bitmap bitmap = cBitmap.escalarBitmap(ubicacionImg);
        iv_Item_Image.setImageBitmap(bitmap);
    }
}
