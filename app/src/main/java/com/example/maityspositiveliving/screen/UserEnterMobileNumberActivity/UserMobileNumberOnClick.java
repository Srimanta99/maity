package com.example.maityspositiveliving.screen.UserEnterMobileNumberActivity;

import android.content.Intent;
import android.view.View;

import com.example.maityspositiveliving.R;
import com.example.maityspositiveliving.screen.UserVerifyMobileNumberActivity.UserVerifyMobileNumberActivity;
import com.example.maityspositiveliving.utils.MyToast;
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

                if (userMobileNumberViewBind.etn_mobno.getText().toString().isEmpty()){
                    MyToast.show(userMobileNumberActivity,"please Enter Your Mobile Number",true);

                }else  if (userMobileNumberViewBind.etn_mobno.getText().toString().length()==10){
                    Intent mainIntent = new Intent(userMobileNumberActivity, UserVerifyMobileNumberActivity.class);
                    userMobileNumberActivity.startActivity(mainIntent);
                }else {
                    MyToast.show(userMobileNumberActivity,"please Enter 10 Numbers",true);

                }
               // userMobileNumberActivity.finish();
            }
            break;





        }
    }
}
