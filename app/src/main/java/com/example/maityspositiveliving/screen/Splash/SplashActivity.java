package com.example.maityspositiveliving.screen.Splash;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.maityspositiveliving.R;
import com.example.maityspositiveliving.screen.main.MainActivity;
import com.example.maityspositiveliving.utils.ApplicationConstant;

public class SplashActivity extends AppCompatActivity {
    SharedPreferences sharedpreferences;
    boolean savelogin,saveCenterManagerlogin,saveTeacherlogin;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        /*savelogin= SessionManager.isLogged();
        saveCenterManagerlogin=SessionManager.isCenterManageLogged();
        saveTeacherlogin=SessionManager.isTeacherLogged();*/

        View view= LayoutInflater.from(this).inflate(R.layout.activity_splash,null);
        setContentView(view);
        new Handler().postDelayed(new Runnable(){
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void run() {

                Intent intent=new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();


               /* if (!saveCenterManagerlogin && !savelogin && !saveTeacherlogin){

                    Intent intent=new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                    return;


                } else if (saveCenterManagerlogin) {

                    Intent intent = new Intent(SplashActivity.this, CenterManagerHomeActivity.class);
                    startActivity(intent);
                    finish();

                    return;
                }

                *//*if (!savelogin){

                    Intent intent=new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();

                }*//*
                else   if (savelogin){

                    Intent intent = new Intent(SplashActivity.this, StudentHomeActivity.class);
                    startActivity(intent);
                    finish();
                    return;
                }

                else   if (saveTeacherlogin){

                    Intent intent = new Intent(SplashActivity.this, TeacherHomeActivity.class);
                    startActivity(intent);
                    finish();
                }
*/

            }
        }, ApplicationConstant.SPLASH_DISPLAY_LENGTH);
    }
}
