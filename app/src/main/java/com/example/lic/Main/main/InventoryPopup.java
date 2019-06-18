package com.example.lic.Main.main;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.lic.Main.Datamodel.Delivered_Datamodel;
import com.example.lic.Main.Datamodel.Inventory_Model;
import com.example.lic.R;

import java.util.List;

import androidx.annotation.Nullable;

public class InventoryPopup extends Activity {

    Button buttonOk;


    private List<Inventory_Model> delivered_datamodels;
    TextView  wholesale;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.popup_inventory);

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

        getWindow().setLayout((int) (width * 0.65), (int) (height * 0.18));


        wholesale = findViewById(R.id.inventoryWholesale);

        wholesale.setText(getIntent().getExtras().getString("wholesaler"));




    }
}
