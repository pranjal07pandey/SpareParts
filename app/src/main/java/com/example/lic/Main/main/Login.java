package com.example.lic.Main.main;

import android.app.ActionBar;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lic.Main.Datamodel.User;
import com.example.lic.Main.Utilities.RetrofitClient;
import com.example.lic.Main.Utilities.SharedPreferenceManager;
import com.example.lic.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    private EditText editViewuserid;
    private EditText editViewpassword;
    private TextView textViewregister;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);





     editViewuserid = findViewById(R.id.editTextuname);
        editViewpassword = findViewById(R.id.editTextpassword);

        button = findViewById(R.id.buttonLogin);
        textViewregister = findViewById(R.id.textviewegister);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userlogin();
            }
        });





    }

    @Override
    protected void onStart() {
        super.onStart();

        if (SharedPreferenceManager.getmInstance(this).isLoggedIn()) {

            Intent intent = new Intent(Login.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);

        }
    }

    private void userlogin() {

        String userid = editViewuserid.getText().toString().trim();
        String password = editViewpassword.getText().toString().trim();

        if (userid.isEmpty()) {
            editViewuserid.setError("Please Enter Username");
           editViewuserid.requestFocus();
           return;
        }

        if (password.isEmpty()) {
           editViewpassword.setError("Password is empty");
            editViewpassword.requestFocus();
            return;
        }

        Call<User> call = RetrofitClient.getmInstance().getApi().verifylogin(userid,password);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                User loginresponse = response.body();

//
                if (loginresponse.getError() == 1){

                    editViewuserid.setError("Username or Password Incorrect");
                    editViewpassword.setError("Username or Password Incorrect");
                    Toast.makeText(Login.this, "Username or Password Incorrect ",Toast.LENGTH_LONG).show();
                    return;

                }

                else if(loginresponse.getError()==2) {

                    editViewpassword.setError("Please Activate Your Account");
                    editViewuserid.setError("Account Expired");
                    Toast.makeText(Login.this, "Account Expired ",Toast.LENGTH_LONG).show();


                    return;

                }

                else if(loginresponse.getError()==3) {

                    editViewpassword.setError("Incomplete Data");
                    editViewuserid.setError("Incomplete Data");
                    Toast.makeText(Login.this, "Incomplete Data ",Toast.LENGTH_LONG).show();

                    return;

                }


                else if(loginresponse.getError()==4) {

                    editViewpassword.setError("Account Not Activated");
                    editViewuserid.setError("Check Your Email");
                    Toast.makeText(Login.this, "Account not active ",Toast.LENGTH_LONG).show();

                    return;

                }

                else if(loginresponse != null || loginresponse.getError()==0){
                    SharedPreferenceManager.getmInstance(Login.this).saveuser(loginresponse);
                    Intent intent = new Intent(Login.this,MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);

                }



            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(Login.this, "Error No Internet Connection"+t, Toast.LENGTH_LONG).show();




            }
        });



    }
}
