package com.maity.maityspositiveliving.screen.UserThankYouActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.maity.maityspositiveliving.R;
import com.maity.maityspositiveliving.screen.UserDeshboardActivity.UserDeshboardActivity;

public class UserThankYouActivity extends AppCompatActivity {
 RelativeLayout lets_start_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_thank_you);

        lets_start_id=findViewById(R.id.lets_start_id);


        lets_start_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(UserThankYouActivity.this, UserDeshboardActivity.class);
                startActivity(intent);
                finish();

            }
        });
    }
}