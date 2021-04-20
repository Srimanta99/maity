package com.maity.maityspositiveliving.screen.Splash;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.maity.maityspositiveliving.R;
import com.maity.maityspositiveliving.screen.UserDeshboardActivity.UserDeshboardActivity;
import com.maity.maityspositiveliving.screen.main.MainActivity;
import com.maity.maityspositiveliving.utils.ApplicationConstant;
import com.maity.maityspositiveliving.utils.SessionManager;

public class SplashActivity extends AppCompatActivity {
    boolean saveRegistration,savelogin;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // For Full Screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        saveRegistration= SessionManager.getLogged(); //get boolean value after registration
        savelogin= SessionManager.getAfterLogin();//get boolean value after login


        View view= LayoutInflater.from(this).inflate(R.layout.activity_splash,null);
        setContentView(view);

        new Handler().postDelayed(new Runnable(){
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void run() {
                if (!saveRegistration && !savelogin ){
                     // if login or registration  is false then go to mainactivity
                    Intent intent=new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();

                }
                else   if (saveRegistration){
                    // if  registration  is true then go to DeshboardActivity
                    Intent intent = new Intent(SplashActivity.this, UserDeshboardActivity.class);
                    startActivity(intent);
                    finish();
                    return;
                }else   if (savelogin){
                    // if  login  is true then go to DeshboardActivity
                    Intent intent = new Intent(SplashActivity.this, UserDeshboardActivity.class);
                    startActivity(intent);
                    finish();
                    return;
                }



            }
        }, ApplicationConstant.SPLASH_DISPLAY_LENGTH);
    }
}
