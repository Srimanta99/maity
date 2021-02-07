package com.example.maityspositiveliving.screen.UserEnterMobileNumberActivity;

import android.content.Intent;
import android.view.View;

import com.example.maityspositiveliving.R;
import com.example.maityspositiveliving.screen.UserVerifyMobileNumberActivity.UserVerifyMobileNumberActivity;

public class UserMobileNumberOnClick implements View.OnClickListener{
    UserMobileNumberActivity userMobileNumberActivity;
    UserMobileNumberViewBind userMobileNumberViewBind;

    public UserMobileNumberOnClick(UserMobileNumberActivity userMobileNumberActivity, UserMobileNumberViewBind userMobileNumberViewBind) {
        this.userMobileNumberActivity=userMobileNumberActivity;
        this.userMobileNumberViewBind=userMobileNumberViewBind;
        setonclicklistner();
    }
    // set click listner.
    private void setonclicklistner() {
        userMobileNumberViewBind.nextid.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.nextid:{
                Intent mainIntent = new Intent(userMobileNumberActivity, UserVerifyMobileNumberActivity.class);
                userMobileNumberActivity.startActivity(mainIntent);
               // userMobileNumberActivity.finish();
            }
            break;





        }
    }
}
