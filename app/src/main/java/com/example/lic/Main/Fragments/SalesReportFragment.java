package com.example.lic.Main.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lic.Main.Datamodel.Reportdata;
import com.example.lic.Main.DataAdapters.SalesReportAdapter;
import com.example.lic.Main.Datamodel.Totaldata;
import com.example.lic.Main.Utilities.RetrofitClient;
import com.example.lic.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SalesReportFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private String startdate,enddate;
    private  int totalprofit,totalcp,totalsp;

    private List<Reportdata> reportdata;
    private SalesReportAdapter salesreportdataadapter;
    TextView textViewprofittotal,textViewsellingpricetotal,textViewcostpricetotal;
    TextView textView1;
    RecyclerView recyclerView;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View v = inflater.inflate(R.layout.fragment_sales_report, container, false);

        recyclerView = v.findViewById(R.id.recyclefrag);

        textViewcostpricetotal = (TextView)v.findViewById(R.id.totalcostprice);
        textViewsellingpricetotal = (TextView)v.findViewById(R.id.totalsellingprice);



        if (getArguments() != null) {
            startdate = getArguments().getString("Start");
            enddate = getArguments().getString("End");

            Call<List<Reportdata>> call = RetrofitClient.getmInstance().getApi().getsalesdata(startdate,enddate);
            call.enqueue(new Callback<List<Reportdata>>() {
                @Override
                public void onResponse(Call<List<Reportdata>> call, Response<List<Reportdata>> response) {

                    if (response.body() != null) {


                        reportdata = response.body();
                        salesreportdataadapter = new SalesReportAdapter(reportdata, getContext());
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        recyclerView.setAdapter(salesreportdataadapter);

                    }

                    else
                    {
                        Toast.makeText(getActivity(),"Something Went Wrong",Toast.LENGTH_SHORT).show();
                    }



                }

                @Override
                public void onFailure(Call<List<Reportdata>> call, Throwable t) {
                    Toast.makeText(getContext(),"Errpr"+t,Toast.LENGTH_SHORT).show();

                }
            });

            Call<Totaldata> calltotal = RetrofitClient.getmInstance().getApi().gettotaldata(startdate,enddate);

            calltotal.enqueue(new Callback<Totaldata>() {
                @Override
                public void onResponse(Call<Totaldata> call, Response<Totaldata> response) {
                    totalcp = response.body().getCostpricetotal();
                    totalsp = response.body().getSellingpricetotal();


                    textViewcostpricetotal.setText(String.valueOf(totalcp));
                    textViewsellingpricetotal.setText(String.valueOf(totalsp));


                }

                @Override
                public void onFailure(Call<Totaldata> call, Throwable t) {

                }
            });




        }

        return v;
    }


}
