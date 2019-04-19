package com.example.lic.Main.Utilities;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.lic.Main.Datamodel.User;

public class SharedPreferenceManager {

        private static  final String Shared_Pref_Name = "new_shared_pref";

        private static com.example.lic.Main.Utilities.SharedPreferenceManager mInstance;
        private static Context mcontext;

        private SharedPreferenceManager(Context mcontext){

            this.mcontext = mcontext;

        }

        public static  synchronized com.example.lic.Main.Utilities.SharedPreferenceManager getmInstance(Context mcontext){
            if(mInstance == null){
                mInstance =  new com.example.lic.Main.Utilities.SharedPreferenceManager(mcontext);
            }
            return mInstance;
        }


        public void saveuser(User user){
            SharedPreferences sharedPreferences = mcontext.getSharedPreferences(Shared_Pref_Name,Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();

            editor.putInt("id",user.getId());
            editor.putString("userid",user.getUserid());
            editor.putString("cname",user.getCname());
            editor.putInt("expirein",user.getExpirein());
            editor.apply();
        }

        public boolean isLoggedIn(){

            SharedPreferences sharedPreferences = mcontext.getSharedPreferences(Shared_Pref_Name,Context.MODE_PRIVATE);
            return sharedPreferences.getInt("id",-1)!= -1;
        }
        //
        public User getUser(){
            SharedPreferences sharedPreferences = mcontext.getSharedPreferences(Shared_Pref_Name,Context.MODE_PRIVATE);
            return new User(

                    sharedPreferences.getInt("id",-1),
                    sharedPreferences.getString("userid",null),
                    sharedPreferences.getString("password",null),
                    sharedPreferences.getString("cname",null),
                    sharedPreferences.getInt("error",0),
                    sharedPreferences.getInt("expirein",-1)
            );


        }

        public void clear(){

            SharedPreferences sharedPreferences = mcontext.getSharedPreferences(Shared_Pref_Name,Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.apply();
        }

    }


