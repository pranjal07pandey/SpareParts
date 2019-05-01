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
import com.example.lic.Main.Datamodel.Undelivered_Datamodel;
import com.example.lic.R;

import java.text.BreakIterator;
import java.util.List;

public class Undelivered_Adapter extends RecyclerView.Adapter<Undelivered_Adapter.Undeliveredviewholder> {

    private List<Undelivered_Datamodel> listundelivered;
    private Context context;

    public Undelivered_Adapter(List<Undelivered_Datamodel> listundelivered, Context context){
        this.listundelivered = listundelivered;
        this.context = context;
    }


    @NonNull
    @Override
    public Undelivered_Adapter.Undeliveredviewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v;

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        v= layoutInflater.inflate(R.layout.card_undelivered, viewGroup, false);

        return new Undeliveredviewholder(v);


    }

    @Override
    public void onBindViewHolder(@NonNull Undelivered_Adapter.Undeliveredviewholder undeliveredviewholder, int i) {

        undeliveredviewholder.textViewname.setText(listundelivered.get(i).getName());
        undeliveredviewholder.textViewcode.setText(listundelivered.get(i).getCode());
        undeliveredviewholder.textViewqunatity.setText(listundelivered.get(i).getQuantity());
        undeliveredviewholder.textViewaddress.setText(listundelivered.get(i).getAddress());
        undeliveredviewholder.textViewprice.setText(listundelivered.get(i).getContact());



    }

    @Override
    public int getItemCount() {
        return listundelivered.size();
    }

    public class Undeliveredviewholder extends RecyclerView.ViewHolder {
        private TextView textViewname, textViewcode, textViewqunatity, textViewaddress, textViewprice;

        CardView cardView;

        public Undeliveredviewholder(View itemView) {
            super(itemView);

            textViewname = itemView.findViewById(R.id.undeliveredname);
            textViewaddress = itemView.findViewById(R.id.undeliveredaddress);
            textViewcode = itemView.findViewById(R.id.undeliveredcode);
            textViewqunatity = itemView.findViewById(R.id.undeliveredquantity);
            textViewprice = itemView.findViewById(R.id.undeliveredprice);

            cardView = itemView.findViewById(R.id.cardviewUndelivered);

        }
    }


}