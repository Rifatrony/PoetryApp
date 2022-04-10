package com.example.poetryapp.Adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.poetryapp.Activity.UpdatePoetry;
import com.example.poetryapp.Api.ApiClient;
import com.example.poetryapp.Api.ApiInterface;
import com.example.poetryapp.Models.PoetryModel;
import com.example.poetryapp.R;
import com.example.poetryapp.Response.DeleteResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PoetryAdapter extends RecyclerView.Adapter<PoetryAdapter.ViewHolder> {

    Context context;
    List<PoetryModel> poetryModels;
    ApiInterface apiInterface;

    public PoetryAdapter(Context context, List<PoetryModel> poetryModels) {
        this.context = context;
        this.poetryModels = poetryModels;
        Retrofit retrofit = ApiClient.getClient();
        apiInterface = retrofit.create(ApiInterface.class);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.poetry_list_design,null);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        PoetryModel data = poetryModels.get(position);

        holder.poetName.setText(data.getPoet_name());
        holder.poetry.setText(data.getPoetry_data());
        holder.date_time.setText(data.getDate_time());

        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deletePoetry(data.getId()+"", position);
            }
        });

        holder.updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(context, data.getId()+"", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(context, UpdatePoetry.class);
                intent.putExtra("p_id", data.getId());
                intent.putExtra("p_data", data.getPoetry_data());
                context.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return poetryModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView poetName, poetry, date_time;
        Button updateBtn, deleteBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            poetName = itemView.findViewById(R.id.textViewPoetName);
            poetry = itemView.findViewById(R.id.textViewPoetryName);
            date_time = itemView.findViewById(R.id.textViewPoetryDateAndTime);

            updateBtn = itemView.findViewById(R.id.updateBtnId);
            deleteBtn = itemView.findViewById(R.id.deleteBtnId);

            /*updateBtn = itemView.findViewById(R.id.update_btn);
            deleteBtn = itemView.findViewById(R.id.delete_btn);*/

        }
    }

    private void deletePoetry(String id, int pose){

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Delete Poetry")
                .setMessage("Do you want to delete this Poetry?")
                .setIcon(R.drawable.add)
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        apiInterface.deletePoetry(id).enqueue(new Callback<DeleteResponse>() {
                            @Override
                            public void onResponse(Call<DeleteResponse> call, Response<DeleteResponse> response) {
                                try {
                                    if (response !=null){
                                        Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_LONG).show();
                                    }

                                    if (response.body().getStatus().equals("1")){
                                        poetryModels.remove(pose);
                                        notifyDataSetChanged();
                                    }

                                }catch (Exception e){
                                    Toast.makeText(context, e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<DeleteResponse> call, Throwable t) {

                                Toast.makeText(context, t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

                            }
                        });


                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

        builder.show();



    }

}
