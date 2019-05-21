package com.example.lic.Main.main;

import android.app.Dialog;
import android.content.Context;
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
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lic.Main.Datamodel.Dashboard;
import com.example.lic.Main.DataAdapters.Dashboard_Adapter;
import com.example.lic.Main.Datamodel.User;
import com.example.lic.Main.Utilities.SharedPreferenceManager;
import com.example.lic.R;

import java.util.ArrayList;
import java.util.List;

import static com.example.lic.R.layout.logout_popup;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    List<Dashboard> dashboardList;
    Button buttonYes;
    ImageButton buttonClose;
    Dialog mydialog;

    private TextView txtnavname,txtuserdays;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        mydialog = new Dialog(MainActivity.this);
        mydialog.setContentView(R.layout.logout_popup);
        buttonYes= mydialog.findViewById(R.id.buttonYes);
        buttonClose = mydialog.findViewById(R.id.buttonClose);

        dashboardList = new ArrayList<>();
        dashboardList.add(new Dashboard("Wholesale",R.drawable.plus));
        dashboardList.add(new Dashboard("Inventory",R.drawable.inventory));
        dashboardList.add(new Dashboard("Report",R.drawable.doc));
        dashboardList.add(new Dashboard("Online",R.drawable.globe));
        dashboardList.add(new Dashboard("Insights",R.drawable.graph));
//        dashboardList.add(new Dashboard("Daily Sales",R.drawable.cash));
//        dashboardList.add(new Dashboard("Return",R.drawable.ret));
        dashboardList.add(new Dashboard("Credit",R.drawable.credit));

        RecyclerView myhrv = (RecyclerView) findViewById(R.id.recyclerviewhome);
        Dashboard_Adapter myhAdapter = new Dashboard_Adapter(this,dashboardList);
        myhrv.setLayoutManager(new GridLayoutManager(this,2));
        myhrv.setAdapter(myhAdapter);




        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



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
    protected void onStart() {
        super.onStart();

        if (!SharedPreferenceManager.getmInstance(this).isLoggedIn()){

            Intent intent = new Intent(this, Login.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.


        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        menuItem.setVisible(false);
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


        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            mydialog.show();

            buttonYes.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            SharedPreferenceManager.getmInstance(getApplicationContext()).clear();
                    Intent t= new Intent(MainActivity.this,Login.class);
                    Toast.makeText(MainActivity.this,"Logout Sucessful",Toast.LENGTH_SHORT).show();
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
                Intent intentz = new Intent(MainActivity.this,MainActivity.class);
                startActivity(intentz);
                break;

            case R.id.nav_wholesale:
                Intent intent = new Intent(MainActivity.this,Wholesale.class);
                startActivity(intent);
                break;

            case R.id.nav_Inventory:
                Intent intent1 = new Intent(MainActivity.this,Inventory.class);
                startActivity(intent1);
                break;

            case R.id.nav_Report:
                Intent intent2 = new Intent(MainActivity.this,Report.class);
                startActivity(intent2);
                break;

            case R.id.nav_Online:
                Intent intent3 = new Intent(MainActivity.this, TransparentActivity.class);
                startActivity(intent3);
                break;

            case R.id.nav_Insights:
                Intent intent4 = new Intent(MainActivity.this,Insights.class);
                startActivity(intent4);
                break;
//
//            case R.id.nav_Sales:
//                Intent intent5 = new Intent(MainActivity.this,DailySales.class);
//                startActivity(intent5);
//                break;
//
//            case R.id.nav_Return:
//                Intent intent6 = new Intent(MainActivity.this,Return.class);
//                startActivity(intent6);
//                break;


            case R.id.nav_Credit:
                Intent intent7 = new Intent(MainActivity.this,Credit.class);
                startActivity(intent7);
                break;

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
