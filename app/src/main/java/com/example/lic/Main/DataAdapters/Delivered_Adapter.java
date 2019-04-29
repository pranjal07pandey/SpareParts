package com.example.lic.Main.DataAdapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lic.Main.Datamodel.Delivered_Datamodel;
import com.example.lic.R;

import java.text.BreakIterator;
import java.util.List;

public class Delivered_Adapter extends RecyclerView.Adapter<Delivered_Adapter.Deliveredviewholder> {

    private List<Delivered_Datamodel> listdelivered;
    private Context context;

    public Delivered_Adapter(List<Delivered_Datamodel> listdelivered, Context context){
        this.listdelivered = listdelivered;
        this.context = context;
    }


    @NonNull
    @Override
    public Delivered_Adapter.Deliveredviewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v;

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        v= layoutInflater.inflate(R.layout.card_delivered, viewGroup, false);

        return new Deliveredviewholder(v);


    }

    @Override
    public void onBindViewHolder(@NonNull Delivered_Adapter.Deliveredviewholder deliveredviewholder, int i) {

        deliveredviewholder.textViewname.setText(listdelivered.get(i).getName());
        deliveredviewholder.textViewcode.setText(listdelivered.get(i).getCode());
        deliveredviewholder.textViewqunatity.setText(listdelivered.get(i).getQuantity());
        deliveredviewholder.textViewaddress.setText(listdelivered.get(i).getAddress());
        deliveredviewholder.textViewprice.setText(listdelivered.get(i).getContact());



    }

    @Override
    public int getItemCount() {
        return listdelivered.size();
    }

    public class Deliveredviewholder extends RecyclerView.ViewHolder {
        private TextView textViewname, textViewcode, textViewqunatity, textViewaddress, textViewprice;

        CardView cardView;

        public Deliveredviewholder(View itemView) {
            super(itemView);

            textViewname = itemView.findViewById(R.id.name_textviewdelivered);
            textViewaddress = itemView.findViewById(R.id.address_textviewdelivered);
            textViewcode = itemView.findViewById(R.id.code_textviewdelivered);
            textViewqunatity = itemView.findViewById(R.id.quantity_textviewdelivered);
            textViewprice = itemView.findViewById(R.id.price_textviewdelivered);

            cardView = itemView.findViewById(R.id.cardviewdelivered_id);

        }
    }
}
