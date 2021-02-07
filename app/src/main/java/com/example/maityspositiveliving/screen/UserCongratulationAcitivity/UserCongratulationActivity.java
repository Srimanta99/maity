package com.example.maityspositiveliving.screen.UserCongratulationAcitivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.maityspositiveliving.R;
import com.example.maityspositiveliving.screen.UserDashboardActivity.UserDashboardActivity;

public class UserCongratulationActivity extends AppCompatActivity {
RelativeLayout home_btnid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_congratulation);
        home_btnid=findViewById(R.id.home_btnid);

        home_btnid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(UserCongratulationActivity.this, UserDashboardActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}