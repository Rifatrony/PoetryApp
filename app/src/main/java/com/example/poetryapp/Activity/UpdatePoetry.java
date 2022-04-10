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

public class UpdatePoetry extends AppCompatActivity {

    EditText updateData;
    Button updateBtn;

    int poetryId;
    String poetryDataString;

    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_poetry);

        initialization();

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String p_data =  updateData.getText().toString();

                if (p_data.equals("")){
                    updateData.setError("Field is blank");
                }
                else {
                    callapi(p_data,poetryId+"");
                    Toast.makeText(getApplicationContext(), "call Api", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initialization() {

        updateData = findViewById(R.id.updatePoetryDataEditTextId);
        updateBtn = findViewById(R.id.updatePoetryButtonId);

        poetryId = getIntent().getIntExtra("p_id",0);
        poetryDataString = getIntent().getStringExtra("p_data");

        updateData.setText(poetryDataString);

        Retrofit retrofit = ApiClient.getClient();

        apiInterface = retrofit.create(ApiInterface.class);

    }

    private  void callapi(String pData, String pid){

        apiInterface.updatePoetry(pData, pid).enqueue(new Callback<DeleteResponse>() {
            @Override
            public void onResponse(Call<DeleteResponse> call, Response<DeleteResponse> response) {

                try {

                    if (response.body().getStatus().equals("1")){
                        Toast.makeText(getApplicationContext(), "Updated Successfully", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Something Went Wrong", Toast.LENGTH_SHORT).show();
                    }

                }catch (Exception e){

                }

            }

            @Override
            public void onFailure(Call<DeleteResponse> call, Throwable t) {

            }
        });
    }
}