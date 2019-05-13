package com.example.lic.Main.DataAdapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lic.Main.Datamodel.Reportdata;
import com.example.lic.R;

import java.util.List;

public class SalesReportAdapter extends RecyclerView.Adapter<SalesReportAdapter.Myreportviewholder>{

    private List<Reportdata> reportdatalist;
    private Context mcontext;
    Integer quantity,tid,sp,profit;
    String quantitystring,tidstring,pidstring,spstring,profitstring,date,date2;

    public SalesReportAdapter(List<Reportdata> reportdatalist, Context mcontext) {
        this.reportdatalist = reportdatalist;
        this.mcontext = mcontext;
    }


    @NonNull
    @Override
    public SalesReportAdapter.Myreportviewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(mcontext);
        view = layoutInflater.inflate(R.layout.card_sales,viewGroup,false);
        return new Myreportviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Myreportviewholder myreportviewholder, int i) {

        final Reportdata reportdata = reportdatalist.get(i);
        quantity = reportdatalist.get(i).getQuantity();
        quantitystring = String.valueOf(quantity);

        profit = reportdatalist.get(i).getProfit();
        profitstring = String.valueOf(profit);

        tid = reportdatalist.get(i).getTid();
        tidstring = String.valueOf(tid);

        pidstring = reportdatalist.get(i).getPid();

        sp = reportdatalist.get(i).getSp();
        spstring = String.valueOf(sp);

        date = reportdata.getDate();
        date2 = date.substring(date.length()-8);
        myreportviewholder.textViewdate.setText(date2);
        myreportviewholder.textViewtransaction.setText(tidstring);
        myreportviewholder.textViewquantity.setText(quantitystring);
        myreportviewholder.textViewcode.setText(pidstring);
        myreportviewholder.textViewcostprice.setText(Integer.toString(reportdata.getCp()));
        myreportviewholder.textViewsellingprice.setText(spstring);



    }

    @Override
    public int getItemCount() {
        return reportdatalist.size();
    }

    public class Myreportviewholder extends RecyclerView.ViewHolder {

        private CardView cardView;
        private TextView textViewdate,textViewtransaction,textViewcode,textViewquantity,textViewcostprice,textViewsellingprice,textViewprofit;


        public Myreportviewholder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardviewreport);
            textViewdate = itemView.findViewById(R.id.date);
            textViewtransaction = itemView.findViewById(R.id.report_transactionid);
            textViewcode = itemView.findViewById(R.id.report_code);
            textViewquantity = itemView.findViewById(R.id.report_quantity);
            textViewcostprice = itemView.findViewById(R.id.report_costprice);
            textViewsellingprice = itemView.findViewById(R.id.report_sellingprice);
        }
    }
}
