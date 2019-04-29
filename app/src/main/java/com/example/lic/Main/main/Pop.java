package com.example.lic.Main.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.lic.Main.DataAdapters.Credit_Adapter;
import com.example.lic.Main.Datamodel.Credit_Datamodel;
import com.example.lic.Main.Utilities.RetrofitClient;
import com.example.lic.R;

import org.w3c.dom.Text;

import java.util.List;

import androidx.annotation.Nullable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Pop extends Activity  {


    Button buttonOk;


    private List<Credit_Datamodel> creditmodel;
    TextView textViewadvance,sellingprice;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.popwindow);

        buttonOk = (Button) findViewById(R.id.buttonOk);

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

        getWindow().setLayout((int) (width * 0.8), (int) (height * 0.5));

        textViewadvance = findViewById(R.id.advance);
        sellingprice = findViewById(R.id.sp);

        textViewadvance.setText(getIntent().getExtras().getString("Advance"));
        sellingprice.setText(getIntent().getExtras().getString("Sp"));


        //Api call



    }


}
