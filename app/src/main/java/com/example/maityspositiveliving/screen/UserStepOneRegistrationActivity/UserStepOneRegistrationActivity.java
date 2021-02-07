package com.example.maityspositiveliving.screen.UserStepOneRegistrationActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.example.maityspositiveliving.R;
import com.example.maityspositiveliving.utils.RegistrationConstant;


public class UserStepOneRegistrationActivity extends AppCompatActivity {
    UserStepOneRegistrationViewBind userStepOneRegistrationViewBind;
    UserStepOneRegistrationOnClick userStepOneRegistrationOnClick;
    String pin,Gender,DOB,address;
    int state,city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view= LayoutInflater.from(this).inflate(R.layout.activity_user_step_one_registration,null);
        setContentView(view);
        userStepOneRegistrationViewBind= new UserStepOneRegistrationViewBind(this,view);
        userStepOneRegistrationOnClick=new UserStepOneRegistrationOnClick(this,userStepOneRegistrationViewBind);



  /*      Intent intent = getIntent();
        String  etn_nameid = intent.getStringExtra("etn_name");
        String etn_email= intent.getStringExtra("etn_email");

          DOB = intent.getStringExtra("DOB");
         Gender= intent.getStringExtra("Gender");

        address= intent.getStringExtra("address");
          pin = intent.getStringExtra("pin");
        state= intent.getIntExtra("state",0);
        city= intent.getIntExtra("city",0);*/

        userStepOneRegistrationViewBind.etn_nameid.setText(RegistrationConstant.NAME);
        userStepOneRegistrationViewBind.etn_emailid.setText(RegistrationConstant.NAME);



    }
}