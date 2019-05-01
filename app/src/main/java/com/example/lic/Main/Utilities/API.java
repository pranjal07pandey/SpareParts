package com.example.lic.Main.Utilities;



import com.example.lic.Main.Datamodel.Credit_Datamodel;
import com.example.lic.Main.Datamodel.Delivered_Datamodel;
import com.example.lic.Main.Datamodel.Insightsdatamodel;
import com.example.lic.Main.Datamodel.Inventory_Model;
import com.example.lic.Main.Datamodel.Reportdata;
import com.example.lic.Main.Datamodel.Totaldata;
import com.example.lic.Main.Datamodel.Undelivered_Datamodel;
import com.example.lic.Main.Datamodel.User;
import com.example.lic.Main.Datamodel.Wholesale_Datamodel;
import com.example.lic.Main.main.Credit;
import com.example.lic.Main.main.Inventory;
import com.example.lic.Main.main.Report;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface API {

    @GET("getDistinctProducts")
    Call<List<Inventory_Model>> getInventoryitems(@Query("PAN") String pan);

    @GET("getAllInventory.php/")
    Call<List<Inventory_Model>> getspecificdata(@Query("id") int id,@Query("PAN") String pan);

    @FormUrlEncoded
    @POST("userLogin.php/")
    Call<User> verifylogin(
            @Field("username") String username,
            @Field("password") String password
    );

    @GET("getProfitReport.php/")
    Call<List<Reportdata>> getprofitdata(@Query("start") String start,@Query("end") String end,@Query("PAN") String pan);

    @GET("getInventoryReport.php/")
    Call<List<Reportdata>> getinventoryreportdata(@Query("start") String start,@Query("end") String end,@Query("PAN") String pan);

    @GET("getImportReport.php/")
    Call<List<Reportdata>> getimportdata(@Query("start") String start,@Query("end") String end,@Query("PAN") String pan);

    @GET("getSalesReport.php/")
    Call<List<Reportdata>> getsalesdata(@Query("start") String start,@Query("end") String end, @Query("PAN") String pan);

    @GET("getTotal.php/")
    Call<Totaldata> gettotaldata(@Query("start") String start,@Query("end") String end,@Query("PAN") String pan);

    @GET("getInsights1.php/")
    Call<List<Insightsdatamodel>> getinsightsdata(@Query("PAN") String pan);

    @GET("getInsights6.php/")
    Call<List<Insightsdatamodel>> getinsightsdatalong(@Query("PAN") String pan);

    @GET("getWholeseller.php/")
    Call<List<Wholesale_Datamodel>> getwholesalemode(@Query("PAN") String pan);

    @GET("getCredit.php/")
    Call<List<Credit_Datamodel>> getcreditmode();

    @GET("getUndelivered.php/")
    Call<List<Delivered_Datamodel>> getdeliveredmode();


    @GET("getUndelivered.php/")
    Call<List<Undelivered_Datamodel>> getundeliveredmode();










}