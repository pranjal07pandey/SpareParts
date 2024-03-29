package com.example.lic.Main.main;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lic.Main.Datamodel.User;
import com.example.lic.Main.Fragments.sixmonthsreportfragment;
import com.example.lic.Main.Utilities.SharedPreferenceManager;
import com.example.lic.R;
import com.example.lic.Main.Fragments.twomonthsreportfragment;

public class Insights extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    Button buttonYes;
    ImageButton buttonClose;
    Dialog mydialog;
    TextView txtnavname,txtuserdays;

    Fragment fragment;
    public static Bundle myBundle = new Bundle();
    private String startdate,enddate;
    Button twomonthsbutton,sixmonthbutton;
    FragmentTransaction fragmentTransaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insights);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        mydialog = new Dialog(Insights.this);
        mydialog.setContentView(R.layout.logout_popup);
        buttonYes= mydialog.findViewById(R.id.buttonYes);
        buttonClose = mydialog.findViewById(R.id.buttonClose);

        sixmonthbutton = findViewById(R.id.twomonthsbutton);
        twomonthsbutton = findViewById(R.id.sixmonthsbutton);

        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmentcontainer,new twomonthsreportfragment());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();


        sixmonthbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Changefragment(v);
            }
        });

        twomonthsbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Changefragment(v);
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
        finish();
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
                    Intent t= new Intent(Insights.this,Login.class);
                    Toast.makeText(Insights.this,"Logout Sucessful",Toast.LENGTH_SHORT).show();
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

    public void Changefragment(View view){

        if (view == findViewById(R.id.twomonthsbutton)){
            fragment = new twomonthsreportfragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragmentcontainer,fragment);
            fragmentTransaction.commit();


        }

        if (view == findViewById(R.id.sixmonthsbutton)){

            fragment = new sixmonthsreportfragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();

            fragmentTransaction.replace(R.id.fragmentcontainer,fragment);
            fragmentTransaction.commit();



        }



    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        switch (id){
            case R.id.nav_dashboard:
                Intent intentz = new Intent(Insights.this,MainActivity.class);
                startActivity(intentz);
                break;
            case R.id.nav_wholesale:
                Intent intent = new Intent(Insights.this,Wholesale.class);
                startActivity(intent);
                break;

            case R.id.nav_Inventory:
                Intent intent1 = new Intent(Insights.this,Inventory.class);
                startActivity(intent1);
                break;

            case R.id.nav_Report:
                Intent intent2 = new Intent(Insights.this,Report.class);
                startActivity(intent2);
                break;

//            case R.id.nav_Online:
//                Intent intent3 = new Intent(Insights.this, TransparentActivity.class);
//                startActivity(intent3);
//                break;

            case R.id.nav_Insights:
                Intent intent4 = new Intent(Insights.this,Insights.class);
                startActivity(intent4);
                break;

//            case R.id.nav_Sales:
//                Intent intent5 = new Intent(Insights.this,DailySales.class);
//                startActivity(intent5);
//                break;
//
//            case R.id.nav_Return:
//                Intent intent6 = new Intent(Insights.this,Return.class);
//                startActivity(intent6);
//                break;


            case R.id.nav_Credit:
                Intent intent7 = new Intent(Insights.this,Credit.class);
                startActivity(intent7);
                break;

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
