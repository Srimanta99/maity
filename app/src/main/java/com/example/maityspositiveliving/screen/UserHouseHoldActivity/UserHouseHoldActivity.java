package com.example.maityspositiveliving.screen.UserHouseHoldActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.maityspositiveliving.R;

public class UserHouseHoldActivity extends AppCompatActivity {

    UserHouseHoldViewBind userHouseHoldViewBind;
    UserHouseHoldOnClick userHouseHoldOnClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_user_house_hold);

        View view= LayoutInflater.from(this).inflate(R.layout.activity_user_house_hold,null);
        setContentView(view);
        userHouseHoldViewBind= new UserHouseHoldViewBind(this,view);
        userHouseHoldOnClick=new UserHouseHoldOnClick(this,userHouseHoldViewBind);




    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}