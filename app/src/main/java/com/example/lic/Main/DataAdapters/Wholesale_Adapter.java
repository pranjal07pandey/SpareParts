package com.example.lic.Main.DataAdapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lic.Main.Datamodel.Wholesale_Datamodel;
import com.example.lic.R;

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
          v = layoutInflater.inflate(R.layout.card_wholesale,viewGroup,false);
        return new Wholesaleviewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Wholesaleviewholder wholesaleviewholder, int i) {

        wholesaleviewholder.textViewname.setText(listwholesale.get(i).getName());
        wholesaleviewholder.textViewcontact.setText(listwholesale.get(i).getContact());
        wholesaleviewholder.textViewaddress.setText(listwholesale.get(i).getAddress());



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
            textViewname = itemView.findViewById(R.id.name_textviewwholesale);
            textViewaddress = itemView.findViewById(R.id.address_textviewwholesale);
            textViewcontact = itemView.findViewById(R.id.phone_textviewwholesale);
            cardView = itemView.findViewById(R.id.cardviewwholesale_id);
        }
    }
}
