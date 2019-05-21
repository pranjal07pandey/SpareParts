package com.example.lic.Main.main;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.lic.Main.Datamodel.Insightsdatamodel;
import com.example.lic.Main.Utilities.RetrofitClient;
import com.example.lic.Main.Utilities.SharedPreferenceManager;
import com.example.lic.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransparentActivityforinsights extends AppCompatActivity {
    ProgressDialog progressDialog;
    String pan;
    List<Insightsdatamodel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transparent_activityforinsights);
        pan = SharedPreferenceManager.getmInstance(this).getUser().getUserid();


        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Checking Required Data");
        progressDialog.setMessage("Please check while we validate the data");
        progressDialog.show();


        Call<List<Insightsdatamodel>> calldata = RetrofitClient.getmInstance().getApi().getinsightsdata(pan);
        calldata.enqueue(new Callback<List<Insightsdatamodel>>() {
            @Override
            public void onResponse(Call<List<Insightsdatamodel>> call, Response<List<Insightsdatamodel>> response) {
                list = response.body();
                Toast.makeText(TransparentActivityforinsights.this, String.valueOf(list.size())+response.body(), Toast.LENGTH_SHORT).show();

//                if (response.body()!=null){
//
//
//                    progressDialog.dismiss();
//                    Toast.makeText(TransparentActivityforinsights.this, "No sufficient data", Toast.LENGTH_SHORT).show();
//
//
//                }
//
//                else {
//
//                    progressDialog.dismiss();
//                    Toast.makeText(TransparentActivityforinsights.this, "Data Verified", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(TransparentActivityforinsights.this,Insights.class);
//                    startActivity(intent);
//                }
            }

            @Override
            public void onFailure(Call<List<Insightsdatamodel>> call, Throwable t) {

                Toast.makeText(TransparentActivityforinsights.this, "No Internet Connection", Toast.LENGTH_SHORT).show();

            }
        });
    }





}
