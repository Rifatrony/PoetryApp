package com.example.poetryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.poetryapp.Adapter.PoetryAdapter;
import com.example.poetryapp.Models.PoetryModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    PoetryAdapter poetryAdapter;

    List<PoetryModel> poetryModelList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        poetryModelList.add(new PoetryModel(1, "Poetry is a form of literature that uses aesthetic and often rhythmic qualities of language − such as phonaesthetics, sound symbolism, and metre − to","Testing poet name","02-may-2020"));
        poetryModelList.add(new PoetryModel(1, "Poetry is a form of literature that uses aesthetic and often rhythmic qualities of language − such as phonaesthetics, sound symbolism, and metre − to","Testing poet name","02-may-2020"));
        poetryModelList.add(new PoetryModel(1, "Poetry is a form of literature that uses aesthetic and often rhythmic qualities of language − such as phonaesthetics, sound symbolism, and metre − to","Testing poet name","02-may-2020"));
        poetryModelList.add(new PoetryModel(1, "Poetry is a form of literature that uses aesthetic and often rhythmic qualities of language − such as phonaesthetics, sound symbolism, and metre − to","Testing poet name","02-may-2020"));
        poetryModelList.add(new PoetryModel(1, "Poetry is a form of literature that uses aesthetic and often rhythmic qualities of language − such as phonaesthetics, sound symbolism, and metre − to","Testing poet name","02-may-2020"));
        poetryModelList.add(new PoetryModel(1, "Poetry is a form of literature that uses aesthetic and often rhythmic qualities of language − such as phonaesthetics, sound symbolism, and metre − to","Testing poet name","02-may-2020"));
        poetryModelList.add(new PoetryModel(1, "Poetry is a form of literature that uses aesthetic and often rhythmic qualities of language − such as phonaesthetics, sound symbolism, and metre − to","Testing poet name","02-may-2020"));
        poetryModelList.add(new PoetryModel(1, "Poetry is a form of literature that uses aesthetic and often rhythmic qualities of language − such as phonaesthetics, sound symbolism, and metre − to","Testing poet name","02-may-2020"));

        initialization();
        setAdapter(poetryModelList);

    }

    private void initialization(){
        recyclerView = findViewById(R.id.poetry_recyclerview);
    }

    private void setAdapter(List<PoetryModel> poetryModels){

        poetryAdapter = new PoetryAdapter(this,poetryModels);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(poetryAdapter);
    }
}