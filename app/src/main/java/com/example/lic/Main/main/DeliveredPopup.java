package com.example.lic.Main.main;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.lic.Main.Datamodel.Credit_Datamodel;
import com.example.lic.Main.Datamodel.Delivered_Datamodel;
import com.example.lic.R;

import java.util.List;

import androidx.annotation.Nullable;

public class DeliveredPopup  extends Activity {

    Button buttonOk;


    private List<Delivered_Datamodel> delivered_datamodels;
    TextView sellingprice, color, size, delivery_date;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.popup_delivered);

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

        color = findViewById(R.id.deliveredColor);
        sellingprice = findViewById(R.id.deliveredSp);
        size = findViewById(R.id.deliveredSize);
        delivery_date= findViewById(R.id.deliveredDate);


        color.setText(getIntent().getExtras().getString("Color"));
        sellingprice.setText(getIntent().getExtras().getString("Sp"));
        size.setText(getIntent().getExtras().getString("Size"));
        delivery_date.setText(getIntent().getExtras().getString("Delivered Date"));



        //Api call



    }
}
