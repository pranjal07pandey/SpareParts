package com.example.lic.Main.main;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.lic.R;

public class Transparentactivity2 extends AppCompatActivity {
    Dialog mydialog;
    String contact,name;
    ImageButton imageButton;
    TextView textView,textViewname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transparentactivity2);

        mydialog = new Dialog(Transparentactivity2.this);

         mydialog.setContentView(R.layout.activity_po_pupcall);
        mydialog.show();

        textView = mydialog.findViewById(R.id.txtclose);
        textViewname = mydialog.findViewById(R.id.nameofshop1);
        imageButton = mydialog.findViewById(R.id.call);
        contact = "tel:"+ getIntent().getExtras().getString("contact");
        name = getIntent().getExtras().getString("name");

        textViewname.setText(name);


        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse(contact));
                startActivity(intent);
            }
        });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mydialog.dismiss();
                finish();
            }
        });





    }
}
