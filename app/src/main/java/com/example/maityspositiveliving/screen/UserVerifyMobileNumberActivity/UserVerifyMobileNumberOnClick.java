package com.example.maityspositiveliving.screen.UserVerifyMobileNumberActivity;

import android.content.Intent;
import android.view.View;

import com.example.maityspositiveliving.R;
import com.example.maityspositiveliving.screen.UserEnterMobileNumberActivity.UserMobileNumberActivity;
import com.example.maityspositiveliving.screen.UserEnterMobileNumberActivity.UserMobileNumberViewBind;
import com.example.maityspositiveliving.screen.UserStepOneRegistrationActivity.UserStepOneRegistrationActivity;

public class UserVerifyMobileNumberOnClick implements View.OnClickListener{
    UserVerifyMobileNumberActivity userVerifyMobileNumberActivity;
    UserVerifyMobileNumberViewBind userVerifyMobileNumberViewBind;

    public UserVerifyMobileNumberOnClick( UserVerifyMobileNumberActivity userVerifyMobileNumberActivity,UserVerifyMobileNumberViewBind userVerifyMobileNumberViewBind) {
        this.userVerifyMobileNumberActivity=userVerifyMobileNumberActivity;
        this.userVerifyMobileNumberViewBind=userVerifyMobileNumberViewBind;
        setonclicklistner();
    }
    // set click listner.
    private void setonclicklistner() {
        userVerifyMobileNumberViewBind.verifylvid.setOnClickListener(this);
        userVerifyMobileNumberViewBind.back_icon.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.verifylvid:{
                Intent mainIntent = new Intent(userVerifyMobileNumberActivity, UserStepOneRegistrationActivity.class);
                userVerifyMobileNumberActivity.startActivity(mainIntent);
                userVerifyMobileNumberActivity.finish();
            }
            break;

            case R.id.back_icon:{
                Intent mainIntent = new Intent(userVerifyMobileNumberActivity, UserMobileNumberActivity.class);
                userVerifyMobileNumberActivity.startActivity(mainIntent);
                userVerifyMobileNumberActivity.finish();
            }
            break;





        }
    }
}
