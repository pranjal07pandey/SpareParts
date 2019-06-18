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
    Integer quantity,tid,sp,profit,cp;
    String quantitystring,tidstring,pidstring,spstring,cpstring,date,date2,profitstring;

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


        tid = reportdatalist.get(i).getTransaction_id();
        tidstring = String.valueOf(tid);

        pidstring = reportdatalist.get(i).getProduct_id();

        sp = reportdatalist.get(i).getSp();
        spstring = String.valueOf(sp);

        cp = reportdatalist.get(i).getCp();
        cpstring = String.valueOf(cp);

        date = reportdata.getDate();
        date2 = date.substring(date.length()-8);
        myreportviewholder.textViewdate.setText(date2);
        myreportviewholder.textViewtransactionId.setText(tidstring);
        myreportviewholder.textViewquantity.setText(quantitystring);
        myreportviewholder.textViewcustomerName.setText(reportdatalist.get(i).getCustomer_name());
        myreportviewholder.textViewcostprice.setText(cpstring);
        myreportviewholder.textViewsellingprice.setText(spstring);
        myreportviewholder.textViewName.setText(reportdatalist.get(i).getName());


    }

    @Override
    public int getItemCount() {
        return reportdatalist.size();
    }

    public class Myreportviewholder extends RecyclerView.ViewHolder {

        private CardView cardView;
        private TextView textViewdate,textViewtransactionId,textViewcustomerName,textViewquantity,textViewcostprice,textViewsellingprice,textViewName;


        public Myreportviewholder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardviewreport);

            textViewdate = itemView.findViewById(R.id.sales_report_date);
            textViewtransactionId = itemView.findViewById(R.id.sales_report_tid);
            textViewcustomerName = itemView.findViewById(R.id.sales_report_custname);
            textViewquantity = itemView.findViewById(R.id.sales_report_quantity);
            textViewcostprice = itemView.findViewById(R.id.sales_report_costprice);
            textViewsellingprice = itemView.findViewById(R.id.sales_report_sellingprice);
            textViewName = itemView.findViewById(R.id.sales_report_name);

        }
    }
}
