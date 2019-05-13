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
import android.widget.Toast;

import com.example.lic.Main.Datamodel.Credit_Datamodel;
import com.example.lic.Main.Datamodel.Dashboard;
import com.example.lic.Main.main.MainActivity;
import com.example.lic.Main.main.Pop;
import com.example.lic.R;

import java.util.List;

public class Credit_Adapter extends RecyclerView.Adapter<Credit_Adapter.Creditviewholder> {

    private List<Credit_Datamodel> listcredit;
    String advance,sp;
    private Context context;

    public Credit_Adapter(List<Credit_Datamodel> listcredit, Context context) {
        this.listcredit = listcredit;
        this.context = context;
    }

    @NonNull
    @Override
    public Credit_Adapter.Creditviewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        v= layoutInflater.inflate(R.layout.card_credit,viewGroup,false);

        return new Creditviewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Credit_Adapter.Creditviewholder creditviewholder, final int i) {

        creditviewholder.textViewname.setText(listcredit.get(i).getName());
        creditviewholder.textViewcontact.setText(listcredit.get(i).getContact());
        advance = String.valueOf(listcredit.get(i).getAdvance());
        sp = String.valueOf(listcredit.get(i).getSp());

//
        creditviewholder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context,Pop.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("Advance",advance);
                intent.putExtra("Sp",sp);
                context.startActivity(intent);


            }
        });

    }

    @Override
    public int getItemCount() {
        return listcredit.size();
    }

    public class Creditviewholder extends RecyclerView.ViewHolder{
        private TextView textViewname, textViewadvance, textViewcontact,textViewsp;
        CardView cardView;
        public Creditviewholder(@NonNull View itemView) {
            super(itemView);

            textViewname = itemView.findViewById(R.id.name_textviewcredit);
//            textViewadvance = itemView.findViewById(R.id.advance_textviewcredit);
            textViewcontact = itemView.findViewById(R.id.phone_textviewcredit);
//            textViewsp = itemView.findViewById(R.id.sp_textviewcredit);
            cardView = itemView.findViewById(R.id.cardviewcredit_id);



        }
    }
}
