package com.example.maityspositiveliving.screen.UserStepOneRegistrationActivity;

import android.content.Intent;
import android.view.View;

import com.example.maityspositiveliving.R;
import com.example.maityspositiveliving.screen.UserStepTwoRegistrationActivity.UserStepTwoRegistrationActivity;
import com.example.maityspositiveliving.screen.UserVerifyMobileNumberActivity.UserVerifyMobileNumberActivity;
import com.example.maityspositiveliving.utils.MyToast;
import com.example.maityspositiveliving.utils.RegistrationConstant;


public class UserStepOneRegistrationOnClick implements View.OnClickListener {
    UserStepOneRegistrationActivity userStepOneRegistrationActivity;
    UserStepOneRegistrationViewBind userStepOneRegistrationViewBind;

    String etn_name,etn_email;

    public UserStepOneRegistrationOnClick(UserStepOneRegistrationActivity userStepOneRegistrationActivity, UserStepOneRegistrationViewBind userStepOneRegistrationViewBind) {
        this.userStepOneRegistrationActivity = userStepOneRegistrationActivity;
        this.userStepOneRegistrationViewBind = userStepOneRegistrationViewBind;
        setonclicklistner();
    }

    // set click listner.
    private void setonclicklistner() {
        userStepOneRegistrationViewBind.nextid.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.nextid: {

                etn_name=  userStepOneRegistrationViewBind.etn_nameid.getText().toString();
                etn_email=  userStepOneRegistrationViewBind.etn_emailid.getText().toString();

                RegistrationConstant.NAME=etn_name;
                RegistrationConstant.EMAIL_ID=etn_email;

                if (RegistrationConstant.NAME.isEmpty()){
                    MyToast.show(userStepOneRegistrationActivity,"please Enter Your Name",true);

                }else if (RegistrationConstant.EMAIL_ID.isEmpty()){
                    MyToast.show(userStepOneRegistrationActivity,"please Enter Your Email id",true);

                }
                else {
                    Intent mainIntent = new Intent(userStepOneRegistrationActivity, UserStepTwoRegistrationActivity.class);
                    userStepOneRegistrationActivity.startActivity(mainIntent);
                }



               /* Intent mainIntent = new Intent(userStepOneRegistrationActivity, UserStepTwoRegistrationActivity.class);
                userStepOneRegistrationActivity.startActivity(mainIntent);*/
                // userStepOneRegistrationActivity.finish();
            }
            break;


        }
    }

    void saveWithFinish(){

    }
}