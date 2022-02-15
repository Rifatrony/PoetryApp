package com.example.poetryapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.poetryapp.Models.PoetryModel;
import com.example.poetryapp.R;

import java.util.List;

public class PoetryAdapter extends RecyclerView.Adapter<PoetryAdapter.ViewHolder> {

    Context context;
    List<PoetryModel> poetryModels;

    public PoetryAdapter(Context context, List<PoetryModel> poetryModels) {
        this.context = context;
        this.poetryModels = poetryModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.poetry_list_design,null);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        PoetryModel data = poetryModels.get(position);

        holder.poetName.setText(data.getPoet_name());
        holder.poetry.setText(data.getPoetry_data());
        holder.date_time.setText(data.getDate_time());


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

            updateBtn = itemView.findViewById(R.id.update_btn);
            deleteBtn = itemView.findViewById(R.id.delete_btn);

        }
    }

}
