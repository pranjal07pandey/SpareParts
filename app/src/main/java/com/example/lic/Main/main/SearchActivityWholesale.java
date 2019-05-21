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
import com.example.lic.Main.DataAdapters.Wholesale_Adapter;
import com.example.lic.Main.Datamodel.Inventory_Model;
import com.example.lic.Main.Datamodel.Wholesale_Datamodel;
import com.example.lic.Main.Utilities.RetrofitClient;
import com.example.lic.Main.Utilities.SharedPreferenceManager;
import com.example.lic.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivityWholesale extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner spinnerwholesale;
    EditText searchingvalueet;
    Button buttonsearch;
    ProgressBar progressBar;
    RecyclerView recyclerViewsearch;
    private Wholesale_Adapter wholesale_adapter;
    String selected,pan,searchingclass,searchingvalue;
    List<Wholesale_Datamodel> wholesaleDatamodelList;
    RecyclerView recyclerViewsearcheddata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_wholesale);

        searchingvalueet = findViewById(R.id.edittextwholesalesearchdata);
        recyclerViewsearch = findViewById(R.id.recycleviewsearcheddatawholesale);

        getSupportActionBar().setTitle("Search Inventory Items");
        spinnerwholesale = findViewById(R.id.searchbywholsesalespinner);

        buttonsearch = findViewById(R.id.searchbuttonwholesale);
        progressBar = findViewById(R.id.progressbarsearchwholesale);
        progressBar.setVisibility(View.GONE);
        pan = SharedPreferenceManager.getmInstance(this).getUser().getUserid();

        searchingclass = "wholesaler";
        getSupportActionBar().setTitle("Search Wholesale Items");

        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_material);
        upArrow.setColorFilter(getResources().getColor(R.color.Titlehome), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Optionswholesale, android.R.layout.simple_spinner_item);

//set the spinners adapter to the previously created one.
        spinnerwholesale.setAdapter(adapter);
        spinnerwholesale.setOnItemSelectedListener(this);

        buttonsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchingvalue = searchingvalueet.getText().toString();
//               Toast.makeText(SearchActivityInventory.this, searchingvalue, Toast.LENGTH_SHORT).show();
                callsearchingapi(pan,searchingvalue,selected,searchingclass);
                progressBar.setVisibility(View.VISIBLE);

            }
        });




    }

    private void callsearchingapi(String pan, String searchingvalue,String selected, String searchingclass) {


        Call<List<Wholesale_Datamodel>> call = RetrofitClient.getmInstance().getApi().getsearchwholesale(pan,searchingvalue,selected,searchingclass);


      call.enqueue(new Callback<List<Wholesale_Datamodel>>() {
          @Override
          public void onResponse(Call<List<Wholesale_Datamodel>> call, Response<List<Wholesale_Datamodel>> response) {
              progressBar.setVisibility(View.GONE);
              wholesaleDatamodelList = response.body();

              if (response.body()!=null){
                  wholesale_adapter = new Wholesale_Adapter(wholesaleDatamodelList,getApplicationContext());
                  recyclerViewsearch.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                  recyclerViewsearch.setAdapter(wholesale_adapter);




              }

              else {

                  Toast.makeText(SearchActivityWholesale.this, "No data", Toast.LENGTH_SHORT).show();
              }

          }

          @Override
          public void onFailure(Call<List<Wholesale_Datamodel>> call, Throwable t) {
              progressBar.setVisibility(View.GONE);

              Toast.makeText(SearchActivityWholesale.this, "No data"+t, Toast.LENGTH_SHORT).show();

          }
      });

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch (position) {
            case 0:
                selected  = "name";

//                selected  = parent.getItemAtPosition(position).toString();

                // Whatever you want to happen when the first item gets selected
                break;
            case 1:


                selected  = "contactNo";

                // Whatever you want to happen when the second item gets selected
                break;
            case 2:
                selected  = "address";

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
