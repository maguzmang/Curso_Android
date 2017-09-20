package com.hame.maguzman.tareados.clases;

import android.app.Service;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hame.maguzman.tareados.R;

/**
 * Created by maguzman on 12/09/2017.
 */

public class CuadroDialogo
{
    public CuadroDialogo(Context c, int i, ViewGroup v, int imagen, String nombre)
    {
        Toast toast = new Toast(c.getApplicationContext());

        LayoutInflater inflater = (LayoutInflater) c.getSystemService(Service.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(i, v);

        TextView txtMsg = layout.findViewById(R.id.txtMensaje);
        txtMsg.setText(nombre);

        ImageView ivImagen = layout.findViewById(R.id.iv_imagen);
        ivImagen.setImageResource(imagen);

        toast.setGravity(Gravity.CENTER|Gravity.BOTTOM, 0,0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }
}
