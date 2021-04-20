package com.maity.maityspositiveliving.screen.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.maity.maityspositiveliving.R;


public class MainActivity extends AppCompatActivity {
    MainActivityViewBind mainActivityViewBind;
    MainActivityOnClick mainActivityOnClick;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view= LayoutInflater.from(this).inflate(R.layout.activity_main,null);
        setContentView(view);
        mainActivityViewBind= new MainActivityViewBind(this,view);
        mainActivityOnClick=new MainActivityOnClick(this,mainActivityViewBind);
    }
}
