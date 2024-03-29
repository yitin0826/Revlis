package com.example.revlisapp.Fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.revlisapp.Data.DataFeature;
import com.example.revlisapp.Data.DataImage;
import com.example.revlisapp.Adapter.FeatureAdapter;
import com.example.revlisapp.Adapter.ImageNetAdapter;
import com.example.revlisapp.R;
import com.example.revlisapp.Util.CommonUtil;
import com.example.revlisapp.WrapContentLinearLayoutManager;
import com.obsez.android.lib.filechooser.ChooserDialog;
import com.youth.banner.Banner;
import com.youth.banner.indicator.CircleIndicator;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class HomePage extends Fragment {

    /** UI **/
    private View view;
    private Banner banner;
    private TextView txt_filename;
    private Button btn_choose,btn_count,btn_CountCancel,btn_CountSure,btn_addSelect,btn_addCancel,btn_addDone;
    private ImageButton imgbtn_add;
    private Spinner spinner_hdpSBP,spinner_hdpDBP,spinner_morningDIA,spinner_aftermealDIA,spinner_mealStatus,spinner_medicationStatus;
    private RecyclerView recycler_feature;
    private RecyclerView.Adapter adapter_feature;
    private ChooserDialog chooserDialog;
    private Dialog dialogFeature, dialogValueInput,dialogCount;
    private String filePath,xName;
    private List<String> checkFeature = new ArrayList<>();
    ArrayList<DataFeature> featureList = new ArrayList<>();
    Unbinder unbinder;
    @BindViews({R.id.check_dbp, R.id.check_sbp, R.id.check_bs, R.id.check_hr, R.id.check_sdnn, R.id.check_rmssd})
    List<CheckBox> dialogCheck;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home_page,container,false);
        initParameter();
        initChooser();
        return view;
    }

    @Override
    public void onResume(){
        super.onResume();
        banner.start();
    }

    @Override
    public void onPause(){
        super.onPause();
        banner.stop();
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        //unbinder.unbind();
    }

    /** 宣告參數 **/
    public void initParameter(){
        banner = view.findViewById(R.id.banner);
        recycler_feature = view.findViewById(R.id.recycler_feature);
        txt_filename = view.findViewById(R.id.txt_filename);
        btn_choose = view.findViewById(R.id.btn_choose);
        btn_count = view.findViewById(R.id.btn_count);
        imgbtn_add = view.findViewById(R.id.imgbtn_add);
        btn_count.setOnClickListener(lis);
        imgbtn_add.setOnClickListener(lis);
        useBanner();
        RecyclerViewFeature();
    }

    /** 選擇檔案 **/
    public void initChooser(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                chooserDialog = new ChooserDialog(getActivity())
                        .withStartFile("/storage/emulated/0/")
                        .withOnCancelListener(new DialogInterface.OnCancelListener() {
                            @Override
                            public void onCancel(DialogInterface dialogInterface) {
                                dialogInterface.cancel();
                            }
                        })
                        .withChosenListener(new ChooserDialog.Result() {
                            @Override
                            public void onChoosePath(String dir, File dirFile) {
                                filePath = dir;
                                File file = new File(dir);
                                xName = file.getName();
                                Log.d("xName",xName);
                                txt_filename.setText(xName);
                                txt_filename.setTextSize(16);
                                buildValueInputDialog();
                            }
                        })
                        .withOnBackPressedListener(dialog -> chooserDialog.goBack())
                        .withOnLastBackPressedListener(dialog -> dialog.cancel());
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        btn_choose.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                chooserDialog.build();
                                chooserDialog.show();
                            }
                        });
                    }
                });
            }
        });
        thread.start();
    }

    View.OnClickListener lis = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btn_choose:
                    break;
                case R.id.btn_count:
                    buildCountDialog();
                    break;
                case R.id.imgbtn_add:
                    buildFeatureDialog();
                    break;
                case R.id.btn_addSelect:
                    break;
                case R.id.btn_addCancel:
                    dialogFeature.dismiss();
                    break;
                case R.id.btn_addDone:
                    Toast.makeText(getContext(),checkFeature.toString(),Toast.LENGTH_SHORT).show();
                    notifyDataChanged();
                    unbinder.unbind();
                    dialogFeature.dismiss();
                    break;
                case R.id.btn_CountCancel:
                    dialogValueInput.dismiss();
                    break;
                case R.id.btn_CountSure:
                    break;
            }
        }
    };

    /** 建立Feature Dialog **/
    public void buildFeatureDialog(){
        dialogFeature = new Dialog(getActivity(),R.style.custom_dialog);
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_add,null);
        dialogFeature.setContentView(dialogView);
        /** 宣告UI參數 **/
        btn_addSelect = dialogView.findViewById(R.id.btn_addSelect);
        btn_addCancel = dialogView.findViewById(R.id.btn_addCancel);
        btn_addDone = dialogView.findViewById(R.id.btn_addDone);
        unbinder = ButterKnife.bind(this,dialogView);
        dialogFeature.show();
        Window window = dialogFeature.getWindow();
        window.setLayout(WindowManager.LayoutParams.WRAP_CONTENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);
        WindowManager.LayoutParams lp = dialogFeature.getWindow().getAttributes();
        lp.width = ViewGroup.LayoutParams.WRAP_CONTENT;
        lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        dialogFeature.getWindow().setAttributes(lp);
        dialogFeature.setCancelable(false);
        btn_addSelect.setOnClickListener(lis);
        btn_addDone.setOnClickListener(lis);
        btn_addCancel.setOnClickListener(lis);
    }

    /** 建立計算所需值輸入 Dialog **/
    public void buildValueInputDialog(){
        dialogValueInput = new Dialog(getActivity(),R.style.custom_dialog);
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_inputvalue, null);
        dialogValueInput.setContentView(dialogView);
        spinner_hdpSBP = dialogView.findViewById(R.id.spinner_hbpSBP);
        spinner_hdpDBP = dialogView.findViewById(R.id.spinner_hbpDBP);
        spinner_morningDIA = dialogView.findViewById(R.id.spinner_morningDIA);
        spinner_aftermealDIA = dialogView.findViewById(R.id.spinner_aftermealDIA);
        spinner_mealStatus = dialogView.findViewById(R.id.spinner_mealStatus);
        spinner_medicationStatus = dialogView.findViewById(R.id.spinner_medicationStatus);
        btn_CountCancel = dialogView.findViewById(R.id.btn_CountCancel);
        btn_CountSure = dialogView.findViewById(R.id.btn_CountSure);
        dialogValueInput.show();
        setSpinnerValue();
        WindowManager.LayoutParams lp = dialogValueInput.getWindow().getAttributes();
        WindowManager windowManager = getActivity().getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        int width = display.getWidth();
        int height = display.getHeight();
        lp.width = (int)(width * 0.5);
        lp.height = (int)(height * 0.5);
        btn_CountCancel.setOnClickListener(lis);
        btn_CountSure.setOnClickListener(lis);
    }

    /** 建立計算進度條 Dialog **/
    public void buildCountDialog(){
        dialogCount = new Dialog(getActivity(),R.style.custom_dialog);
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_count, null);
        dialogCount.setContentView(dialogView);
        /** 宣告UI **/
        ProgressBar progress_bar = dialogView.findViewById(R.id.progress_bar);
        TextView progress_text = dialogView.findViewById(R.id.progress_text);
        Button btn_resultconfirm = dialogView.findViewById(R.id.btn_resultconfirm);
        TextView txt_end = dialogView.findViewById(R.id.txt_end);
        dialogCount.show();
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                int i=0;
                if (i<=100){
                    progress_text.setText(""+i+"%");
                    progress_bar.setProgress(i);
                    i+=1;
                    handler.postDelayed(this,900);
                }
                else {
                    handler.removeCallbacks(this);
                }

                if (i>100){
                    txt_end.setVisibility(View.VISIBLE);
                    btn_resultconfirm.setVisibility(View.VISIBLE);
                    btn_resultconfirm.setOnClickListener(lis);
                }
            }
        },200);
    }

    /** Spinner值設定 **/
    private void setSpinnerValue(){
        List<String> hbpSBP = Arrays.asList("1");
        List<String> hbpDBP = Arrays.asList("1");
        List<String> morningDIA = Arrays.asList("1");
        List<String> aftermealDIA = Arrays.asList("1");
        List<String> mealStatus = Arrays.asList("1");
        List<String> medicationStatus = Arrays.asList("1");
        ArrayAdapter adapter1 = new ArrayAdapter(getContext(),R.layout.selected_item,hbpSBP);
        ArrayAdapter adapter2 = new ArrayAdapter(getContext(),R.layout.selected_item,hbpDBP);
        ArrayAdapter adapter3 = new ArrayAdapter(getContext(),R.layout.selected_item,morningDIA);
        ArrayAdapter adapter4 = new ArrayAdapter(getContext(),R.layout.selected_item,aftermealDIA);
        ArrayAdapter adapter5 = new ArrayAdapter(getContext(),R.layout.selected_item,mealStatus);
        ArrayAdapter adapter6 = new ArrayAdapter(getContext(),R.layout.selected_item,medicationStatus);
        adapter1.setDropDownViewResource(R.layout.dropdown_item);
        adapter2.setDropDownViewResource(R.layout.dropdown_item);
        adapter3.setDropDownViewResource(R.layout.dropdown_item);
        adapter4.setDropDownViewResource(R.layout.dropdown_item);
        adapter5.setDropDownViewResource(R.layout.dropdown_item);
        adapter6.setDropDownViewResource(R.layout.dropdown_item);
        spinner_hdpSBP.setAdapter(adapter1);
        spinner_hdpDBP.setAdapter(adapter2);
        spinner_morningDIA.setAdapter(adapter3);
        spinner_aftermealDIA.setAdapter(adapter4);
        spinner_mealStatus.setAdapter(adapter5);
        spinner_medicationStatus.setAdapter(adapter6);
    }

    @OnClick({R.id.check_dbp, R.id.check_sbp, R.id.check_bs, R.id.check_hr, R.id.check_sdnn, R.id.check_rmssd})
    public void checkFeature(CheckBox checkBox){
        checkFeature = CommonUtil.getMany(dialogCheck);
    }

    /** 圖片輪播 **/
    public void useBanner(){
        banner.addBannerLifecycleObserver(this)
                .setAdapter(new ImageNetAdapter(DataImage.getTestData3()))
                .setIndicator(new CircleIndicator(getContext()))
                .setBannerRound(60);
    }

    /** Feature RecyclerView設定 **/
    private void RecyclerViewFeature(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        recycler_feature.setLayoutManager(new WrapContentLinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));

        featureList = new ArrayList<>();
        if (checkFeature.size()==0) {
            featureList.add(new DataFeature("無資料", 0.0));
        }

        adapter_feature = new FeatureAdapter(featureList);
        recycler_feature.setAdapter(adapter_feature);
    }

    private void notifyDataChanged(){
        if (featureList != null){
            featureList.clear();
            adapter_feature.notifyItemRangeRemoved(0,featureList.size());
            Random x = new Random();
            for (int i=0; i<checkFeature.size(); i++){
                Double y = (double)Math.round((x.nextDouble()*100.0)/100.0);
                featureList.add(new DataFeature(checkFeature.get(i),y));
            }
            adapter_feature.notifyItemRangeInserted(0,checkFeature.size());
        }
    }
    
}