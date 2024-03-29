package com.example.revlisapp.Adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.revlisapp.Data.DataDetail;
import com.example.revlisapp.R;

import java.util.ArrayList;

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.ViewHolder> {

    ArrayList<DataDetail> detailList;

    public DetailAdapter(ArrayList<DataDetail> detailList){
        this.detailList = detailList;
    }

    @NonNull
    @Override
    public DetailAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_detail,parent,false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailAdapter.ViewHolder holder, int position) {
        holder.txt_title.setText(detailList.get(position).getTitle());
        holder.txt_value.setText(detailList.get(position).getValue());
        if (holder.txt_value.getText().toString() != "尚未獲取紀錄"){
            holder.txt_value.setTextColor(Color.parseColor("#6D8B75"));
            holder.txt_value.setTextSize(30);
        }
    }

    @Override
    public int getItemCount() {
        return detailList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txt_title,txt_value;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_title = itemView.findViewById(R.id.txt_title);
            txt_value = itemView.findViewById(R.id.txt_value);
        }
    }
}
