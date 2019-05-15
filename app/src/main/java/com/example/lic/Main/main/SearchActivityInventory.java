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
import android.widget.TextView;
import android.widget.Toast;

import com.example.lic.Main.DataAdapters.Inventory_Adapter;
import com.example.lic.Main.Datamodel.Inventory_Model;
import com.example.lic.Main.Utilities.RetrofitClient;
import com.example.lic.Main.Utilities.SharedPreferenceManager;
import com.example.lic.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivityInventory extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spinner;
    EditText searchingvaluetv;
    ProgressBar progressBar;
    Button buttonsearch;
    RecyclerView recyclerViewsearch;
    private Inventory_Adapter inventoryAdapter;
    String selected,pan,searchingclass,searchingvalue;
    List<Inventory_Model> inventoryModelList;
    RecyclerView recyclerViewsearcheddata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_inventory);

        searchingvaluetv = findViewById(R.id.edittextinventorysearchdata);
        recyclerViewsearch = findViewById(R.id.recycleviewsearcheddata);

        progressBar = findViewById(R.id.progressbarsearchinventory);
        progressBar.setVisibility(View.GONE);
        spinner = findViewById(R.id.searchbyinventoryspinner);


        getSupportActionBar().setTitle("Search Inventory Items");

        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_material);
        upArrow.setColorFilter(getResources().getColor(R.color.Titlehome), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        buttonsearch = findViewById(R.id.searchbuttoninventory);

        pan = SharedPreferenceManager.getmInstance(this).getUser().getUserid();

        searchingclass = "inventory";



        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Options, android.R.layout.simple_spinner_item);

//set the spinners adapter to the previously created one.
       spinner.setAdapter(adapter);
       spinner.setOnItemSelectedListener(this);

       buttonsearch.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               searchingvalue = searchingvaluetv.getText().toString();
               progressBar.setVisibility(View.VISIBLE);
//               Toast.makeText(SearchActivityInventory.this, searchingvalue, Toast.LENGTH_SHORT).show();
               callsearchingapi(pan,searchingvalue,selected,searchingclass);

           }
       });




    }

    private void callsearchingapi(String pan, String searchingvalue,String selected, String searchingclass) {


        Call<List<Inventory_Model>> call = RetrofitClient.getmInstance().getApi().getsearch(pan,searchingvalue,selected,searchingclass);

        call.enqueue(new Callback<List<Inventory_Model>>() {
            @Override
            public void onResponse(Call<List<Inventory_Model>> call, Response<List<Inventory_Model>> response) {
                inventoryModelList = response.body();
                progressBar.setVisibility(View.GONE);

                if (response.body()!=null){
                    inventoryAdapter = new Inventory_Adapter(inventoryModelList,getApplicationContext());
                    recyclerViewsearch.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    recyclerViewsearch.setAdapter(inventoryAdapter);




                }

                else {
                    progressBar.setVisibility(View.GONE);

                    Toast.makeText(SearchActivityInventory.this, "No data"+response.body(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Inventory_Model>> call, Throwable t) {

                Toast.makeText(SearchActivityInventory.this, "Please Check Your Connection and Try Again"+t, Toast.LENGTH_LONG).show();

            }
        });
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch (position) {
            case 0:
                selected  = "code";
//                selected  = parent.getItemAtPosition(position).toString();

                // Whatever you want to happen when the first item gets selected
                break;
            case 1:


                selected  = parent.getItemAtPosition(position).toString();

                // Whatever you want to happen when the second item gets selected
                break;
            case 2:
                selected  = parent.getItemAtPosition(position).toString();

                // Whatever you want to happen when the thrid item gets selected
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
