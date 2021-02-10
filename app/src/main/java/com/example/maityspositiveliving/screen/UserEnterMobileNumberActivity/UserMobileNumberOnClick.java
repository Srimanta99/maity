package com.example.maityspositiveliving.screen.UserEnterMobileNumberActivity;

import android.content.Intent;
import android.view.View;

import com.example.maityspositiveliving.R;
import com.example.maityspositiveliving.screen.UserVerifyMobileNumberActivity.UserVerifyMobileNumberActivity;
import com.example.maityspositiveliving.utils.RegistrationConstant;

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
                RegistrationConstant.MOBILE_NUMBER=userMobileNumberViewBind.etn_mobno.getText().toString();
                Intent mainIntent = new Intent(userMobileNumberActivity, UserVerifyMobileNumberActivity.class);
                userMobileNumberActivity.startActivity(mainIntent);
               // userMobileNumberActivity.finish();
            }
            break;





        }
    }
}
