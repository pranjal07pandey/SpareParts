package com.example.lic.Main.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.lic.R;

import androidx.annotation.Nullable;

public class OnlinePopup extends Activity {

    Button delivered, undelivered, exit;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.online_popup);

        delivered = findViewById(R.id.buttonOk1);

        delivered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openActivity2();

            }
        });



        undelivered = findViewById(R.id.buttonOk2);

        undelivered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });

//        exit = findViewById(R.id.buttonOk3);
//        exit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });


        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * 0.8), (int) (height * 0.5));

    }

    public void openActivity2(){
        Intent intent = new Intent(this, Online_Delievered.class);
        startActivity(intent);

    }
}
