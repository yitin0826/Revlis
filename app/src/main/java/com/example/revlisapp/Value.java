package com.example.revlisapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.revlisapp.Adapter.DetailAdapter;
import com.example.revlisapp.Data.DataDetail;

import java.util.ArrayList;

public class Value extends Fragment {
    private View view;
    private RecyclerView recycler_detail;
    private RecyclerView.Adapter adapter_detail;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_value, container, false);
        recycler_detail = view.findViewById(R.id.recycler_detail);
        return view;
    }

    private void RecyclerViewDetail() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recycler_detail.setLayoutManager(linearLayoutManager);

        ArrayList<DataDetail> dataDetails = DataDetail.getList_AI();
        dataDetails.add(new DataDetail("心臟年紀 Heart Age", "35"));
        dataDetails.add(new DataDetail("情緒AI Emotion AI", "155"));

        adapter_detail = new DetailAdapter(dataDetails);
        recycler_detail.setAdapter(adapter_detail);
    }
}