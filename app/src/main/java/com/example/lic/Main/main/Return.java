package com.example.lic.Main.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.lic.R;

public class Return extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_return);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main,menu);
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
                Intent intentz = new Intent(Return.this,MainActivity.class);
                startActivity(intentz);
                break;
            case R.id.nav_wholesale:
                Intent intent = new Intent(Return.this, Wholesale.class);
                startActivity(intent);
                break;

            case R.id.nav_Inventory:
                Intent intent1 = new Intent(Return.this, Inventory.class);
                startActivity(intent1);
                break;

            case R.id.nav_Report:
                Intent intent2 = new Intent(Return.this, Report.class);
                startActivity(intent2);
                break;

            case R.id.nav_Online:
                Intent intent3 = new Intent(Return.this, TransparentActivity.class);
                startActivity(intent3);
                break;

            case R.id.nav_Insights:
                Intent intent4 = new Intent(Return.this, Insights.class);
                startActivity(intent4);
                break;
//
//            case R.id.nav_Sales:
//                Intent intent5 = new Intent(Return.this, DailySales.class);
//                startActivity(intent5);
//                break;
//
//            case R.id.nav_Return:
//                Intent intent6 = new Intent(Return.this, Return.class);
//                startActivity(intent6);
//                break;


            case R.id.nav_Credit:
                Intent intent7 = new Intent(Return.this, Credit.class);
                startActivity(intent7);
                break;

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
