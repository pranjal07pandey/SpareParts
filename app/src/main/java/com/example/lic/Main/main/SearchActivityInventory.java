package com.example.lic.Main.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.lic.R;

public class SearchActivityInventory extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spinner;
    String selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_inventory);

        getSupportActionBar().setTitle("Search Inventory Items");
        spinner = findViewById(R.id.searchbyinventoryspinner);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Options, android.R.layout.simple_spinner_item);

//set the spinners adapter to the previously created one.
       spinner.setAdapter(adapter);
       spinner.setOnItemSelectedListener(this);




    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch (position) {
            case 0:

                selected  = parent.getItemAtPosition(position).toString();

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

    }
}
