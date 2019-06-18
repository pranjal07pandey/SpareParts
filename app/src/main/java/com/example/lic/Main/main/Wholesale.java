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
import android.support.v7.widget.GridLayoutManager;
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

import com.example.lic.Main.DataAdapters.Wholesale_Adapter;
import com.example.lic.Main.Datamodel.User;
import com.example.lic.Main.Datamodel.Wholesale_Datamodel;
import com.example.lic.Main.Utilities.RetrofitClient;
import com.example.lic.Main.Utilities.SharedPreferenceManager;
import com.example.lic.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Wholesale extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,SwipeRefreshLayout.OnRefreshListener{

    Button buttonYes;
    ImageButton buttonClose;
    Dialog mydialog;

    TextView txtnavname,txtuserdays;

    SwipeRefreshLayout swipeRefreshLayout;

    private Wholesale_Adapter wholesaleadapeter;
    ProgressBar progressBar;
    private List<Wholesale_Datamodel> wholesalemodel;
    TextView textViewnodata;
    private String Pan;
    RecyclerView recyclerView;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_wholesale);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        progressBar = findViewById(R.id.progress_circular);
        progressBar.setVisibility(View.VISIBLE);


        mydialog = new Dialog(Wholesale.this);
        mydialog.setContentView(R.layout.logout_popup);
        buttonYes= mydialog.findViewById(R.id.buttonYes);
        buttonClose = mydialog.findViewById(R.id.buttonClose);

        recyclerView = findViewById(R.id.recycleviewwholesale);
        swipeRefreshLayout = findViewById(R.id.swipetorefereshwholesale);

        swipeRefreshLayout.setColorSchemeColors(R.color.bluelight);
        swipeRefreshLayout.setOnRefreshListener(this);

        textViewnodata = findViewById(R.id.nodatwholesale);
        textViewnodata.setVisibility(View.GONE);


        User user = SharedPreferenceManager.getmInstance(getApplicationContext()).getUser();
        Pan  = user.getUserid();



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //API call


        callapi(Pan);



    }

    private void callapi(String pan) {

        Call<List<Wholesale_Datamodel>> call = RetrofitClient.getmInstance().getApi().getwholesalemode(Pan);
        call.enqueue(new Callback<List<Wholesale_Datamodel>>() {
            @Override
            public void onResponse(Call<List<Wholesale_Datamodel>> call, Response<List<Wholesale_Datamodel>> response) {
                wholesalemodel = response.body();
                if (wholesalemodel!=null){

                    wholesaleadapeter = new Wholesale_Adapter(wholesalemodel,getApplicationContext());
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    recyclerView.setAdapter(wholesaleadapeter);
                    swipeRefreshLayout.setRefreshing(false);
                    progressBar.setVisibility(View.GONE);


                }

                else {
                    recyclerView.setVisibility(View.GONE);
                    textViewnodata.setVisibility(View.VISIBLE);
                    swipeRefreshLayout.setRefreshing(false);
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(Wholesale.this, "No data found", Toast.LENGTH_SHORT).show();



                }
            }

            @Override
            public void onFailure(Call<List<Wholesale_Datamodel>> call, Throwable t) {


                swipeRefreshLayout.setRefreshing(false);
                progressBar.setVisibility(View.GONE);
                textViewnodata.setText("Please Connect To Internet And Try Again");
                textViewnodata.setVisibility(View.VISIBLE);
                Toast.makeText(Wholesale.this,"Please Check Your Internet Connection and Try Again",Toast.LENGTH_LONG).show();

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
            Intent intent = new Intent(Wholesale.this,SearchActivityWholesale.class);
            startActivity(intent);
        }
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            mydialog.show();

            buttonYes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferenceManager.getmInstance(getApplicationContext()).clear();
                    Intent t= new Intent(Wholesale.this,Login.class);
                    Toast.makeText(Wholesale.this,"Logout Sucessful",Toast.LENGTH_SHORT).show();
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


        switch (id) {

            case R.id.nav_dashboard:
                Intent intentz = new Intent(Wholesale.this,MainActivity.class);
                startActivity(intentz);
                break;

            case R.id.nav_wholesale:
                Intent intent = new Intent(Wholesale.this, Wholesale.class);
                startActivity(intent);
                break;

            case R.id.nav_Inventory:
                Intent intent1 = new Intent(Wholesale.this, Inventory.class);
                startActivity(intent1);
                break;

            case R.id.nav_Report:
                Intent intent2 = new Intent(Wholesale.this, Report.class);
                startActivity(intent2);
                break;

//            case R.id.nav_Online:
//                Intent intent3 = new Intent(Wholesale.this, TransparentActivity.class);
//                startActivity(intent3);
//                break;

            case R.id.nav_Insights:
                Intent intent4 = new Intent(Wholesale.this, Insights.class);
                startActivity(intent4);
                break;

//            case R.id.nav_Sales:
//                Intent intent5 = new Intent(Wholesale.this, DailySales.class);
//                startActivity(intent5);
//                break;
//
//            case R.id.nav_Return:
//                Intent intent6 = new Intent(Wholesale.this, Return.class);
//                startActivity(intent6);
//                break;


            case R.id.nav_Credit:
                Intent intent7 = new Intent(Wholesale.this, Credit.class);
                startActivity(intent7);
                break;

        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    @Override
    public void onRefresh() {
        callapi(Pan);

    }
}
