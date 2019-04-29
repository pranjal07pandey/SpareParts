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

import com.example.lic.Main.Datamodel.Inventory_Model;
import com.example.lic.Main.main.Inventory_Secong;
import com.example.lic.R;

import java.util.List;

public class Inventory_Adapter extends RecyclerView.Adapter<Inventory_Adapter.Myviewholder> {

    private List<Inventory_Model> inventoryList;
    private Context mContext;

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
    public void onBindViewHolder(@NonNull Myviewholder myviewholder, int i) {

        final Inventory_Model inventory_model = inventoryList.get(i);
        myviewholder.tvproductid.setText(inventory_model.getProductID().trim());
        myviewholder.tvbrand.setText(inventory_model.getBrand().trim());
        myviewholder.tvtype.setText(inventory_model.getType());
        myviewholder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, Inventory_Secong.class);
                intent.putExtra("ID",inventory_model.getId());
                intent.putExtra("Productname",inventory_model.getType());
                intent.putExtra("brand",inventory_model.getBrand());
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
        TextView tvproductid;
        TextView tvbrand;
        TextView tvtype;
        TextView tvcolor;
        TextView tvsize;
        TextView tvquantity;


        public Myviewholder(@NonNull View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.cardviewinventory_id);
            tvproductid = itemView.findViewById(R.id.productid_textview);
            tvbrand = itemView.findViewById(R.id.brand_textview);
            tvtype = itemView.findViewById(R.id.type_textview);


        }
    }
}
