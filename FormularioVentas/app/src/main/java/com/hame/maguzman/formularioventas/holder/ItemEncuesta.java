package com.hame.maguzman.formularioventas.holder;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hame.maguzman.formularioventas.R;
import com.hame.maguzman.formularioventas.activitys.FrmListaInformacion;
import com.hame.maguzman.formularioventas.clases.ClaseEncuesta;

import java.io.File;

/**
 * Created by maguzman on 27/09/2017.
 */

public class ItemEncuesta extends RecyclerView.ViewHolder
{
    private RelativeLayout rl_Item_Encuesta;

    private ImageView iv_Item_Image;

    private TextView tv_Nombre_Cliente;
    private TextView tv_Direccion_Cliente;
    private TextView tv_Telefono_Cliente;

    private ClaseEncuesta encuesta;

    private Context context;

    private int SCALE_FACTOR_IMAGE_VIEW = 4;

    public ItemEncuesta(View itemView)
    {
        super(itemView);

        rl_Item_Encuesta = itemView.findViewById(R.id.rl_item_encuesta);
        iv_Item_Image = itemView.findViewById(R.id.iv_item_image);
        tv_Nombre_Cliente = itemView.findViewById(R.id.tv_nombre_cliente);
        tv_Direccion_Cliente = itemView.findViewById(R.id.tv_direccion_cliente);
        tv_Telefono_Cliente = itemView.findViewById(R.id.tv_telefono_cliente);
        rl_Item_Encuesta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((FrmListaInformacion) context).agregar(encuesta);
            }
        });
    }

    public void setDatos(ClaseEncuesta encuesta, Context context)
    {
        this.encuesta = encuesta;
        this.context = context;
        try
        {
            Bitmap bitmap = escalarBitmap(encuesta.getImagen(), SCALE_FACTOR_IMAGE_VIEW);
            if(bitmap != null)
            {
                iv_Item_Image.setImageBitmap(bitmap);
            }
            else
            {
                iv_Item_Image.setImageResource(R.drawable.imagen);
            }
        }
        catch(Exception e)
        {
            iv_Item_Image.setImageResource(R.drawable.imagen);
        }
        tv_Nombre_Cliente.setText(encuesta.getNombre());
        tv_Direccion_Cliente.setText(encuesta.getDireccion());
        tv_Telefono_Cliente.setText(encuesta.getTelefono());
    }

    public Bitmap escalarBitmap(String uri, Integer factor) {
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = factor;
        bmOptions.inPurgeable = true;
        return rotarBitmap(uri, BitmapFactory.decodeFile(uri, bmOptions));
    }

    private Bitmap rotarBitmap(String Url, Bitmap bitmap) {
        try {
            ExifInterface exifInterface = new ExifInterface(Url);
            int orientacion = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, 1);
            Matrix matrix = new Matrix();

            if (orientacion == 6) {
                matrix.postRotate(90);
            } else if (orientacion == 3) {
                matrix.postRotate(180);
            } else if (orientacion == 8) {
                matrix.postRotate(270);
            }

            bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        } catch (Exception e) {

        }
        return bitmap;
    }
}
