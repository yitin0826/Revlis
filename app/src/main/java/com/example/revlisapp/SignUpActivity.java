package com.example.revlisapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.revlisapp.SignUpFragment.Habit;
import com.example.revlisapp.SignUpFragment.Health;
import com.example.revlisapp.SignUpFragment.Info;

import java.util.List;

import butterknife.ButterKnife;

public class SignUpActivity extends AppCompatActivity {

    Button btn_nextPage,btn_upPage;
    TextView txt_signUPtitle;
    ImageView img_signup,img_signUpback;
    private Info info = new Info();
    private Health health = new Health();
    private Habit habit = new Habit();
    private String page,phone,height,weight,age,sex,smoke,drink,disease,hbp,diabetes;
    private Fragment currentFragment;
    private FrameLayout container_signup;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initWidget();
        ButterKnife.bind(SignUpActivity.this);
    }

    @Override
    protected void onStart(){
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("getStatus","stop");
    }

    private void initWidget(){
        Bundle bundle = getIntent().getExtras();
        img_signup = findViewById(R.id.img_signup);
        img_signUpback = findViewById(R.id.img_signUpback);
        btn_nextPage = findViewById(R.id.btn_nextPage);
        btn_upPage = findViewById(R.id.btn_upPage);
        txt_signUPtitle = findViewById(R.id.txt_signUPtitle);
        container_signup = findViewById(R.id.container_signup);
        if (bundle != null){
            page = bundle.getString("page");
            info.setArguments(bundle);
            health.setArguments(bundle);
            habit.setArguments(bundle);
            Log.d("signupp", page);
            img_signup.setImageResource(R.drawable.profile);
        }else {
            img_signup.setImageResource(R.drawable.sign_up2);
        }
        fragmentManager = getSupportFragmentManager();
        img_signUpback.setImageResource(R.drawable.art);
        btn_upPage.setVisibility(View.INVISIBLE);
        btn_upPage.setClickable(false);
        btn_nextPage.setOnClickListener(lis);
        btn_upPage.setOnClickListener(lis);
        try {
            setFragment();
        }catch (Exception e){
            Log.e("tessttt",e.toString());
        }
    }

    private void setFragment(){
        transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.container_signup,info);
        transaction.add(R.id.container_signup,health);
        transaction.add(R.id.container_signup,habit);
        if (page != null){
            if (page.equals("info")){
                txt_signUPtitle.setText("個人資料 Profile");
                transaction.hide(health);
                transaction.hide(habit);
                currentFragment = info;
            }else if (page.equals("health")){
                txt_signUPtitle.setText("健康調查 Health");
                transaction.hide(info);
                transaction.hide(habit);
                currentFragment = health;
                btn_upPage.setClickable(true);
                btn_upPage.setVisibility(View.VISIBLE);
            }else if (page.equals("habit")){
                txt_signUPtitle.setText("生活習慣 Habit");
                transaction.hide(health);
                transaction.hide(info);
                currentFragment = habit;
                btn_upPage.setClickable(true);
                btn_upPage.setVisibility(View.VISIBLE);
                btn_nextPage.setText("確認修改");
            }
        }else {
            transaction.hide(health);
            transaction.hide(habit);
        }
        transaction.commit();
    }

    View.OnClickListener lis = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Fragment nowFragment = checkFragment();
            switch (view.getId()){
                case R.id.btn_nextPage:
                    if (nowFragment == info){
                        btn_upPage.setClickable(true);
                        btn_upPage.setVisibility(View.VISIBLE);
                        currentFragment = info;
                        try {
                            FragmentHideShow(health);
                            txt_signUPtitle.setText("健康調查 Health");
                            info.sendValue(new Info.DataReturn() {
                                @Override
                                public void getResult(String value) {
                                    Log.d("getvalue",value);
                                }
                            });
                        }catch (Exception e){
                            Log.e("change",e.toString());
                        }
                    }else if(nowFragment == health){
                        btn_upPage.setClickable(true);
                        btn_upPage.setVisibility(View.VISIBLE);
                        if (page != null){
                            if (page == "habit"){
                                btn_nextPage.setText("確認修改");
                            }
                        }else {
                            btn_nextPage.setText("註冊");
                        }
                        FragmentHideShow(habit);
                        txt_signUPtitle.setText("生活習慣 Habit");
                    }else if(nowFragment == habit){
                        try {
                            Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }catch (Exception e){
                            Log.e("errrr",e.toString());
                        }
                    }
                    break;
                case R.id.btn_upPage:
                    if (nowFragment == habit){
                        btn_nextPage.setText("下一頁");
                        FragmentHideShow(health);
                        txt_signUPtitle.setText("健康調查 Health");
                    }else if(nowFragment == health){
                        btn_upPage.setClickable(false);
                        btn_upPage.setVisibility(View.INVISIBLE);
                        FragmentHideShow(info);
                        txt_signUPtitle.setText("個人資料 Profile");
                    }
                    break;
            }
        }
    };

    private Fragment checkFragment(){
        FragmentManager fm = this.getSupportFragmentManager();
        List<Fragment> fragments = fm.getFragments();
        for (Fragment fragment:fragments){
            if (fragment != null && fragment.isVisible()){
                return fragment;
            }
        }
        return null;
    }

    private void FragmentHideShow(Fragment fg){
        fragmentManager = getSupportFragmentManager();
        transaction= fragmentManager.beginTransaction();
        if(!fg.isAdded()){
            transaction.hide(currentFragment);
            transaction.add(R.id.container_signup,fg);
        }else{
            transaction.hide(currentFragment);
            transaction.show(fg);
        }
        currentFragment=fg;
        transaction.commit();
    }

    private void getValue(){

    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d("getStatus","destroy");
    }

//    /** 判斷輸入欄是否空白 **/
//    private void checkEmpty(){
//        if (userName.isEmpty()){
//            Toast.makeText(SignUpActivity.this, "用戶名稱欄不得空白",Toast.LENGTH_SHORT).show();
//        }
//        if (birth.isEmpty()){
//            Toast.makeText(SignUpActivity.this, "出生日期欄不得空白",Toast.LENGTH_SHORT).show();
//        }
//        if (height.isEmpty()){
//            Toast.makeText(SignUpActivity.this, "身高欄不得空白",Toast.LENGTH_SHORT).show();
//        }
//        if (weight.isEmpty()){
//            Toast.makeText(SignUpActivity.this, "體重欄不得空白",Toast.LENGTH_SHORT).show();
//        }
//        if (checkedSex.isEmpty()){
//            Toast.makeText(SignUpActivity.this, "性別欄不得空白",Toast.LENGTH_SHORT).show();
//        }
//        if (checkedSmoke.isEmpty()){
//            Toast.makeText(SignUpActivity.this, "抽菸欄不得空白",Toast.LENGTH_SHORT).show();
//        }
//        if (checkedDia.isEmpty()){
//            Toast.makeText(SignUpActivity.this, "糖尿病欄不得空白",Toast.LENGTH_SHORT).show();
//        }
//        if (checkedHbp.isEmpty()){
//            Toast.makeText(SignUpActivity.this, "高血壓欄不得空白",Toast.LENGTH_SHORT).show();
//        }
//    }

}