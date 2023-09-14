package com.example.revlisapp.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.revlisapp.Adapter.DetailAdapter;
import com.example.revlisapp.Data.DataDetail;
import com.example.revlisapp.R;

import java.util.ArrayList;

public class Value extends Fragment {
    private View view;
    private RecyclerView recycler_detail;
    private RecyclerView.Adapter adapter_detail;
    private String fg = "";

    public static Value newInstance(String data){
        Value value = new Value();
        Bundle args = new Bundle();
        args.putString("key",data);
        value.setArguments(args);
        return value;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null){
            fg = getArguments().getString("key");
            Log.d("getKey", fg);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_value, container, false);
        recycler_detail = view.findViewById(R.id.recycler_detail);
        RecyclerViewDetail();
        return view;
    }

    private void RecyclerViewDetail() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recycler_detail.setLayoutManager(linearLayoutManager);

        if (fg.equals("ai")){
            ArrayList<DataDetail> dataDetails = DataDetail.getList_AI();
//            dataDetails.add(new DataDetail("心臟年紀 Heart Age", "35"));
//            dataDetails.add(new DataDetail("情緒AI Emotion AI", "155"));
            adapter_detail = new DetailAdapter(dataDetails);
            recycler_detail.setAdapter(adapter_detail);
        }else if (fg.equals("emotional")){
            ArrayList<DataDetail> dataDetails_Emotional = DataDetail.getList_Emotional();
//            dataDetails_Emotional.add(new DataDetail("活力指數", "35"));
//            dataDetails_Emotional.add(new DataDetail("壓力", "155"));
            adapter_detail = new DetailAdapter(dataDetails_Emotional);
            recycler_detail.setAdapter(adapter_detail);
        }else if (fg.equals("body_ai")){
            ArrayList<DataDetail> dataDetails_Body_AI = DataDetail.getList_Body_AI();
//            dataDetails_Body_AI.add(new DataDetail("舒張壓", "35"));
//            dataDetails_Body_AI.add(new DataDetail("收縮壓", "155"));
            adapter_detail = new DetailAdapter(dataDetails_Body_AI);
            recycler_detail.setAdapter(adapter_detail);
        }else if (fg.equals("rsp")){
            ArrayList<DataDetail> dataDetails_RSP = DataDetail.getList_RSP();
//            dataDetails_RSP.add(new DataDetail("RSP", "35"));
//            dataDetails_RSP.add(new DataDetail("PRQ", "155"));
            adapter_detail = new DetailAdapter(dataDetails_RSP);
            recycler_detail.setAdapter(adapter_detail);
        }else if (fg.equals("disease")){
            ArrayList<DataDetail> dataDetails_Disease = DataDetail.getList_Disease();
//            dataDetails_Disease.add(new DataDetail("AF", "35"));
//            dataDetails_Disease.add(new DataDetail("PVC", "155"));
            adapter_detail = new DetailAdapter(dataDetails_Disease);
            recycler_detail.setAdapter(adapter_detail);
        }else if (fg.equals("body_index")){
            ArrayList<DataDetail> dataDetails_Body_index = DataDetail.getList_Body_index();
//            dataDetails_Body_index.add(new DataDetail("健康分數", "35"));
            adapter_detail = new DetailAdapter(dataDetails_Body_index);
            recycler_detail.setAdapter(adapter_detail);
        }else if (fg.equals("hrv")){
            ArrayList<DataDetail> dataDetails_HRV = DataDetail.getList_HRV();
//            dataDetails_HRV.add(new DataDetail("SDNN", "35"));
//            dataDetails_HRV.add(new DataDetail("RMSSD", "155"));
            adapter_detail = new DetailAdapter(dataDetails_HRV);
            recycler_detail.setAdapter(adapter_detail);
        }else if (fg.equals("heartbeat")){
            ArrayList<DataDetail> dataDetails_Heartbeat = DataDetail.getList_Heartbeat();
//            dataDetails_Heartbeat.add(new DataDetail("MaxValue", "35"));
//            dataDetails_Heartbeat.add(new DataDetail("MinValue", "155"));
            adapter_detail = new DetailAdapter(dataDetails_Heartbeat);
            recycler_detail.setAdapter(adapter_detail);
        }else if (fg.equals("signal")){
            ArrayList<DataDetail> dataDetails_signal = DataDetail.getList_signal();
//            dataDetails_signal.add(new DataDetail("紅三燈", "35"));
//            dataDetails_signal.add(new DataDetail("訊號分數", "155"));
            adapter_detail = new DetailAdapter(dataDetails_signal);
            recycler_detail.setAdapter(adapter_detail);
        }

    }
}