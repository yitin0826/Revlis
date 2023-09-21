package com.example.revlisapp.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.revlisapp.Fragment.MiView;
import com.example.revlisapp.Fragment.Value;

public class CategoryPageAdapter extends FragmentStateAdapter {

    Value value = new Value();

    public CategoryPageAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                value = Value.newInstance("ai");
                return value;
            case 1:
                value = Value.newInstance("emotional");
                return value;
            case 2:
                value = Value.newInstance("body_ai");
                return value;
            case 3:
                value = Value.newInstance("rsp");
                return value;
            case 4:
                return new MiView();
            case 5:
                value = Value.newInstance("body_index");
                return value;
            case 6:
                value = Value.newInstance("hrv");
                return value;
            case 7:
                value = Value.newInstance("heartbeat");
                return value;
            case 8:
                value = Value.newInstance("signal");
                return value;
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return 9;
    }
}
