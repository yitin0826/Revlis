package com.example.revlisapp.Fragment;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.revlisapp.Adapter.ProfileAdapter;
import com.example.revlisapp.Data.DataProfile;
import com.example.revlisapp.R;
import com.example.revlisapp.SignUpActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class Profile extends Fragment implements ProfileAdapter.OnItemListener{

    private Button btn_profileOthers,btn_signout;
    ArrayList<DataProfile> profileList = new ArrayList<>();
    private RecyclerView recycler_profile;
    private RecyclerView.Adapter adapter_profile;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        btn_profileOthers = view.findViewById(R.id.btn_profileOthers);
        btn_signout = view.findViewById(R.id.btn_signout);
        recycler_profile = view.findViewById(R.id.recycler_profile);
        btn_profileOthers.setPaintFlags(btn_profileOthers.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        btn_profileOthers.setText("變更其他資料 ->");
        RecyclerViewProfile();
        return view;
    }

    /** Profile資料 **/
    public void RecyclerViewProfile(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recycler_profile.setLayoutManager(linearLayoutManager);

        profileList.add(new DataProfile("手機 Phone","0912345678"));
        profileList.add(new DataProfile("身高 Height","212cm"));
        profileList.add(new DataProfile("體重 Weight","60kg"));
        profileList.add(new DataProfile("年齡 Age","30歲"));
        profileList.add(new DataProfile("性別 Sex","男"));
        profileList.add(new DataProfile("吸菸 Smoke","有"));
        profileList.add(new DataProfile("喝酒 Drink","有"));
        profileList.add(new DataProfile("心血管疾病 Disease","無"));
        profileList.add(new DataProfile("高血壓 Hbp","無"));
        profileList.add(new DataProfile("糖尿病 Diabetes","無"));

        adapter_profile = new ProfileAdapter(profileList, this);
        recycler_profile.setAdapter(adapter_profile);
    }

    @Override
    public void onItemClick(int position) {
        switch (position){
            case 0: //手機
                Toast.makeText(getContext(),"手機號碼無法更改",Toast.LENGTH_SHORT).show();
                break;
            case 1: //身高
            case 2: //體重/
                startNew("info");
                break;
            case 3: //年齡
                Toast.makeText(getContext(),"出生日期無法更改",Toast.LENGTH_SHORT).show();
                break;
            case 4: //性別
                Toast.makeText(getContext(),"性別無法更改",Toast.LENGTH_SHORT).show();
                break;
            case 5: //吸菸
            case 6: //喝酒
                startNew("habit");
                break;
            case 7: //心血管疾病
            case 8: //高血壓
            case 9: //糖尿病
                startNew("health");
                break;
        }
    }

    private void startNew(String page){
        Intent intent = new Intent(getActivity(), SignUpActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("page",page);
        bundle.putString("phone",profileList.get(0).getProfile_data());
        bundle.putString("height",profileList.get(1).getProfile_data());
        bundle.putString("weight",profileList.get(2).getProfile_data());
        bundle.putString("age",profileList.get(3).getProfile_data());
        bundle.putString("sex",profileList.get(4).getProfile_data());
        bundle.putString("smoke",profileList.get(5).getProfile_data());
        bundle.putString("drink",profileList.get(6).getProfile_data());
        bundle.putString("disease",profileList.get(7).getProfile_data());
        bundle.putString("hbp",profileList.get(8).getProfile_data());
        bundle.putString("diabetes",profileList.get(9).getProfile_data());
        intent.putExtras(bundle);
        startActivity(intent);
    }
}