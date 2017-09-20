package com.hame.maguzman.tareados.activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.hame.maguzman.tareados.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void cambiarPantalla(View view)
    {
        startActivity(new Intent(MainActivity.this, ActivityText.class));
    }

    public void cambiarPantallaImg(View view)
    {
        startActivity(new Intent(MainActivity.this, ImageOptActivity.class));
    }

    public void cambiarPantallaSpinner(View view)
    {
        startActivity(new Intent(MainActivity.this, SelectedImageActivity.class));
    }

    public void cambiarPantallaListView(View view)
    {
        startActivity(new Intent(MainActivity.this, ListViewImageActivity.class));
    }

    public void cambiarPantallaRecyclerView(View view)
    {
        startActivity(new Intent(MainActivity.this, RecyclerActivity.class));
    }

    public void cambiarPantallaRecyclerTareaView(View view)
    {
        startActivity(new Intent(MainActivity.this, RecyclerTareaActivity.class));
    }
}
