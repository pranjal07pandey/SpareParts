package com.example.lic.Main.DataAdapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lic.Main.Datamodel.Inventory_Model;
import com.example.lic.R;

import java.util.List;

public class Inventory_Adapter_Second extends RecyclerView.Adapter<Inventory_Adapter_Second.Myviewholder> {

    private List<Inventory_Model> inventoryModelList;
    LinearLayout layout;
    private Context mContext;

    public Inventory_Adapter_Second(List<Inventory_Model> inventoryModelList, Context mContext) {
        this.inventoryModelList = inventoryModelList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public Inventory_Adapter_Second.Myviewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v;
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        v = layoutInflater.inflate(R.layout.card_inventory_second,viewGroup,false);
        return new Myviewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Myviewholder myviewholder, int i) {
        final Inventory_Model inventory_model = inventoryModelList.get(i);


        if (i % 2 == 0){
            myviewholder.layout.setBackgroundResource(R.drawable.kk);

        }
        myviewholder.textViewcolor.setText(inventory_model.getColor());
        myviewholder.textViewQuantity.setText(Integer.toString(inventory_model.getQuantity()));
        myviewholder.textViewSize.setText(inventory_model.getSize());

    }

    @Override
    public int getItemCount() {
        return inventoryModelList.size();
    }

    public class Myviewholder extends RecyclerView.ViewHolder{

        TextView textViewcolor;
        TextView textViewSize;
        TextView textViewQuantity;
        CardView cardView;
        LinearLayout layout;
        public Myviewholder(@NonNull View itemView) {
            super(itemView);

            textViewcolor = itemView.findViewById(R.id.color);
            textViewQuantity = itemView.findViewById(R.id.quantity);
            textViewSize = itemView.findViewById(R.id.size);
            cardView = itemView.findViewById(R.id.cardviewsecondinventory);
            layout = itemView.findViewById(R.id.linearlatou);
        }
    }
}
