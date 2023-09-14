package com.example.revlisapp.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.revlisapp.Adapter.CategoryAdapter;
import com.example.revlisapp.Adapter.DetailAdapter;
import com.example.revlisapp.Data.DataCategory;
import com.example.revlisapp.Data.DataDetail;
import com.example.revlisapp.R;
import com.example.revlisapp.SignUpFragment.Info;
import com.example.revlisapp.Util.CommonUtil;

import java.util.ArrayList;
import java.util.List;

public class Category extends Fragment implements CategoryAdapter.OnItemListener{

    private RecyclerView recycler_category, recycler_detail;
    private RecyclerView.Adapter adapter_category, adapter_detail;
    private FrameLayout container_detail;
    private Fragment currentFragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    MiView miView = new MiView();
    Value value = new Value();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        recycler_category = view.findViewById(R.id.recycler_category);
        container_detail = view.findViewById(R.id.container_detail);
        RecyclerViewCategory();
        fragmentManager = getChildFragmentManager();
        try {
            selectFragment();
        }catch (Exception e){
            Log.e("whyCate",e.toString());
        }
        return view;
    }

    /** Category資料添加 **/
    private void RecyclerViewCategory() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recycler_category.setLayoutManager(linearLayoutManager);

        ArrayList<DataCategory> categoryList = new ArrayList<>();
        categoryList.add(new DataCategory("ai", "AI"));
        categoryList.add(new DataCategory("emotional", "情緒"));
        categoryList.add(new DataCategory("body_ai", "生理AI"));
        categoryList.add(new DataCategory("rsp", "呼吸"));
        categoryList.add(new DataCategory("disease", "疾病"));
        categoryList.add(new DataCategory("body_index", "生理指數"));
        categoryList.add(new DataCategory("hrv", "HRV"));
        categoryList.add(new DataCategory("heartbeat", "心率"));
        categoryList.add(new DataCategory("signal", "訊號"));

        adapter_category = new CategoryAdapter(categoryList, this);
        recycler_category.setAdapter(adapter_category);
    }

    private void selectFragment(){
        fragmentManager = getChildFragmentManager();
        transaction = fragmentManager.beginTransaction();
        value = Value.newInstance("ai");
        transaction.add(R.id.container_detail,value);
        transaction.commit();
    }

//    /** Detail資料添加 **/
//    private void RecyclerViewDetail() {
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
//        recycler_detail.setLayoutManager(linearLayoutManager);
//
//        ArrayList<DataDetail> dataDetails = DataDetail.getList_AI();
//        dataDetails.add(new DataDetail("心臟年紀 Heart Age", "35"));
//        dataDetails.add(new DataDetail("情緒AI Emotion AI", "155"));
//
//        adapter_detail = new DetailAdapter(dataDetails);
//        recycler_detail.setAdapter(adapter_detail);
//    }

    @Override
    public void onItemClick(int position) {
        switch (position) {
            case 0: /** AI **/
                currentFragment = value;
                try{
                    value = Value.newInstance("ai");
                    FragmentHideShow(value);
                }catch (Exception e){
                    Log.d("xxxxtex",e.toString());
                }
                break;
            case 1: /** 情緒 **/
                currentFragment = value;
                try{
                    value = Value.newInstance("emotional");
                    FragmentHideShow(value);
                }catch (Exception e){
                    Log.d("xxxxtex",e.toString());
                }
                break;
            case 2: /** 生理AI **/
                currentFragment = value;
                try{
                    value = Value.newInstance("body_ai");
                    FragmentHideShow(value);
                }catch (Exception e){
                    Log.d("xxxxtex",e.toString());
                }
                break;
            case 3: /** 呼吸 **/
                currentFragment = value;
                try{
                    value = Value.newInstance("rsp");
                    FragmentHideShow(value);
                }catch (Exception e){
                    Log.d("xxxxtex",e.toString());
                }
                break;
            case 4: /** 疾病 **/
                currentFragment = value;
                try{
//                    value = Value.newInstance("disease");
                    FragmentHideShow(miView);
                }catch (Exception e){
                    Log.d("xxxxtex",e.toString());
                }
                break;
            case 5: /** 生理指數 **/
                currentFragment = value;
                try{
                    value = Value.newInstance("body_index");
                    FragmentHideShow(value);
                }catch (Exception e){
                    Log.d("xxxxtex",e.toString());
                }
                break;
            case 6: /** HRV **/
                currentFragment = value;
                try{
                    value = Value.newInstance("hrv");
                    FragmentHideShow(value);
                }catch (Exception e){
                    Log.d("xxxxtex",e.toString());
                }
                break;
            case 7: /** 心率 **/
                currentFragment = value;
                try{
                    value = Value.newInstance("heartbeat");
                    FragmentHideShow(value);
                }catch (Exception e){
                    Log.d("xxxxtex",e.toString());
                }
                break;
            case 8: /** 訊號 **/
                currentFragment = value;
                try{
                    value = Value.newInstance("signal");
                    FragmentHideShow(value);
                }catch (Exception e){
                    Log.d("xxxxtex",e.toString());
                }
                break;
        }
    }

    private void FragmentHideShow(Fragment fg){
        fragmentManager = getChildFragmentManager();
        transaction= fragmentManager.beginTransaction();
        if(!fg.isAdded()){
            transaction.hide(currentFragment);
            transaction.add(R.id.container_detail,fg);
        }else{
            transaction.hide(currentFragment);
            transaction.show(fg);
        }
        currentFragment=fg;
        transaction.commit();
    }
}