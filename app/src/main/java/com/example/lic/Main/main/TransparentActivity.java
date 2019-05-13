package com.example.lic.Main.main;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.lic.R;

public class TransparentActivity  extends AppCompatActivity {



    Dialog mydialog;
    Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transparentactivity);

        RelativeLayout relativeLayout = findViewById(R.id.transparentActivity);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
//                Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_SHORT).show();
            }
        });


        mydialog = new Dialog(TransparentActivity.this);
        mydialog.setContentView(R.layout.online_popup);
        mydialog.show();

        ImageButton b1,b2,b3;

        b1 = mydialog.findViewById(R.id.buttonOk1);
        b2 = mydialog.findViewById(R.id.buttonOk2);
        b3 = mydialog.findViewById(R.id.buttonOk3);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity1();


            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openActivity2();


            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    public void openActivity1(){
        Intent intent = new Intent(this, Online_Delievered.class);
        startActivity(intent);


    }

    public void openActivity2(){

        Intent intent = new Intent(this, Online_Undelievered.class);
        startActivity(intent);

    }
}
