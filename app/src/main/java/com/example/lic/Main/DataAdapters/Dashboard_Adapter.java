package com.example.lic.Main.DataAdapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lic.Main.Datamodel.Dashboard;
import com.example.lic.Main.main.Credit;
import com.example.lic.Main.main.DailySales;
import com.example.lic.Main.main.Insights;
import com.example.lic.Main.main.Inventory;
import com.example.lic.Main.main.Online;
import com.example.lic.Main.main.Report;
import com.example.lic.Main.main.Return;
import com.example.lic.Main.main.Wholesale;
import com.example.lic.R;

import java.util.List;

public class Dashboard_Adapter extends RecyclerView.Adapter<Dashboard_Adapter.Dashboardviewholder> {

    private Context mcontext;
    private List<Dashboard> dashboardList;

    public Dashboard_Adapter(Context mcontext, List<Dashboard> dashboardList) {
        this.mcontext = mcontext;
        this.dashboardList = dashboardList;
    }

    @NonNull
    @Override
    public Dashboard_Adapter.Dashboardviewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v;
        LayoutInflater layoutInflater = LayoutInflater.from(mcontext);
        v = layoutInflater.inflate(R.layout.carddashboard,viewGroup,false);
        return new Dashboardviewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Dashboardviewholder dashboardviewholder, final int i) {

        final Dashboard dashboard = dashboardList.get(i);
        dashboardviewholder.textViewtitle.setText(dashboard.getTitle());
        dashboardviewholder.imageViewdashboard.setImageResource(dashboard.getHthumbnail());
        dashboardviewholder.carddashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dashboardList.get(i).getTitle().toUpperCase().equals("WHOLESALE")){

                    Intent intent = new Intent(mcontext, Wholesale.class);
                     mcontext.startActivity(intent);

                }

                else if (dashboardList.get(i).getTitle().toUpperCase().equals("INVENTORY")){


                    Intent intent = new Intent(mcontext, Inventory.class);
                    mcontext.startActivity(intent);

                }

                else if (dashboardList.get(i).getTitle().toUpperCase().equals("REPORT")){

                    Intent intent = new Intent(mcontext, Report.class);
                    mcontext.startActivity(intent);

                }

                else if (dashboardList.get(i).getTitle().toUpperCase().equals("ONLINE")){

                    Intent intent = new Intent(mcontext, Online.class);
                    mcontext.startActivity(intent);

                }

                else if (dashboardList.get(i).getTitle().toUpperCase().equals("INSIGHTS")){

                    Intent intent = new Intent(mcontext, Insights.class);
                    mcontext.startActivity(intent);

                }

                else if (dashboardList.get(i).getTitle().toUpperCase().equals("DAILY SALES")){

                    Toast.makeText(mcontext,"Upgrade to premium to use this feature",Toast.LENGTH_LONG).show();

                }

                else if (dashboardList.get(i).getTitle().toUpperCase().equals("RETURN")){

                    Toast.makeText(mcontext,"Upgrade to premium to use this feature",Toast.LENGTH_LONG).show();

                }
                else if (dashboardList.get(i).getTitle().toUpperCase().equals("CREDIT")){

                    Intent intent = new Intent(mcontext, Credit.class);
                    mcontext.startActivity(intent);

                }

                else {
                    Toast.makeText(mcontext,"Error",Toast.LENGTH_LONG).show();
                }


            }
        });


    }

    @Override
    public int getItemCount() {
        return dashboardList.size();
    }



    public class Dashboardviewholder extends RecyclerView.ViewHolder {

        private TextView textViewtitle;
        private ImageView imageViewdashboard;
        private CardView carddashboard;
        public Dashboardviewholder(@NonNull View itemView) {
            super(itemView);
            textViewtitle = itemView.findViewById(R.id.textviewdahsboard);
            imageViewdashboard = itemView.findViewById(R.id.imageviewdashboard);
            carddashboard = itemView.findViewById(R.id.cardviewdashboard);

        }
    }
}
