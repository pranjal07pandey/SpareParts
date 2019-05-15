package com.example.lic.Main.main;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.lic.Main.Datamodel.Delivered_Datamodel;
import com.example.lic.Main.Datamodel.Undelivered_Datamodel;
import com.example.lic.R;

import java.util.List;

import androidx.annotation.Nullable;

public class UndeliveredPopup extends Activity {


    Button buttonOk;


    private List<Undelivered_Datamodel> undelivered_datamodels;
    TextView sellingprice, color, size, delivery_date;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.popup_undelivered);

        buttonOk = findViewById(R.id.buttonOk);

        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * 0.85), (int) (height * 0.35));

        color = findViewById(R.id.undeliveredColor);
        sellingprice = findViewById(R.id.undeliveredSp);
        size = findViewById(R.id.undeliveredSize);
        delivery_date = findViewById(R.id.undeliveredDate);


        color.setText(getIntent().getExtras().getString("Color"));
        sellingprice.setText(getIntent().getExtras().getString("Sp"));
        size.setText(getIntent().getExtras().getString("Size"));
        delivery_date.setText(getIntent().getExtras().getString("Delivered Date"));


    }
}
