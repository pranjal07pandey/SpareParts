package com.example.lic.Main.Fragments;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.lic.Main.Datamodel.Insightsdatamodel;
import com.example.lic.Main.Utilities.RetrofitClient;
import com.example.lic.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class twomonthsreportfragment extends Fragment {
    private String date;
    String[] datesales,dateprofit;
    private int i;
    LineChart lineChartsales,lineChartprofit;
    ArrayList<Entry> yValuesprofit = new ArrayList<>();
    ArrayList<Entry> yValues2sales = new ArrayList<>();
    ArrayList<Entry> yValuesinventory = new ArrayList<>();




    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      View v = inflater.inflate(R.layout.fragment_twomonthsreportfragment, container, false);

        lineChartsales = v.findViewById(R.id.mylinegraph1);
        lineChartprofit = v.findViewById(R.id.mylinegraph2);

//        linechart.setOnChartGestureListener(GraphExample.this);
//        linechart.setOnChartValueSelectedListener(GraphExample.this);
//
//        lineChart.setDragEnabled(true);
//        lineChart.setScaleEnabled(false);


        Call< List<Insightsdatamodel>> call = RetrofitClient.getmInstance().getApi().getinsightsdata();
        call.enqueue(new Callback<List<Insightsdatamodel>>() {
                         @Override
                         public void onResponse(Call<List<Insightsdatamodel>> call, Response<List<Insightsdatamodel>> response) {

                             List<Insightsdatamodel> list = response.body();

                             String[] datacomingfrommoedl = new String[list.size()];
                             datesales = new String[list.size()];
                             String[] salessp = new String[list.size()];
                             dateprofit = new String[list.size()];
                             String[] profitsp = new String[list.size()];
                             String[] lablebar = new String[list.size()];
                             String[] spbar = new String[list.size()];

                             for (int i = 0;i< list.size();i++){
                                 datacomingfrommoedl[i] = list.get(i).getName();

                                 if (datacomingfrommoedl[i].equals("sales")){

                                     datesales[i] = list.get(i).getDate();
                                     salessp[i] = String.valueOf(list.get(i).getSp());

                                     yValues2sales.add(new Entry(i,Float.parseFloat(salessp[i])));
//                                    Toast.makeText(getContext(),"Sales"+yValues,Toast.LENGTH_SHORT).show();


                                 }

                                 else if (datacomingfrommoedl[i].equals("profit"))
                                 {

                                     dateprofit[i] = list.get(i).getDate();
                                     profitsp[i] = String.valueOf(list.get(i).getSp());
                                     yValuesprofit.add(new Entry(i,Float.parseFloat(profitsp[i])));

//                                     Toast.makeText(getContext(),"Profit"+dateprofit[i]+" "+profitsp[i],Toast.LENGTH_SHORT).show();
                                 }

                                 else if(datacomingfrommoedl[i].equals("bar")){

                                     lablebar[i] = list.get(i).getLabel();
                                     spbar[i] = String.valueOf(list.get(i).getSp());
                                     yValuesinventory.add(new Entry(i,Float.parseFloat(spbar[i])));
//                                     Toast.makeText(getContext(),"Bar"+lablebar[i]+" "+spbar[i],Toast.LENGTH_SHORT).show();


                                 }


                             }


//
//                             Toast.makeText(getContext(),"Profit"+yValuesprofit,Toast.LENGTH_SHORT).show();
//
//                             Toast.makeText(getContext(),"Bar"+yValuesinventory,Toast.LENGTH_SHORT).show();

                             LineDataSet set1 = new LineDataSet(yValues2sales,"Sales Data");
                             LineDataSet set2 = new LineDataSet(yValuesprofit,"Profit Data");

                             set1.setFillAlpha(500);
                             set1.setColor(Color.parseColor("#1ea1e5"));
                             set1.setLineWidth(3);
                             set1.setValueTextSize(10);

                             set2.setFillAlpha(500);
                             set2.setColor(Color.parseColor("#2a86ab"));
                             set2.setLineWidth(3);
                             set2.setValueTextSize(10);



                             ArrayList<ILineDataSet> dataSets = new ArrayList<>();
                             dataSets.add(set1);

                             ArrayList<ILineDataSet> dataset2 = new ArrayList<>();
                             dataset2.add(set2);

                             LineData data = new LineData(dataSets);
                             lineChartsales.setData(data);
                             lineChartsales.invalidate();
                             lineChartsales.setNoDataText("No Data Found");
                             lineChartsales.getAxisRight().setEnabled(false);
                             XAxis xAxis = lineChartsales.getXAxis();
                             xAxis.setValueFormatter(new Myaxisvalueformatter(datesales));
                             xAxis.setGranularity(1);
                             xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

                             LineData data2 = new LineData(dataset2);
                             lineChartprofit.setData(data2);
                             lineChartprofit.invalidate();
                             lineChartprofit.setNoDataText("No Profit Data");
                             lineChartprofit.getAxisRight().setEnabled(false);
                             XAxis xAxis1 = lineChartprofit.getXAxis();
                             xAxis1.setValueFormatter(new Myaxisvalueformatter(dateprofit));
                             xAxis1.setGranularity(1);
                             xAxis1.setPosition(XAxis.XAxisPosition.BOTTOM);

                         }

                        class Myaxisvalueformatter implements IAxisValueFormatter{

                             private String[] mvalues;
                             public Myaxisvalueformatter(String[] values){
                                 this.mvalues = values;
                             }

                             @Override
                             public String getFormattedValue(float value, AxisBase axis) {
                                 return mvalues[(int)value];
                             }

                         }


            @Override
                public void onFailure(Call<List<Insightsdatamodel>> call, Throwable t) {

                    }
                     }
        );


            return v;
    }


}


