package com.maity.maityspositiveliving.screen.ProviderThankYouActivty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.maity.maityspositiveliving.R;
import com.maity.maityspositiveliving.screen.main.MainActivity;

public class ProviderThankYouActivity extends AppCompatActivity {
RelativeLayout Back_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_thank_you);
        Back_id=findViewById(R.id.Back_id);
        Back_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ProviderThankYouActivity. this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}