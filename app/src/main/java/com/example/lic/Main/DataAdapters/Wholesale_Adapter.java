package com.example.lic.Main.DataAdapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lic.Main.Datamodel.Wholesale_Datamodel;
import com.example.lic.Main.main.Transparentactivity2;
import com.example.lic.R;

import java.net.Inet4Address;
import java.util.List;

public class Wholesale_Adapter extends RecyclerView.Adapter<Wholesale_Adapter.Wholesaleviewholder> {
    private List<Wholesale_Datamodel> listwholesale;
    private Context context;


    public Wholesale_Adapter(List<Wholesale_Datamodel> listwholesale, Context context) {
        this.listwholesale = listwholesale;
        this.context = context;
    }

    @NonNull
    @Override
    public Wholesale_Adapter.Wholesaleviewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v;
          LayoutInflater layoutInflater = LayoutInflater.from(context);
          v = layoutInflater.inflate(R.layout.card_wholesalealternate,viewGroup,false);
        return new Wholesaleviewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final Wholesaleviewholder wholesaleviewholder, int i) {


        wholesaleviewholder.textViewname.setText(listwholesale.get(i).getName());
        wholesaleviewholder.textViewcontact.setText(listwholesale.get(i).getContact());
        wholesaleviewholder.textViewaddress.setText(listwholesale.get(i).getAddress());
        wholesaleviewholder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Transparentactivity2.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("contact",listwholesale.get(wholesaleviewholder.getAdapterPosition()).getContact());
                intent.putExtra("name",listwholesale.get(wholesaleviewholder.getAdapterPosition()).getName());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listwholesale.size();
    }

    public class Wholesaleviewholder extends RecyclerView.ViewHolder{
        private TextView textViewname,textViewaddress,textViewcontact;
        CardView cardView;
        public Wholesaleviewholder(@NonNull View itemView) {
            super(itemView);
            textViewname = itemView.findViewById(R.id.wholesalename);
            textViewaddress = itemView.findViewById(R.id.addresswholesale);
            textViewcontact = itemView.findViewById(R.id.wholesalephone);
            cardView = itemView.findViewById(R.id.cardviewwholesalealternate);
        }
    }
}
