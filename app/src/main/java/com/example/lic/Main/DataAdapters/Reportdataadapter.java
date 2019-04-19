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

public class Reportdataadapter extends RecyclerView.Adapter<Reportdataadapter.Myreportviewholder> {
    private List<Reportdata> reportdatalist;
    private Context mcontext;
    Integer quantity,tid,sp,profit1,profit,totalprofit;
    String quantitystring,tidstring,pidstring,spstring,profitstring;

    public Reportdataadapter(List<Reportdata> reportdatalist, Context mcontext) {
        this.reportdatalist = reportdatalist;
        this.mcontext = mcontext;
    }


    @NonNull
    @Override
    public Reportdataadapter.Myreportviewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(mcontext);
        view = layoutInflater.inflate(R.layout.card_report,viewGroup,false);

        return new Myreportviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Myreportviewholder myreportviewholder, int i) {

        final Reportdata reportdata = reportdatalist.get(i);
        quantity = reportdatalist.get(i).getQuantity();
        quantitystring = String.valueOf(quantity);
        totalprofit=0;

        profit = reportdatalist.get(i).getProfit();
        profitstring = String.valueOf(profit);
        tid = reportdatalist.get(i).getTid();
        tidstring = String.valueOf(tid);
        pidstring = reportdatalist.get(i).getPid();
        sp = reportdatalist.get(i).getSp();
        spstring = String.valueOf(sp);

        myreportviewholder.textViewdate.setText(reportdata.getDate());
        myreportviewholder.textViewtransaction.setText(tidstring);
        myreportviewholder.textViewquantity.setText(quantitystring);
        myreportviewholder.textViewcode.setText(pidstring);
        myreportviewholder.textViewcostprice.setText(Integer.toString(reportdata.getCp()));
        myreportviewholder.textViewsellingprice.setText(spstring);
        myreportviewholder.textViewprofit.setText(profitstring);



//        if (i+1 == reportdatalist.size()){
//
//            for(int j = 0 ; j<reportdatalist.size();j++){
//
//                profit1 = reportdatalist.get(i).getProfit();
//                totalprofit = profit+totalprofit;
//                Toast.makeText(mcontext,String.valueOf(j),Toast.LENGTH_SHORT).show();
//
//
//            }
//
//
//
//
//        }



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
            textViewprofit = itemView.findViewById(R.id.report_profit);
        }
    }
}
