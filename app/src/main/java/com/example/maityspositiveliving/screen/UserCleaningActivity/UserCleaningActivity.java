package com.example.maityspositiveliving.screen.UserCleaningActivity;

import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.maityspositiveliving.R;
import com.example.maityspositiveliving.screen.UserHouseHoldActivity.UserHouseHoldOnClick;
import com.example.maityspositiveliving.screen.UserHouseHoldActivity.UserHouseHoldViewBind;

public class UserCleaningActivity extends AppCompatActivity {

    UserCleaningViewBind userCleaningViewBind;
    UserCleaningOnClick userCleaningOnClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_user_cleaning);


        View view= LayoutInflater.from(this).inflate(R.layout.activity_user_cleaning,null);
        setContentView(view);
        userCleaningViewBind= new UserCleaningViewBind(this,view);
        userCleaningOnClick=new UserCleaningOnClick(this,userCleaningViewBind);



       userCleaningViewBind. headername_tvid.setText("CLEANING");

        String name = getColoredSpanned("Note : ", "#0288D1");
        String surName = getColoredSpanned("Please fillup the form and submit your details","#323545");

        userCleaningViewBind.  note_tvid.setText(Html.fromHtml(name+" "+surName));
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    private String getColoredSpanned(String text, String color) {
        String input = "<font color=" + color + ">" + text + "</font>";
        return input;
    }

}