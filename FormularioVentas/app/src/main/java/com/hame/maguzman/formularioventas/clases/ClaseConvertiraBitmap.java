package com.hame.maguzman.formularioventas.clases;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;

/**
 * Created by magga on 01/10/2017.
 */

public class ClaseConvertiraBitmap
{
    private int factor = 4;

    public ClaseConvertiraBitmap()
    {

    }

    public Bitmap escalarBitmap(String uri)
    {
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
