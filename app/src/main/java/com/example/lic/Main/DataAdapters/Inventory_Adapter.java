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

import com.example.lic.Main.Datamodel.Inventory_Model;
//import com.example.lic.Main.main.Inventory_Secong;
import com.example.lic.Main.main.InventoryPopup;
import com.example.lic.R;

import java.util.List;

public class Inventory_Adapter extends RecyclerView.Adapter<Inventory_Adapter.Myviewholder> {

    private List<Inventory_Model> inventoryList;
    private Context mContext;
    String wholesaler;

    public Inventory_Adapter(List<Inventory_Model> inventoryList, Context mContext) {
        this.inventoryList = inventoryList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public Inventory_Adapter.Myviewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v;
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        v =layoutInflater.inflate(R.layout.card_inventory,viewGroup,false);

       return new Myviewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final Myviewholder myviewholder, final int i) {

        final Inventory_Model inventory_model = inventoryList.get(i);

        myviewholder.inventoryName.setText(inventory_model.getName());
        myviewholder.inventoryProductId.setText(inventory_model.getProduct_id());
        myviewholder.inventoryQuantity.setText(String.valueOf(inventory_model.getQuantity()));
        myviewholder.inventoryCp.setText(String.valueOf(inventory_model.getCp()));
        myviewholder.inventoryMp.setText(String.valueOf(inventory_model.getMp()));

//        wholesaler = String.valueOf(inventoryList.get(i).getWholesaler());
        wholesaler = inventoryList.get(i).getWholesaler();
        myviewholder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, InventoryPopup.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("wholesaler",inventoryList.get(myviewholder.getAdapterPosition()).getWholesaler());
                mContext.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return inventoryList.size();
    }

    public class Myviewholder extends RecyclerView.ViewHolder{

        CardView cardView;

        TextView inventoryName;
        TextView inventoryProductId;
        TextView inventoryQuantity;
        TextView inventoryCp;
        TextView inventoryMp;


        public Myviewholder(@NonNull View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.cardviewinventory_id);

            inventoryName = itemView.findViewById(R.id.inventoryName1);
            inventoryProductId = itemView.findViewById(R.id.inventoryProductId1);
            inventoryQuantity = itemView.findViewById(R.id.inventoryQuantity1);
            inventoryCp = itemView.findViewById(R.id.inventoryCp1);
            inventoryMp = itemView.findViewById(R.id.inventoryMp1);


        }
    }
}
