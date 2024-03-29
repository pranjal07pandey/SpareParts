package com.example.lic.Main.Utilities;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String BaseURL = "http://afs.com.np/afs_billing/api/";
    private static final String BaseURLlocal = "http://192.168.10.101/antique_fashion_store/api/";
    private static final String BaseUrlfinal = "https://logisparktech.com/spare-parts/api/";


    private static RetrofitClient mInstance;
    private Retrofit retrofit;


    private RetrofitClient(){

            retrofit = new Retrofit.Builder().baseUrl(BaseUrlfinal)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

    }

    public static synchronized RetrofitClient getmInstance(){
        if (mInstance == null){
                mInstance = new RetrofitClient();
        }

        return mInstance;
    }

    public API getApi(){
        return retrofit.create(API.class);

    }


}
