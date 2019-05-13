package com.example.lic.Main.main;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.lic.Main.DataAdapters.Delivered_Adapter;
import com.example.lic.Main.Datamodel.Delivered_Datamodel;
import com.example.lic.Main.Datamodel.User;
import com.example.lic.Main.Utilities.RetrofitClient;
import com.example.lic.Main.Utilities.SharedPreferenceManager;
import com.example.lic.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Online_Delievered extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,SwipeRefreshLayout.OnRefreshListener {

    private Delivered_Adapter delivered_adapter;
    ProgressBar progressBar;
    SwipeRefreshLayout swipeRefreshLayoutonlinedelievered;
    String pan;
    private List<Delivered_Datamodel> deliverdmodel;
    RecyclerView recyclerView;


    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        progressBar = findViewById(R.id.progress_circularonline);
        progressBar.setVisibility(View.VISIBLE);
        getSupportActionBar().setTitle("Delivered Items");
        recyclerView = findViewById(R.id.recycleviewdelivered);

        swipeRefreshLayoutonlinedelievered = findViewById(R.id.swipetorefereshonlinedelievered);
        swipeRefreshLayoutonlinedelievered.setColorSchemeColors(R.color.bluelight);
        swipeRefreshLayoutonlinedelievered.setOnRefreshListener(this);

        User user = SharedPreferenceManager.getmInstance(this).getUser();
        pan = user.getUserid();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        final NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        //call api to display
        callapionlinedelievered(pan);



    }

    private void callapionlinedelievered(String pan) {

        Call<List<Delivered_Datamodel>> call = RetrofitClient.getmInstance().getApi().getdeliveredmode(pan);
        call.enqueue(new Callback<List<Delivered_Datamodel>>() {
            @Override
            public void onResponse(Call<List<Delivered_Datamodel>> call, Response<List<Delivered_Datamodel>> response) {
                progressBar.setVisibility(View.GONE);

                deliverdmodel = response.body();

                if (deliverdmodel != null){


                    delivered_adapter = new Delivered_Adapter(deliverdmodel, getApplicationContext());
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    recyclerView.setAdapter(delivered_adapter);
                    swipeRefreshLayoutonlinedelievered.setRefreshing(false);


                }

                else{
                    swipeRefreshLayoutonlinedelievered.setRefreshing(false);
                    recyclerView.setVisibility(View.GONE);
                }


            }

            @Override
            public void onFailure(Call<List<Delivered_Datamodel>> call, Throwable t) {

                progressBar.setVisibility(View.GONE);
                swipeRefreshLayoutonlinedelievered.setRefreshing(false);
                Toast.makeText(Online_Delievered.this, "Error"+ t, Toast.LENGTH_SHORT).show();

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
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id) {

            case R.id.nav_dashboard:
                Intent intentz = new Intent(Online_Delievered.this,MainActivity.class);
                startActivity(intentz);
                break;

            case R.id.nav_wholesale:
                Intent intent = new Intent(Online_Delievered.this, Wholesale.class);
                startActivity(intent);
                break;

            case R.id.nav_Inventory:
                Intent intent1 = new Intent(Online_Delievered.this, Inventory.class);
                startActivity(intent1);
                break;

            case R.id.nav_Report:
                Intent intent2 = new Intent(Online_Delievered.this, Report.class);
                startActivity(intent2);
                break;

            case R.id.nav_Online:
                Intent intent3 = new Intent(Online_Delievered.this, TransparentActivity.class);
                startActivity(intent3);
                break;

            case R.id.nav_Insights:
                Intent intent4 = new Intent(Online_Delievered.this, Insights.class);
                startActivity(intent4);
                break;

//            case R.id.nav_Sales:
//                Intent intent5 = new Intent(Online_Delievered.this, DailySales.class);
//                startActivity(intent5);
//                break;
//
//            case R.id.nav_Return:
//                Intent intent6 = new Intent(Online_Delievered.this, Return.class);
//                startActivity(intent6);
//                break;


            case R.id.nav_Credit:
                Intent intent7 = new Intent(Online_Delievered.this, Credit.class);
                startActivity(intent7);
                break;

        }
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }

    @Override
    public void onRefresh() {

        callapionlinedelievered(pan);

    }
}

