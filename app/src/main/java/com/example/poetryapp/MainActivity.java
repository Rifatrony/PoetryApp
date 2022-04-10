package com.example.poetryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.poetryapp.Activity.AddPoetryActivity;
import com.example.poetryapp.Adapter.PoetryAdapter;
import com.example.poetryapp.Api.ApiClient;
import com.example.poetryapp.Api.ApiInterface;
import com.example.poetryapp.Models.PoetryModel;
import com.example.poetryapp.Response.GetPoetryResponse;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    PoetryAdapter poetryAdapter;
    FloatingActionButton fabAddPoetry;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialization();
        getData();

        fabAddPoetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddPoetryActivity.class);
                startActivity(intent);
            }
        });

    }

    private void initialization(){
        recyclerView = findViewById(R.id.poetry_recyclerview);
        fabAddPoetry = findViewById(R.id.fabAdd);

        Retrofit retrofit = ApiClient.getClient();

        apiInterface = retrofit.create(ApiInterface.class);
    }

    private void setAdapter(List<PoetryModel> poetryModels){

        poetryAdapter = new PoetryAdapter(this,poetryModels);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(poetryAdapter);
    }

    /*View all the data*/
    private void getData(){
        apiInterface.getPoetry().enqueue(new Callback<GetPoetryResponse>() {
            @Override
            public void onResponse(Call<GetPoetryResponse> call, Response<GetPoetryResponse> response) {

                try {
                    if (response!= null){
                        if (response.body().getStatus().equals("1")){

                            setAdapter(response.body().getData());

                        }
                        else {
                            Toast.makeText(MainActivity.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }

                }
                catch (Exception e){

                    System.out.println("Exception================>"+ e.getLocalizedMessage());

                }

            }

            @Override
            public void onFailure(Call<GetPoetryResponse> call, Throwable t) {
                System.out.println("Failure==========================>"+ t.getLocalizedMessage());
            }
        });
    }

}