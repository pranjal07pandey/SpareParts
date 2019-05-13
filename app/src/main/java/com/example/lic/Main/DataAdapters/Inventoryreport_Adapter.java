package com.example.lic.Main.DataAdapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lic.Main.Datamodel.Reportdata;
import com.example.lic.R;

import java.util.List;

public class Inventoryreport_Adapter extends RecyclerView.Adapter<Inventoryreport_Adapter.Myinventoryviewholder> {
    private List<Reportdata> reportdatalist;
    private Context mcontext;
    String date,date2;
    Integer quantity,sp,profit1,profit,totalprofit;


    public Inventoryreport_Adapter(List<Reportdata> reportdatalist, Context mcontext) {
        this.reportdatalist = reportdatalist;
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public Myinventoryviewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(mcontext);
        view = layoutInflater.inflate(R.layout.card_inventoryreport,viewGroup,false);

        return new Inventoryreport_Adapter.Myinventoryviewholder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull Myinventoryviewholder myinventoryviewholder, int i) {



        date = reportdatalist.get(i).getDate();
        date2 = date.substring(date.length()-8);

        myinventoryviewholder.textViewdate.setText(date2);
        myinventoryviewholder.textViewcode.setText(reportdatalist.get(i).getPid());
        myinventoryviewholder.textViewquantity.setText(String.valueOf(reportdatalist.get(i).getQuantity()));
        myinventoryviewholder.textViewcode.setText(reportdatalist.get(i).getPid());
        myinventoryviewholder.textViewbrand.setText(reportdatalist.get(i).getBrand());
        myinventoryviewholder.textViewsize.setText(reportdatalist.get(i).getSize());
        myinventoryviewholder.textViewcolor.setText(reportdatalist.get(i).getColor());
        myinventoryviewholder.textViewwholesale.setText(reportdatalist.get(i).getWholeseller());
        myinventoryviewholder.textviewtype.setText(reportdatalist.get(i).getType());

    }

    @Override
    public int getItemCount() {
        return reportdatalist.size();
    }

    public class Myinventoryviewholder extends RecyclerView.ViewHolder {
        private CardView cardView;
        private TextView textViewdate,textViewcode,textViewquantity,textViewcolor,textViewsize,textViewwholesale,textViewbrand,textviewtype;

        public Myinventoryviewholder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardviewreportinvenntory);
            textViewcode = itemView.findViewById(R.id.inventory_report_code);
            textViewdate = itemView.findViewById(R.id.inventory_datereport);
            textViewcolor = itemView.findViewById(R.id.inventory_report_color);
            textViewquantity = itemView.findViewById(R.id.inventory_report_quantity);
            textViewsize = itemView.findViewById(R.id.inventory_report_size);
            textViewwholesale = itemView.findViewById(R.id.inventory_reportwholsale);
            textViewbrand = itemView.findViewById(R.id.inventory_report_brand);
            textViewwholesale = itemView.findViewById(R.id.inventory_reportwholsale);
            textviewtype = itemView.findViewById(R.id.inventory_report_type);


        }
    }
}
