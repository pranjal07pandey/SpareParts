package com.example.lic.Main.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lic.Main.DataAdapters.Inventoryreport_Adapter;
import com.example.lic.Main.DataAdapters.Reportdataadapter;
import com.example.lic.Main.Datamodel.Reportdata;
import com.example.lic.Main.Datamodel.Totaldata;
import com.example.lic.Main.Datamodel.User;
import com.example.lic.Main.Utilities.RetrofitClient;
import com.example.lic.Main.Utilities.SharedPreferenceManager;
import com.example.lic.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class InventoryReportFrag extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private String startdate,enddate,pan;
    ProgressBar progressBar;
    private  int totalquantity,totalcp,totalmp;
    private List<Reportdata> reportdata;
    private Inventoryreport_Adapter reportdataadapter;
    TextView textViewquantitytotal,textViewmarkedpricetotal,textViewcostpricetotal,textViewnodata;
    TextView textView1;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_inventory_report, container, false);

        recyclerView = v.findViewById(R.id.recyclefrag_inventoryreport);
        textViewnodata = v.findViewById(R.id.nodatafound);

        progressBar = v.findViewById(R.id.progress_circularinventoryfrag);
        progressBar.setVisibility(View.VISIBLE);


        User user = SharedPreferenceManager.getmInstance(getContext()).getUser();
        pan = String.valueOf(user.getUserid());

        textViewcostpricetotal = (TextView)v.findViewById(R.id.totalcostprice_inventory);
        textViewmarkedpricetotal = (TextView)v.findViewById(R.id.totalsellingprice_inventory);
        textViewquantitytotal= (TextView)v.findViewById(R.id.totalquantity);

        if (getArguments() != null) {
            startdate = getArguments().getString("Start");
            enddate = getArguments().getString("End");

            Call<List<Reportdata>> call = RetrofitClient.getmInstance().getApi().getinventoryreportdata(startdate,enddate,pan);
            call.enqueue(new Callback<List<Reportdata>>() {
                @Override
                public void onResponse(Call<List<Reportdata>> call, Response<List<Reportdata>> response) {
                    reportdata = response.body();

                    if (response.body()!= null){

                        reportdataadapter = new Inventoryreport_Adapter(reportdata,getContext());
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        recyclerView.setAdapter(reportdataadapter);
                        progressBar.setVisibility(View.GONE);



                    }

                    else
                    {
                        recyclerView.setVisibility(View.GONE);
                        textViewnodata.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.GONE);


                    }



                }

                @Override
                public void onFailure(Call<List<Reportdata>> call, Throwable t) {
                    Toast.makeText(getContext(),"Please Connect To Internet And Try Again",Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);


                }
            });


            Call<Totaldata> calltotal = RetrofitClient.getmInstance().getApi().gettotaldata(startdate,enddate,pan);

            calltotal.enqueue(new Callback<Totaldata>() {
                @Override
                public void onResponse(Call<Totaldata> call, Response<Totaldata> response) {
                    if (response.body()!=null){

                        totalquantity = response.body().getQuantitytotalinventory();
                        totalcp = response.body().getCp_inventory();
                        totalmp = response.body().getMp_inventory();


                        textViewcostpricetotal.setText(String.valueOf(totalcp));
                       textViewmarkedpricetotal.setText(String.valueOf(totalmp));
                        textViewquantitytotal.setText(String.valueOf(totalquantity));

                    }

                    else {
                        textViewnodata.setText("No data found");
                    }



                }

                @Override
                public void onFailure(Call<Totaldata> call, Throwable t) {

                    Toast.makeText(getContext(),"Please Connect To Internet And Try Again",Toast.LENGTH_LONG).show();

                }
            });

        }

        return v;


    }

    // TODO: Rename method, update argument and hook method into UI event

}
