package com.example.maityspositiveliving.screen.UserVerifyMobileNumberActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.example.maityspositiveliving.R;
import com.example.maityspositiveliving.screen.UserEnterMobileNumberActivity.UserMobileNumberOnClick;
import com.example.maityspositiveliving.screen.UserEnterMobileNumberActivity.UserMobileNumberViewBind;

public class UserVerifyMobileNumberActivity extends AppCompatActivity {
    UserVerifyMobileNumberViewBind userVerifyMobileNumberViewBind;
    UserVerifyMobileNumberOnClick userVerifyMobileNumberOnClick;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view= LayoutInflater.from(this).inflate(R.layout.activity_user_verify_mobile_number,null);
        setContentView(view);
        userVerifyMobileNumberViewBind= new UserVerifyMobileNumberViewBind(this,view);
        userVerifyMobileNumberOnClick=new UserVerifyMobileNumberOnClick(this,userVerifyMobileNumberViewBind);
    }
}