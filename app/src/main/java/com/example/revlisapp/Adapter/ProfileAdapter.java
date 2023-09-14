package com.example.revlisapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.revlisapp.Data.DataProfile;
import com.example.revlisapp.R;

import java.util.ArrayList;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder> {
    private final OnItemListener onItemListener;
    ArrayList<DataProfile> profileList;

    public ProfileAdapter(ArrayList<DataProfile> profileList, OnItemListener onItemListener){
        this.profileList = profileList;
        this.onItemListener = onItemListener;
    }

    @NonNull
    @Override
    public ProfileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate  = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_profile,parent,false);
        return new ProfileViewHolder(inflate, onItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileViewHolder holder, int position) {
        holder.txt_profileTitle.setText(profileList.get(position).getProfile_title());
        holder.txt_profileData.setText(profileList.get(position).getProfile_data());
    }

    @Override
    public int getItemCount() {
        return profileList.size();
    }

    public interface OnItemListener{
        void  onItemClick(int position);
    }

    public class ProfileViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView txt_profileTitle,txt_profileData;
        private ProfileAdapter.OnItemListener onItemListener;

        public ProfileViewHolder(@NonNull View itemView, ProfileAdapter.OnItemListener onItemListener) {
            super(itemView);
            txt_profileTitle = itemView.findViewById(R.id.txt_profileTitle);
            txt_profileData = itemView.findViewById(R.id.txt_profileData);
            this.onItemListener = onItemListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onItemListener.onItemClick(getAdapterPosition());
        }
    }
}
