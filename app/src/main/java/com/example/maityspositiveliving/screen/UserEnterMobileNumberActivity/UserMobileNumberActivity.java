package com.example.maityspositiveliving.screen.UserEnterMobileNumberActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.example.maityspositiveliving.R;

public class UserMobileNumberActivity extends AppCompatActivity {
    UserMobileNumberViewBind userMobileNumberViewBind;
    UserMobileNumberOnClick userMobileNumberOnClick;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view= LayoutInflater.from(this).inflate(R.layout.activity_user_mobile_number,null);
        setContentView(view);
        userMobileNumberViewBind= new UserMobileNumberViewBind(this,view);
        userMobileNumberOnClick=new UserMobileNumberOnClick(this,userMobileNumberViewBind);
    }
}