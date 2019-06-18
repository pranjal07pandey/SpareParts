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
        myinventoryviewholder.textViewproductId.setText(reportdatalist.get(i).getProduct_id());
        myinventoryviewholder.textViewquantity.setText(String.valueOf(reportdatalist.get(i).getQuantity()));
        myinventoryviewholder.textViewwholesaler.setText(reportdatalist.get(i).getWholesaler());
        myinventoryviewholder.textViewname.setText(reportdatalist.get(i).getName());
        myinventoryviewholder.textViewCp.setText(String.valueOf(reportdatalist.get(i).getCp()));
        myinventoryviewholder.textViewMp.setText(String.valueOf(reportdatalist.get(i).getMp()));


    }

    @Override
    public int getItemCount() {
        return reportdatalist.size();
    }

    public class Myinventoryviewholder extends RecyclerView.ViewHolder {
        private CardView cardView;
        private TextView textViewdate,textViewproductId,textViewquantity,textViewwholesaler,textViewname,textViewCp,textViewMp;

        public Myinventoryviewholder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardviewreportinvenntory);

            textViewproductId = itemView.findViewById(R.id.inventory_report_pid);
            textViewdate = itemView.findViewById(R.id.inventory_report_date);
            textViewquantity = itemView.findViewById(R.id.inventory_report_quantity);
            textViewwholesaler = itemView.findViewById(R.id.inventory_report_wholsaler);
            textViewname = itemView.findViewById(R.id.inventory_report_name);
            textViewCp = itemView.findViewById(R.id.inventory_report_cp);
            textViewMp = itemView.findViewById(R.id.inventory_report_mp);

        }
    }
}
