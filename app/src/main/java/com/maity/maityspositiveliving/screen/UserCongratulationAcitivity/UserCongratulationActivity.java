package com.maity.maityspositiveliving.screen.UserCongratulationAcitivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.maity.maityspositiveliving.R;
import com.maity.maityspositiveliving.screen.UserDeshboardActivity.UserDeshboardActivity;

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
                Intent intent=new Intent(UserCongratulationActivity.this, UserDeshboardActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}