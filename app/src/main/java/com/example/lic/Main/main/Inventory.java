package com.example.lic.Main.main;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lic.Main.Datamodel.Inventory_Model;
import com.example.lic.Main.Datamodel.User;
import com.example.lic.Main.DataAdapters.Inventory_Adapter;
import com.example.lic.Main.Utilities.RetrofitClient;
import com.example.lic.Main.Utilities.SharedPreferenceManager;
import com.example.lic.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Inventory extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Inventory_Adapter inventory_adapter;
    private TextView txtnavname,txtuserdays,textViewnodata;
    String Pan;

    private List<Inventory_Model> list;
    RecyclerView recyclerView;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        progressDialog = new ProgressDialog(Inventory.this);
        progressDialog.setTitle("Fetching Data");
        progressDialog.setMessage("Please wait while we fetch the data");
        progressDialog.show();

        recyclerView = findViewById(R.id.recyclerview);
        textViewnodata = findViewById(R.id.inventory_nodata);

        User user = SharedPreferenceManager.getmInstance(this).getUser();
        Pan = String.valueOf(user.getUserid());





        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        Call<List<Inventory_Model>> call = RetrofitClient.getmInstance().getApi().getInventoryitems(Pan);

        call.enqueue(new Callback<List<Inventory_Model>>() {
            @Override
            public void onResponse(Call<List<Inventory_Model>> call, Response<List<Inventory_Model>> response) {

                list = response.body();
                if (list!=null){

                inventory_adapter = new Inventory_Adapter(list,getApplicationContext());
                recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
                recyclerView.setAdapter(inventory_adapter);
                progressDialog.dismiss();

                }

                else {
                    recyclerView.setVisibility(View.GONE);
                    textViewnodata.setVisibility(View.VISIBLE);

                }






            }

            @Override
            public void onFailure(Call<List<Inventory_Model>> call, Throwable t) {

                Toast.makeText(Inventory.this,"No Internet ",Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
                textViewnodata.setVisibility(View.VISIBLE);
                textViewnodata.setText("Please Connect To Internet And Try Again");



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
                Intent intentz = new Intent(Inventory.this,MainActivity.class);
                startActivity(intentz);
                break;

            case R.id.nav_wholesale:
                Intent intent = new Intent(Inventory.this,Wholesale.class);
                startActivity(intent);
                break;

            case R.id.nav_Inventory:
                Intent intent1 = new Intent(Inventory.this,Inventory.class);
                startActivity(intent1);
                break;

            case R.id.nav_Report:
                Intent intent2 = new Intent(Inventory.this,Report.class);
                startActivity(intent2);
                break;

            case R.id.nav_Online:
                Intent intent3 = new Intent(Inventory.this,Online.class);
                startActivity(intent3);
                break;

            case R.id.nav_Insights:
                Intent intent4 = new Intent(Inventory.this,Insights.class);
                startActivity(intent4);
                break;

            case R.id.nav_Sales:
                Intent intent5 = new Intent(Inventory.this,DailySales.class);
                startActivity(intent5);
                break;

            case R.id.nav_Return:
                Intent intent6 = new Intent(Inventory.this,Return.class);
                startActivity(intent6);
                break;


            case R.id.nav_Credit:
                Intent intent7 = new Intent(Inventory.this,Credit.class);
                startActivity(intent7);
                break;

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
