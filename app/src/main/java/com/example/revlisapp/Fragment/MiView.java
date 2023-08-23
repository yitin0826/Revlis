package com.example.revlisapp.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.revlisapp.R;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator3;

public class MiView extends Fragment {
    private View view;
    private TextView txt_grade,txt_change,txt_beat,txt_diffuse;
    private ViewPager2 viewpager_compare;
    private CircleIndicator3 indicator_compare;
    private CompareAdapter compareAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mi_view, container, false);
        txt_grade = view.findViewById(R.id.txt_grade);
        txt_change = view.findViewById(R.id.txt_change);
        txt_beat = view.findViewById(R.id.txt_beat);
        txt_diffuse = view.findViewById(R.id.txt_diffuse);
        viewpager_compare = view.findViewById(R.id.viewpager_compare);
        indicator_compare = view.findViewById(R.id.indicator_compare);
        setCompareImage();
        return view;
    }

    private void setCompareImage(){
        List<Integer> imgUrl = new ArrayList<>();
        imgUrl.add(R.drawable.compare_af);
        imgUrl.add(R.drawable.compare_stdown);
        imgUrl.add(R.drawable.compare_stup);
        compareAdapter = new CompareAdapter(getContext(),imgUrl);
        viewpager_compare.setAdapter(compareAdapter);
        viewpager_compare.setCurrentItem(0);
        indicator_compare.setViewPager(viewpager_compare);
    }
}

class CompareAdapter extends RecyclerView.Adapter<CompareAdapter.CompareViewHolder>{
    private Context context;
    private List<Integer> imgUrl;

    public CompareAdapter(Context context, List<Integer> imgUrl){
        this.context = context;
        this.imgUrl = imgUrl;
    }

    @NonNull
    @Override
    public CompareViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_compare,parent,false);
        return new CompareViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CompareViewHolder holder, int position) {
        Glide.with(context).load(imgUrl.get(position)).into(holder.img_compare);
    }

    @Override
    public int getItemCount() {
        return imgUrl.size();
    }

    class CompareViewHolder extends RecyclerView.ViewHolder{

        private ImageView img_compare;

        public CompareViewHolder(@NonNull View itemView) {
            super(itemView);
            img_compare = itemView.findViewById(R.id.img_compare);
        }
    }
}