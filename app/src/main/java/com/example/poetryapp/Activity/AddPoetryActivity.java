package com.example.poetryapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.poetryapp.Api.ApiClient;
import com.example.poetryapp.Api.ApiInterface;
import com.example.poetryapp.R;
import com.example.poetryapp.Response.DeleteResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AddPoetryActivity extends AppCompatActivity {

    EditText name, data;
    Button saveBtn;

    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_poetry);

        initialization();



        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String poetryDataString =  data.getText().toString();
                String poetNameString =  name.getText().toString();


                if (poetryDataString.equals("")){
                    data.setError("Field is empty");
                }
                else {

                    if (poetNameString.equals("")){
                        name.setError("Field is blank");
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Here will call api", Toast.LENGTH_SHORT).show();

                        callapi(poetryDataString, poetNameString);
                    }
                }

            }
        });

    }

    private void initialization() {

        name = findViewById(R.id.poetNameEditTextId);
        data = findViewById(R.id.poetryDataEditTextId);
        saveBtn = findViewById(R.id.savePoetryButtonId);

        Retrofit retrofit = ApiClient.getClient();

        apiInterface = retrofit.create(ApiInterface.class);

    }

    private  void callapi(String poetryData, String poetName){
        apiInterface.addpoetry(poetryData, poetName).enqueue(new Callback<DeleteResponse>() {
            @Override
            public void onResponse(Call<DeleteResponse> call, Response<DeleteResponse> response) {

                try {

                    if (response.body().getStatus().equals("1")){

                        Toast.makeText(AddPoetryActivity.this, "Added Successfully", Toast.LENGTH_LONG).show();
                    }
                    else {
                        Toast.makeText(AddPoetryActivity.this, "Not Added Successfully", Toast.LENGTH_LONG).show();

                    }
                }catch (Exception e)
                {
                    Toast.makeText(AddPoetryActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<DeleteResponse> call, Throwable t) {

                Toast.makeText(AddPoetryActivity.this,"Problem Occure"+ t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}