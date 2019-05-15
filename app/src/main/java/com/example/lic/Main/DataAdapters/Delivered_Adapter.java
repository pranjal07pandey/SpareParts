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
import com.example.lic.Main.main.DeliveredPopup;
import com.example.lic.R;

import java.util.List;

public class Delivered_Adapter extends RecyclerView.Adapter<Delivered_Adapter.Deliveredviewholder> {

    private List<Delivered_Datamodel> listdelivered;
    private Context context;
    String sp, color, size, deliveredDate;

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
    public void onBindViewHolder(@NonNull Delivered_Adapter.Deliveredviewholder deliveredviewholder, final int i) {

        deliveredviewholder.textViewname.setText(listdelivered.get(i).getName());
        deliveredviewholder.textViewcode.setText(listdelivered.get(i).getCode());
        deliveredviewholder.textViewqunatity.setText(listdelivered.get(i).getQuantity());
        deliveredviewholder.textViewaddress.setText(listdelivered.get(i).getAddress());
        deliveredviewholder.textViewcontact.setText(listdelivered.get(i).getContact());

        sp = String.valueOf(listdelivered.get(i).getSp());
        color = String.valueOf(listdelivered.get(i).getColor());
        size = String.valueOf(listdelivered.get(i).getSize());
        deliveredDate = String.valueOf(listdelivered.get(i).getDelivery_date());


        deliveredviewholder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DeliveredPopup.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("Sp",sp);
                intent.putExtra("Color", color);
                intent.putExtra("Size", size);
                intent.putExtra("Delivered Date",deliveredDate);
                context.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return listdelivered.size();
    }

    public class Deliveredviewholder extends RecyclerView.ViewHolder {
        private TextView textViewname, textViewcode, textViewqunatity, textViewaddress, textViewcontact;

        CardView cardView;

        public Deliveredviewholder(View itemView) {
            super(itemView);

            textViewname = itemView.findViewById(R.id.deliveredname);
            textViewaddress = itemView.findViewById(R.id.deliveredaddress);
            textViewcode = itemView.findViewById(R.id.deliveredcode);
            textViewqunatity = itemView.findViewById(R.id.deliveredquantity);
            textViewcontact = itemView.findViewById(R.id.deliveredcontact);

            cardView = itemView.findViewById(R.id.cardviewdelivered);

        }
    }
}
