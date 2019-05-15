package com.example.lic.Main.main;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.lic.Main.DataAdapters.Credit_Adapter;
import com.example.lic.Main.DataAdapters.Inventory_Adapter;
import com.example.lic.Main.Datamodel.Credit_Datamodel;
import com.example.lic.Main.Datamodel.Inventory_Model;
import com.example.lic.Main.Utilities.RetrofitClient;
import com.example.lic.Main.Utilities.SharedPreferenceManager;
import com.example.lic.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivityCredit extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner spinnercredit;
    EditText searchingvalueetcredit;
    Button buttonsearchcredit;
    RecyclerView recyclerViewsearch;
    private Credit_Adapter creditAdapter;
    ProgressBar progressBar;
    String selected,pan,searchingclass,searchingvalue;
    List<Credit_Datamodel> creditDatamodelListe;
    RecyclerView recyclerViewsearcheddata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_credit);

        searchingvalueetcredit = findViewById(R.id.edittextcreditsearchdata);
        recyclerViewsearch = findViewById(R.id.recycleviewsearcheddatacredit);

        progressBar = findViewById(R.id.progressbarsearchcredit);
        progressBar.setVisibility(View.GONE);

        getSupportActionBar().setTitle("Search Credit Items");
        spinnercredit= findViewById(R.id.searchbycreditspinner);
        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_material);
        upArrow.setColorFilter(getResources().getColor(R.color.Titlehome), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        buttonsearchcredit = findViewById(R.id.searchbuttoncredit);

        pan = SharedPreferenceManager.getmInstance(this).getUser().getUserid();

        searchingclass = "credit";



        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Optionscredit, android.R.layout.simple_spinner_item);

//set the spinners adapter to the previously created one.
        spinnercredit.setAdapter(adapter);
        spinnercredit.setOnItemSelectedListener(this);

        buttonsearchcredit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                searchingvalue = searchingvalueetcredit.getText().toString();
//               Toast.makeText(SearchActivityInventory.this, searchingvalue, Toast.LENGTH_SHORT).show();
                callsearchingapi(pan,searchingvalue,selected,searchingclass);

            }
        });




    }

    private void callsearchingapi(String pan, String searchingvalue,String selected, String searchingclass) {


        Call<List<Credit_Datamodel>> call = RetrofitClient.getmInstance().getApi().getsearchcredit(pan,searchingvalue,selected,searchingclass);
        call.enqueue(new Callback<List<Credit_Datamodel>>() {
            @Override
            public void onResponse(Call<List<Credit_Datamodel>> call, Response<List<Credit_Datamodel>> response) {
                creditDatamodelListe = response.body();
                progressBar.setVisibility(View.GONE);

                if (response.body()!=null){
                    creditAdapter = new Credit_Adapter(creditDatamodelListe,getApplicationContext());
                    recyclerViewsearch.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    recyclerViewsearch.setAdapter(creditAdapter);




                }

                else {

                    Toast.makeText(SearchActivityCredit.this, "No data "+response.body(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List<Credit_Datamodel>> call, Throwable t) {

                progressBar.setVisibility(View.GONE);
                Toast.makeText(SearchActivityCredit.this, "Please Connect To Internet And Try Again", Toast.LENGTH_SHORT).show();


            }
        });


    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch (position) {
            case 0:

                selected  = "name";

                // Whatever you want to happen when the first item gets selected
                break;
            case 1:


                selected  = "phone_no";

                // Whatever you want to happen when the second item gets selected
                break;


        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

        selected = parent.getItemAtPosition(0).toString();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
