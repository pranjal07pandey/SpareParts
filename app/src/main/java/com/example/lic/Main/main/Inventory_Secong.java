package com.example.lic.Main.main;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.lic.Main.Datamodel.Inventory_Model;
import com.example.lic.Main.DataAdapters.Inventory_Adapter_Second;
import com.example.lic.Main.Datamodel.User;
import com.example.lic.Main.Utilities.RetrofitClient;
import com.example.lic.Main.Utilities.SharedPreferenceManager;
import com.example.lic.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Inventory_Secong extends AppCompatActivity {

    int id;
    String type,brand;
    RecyclerView recyclerView2;
    ProgressBar progressBar;
    private List<Inventory_Model> inventoryModelList;
    private Inventory_Adapter_Second inventory_adapter_second;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory__secong);

       recyclerView2 = findViewById(R.id.recyclerviewsecondinventory);

        User user = SharedPreferenceManager.getmInstance(this).getUser();
       String Pan = String.valueOf(user.getUserid());

       progressBar = findViewById(R.id.progress_circularinventsecond);
       progressBar.setVisibility(View.VISIBLE);

        type = getIntent().getExtras().getString("Productname");
        brand = getIntent().getExtras().getString("brand");
        id = getIntent().getExtras().getInt("ID");

        getSupportActionBar().setTitle(brand+" "+"("+type+")" + id);
        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_material);
        upArrow.setColorFilter(getResources().getColor(R.color.bluelight), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Call<List<Inventory_Model>> call = RetrofitClient.getmInstance().getApi().getspecificdata(id,Pan);

        call.enqueue(new Callback<List<Inventory_Model>>() {
            @Override
            public void onResponse(Call<List<Inventory_Model>> call, Response<List<Inventory_Model>> response) {
                
                progressBar.setVisibility(View.GONE);

                inventoryModelList = response.body();
                if (inventoryModelList!=null){
                    inventory_adapter_second = new Inventory_Adapter_Second(inventoryModelList,getApplicationContext());
                    recyclerView2.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    recyclerView2.setAdapter(inventory_adapter_second);
                }

                else {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(Inventory_Secong.this, "No data", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List<Inventory_Model>> call, Throwable t) {
                Toast.makeText(Inventory_Secong.this, "Please Check You Internet and Try Again", Toast.LENGTH_SHORT).show();

            }
        });






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
