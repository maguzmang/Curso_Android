package com.hame.maguzman.tareados.activitys;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.hame.maguzman.tareados.R;
import com.hame.maguzman.tareados.adapters.ItemRecyclerAdapter;

public class RecyclerActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ItemRecyclerAdapter itemAdapter;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        context = this;
        inicializarComponentes();
    }

    public void inicializarComponentes()
    {
        recyclerView = (RecyclerView) findViewById(R.id.rv_item_list);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 3);
        recyclerView.setLayoutManager(gridLayoutManager);

        int [] imageResources = new int[]{ R.drawable.atm, R.drawable.bag, R.drawable.basket, R.drawable.box, R.drawable.briefcase, R.drawable.calculator};
        String [] labels = getResources().getStringArray(R.array.selected_options);

        itemAdapter = new ItemRecyclerAdapter(imageResources, labels, context);

        recyclerView.setAdapter(itemAdapter);
    }
}
