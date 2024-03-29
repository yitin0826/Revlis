package com.example.revlisapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.example.revlisapp.R;

public class BeginActivity extends AppCompatActivity {

    Button btn_signin,btn_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        if(window != null){
            window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);
            window.setEnterTransition(new android.transition.Slide(Gravity.BOTTOM));
            window.setExitTransition(new android.transition.Slide(Gravity.TOP));
            window.setReenterTransition(new android.transition.Slide(Gravity.BOTTOM));
            window.setReturnTransition(new android.transition.Slide(Gravity.TOP));
        }
        setContentView(R.layout.activity_begin);
        initParameter();
    }

    public void initParameter(){
        btn_signin = findViewById(R.id.btn_signin);
        btn_signup = findViewById(R.id.btn_signup);
        btn_signin.setOnClickListener(lis);
        btn_signup.setOnClickListener(lis);
    }

    View.OnClickListener lis = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btn_signin:
                    Intent intent_IN = new Intent();
                    intent_IN.setClass(BeginActivity.this, SignInActivity.class);
                    startActivity(intent_IN);
                    break;
                case R.id.btn_signup:
                    Intent intent_UP = new Intent();
                    intent_UP.setClass(BeginActivity.this, SignUpActivity.class);
                    startActivity(intent_UP);
                    break;
            }
        }
    };
}