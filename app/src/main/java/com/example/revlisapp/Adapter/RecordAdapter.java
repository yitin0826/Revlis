package com.example.revlisapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.revlisapp.Data.DataCategory;
import com.example.revlisapp.Data.DataRecord;
import com.example.revlisapp.R;

import java.util.ArrayList;

public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.RecordViewHolder>{

    private final RecordAdapter.OnItemListener onItemListener;
    ArrayList<DataRecord> recordList;

    public RecordAdapter(ArrayList<DataRecord> recordList, RecordAdapter.OnItemListener onItemListener){
        this.recordList = recordList;
        this.onItemListener = onItemListener;
    }

    @NonNull
    @Override
    public RecordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.view_record,parent,false);
        return new RecordAdapter.RecordViewHolder(view, onItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecordViewHolder holder, int position) {
        holder.txt_recordDate.setText(recordList.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return recordList.size();
    }

    public interface OnItemListener{
        void onItemClick(int position);
    }

    public class RecordViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView txt_recordDate;
        private final RecordAdapter.OnItemListener onItemListener;

        public RecordViewHolder(@NonNull View itemView, RecordAdapter.OnItemListener onItemListener) {
            super(itemView);
            txt_recordDate = itemView.findViewById(R.id.txt_recordDate);
            this.onItemListener = onItemListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onItemListener.onItemClick(getAdapterPosition());
        }
    }
}
