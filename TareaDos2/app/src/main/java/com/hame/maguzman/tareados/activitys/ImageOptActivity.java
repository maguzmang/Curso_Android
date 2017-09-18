package com.hame.maguzman.tareados.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.hame.maguzman.tareados.R;

public class ImageOptActivity extends AppCompatActivity
{
    private CheckBox cbxAtm;
    private CheckBox cbxBag;
    private CheckBox cbxBasket;
    private CheckBox cbxBox;
    private CheckBox cbxBriefcase;
    private CheckBox cbxCalculator;

    private ImageView imgAtm;
    private ImageView imgBag;
    private ImageView imgBasket;
    private ImageView imgBox;
    private ImageView imgBriefcase;
    private ImageView imgCalculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_opt);

        inicializarComponentes();
    }

    public void inicializarComponentes()
    {
        cbxAtm = (CheckBox) findViewById(R.id.cbx_atm);
        cbxAtm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked)
                {
                    imgAtm.setVisibility(View.VISIBLE);
                }
                else
                {
                    imgAtm.setVisibility(View.INVISIBLE);
                }
            }
        });

        cbxBag = (CheckBox) findViewById(R.id.cbx_bag);
        cbxBag.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked)
                {
                    imgBag.setVisibility(View.VISIBLE);
                }
                else
                {
                    imgBag.setVisibility(View.INVISIBLE);
                }
            }
        });

        cbxBasket = (CheckBox) findViewById(R.id.cbx_basket);
        cbxBasket.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked)
                {
                    imgBasket.setVisibility(View.VISIBLE);
                }
                else
                {
                    imgBasket.setVisibility(View.INVISIBLE);
                }
            }
        });

        cbxBox = (CheckBox) findViewById(R.id.cbx_box);
        cbxBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked)
                {
                    imgBox.setVisibility(View.VISIBLE);
                }
                else
                {
                    imgBox.setVisibility(View.INVISIBLE);
                }
            }
        });

        cbxBriefcase = (CheckBox) findViewById(R.id.cbx_briefcase);
        cbxBriefcase.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked)
                {
                    imgBriefcase.setVisibility(View.VISIBLE);
                }
                else
                {
                    imgBriefcase.setVisibility(View.INVISIBLE);
                }
            }
        });

        cbxCalculator = (CheckBox) findViewById(R.id.cbx_calculator);
        cbxCalculator.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked)
                {
                    imgCalculator.setVisibility(View.VISIBLE);
                }
                else
                {
                    imgCalculator.setVisibility(View.INVISIBLE);
                }
            }
        });

        imgAtm = (ImageView) findViewById(R.id.iv_atm);
        imgBag = (ImageView) findViewById(R.id.iv_bag);
        imgBasket = (ImageView) findViewById(R.id.iv_basket);
        imgBox = (ImageView) findViewById(R.id.iv_box);
        imgBriefcase = (ImageView) findViewById(R.id.iv_briefcase);
        imgCalculator = (ImageView) findViewById(R.id.iv_calculator);
    }
}
