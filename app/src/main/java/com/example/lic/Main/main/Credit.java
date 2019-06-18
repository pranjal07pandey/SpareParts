package com.example.lic.Main.main;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lic.Main.DataAdapters.Credit_Adapter;
import com.example.lic.Main.Datamodel.Credit_Datamodel;
import com.example.lic.Main.Datamodel.User;
import com.example.lic.Main.Utilities.RetrofitClient;
import com.example.lic.Main.Utilities.SharedPreferenceManager;
import com.example.lic.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Credit extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,SwipeRefreshLayout.OnRefreshListener {

    private Credit_Adapter creditadapter;
    ProgressBar progressBarcredit;
    SwipeRefreshLayout swipeRefreshLayoutcredit;
    TextView textViewnodata,txtnavname,txtuserdays;
    String pan;
    private List<Credit_Datamodel> creditmodel;
    RecyclerView recyclerView;

    Button buttonYes;
    ImageButton buttonClose;
    Dialog mydialog;


    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        User user = SharedPreferenceManager.getmInstance(this).getUser();
        pan = user.getUserid();

        recyclerView = findViewById(R.id.recycleviewcredit);
        textViewnodata = findViewById(R.id.nodatacredit);
        textViewnodata.setVisibility(View.GONE);

        progressBarcredit = findViewById(R.id.pbarcredit);
        progressBarcredit.setVisibility(View.VISIBLE);

        swipeRefreshLayoutcredit = findViewById(R.id.swipetorefereshcredit);
        swipeRefreshLayoutcredit.setColorSchemeColors(R.color.bluelight);
        swipeRefreshLayoutcredit.setOnRefreshListener(this);



        mydialog = new Dialog(Credit.this);
        mydialog.setContentView(R.layout.logout_popup);
        buttonYes= mydialog.findViewById(R.id.buttonYes);
        buttonClose = mydialog.findViewById(R.id.buttonClose);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        //Api call

        callapicredit(pan);




    }

    private void callapicredit(String pan) {
        Call<List<Credit_Datamodel>> call = RetrofitClient.getmInstance().getApi().getcreditmode(pan);
        call.enqueue(new Callback<List<Credit_Datamodel>>() {
            @Override
            public void onResponse(Call<List<Credit_Datamodel>> call, Response<List<Credit_Datamodel>> response) {

                progressBarcredit.setVisibility(View.GONE);
                creditmodel = response.body();
                if (creditmodel!=null){

                    creditadapter = new Credit_Adapter(creditmodel,getApplicationContext());
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    recyclerView.setAdapter(creditadapter);
                    swipeRefreshLayoutcredit.setRefreshing(false);


                }
                    else {

                        swipeRefreshLayoutcredit.setRefreshing(false);
                        progressBarcredit.setVisibility(View.GONE);
                        textViewnodata.setVisibility(View.VISIBLE);

                    Toast.makeText(Credit.this, "No data Found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Credit_Datamodel>> call, Throwable t) {
                progressBarcredit.setVisibility(View.GONE);
                swipeRefreshLayoutcredit.setRefreshing(false);
                Toast.makeText(getApplicationContext(),"Please Connect To Internet And Try Again",Toast.LENGTH_LONG).show();

            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        txtnavname = findViewById(R.id.txtcompanyname);
        txtuserdays = findViewById(R.id.textremainingdays);
        User user = SharedPreferenceManager.getmInstance(this).getUser();
        txtnavname.setText(user.getCname());
        txtuserdays.setText("Logispark Inventory Control");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();

        if (id == R.id.action_search){
            Intent intent = new Intent(Credit.this,SearchActivityCredit.class);
            startActivity(intent);
        }
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            mydialog.show();

            buttonYes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferenceManager.getmInstance(getApplicationContext()).clear();
                    Intent t= new Intent(Credit.this,Login.class);
                    Toast.makeText(Credit.this,"Logout Sucessful",Toast.LENGTH_SHORT).show();
                    startActivity(t);

                }
            });

            buttonClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mydialog.dismiss();
                }
            });




            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id){

            case R.id.nav_dashboard:
                Intent intentz = new Intent(Credit.this,MainActivity.class);
                startActivity(intentz);
                break;


            case R.id.nav_wholesale:
                Intent intent = new Intent(Credit.this,Wholesale.class);
                startActivity(intent);
                break;

            case R.id.nav_Inventory:
                Intent intent1 = new Intent(Credit.this,Inventory.class);
                startActivity(intent1);
                break;

            case R.id.nav_Report:
                Intent intent2 = new Intent(Credit.this,Report.class);
                startActivity(intent2);
                break;
//
//            case R.id.nav_Online:
//                Intent intent3 = new Intent(Credit.this, TransparentActivity.class);
//                startActivity(intent3);
//                break;

            case R.id.nav_Insights:
                Intent intent4 = new Intent(Credit.this,Insights.class);
                startActivity(intent4);
                break;
//
//            case R.id.nav_Sales:
//                Intent intent5 = new Intent(Credit.this,DailySales.class);
//                startActivity(intent5);
//                break;
//
//            case R.id.nav_Return:
//                Intent intent6 = new Intent(Credit.this,Return.class);
//                startActivity(intent6);
//                break;


            case R.id.nav_Credit:
                Intent intent7 = new Intent(Credit.this,Credit.class);
                startActivity(intent7);
                break;

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onRefresh() {

        callapicredit(pan);

    }
}
