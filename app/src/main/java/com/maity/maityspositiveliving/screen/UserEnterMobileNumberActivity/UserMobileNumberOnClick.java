package com.maity.maityspositiveliving.screen.UserEnterMobileNumberActivity;

import android.content.Intent;
import android.view.View;

import com.maity.maityspositiveliving.R;

import com.maity.maityspositiveliving.screen.UserLoginActivity.UserLoginActivity;
import com.maity.maityspositiveliving.screen.UserVerifyMobileNumberActivity.UserVerifyMobileNumberActivity;
import com.maity.maityspositiveliving.utils.MyToast;
import com.maity.maityspositiveliving.utils.RegistrationConstant;


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
        userMobileNumberViewBind.lv_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.nextid:{
                RegistrationConstant.MOBILE_NUMBER=userMobileNumberViewBind.etn_mobno.getText().toString();

                if (userMobileNumberViewBind.etn_mobno.getText().toString().isEmpty()){
                    MyToast.show(userMobileNumberActivity,"Please enter your mobile number",true);

                }else  if (userMobileNumberViewBind.etn_mobno.getText().toString().length()==10){
                    // api call for unique contact
                    userMobileNumberActivity.apicallForisContactNUmberUnique();
                 /*   Intent mainIntent = new Intent(userMobileNumberActivity, UserVerifyMobileNumberActivity.class);
                    mainIntent.putExtra("customer_phone",userMobileNumberViewBind.etn_mobno.getText().toString());
                    userMobileNumberActivity.   startActivity(mainIntent);*/
                }else {
                    MyToast.show(userMobileNumberActivity,"Please enter 10 numbers",true);

                }
            }
            break;


            case  R.id.lv_login:{
                Intent mainIntent = new Intent(userMobileNumberActivity, UserLoginActivity.class);
                userMobileNumberActivity.startActivity(mainIntent);

            }
            break;


        }
    }
}
