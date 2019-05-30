package com.example.lic.Main.main;

import android.app.DatePickerDialog;
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
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lic.Main.Datamodel.User;
import com.example.lic.Main.Fragments.Import_Report_Fragment;
import com.example.lic.Main.Fragments.InventoryReportFrag;
import com.example.lic.Main.Fragments.Profitreportfragment;
import com.example.lic.Main.Fragments.twomonthsreportfragment;
import com.example.lic.Main.Utilities.SharedPreferenceManager;
import com.example.lic.R;
import com.example.lic.Main.Fragments.SalesReportFragment;

import java.util.Calendar;

public class Report extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Button buttonYes;
    ImageButton buttonClose;
    Dialog mydialog;

    TextView txtnavname,txtuserdays;
    Button button,button2,button3,button4,buttonstartdate,buttonenddate;
    LinearLayout linerlayout;
    FragmentTransaction fragmentTransaction;

    public static Bundle myBundle = new Bundle();
    String startdate,enddate;
    String startbuttonvalue1,endbuttovalue2;
    Fragment fragment;
    Calendar calendar,calendar2;
    int year,year2;
    int month,month2;
    int i = 0;
    int day,day2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_report);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        button = findViewById(R.id.inventorybutton);
        buttonenddate = findViewById(R.id.enddate);
        linerlayout = findViewById(R.id.linearlayoutreport);
        buttonstartdate = findViewById(R.id.startdate);
        button2 = findViewById(R.id.salesbutton);
        button3 = findViewById(R.id.importbutton);
        button4 = findViewById(R.id.profitbutton);
        startbuttonvalue1 = buttonstartdate.getText().toString();
        endbuttovalue2 = buttonenddate.getText().toString();
        linerlayout.setVisibility(View.GONE);


        mydialog = new Dialog(Report.this);
        mydialog.setContentView(R.layout.logout_popup);
        buttonYes= mydialog.findViewById(R.id.buttonYes);
        buttonClose = mydialog.findViewById(R.id.buttonClose);

        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmentplace,new Profitreportfragment());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

        Toast.makeText(this, "Please Select Date To Get Started", Toast.LENGTH_LONG).show();


        buttonstartdate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();
               year = calendar.get(Calendar.YEAR);
               month = calendar.get(Calendar.MONTH);
               day = calendar.get(Calendar.DAY_OF_MONTH);

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {


                    DatePickerDialog datePickerDialog = new DatePickerDialog(Report.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            startdate = year+"-"+(month+1)+"-"+dayOfMonth;

                          buttonstartdate.setText(startdate);
                          i = i+1;
                          if (i >= 2){
                              checkvisibility();
                          }


                            myBundle.putString("Start",String.valueOf(startdate));




                        }
                    },year,month,day);
                     datePickerDialog.show();

                }

            }

        });


        buttonenddate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                calendar2 = Calendar.getInstance();
                year2 = calendar2.get(Calendar.YEAR);
                month2 = calendar2.get(Calendar.MONTH);
                day2 = calendar2.get(Calendar.DAY_OF_MONTH);


                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {


                    DatePickerDialog datePickerDialog = new DatePickerDialog(Report.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            enddate =   year+"-"+(month+1)+"-"+dayOfMonth;
                            buttonenddate.setText(enddate);
                            i = i +1 ;
                            if (i>=2) {
                                checkvisibility();
                            }

                        }
                    },year,month,day2);
                    datePickerDialog.show();


                }




            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Changefragment(v);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Changefragment(v);

            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Changefragment(v);
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Changefragment(v);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void checkvisibility() {


        if (!(startdate.equals(null)&& startdate.equals("START DATE"))) {

            if (!(enddate.equals(null)&& enddate.equals("END DATE"))) {


                linerlayout.setVisibility(View.VISIBLE);

            }
        }

    }


    public void Changefragment(View view){


        if (view == findViewById(R.id.profitbutton)){
            fragment = new Profitreportfragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            myBundle.putString("End",String.valueOf(enddate));
            myBundle.putString("Start",String.valueOf(startdate));
            fragment.setArguments(myBundle);
            fragmentTransaction.replace(R.id.fragmentplace,fragment);
            fragmentTransaction.commit();


        }

        if (view == findViewById(R.id.salesbutton)){

            fragment = new SalesReportFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            myBundle.putString("End",String.valueOf(enddate));
            myBundle.putString("Start",String.valueOf(startdate));
            fragment.setArguments(myBundle);
            fragmentTransaction.replace(R.id.fragmentplace,fragment);
            fragmentTransaction.commit();



        }

        if (view == findViewById(R.id.importbutton)){

            fragment = new Import_Report_Fragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            myBundle.putString("End",String.valueOf(enddate));
            myBundle.putString("Start",String.valueOf(startdate));
            fragment.setArguments(myBundle);
            fragmentTransaction.replace(R.id.fragmentplace,fragment);
            fragmentTransaction.commit();



        }

        if (view == findViewById(R.id.inventorybutton)){

            fragment = new InventoryReportFrag();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            myBundle.putString("End",String.valueOf(enddate));
            myBundle.putString("Start",String.valueOf(startdate));
            fragment.setArguments(myBundle);
            fragmentTransaction.replace(R.id.fragmentplace,fragment);
             fragmentTransaction.commit();




        }

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
                    Intent t= new Intent(Report.this,Login.class);
                    Toast.makeText(Report.this,"Logout Sucessful",Toast.LENGTH_SHORT).show();
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
                Intent intentz = new Intent(Report.this,MainActivity.class);
                startActivity(intentz);
                break;
            case R.id.nav_wholesale:
                Intent intent = new Intent(Report.this, Wholesale.class);
                startActivity(intent);
                break;

            case R.id.nav_Inventory:
                Intent intent1 = new Intent(Report.this, Inventory.class);
                startActivity(intent1);
                break;

            case R.id.nav_Report:
                Intent intent2 = new Intent(Report.this, Report.class);
                startActivity(intent2);
                break;

            case R.id.nav_Online:
                Intent intent3 = new Intent(Report.this, TransparentActivity.class);
                startActivity(intent3);
                break;

            case R.id.nav_Insights:
                Intent intent4 = new Intent(Report.this, Insights.class);
                startActivity(intent4);
                break;

//            case R.id.nav_Sales:
//                Intent intent5 = new Intent(Report.this, DailySales.class);
//                startActivity(intent5);
//                break;
//
//            case R.id.nav_Return:
//                Intent intent6 = new Intent(Report.this, Return.class);
//                startActivity(intent6);
//                break;


            case R.id.nav_Credit:
                Intent intent7 = new Intent(Report.this, Credit.class);
                startActivity(intent7);
                break;

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
