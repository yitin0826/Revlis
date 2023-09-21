package com.example.revlisapp.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.revlisapp.Adapter.CategoryAdapter;
import com.example.revlisapp.Adapter.CategoryPageAdapter;
import com.example.revlisapp.Adapter.DetailAdapter;
import com.example.revlisapp.Data.DataCategory;
import com.example.revlisapp.Data.DataDetail;
import com.example.revlisapp.R;
import com.example.revlisapp.SignUpFragment.Info;
import com.example.revlisapp.Util.CommonUtil;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class Category extends Fragment{

    private TabLayout tab;
    private ViewPager2 viewPager;
//    private RecyclerView recycler_category, recycler_detail;
//    private RecyclerView.Adapter adapter_category, adapter_detail;
//    private FrameLayout container_detail;
//    private Fragment currentFragment;
//    private FragmentManager fragmentManager;
//    private FragmentTransaction transaction;
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
        tab = view.findViewById(R.id.tab);
        viewPager = view.findViewById(R.id.viewPager);
        setTab();
        setViewPager();
//        recycler_category = view.findViewById(R.id.recycler_category);
//        container_detail = view.findViewById(R.id.container_detail);
//        RecyclerViewCategory();
//        fragmentManager = getChildFragmentManager();
//        try {
//            selectFragment();
//        }catch (Exception e){
//            Log.e("whyCate",e.toString());
//        }
        return view;
    }

    private void setTab(){
        String[] tabNames = {"ai", "emotional", "body_ai", "rsp", "disease", "body_index", "hrv", "heartbeat", "signal"};
        String[] tabText = {"AI", "情緒", "生理AI", "呼吸", "疾病", "生理指數", "HRV", "心率", "訊號"};
        int[] tabIcons = {R.mipmap.ai, R.mipmap.emotional, R.mipmap.body_ai, R.mipmap.rsp, R.mipmap.disease, R.mipmap.body_index, R.mipmap.hrv, R.mipmap.heartbeat, R.mipmap.signal};

        for (int i = 0; i < tabNames.length; i++) {
            TabLayout.Tab tabItem = tab.newTab();
            tabItem.setCustomView(R.layout.view_category);

            ImageView iconImageView = tabItem.getCustomView().findViewById(R.id.img_category);
            TextView iconText = tabItem.getCustomView().findViewById(R.id.txt_category);
            iconImageView.setImageResource(tabIcons[i]);
            iconText.setText(tabText[i]);

            tab.addTab(tabItem);
        }
    }

    private void setViewPager(){
        String[] tabText = {"AI", "情緒", "生理AI", "呼吸", "疾病", "生理指數", "HRV", "心率", "訊號"};
        int[] tabIcons = {R.mipmap.ai, R.mipmap.emotional, R.mipmap.body_ai, R.mipmap.rsp, R.mipmap.disease, R.mipmap.body_index, R.mipmap.hrv, R.mipmap.heartbeat, R.mipmap.signal};
        CategoryPageAdapter adapter = new CategoryPageAdapter(getActivity());
        viewPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        viewPager.setAdapter(adapter);
        new TabLayoutMediator(tab, viewPager, true, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tabItem, int position) {
                tabItem.setCustomView(R.layout.view_category);

                ImageView iconImageView = tabItem.getCustomView().findViewById(R.id.img_category);
                TextView iconText = tabItem.getCustomView().findViewById(R.id.txt_category);
                iconImageView.setImageResource(tabIcons[position]);
                iconText.setText(tabText[position]);
                viewPager.setCurrentItem(tabItem.getPosition());
            }
        }).attach();
//        viewPager.setCurrentItem(0);
    }

    /** Category資料添加 **/
   /** private void RecyclerViewCategory() {
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
    }**/

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

//    @Override
//    public void onItemClick(int position) {
//        switch (position) {
//            case 0: /** AI **/
//                currentFragment = value;
//                try{
//                    value = Value.newInstance("ai");
//                    FragmentHideShow(value);
//                }catch (Exception e){
//                    Log.d("xxxxtex",e.toString());
//                }
//                break;
//            case 1: /** 情緒 **/
//                currentFragment = value;
//                try{
//                    value = Value.newInstance("emotional");
//                    FragmentHideShow(value);
//                }catch (Exception e){
//                    Log.d("xxxxtex",e.toString());
//                }
//                break;
//            case 2: /** 生理AI **/
//                currentFragment = value;
//                try{
//                    value = Value.newInstance("body_ai");
//                    FragmentHideShow(value);
//                }catch (Exception e){
//                    Log.d("xxxxtex",e.toString());
//                }
//                break;
//            case 3: /** 呼吸 **/
//                currentFragment = value;
//                try{
//                    value = Value.newInstance("rsp");
//                    FragmentHideShow(value);
//                }catch (Exception e){
//                    Log.d("xxxxtex",e.toString());
//                }
//                break;
//            case 4: /** 疾病 **/
//                currentFragment = value;
//                try{
////                    value = Value.newInstance("disease");
//                    FragmentHideShow(miView);
//                }catch (Exception e){
//                    Log.d("xxxxtex",e.toString());
//                }
//                break;
//            case 5: /** 生理指數 **/
//                currentFragment = value;
//                try{
//                    value = Value.newInstance("body_index");
//                    FragmentHideShow(value);
//                }catch (Exception e){
//                    Log.d("xxxxtex",e.toString());
//                }
//                break;
//            case 6: /** HRV **/
//                currentFragment = value;
//                try{
//                    value = Value.newInstance("hrv");
//                    FragmentHideShow(value);
//                }catch (Exception e){
//                    Log.d("xxxxtex",e.toString());
//                }
//                break;
//            case 7: /** 心率 **/
//                currentFragment = value;
//                try{
//                    value = Value.newInstance("heartbeat");
//                    FragmentHideShow(value);
//                }catch (Exception e){
//                    Log.d("xxxxtex",e.toString());
//                }
//                break;
//            case 8: /** 訊號 **/
//                currentFragment = value;
//                try{
//                    value = Value.newInstance("signal");
//                    FragmentHideShow(value);
//                }catch (Exception e){
//                    Log.d("xxxxtex",e.toString());
//                }
//                break;
//        }
//    }

//    private void FragmentHideShow(Fragment fg){
//        fragmentManager = getChildFragmentManager();
//        transaction= fragmentManager.beginTransaction();
//        if(!fg.isAdded()){
//            transaction.hide(currentFragment);
//            transaction.add(R.id.container_detail,fg);
//        }else{
//            transaction.hide(currentFragment);
//            transaction.show(fg);
//        }
//        currentFragment=fg;
//        transaction.commit();
//    }
}