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

import com.example.lic.Main.Datamodel.Delivered_Datamodel;
import com.example.lic.Main.Datamodel.Undelivered_Datamodel;
import com.example.lic.Main.main.UndeliveredPopup;
import com.example.lic.R;

import java.text.BreakIterator;
import java.util.List;

public class Undelivered_Adapter extends RecyclerView.Adapter<Undelivered_Adapter.Undeliveredviewholder> {

    private List<Undelivered_Datamodel> listundelivered;
    private Context context;
    String sp, color, size, deliveredDate;


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
    public void onBindViewHolder(@NonNull final Undelivered_Adapter.Undeliveredviewholder undeliveredviewholder, int i) {

        undeliveredviewholder.textViewname.setText(listundelivered.get(i).getName());
        undeliveredviewholder.textViewcode.setText(listundelivered.get(i).getCode());
        undeliveredviewholder.textViewqunatity.setText(listundelivered.get(i).getQuantity());
        undeliveredviewholder.textViewaddress.setText(listundelivered.get(i).getAddress());
        undeliveredviewholder.textViewcontact.setText(listundelivered.get(i).getContact());


        sp = String.valueOf(listundelivered.get(i).getSp());
        color = String.valueOf(listundelivered.get(i).getColor());
        size = String.valueOf(listundelivered.get(i).getSize());
        deliveredDate = String.valueOf(listundelivered.get(i).getDelivery_date());

        undeliveredviewholder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UndeliveredPopup.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("Sp",listundelivered.get(undeliveredviewholder.getAdapterPosition()).getSp());
                intent.putExtra("Color",listundelivered.get(undeliveredviewholder.getAdapterPosition()).getColor());
                intent.putExtra("Size",listundelivered.get(undeliveredviewholder.getAdapterPosition()).getSize());
                intent.putExtra("Delivered Date",listundelivered.get(undeliveredviewholder.getAdapterPosition()).getDelivery_date());
                context.startActivity(intent);
            }
        });







    }

    @Override
    public int getItemCount() {
        return listundelivered.size();
    }

    public class Undeliveredviewholder extends RecyclerView.ViewHolder {
        private TextView textViewname, textViewcode, textViewqunatity, textViewaddress, textViewcontact;

        CardView cardView;

        public Undeliveredviewholder(View itemView) {
            super(itemView);

            textViewname = itemView.findViewById(R.id.undeliveredname);
            textViewaddress = itemView.findViewById(R.id.undeliveredaddress);
            textViewcode = itemView.findViewById(R.id.undeliveredcode);
            textViewqunatity = itemView.findViewById(R.id.undeliveredquantity);
            textViewcontact = itemView.findViewById(R.id.undeliveredcontact);

            cardView = itemView.findViewById(R.id.cardviewUndelivered);

        }
    }


}