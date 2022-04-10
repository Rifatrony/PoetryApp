package com.example.poetryapp.Api;

import com.example.poetryapp.Response.DeleteResponse;
import com.example.poetryapp.Response.GetPoetryResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

    //get all data here

    @GET("getpoetry.php")
    Call<GetPoetryResponse> getPoetry();

    //delete poetry
    @FormUrlEncoded
    @POST("deletepoetry.php")
    Call<DeleteResponse> deletePoetry(@Field("poetry_id") String poetry_id);


    //add poetry
    @FormUrlEncoded
    @POST("addpoetry.php")
    Call<DeleteResponse> addpoetry(@Field("poetry") String poetryData, @Field("poet_name") String poet_name);

    //Update Poetry

    @FormUrlEncoded
    @POST("updatepoetry.php")
    Call<DeleteResponse> updatePoetry(@Field("poetry_data") String poetryData, @Field("id") String id);




}
