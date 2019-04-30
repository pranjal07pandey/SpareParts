package com.example.lic.Main.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lic.Main.Datamodel.Reportdata;
import com.example.lic.Main.DataAdapters.Reportdataadapter;
import com.example.lic.Main.Datamodel.Totaldata;
import com.example.lic.Main.Datamodel.User;
import com.example.lic.Main.Utilities.RetrofitClient;
import com.example.lic.Main.Utilities.SharedPreferenceManager;
import com.example.lic.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Profitreportfragment extends Fragment {

    private String startdate,enddate,pan;
    private  int totalprofit,totalcp,totalsp;
    ProgressBar progressBar;
    private List<Reportdata> reportdata;
    private Reportdataadapter reportdataadapter;
    TextView textViewprofittotal,textViewsellingpricetotal,textViewcostpricetotal,textViewnodata;
    TextView textView1;
    RecyclerView recyclerView;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    @Override
    public void onCreate( Bundle savedInstanceState) {




        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_profitreportfragment, container, false);

        recyclerView = v.findViewById(R.id.recyclefrag);
        textViewnodata = v.findViewById(R.id.nodatafound);

        progressBar = v.findViewById(R.id.progress_circularprofitfrag);
        progressBar.setVisibility(View.GONE);


        User user = SharedPreferenceManager.getmInstance(getContext()).getUser();
        pan = String.valueOf(user.getUserid());

        textViewcostpricetotal = (TextView)v.findViewById(R.id.totalcostprice);
        textViewsellingpricetotal = (TextView)v.findViewById(R.id.totalsellingprice);
        textViewprofittotal= (TextView)v.findViewById(R.id.totalprofit);

        if (getArguments() != null) {
            startdate = getArguments().getString("Start");
            enddate = getArguments().getString("End");

            Call<List<Reportdata>> call = RetrofitClient.getmInstance().getApi().getprofitdata(startdate,enddate,pan);
            call.enqueue(new Callback<List<Reportdata>>() {
                @Override
                public void onResponse(Call<List<Reportdata>> call, Response<List<Reportdata>> response) {
                    reportdata = response.body();
                    if (response.body()!=null){

                        reportdataadapter = new Reportdataadapter(reportdata,getContext());
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        recyclerView.setAdapter(reportdataadapter);
                        progressBar.setVisibility(View.GONE);

                    }

                    else {
                        recyclerView.setVisibility(View.GONE);
                        textViewnodata.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.GONE);

                    }




                }

                @Override
                public void onFailure(Call<List<Reportdata>> call, Throwable t) {
                    Toast.makeText(getContext(),"Error"+t,Toast.LENGTH_SHORT).show();

                }
            });


            Call<Totaldata> calltotal = RetrofitClient.getmInstance().getApi().gettotaldata(startdate,enddate,pan);

            calltotal.enqueue(new Callback<Totaldata>() {
                @Override
                public void onResponse(Call<Totaldata> call, Response<Totaldata> response) {

                    if(response.body()!=null){
                        totalprofit = response.body().getProfittotal();
                        totalcp = response.body().getCostpricetotal();
                        totalsp = response.body().getSellingpricetotal();


                        textViewcostpricetotal.setText(String.valueOf(totalcp));
                        textViewsellingpricetotal.setText(String.valueOf(totalsp));
                        textViewprofittotal.setText(String.valueOf(totalprofit));
                    }

                    else {
                        Toast.makeText(getContext(),"No Data To Calculate Total",Toast.LENGTH_LONG).show();
                    }


                }

                @Override
                public void onFailure(Call<Totaldata> call, Throwable t) {

                }
            });

        }

        return v;


    }










}
