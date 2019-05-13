package com.example.lic.Main.main;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lic.Main.DataAdapters.Delivered_Adapter;
import com.example.lic.Main.DataAdapters.Undelivered_Adapter;
import com.example.lic.Main.Datamodel.Delivered_Datamodel;
import com.example.lic.Main.Datamodel.Undelivered_Datamodel;
import com.example.lic.Main.Datamodel.User;
import com.example.lic.Main.Utilities.RetrofitClient;
import com.example.lic.Main.Utilities.SharedPreferenceManager;
import com.example.lic.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Online_Undelievered extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,SwipeRefreshLayout.OnRefreshListener {

    private Undelivered_Adapter undelivered_adapter;
    private List<Undelivered_Datamodel> undeliverdmodel;
    ProgressBar progressBarundelievered;
    String pan;
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayoutonlineundelievered;

    private TextView txtnavname,txtuserdays,textViewnodata;


    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online__undelievered);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recycleviewUndelivered);

        User user = SharedPreferenceManager.getmInstance(this).getUser();
        pan = user.getUserid();
        swipeRefreshLayoutonlineundelievered  = findViewById(R.id.swipetorefereshonlineundelievered);
        swipeRefreshLayoutonlineundelievered.setColorSchemeColors(R.color.bluelight);
        swipeRefreshLayoutonlineundelievered.setOnRefreshListener(this);


        getSupportActionBar().setTitle("Undelivered Items");

        progressBarundelievered = findViewById(R.id.progress_circularundelievered);
        progressBarundelievered.setVisibility(View.VISIBLE);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        callundelievereditems(pan);

        //call api to display

     }

    private void callundelievereditems(String pan) {

        Call<List<Undelivered_Datamodel>> call = RetrofitClient.getmInstance().getApi().getundeliveredmode(pan);
        call.enqueue(new Callback<List<Undelivered_Datamodel>>() {
            @Override
            public void onResponse(Call<List<Undelivered_Datamodel>> call, Response<List<Undelivered_Datamodel>> response) {
                undeliverdmodel = response.body();

                progressBarundelievered.setVisibility(View.GONE);
                if (undeliverdmodel != null){

                    undelivered_adapter = new Undelivered_Adapter(undeliverdmodel, getApplicationContext());
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    recyclerView.setAdapter(undelivered_adapter);
                    swipeRefreshLayoutonlineundelievered.setRefreshing(false);

                }

                else{
                    swipeRefreshLayoutonlineundelievered.setRefreshing(false);
                    recyclerView.setVisibility(View.GONE);
                }


            }

            @Override
            public void onFailure(Call<List<Undelivered_Datamodel>> call, Throwable t) {
                progressBarundelievered.setVisibility(View.GONE);
                swipeRefreshLayoutonlineundelievered.setRefreshing(false);

                Toast.makeText(Online_Undelievered.this, "Error"+ t, Toast.LENGTH_SHORT).show();

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
        txtnavname.setText(user.getUserid());
        txtuserdays.setText("Logispark Inventory Control");

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
        switch (id){

            case R.id.nav_dashboard:
                Intent intentz = new Intent(Online_Undelievered.this,MainActivity.class);
                startActivity(intentz);
                break;

            case R.id.nav_wholesale:
                Intent intent = new Intent(Online_Undelievered.this,Wholesale.class);
                startActivity(intent);
                break;

            case R.id.nav_Inventory:
                Intent intent1 = new Intent(Online_Undelievered.this,Inventory.class);
                startActivity(intent1);
                break;

            case R.id.nav_Report:
                Intent intent2 = new Intent(Online_Undelievered.this,Report.class);
                startActivity(intent2);
                break;

            case R.id.nav_Online:
                Intent intent3 = new Intent(Online_Undelievered.this, TransparentActivity.class);
                startActivity(intent3);
                break;

            case R.id.nav_Insights:
                Intent intent4 = new Intent(Online_Undelievered.this,Insights.class);
                startActivity(intent4);
                break;

//            case R.id.nav_Sales:
//                Intent intent5 = new Intent(Inventory.this,DailySales.class);
//                startActivity(intent5);
//                break;
//
//            case R.id.nav_Return:
//                Intent intent6 = new Intent(Inventory.this,Return.class);
//                startActivity(intent6);
//                break;


            case R.id.nav_Credit:
                Intent intent7 = new Intent(Online_Undelievered.this,Credit.class);
                startActivity(intent7);
                break;

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onRefresh() {

        callundelievereditems(pan);

    }
}
