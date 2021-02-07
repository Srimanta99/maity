package com.example.maityspositiveliving.screen.UserDashboardActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.example.maityspositiveliving.R;
import com.example.maityspositiveliving.screen.Fragment.UserDeshBoardFragment.UserDeshBoardFragment;

public class UserDashboardActivity extends AppCompatActivity {
    UserDashboardViewBind userDashboardViewBind;
    UserDashboardOnClick userDashboardOnClick;
    String otp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);

        View view= LayoutInflater.from(this).inflate(R.layout.activity_user_dashboard,null);
        setContentView(view);

        userDashboardViewBind= new UserDashboardViewBind(this,view);
        userDashboardOnClick=new UserDashboardOnClick(this,userDashboardViewBind);

        otp = getIntent().getStringExtra("otp");

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentid, new UserDeshBoardFragment()).commit();
            userDashboardViewBind.navigationView.setCheckedItem(R.id.dashboardid);
        }
    }


    public void onBackPressed() {
        finish();
    }

}