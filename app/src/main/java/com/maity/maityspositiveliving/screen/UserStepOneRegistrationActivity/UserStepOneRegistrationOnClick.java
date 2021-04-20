package com.maity.maityspositiveliving.screen.UserStepOneRegistrationActivity;

import android.view.View;

import com.maity.maityspositiveliving.R;
import com.maity.maityspositiveliving.utils.MyToast;
import com.maity.maityspositiveliving.utils.RegistrationConstant;


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

                RegistrationConstant.NAME=etn_name;  // enter name in edittext ,this name is catch statically RegistrationConstant class
                RegistrationConstant.EMAIL_ID=etn_email;// enter email in edittext ,this email is catch statically RegistrationConstant class

               //if etn_name is empty
                if (RegistrationConstant.NAME.isEmpty()){
                    MyToast.show(userStepOneRegistrationActivity,"Please enter your name",true);

                }else if (RegistrationConstant.EMAIL_ID.isEmpty()){  //if etn_email is empty
                    MyToast.show(userStepOneRegistrationActivity,"Please enter your email id",true);

                }  // method call for isValidEmailformet
                else if (!userStepOneRegistrationActivity. isValidEmailId(userStepOneRegistrationViewBind.etn_emailid.getText().toString().trim())){
                    MyToast.show(userStepOneRegistrationActivity,"InValid email address.",true);

                }
                else {
                    // api call for unique Email
                    userStepOneRegistrationActivity.apicallForsEmailUnique();
                }

            }
            break;


        }
    }


}