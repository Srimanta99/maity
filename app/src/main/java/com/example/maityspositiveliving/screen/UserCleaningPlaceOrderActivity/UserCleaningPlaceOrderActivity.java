package com.example.maityspositiveliving.screen.UserCleaningPlaceOrderActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.maityspositiveliving.R;

public class UserCleaningPlaceOrderActivity extends AppCompatActivity {
TextView headername_tvid;
    RelativeLayout back_icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_cleaning_place_order);

        headername_tvid=findViewById(R.id.headername_tvid);
        back_icon=findViewById(R.id.back_icon);

        headername_tvid.setText("CLEANING");

        back_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

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