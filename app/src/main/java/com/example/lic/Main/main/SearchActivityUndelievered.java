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

import com.example.lic.Main.DataAdapters.Delivered_Adapter;
import com.example.lic.Main.DataAdapters.Undelivered_Adapter;
import com.example.lic.Main.Datamodel.Delivered_Datamodel;
import com.example.lic.Main.Datamodel.Undelivered_Datamodel;
import com.example.lic.Main.Utilities.RetrofitClient;
import com.example.lic.Main.Utilities.SharedPreferenceManager;
import com.example.lic.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivityUndelievered extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner spinnerundelievered;
    ProgressBar progressBar;
    EditText searchingvalueetundelievered;
    Button buttonsearchundelievered;
    RecyclerView recyclerViewsearch;
    private Undelivered_Adapter undelivered_adapter;
    String selected,pan,searchingclass,searchingvalue;
    List<Undelivered_Datamodel> undelivered_datamodels;
    RecyclerView recyclerViewsearcheddata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_undelievered);


        searchingvalueetundelievered = findViewById(R.id.edittextundelieveredsearchdata);
        recyclerViewsearch = findViewById(R.id.recycleviewsearcheddataundelievered);

        progressBar = findViewById(R.id.progressbarsearchundelievered);

        getSupportActionBar().setTitle("Search Undelievered Items");
        spinnerundelievered= findViewById(R.id.searchbyundelieveredspinner);
        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_material);
        upArrow.setColorFilter(getResources().getColor(R.color.Titlehome), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        buttonsearchundelievered = findViewById(R.id.searchbuttonundelievered);

        pan = SharedPreferenceManager.getmInstance(this).getUser().getUserid();

        searchingclass = "undelivered";

        progressBar.setVisibility(View.GONE);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Optionsdelievered, android.R.layout.simple_spinner_item);

//set the spinners adapter to the previously created one.
        spinnerundelievered.setAdapter(adapter);
        spinnerundelievered.setOnItemSelectedListener(this);

        buttonsearchundelievered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchingvalue = searchingvalueetundelievered.getText().toString();
                progressBar.setVisibility(View.VISIBLE);
//               Toast.makeText(SearchActivityInventory.this, searchingvalue, Toast.LENGTH_SHORT).show();
                callsearchingapi(pan,searchingvalue,selected,searchingclass);

            }
        });




    }

    private void callsearchingapi(String pan, String searchingvalue,String selected, String searchingclass) {


        Call<List<Undelivered_Datamodel>> call = RetrofitClient.getmInstance().getApi().getsearchundelievered(pan,searchingvalue,selected,searchingclass);

        call.enqueue(new Callback<List<Undelivered_Datamodel>>() {
            @Override
            public void onResponse(Call<List<Undelivered_Datamodel>> call, Response<List<Undelivered_Datamodel>> response) {
                undelivered_datamodels= response.body();

                if (response.body()!=null){
                    progressBar.setVisibility(View.GONE);
                    undelivered_adapter = new Undelivered_Adapter(undelivered_datamodels,getApplicationContext());
                    recyclerViewsearch.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    recyclerViewsearch.setAdapter(undelivered_adapter);




                }

                else {

                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(SearchActivityUndelievered.this, "No data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Undelivered_Datamodel>> call, Throwable t) {

                Toast.makeText(SearchActivityUndelievered.this, "Please Connect To Internet and Try Again", Toast.LENGTH_SHORT).show();


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


                selected  = "phoneNo";

                // Whatever you want to happen when the second item gets selected
                break;

            case 2:


                selected  = "Address";

                // Whatever you want to happen when the second item gets selected
                break;

            case 3:


                selected  = "delivery_date";

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

