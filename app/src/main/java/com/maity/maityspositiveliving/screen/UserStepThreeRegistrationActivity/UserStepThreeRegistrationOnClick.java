package com.maity.maityspositiveliving.screen.UserStepThreeRegistrationActivity;

import android.view.View;

import com.maity.maityspositiveliving.R;
import com.maity.maityspositiveliving.utils.MyToast;
import com.maity.maityspositiveliving.utils.RegistrationConstant;


public class UserStepThreeRegistrationOnClick implements View.OnClickListener {
    UserStepThreeRegistrationActivity userStepThreeRegistrationActivity;
    UserStepThreeRegistrationViewBind userStepThreeRegistrationViewBind;

    public UserStepThreeRegistrationOnClick(UserStepThreeRegistrationActivity userStepThreeRegistrationActivity, UserStepThreeRegistrationViewBind userStepThreeRegistrationViewBind) {
        this.userStepThreeRegistrationActivity = userStepThreeRegistrationActivity;
        this.userStepThreeRegistrationViewBind = userStepThreeRegistrationViewBind;
        setonclicklistner();
    }

    // set click listner.
    private void setonclicklistner() {
        userStepThreeRegistrationViewBind.nextid.setOnClickListener(this);
        userStepThreeRegistrationViewBind.back_icon.setOnClickListener(this);
        userStepThreeRegistrationViewBind.img_state.setOnClickListener(this);
        userStepThreeRegistrationViewBind.img_city  .setOnClickListener(this);
        userStepThreeRegistrationViewBind.tv_state.setOnClickListener(this);
        userStepThreeRegistrationViewBind.tv_city.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.nextid: {




                if (RegistrationConstant.STATE.isEmpty()){
                    MyToast.show(userStepThreeRegistrationActivity,"Please  select state",true);

                }else if (RegistrationConstant.CITY.isEmpty()){
                    MyToast.show(userStepThreeRegistrationActivity,"Please select city",true);

                }else if (userStepThreeRegistrationViewBind.address_etnid.getText().toString().isEmpty()){
                    MyToast.show(userStepThreeRegistrationActivity,"Please enter your address",true);

                }else if (userStepThreeRegistrationViewBind.pin_etnid.getText().toString().isEmpty()){
                    MyToast.show(userStepThreeRegistrationActivity,"Please enter your pin",true);

                }
                else {
                    userStepThreeRegistrationActivity.apiForRegister();// api call for Register

                }

            }
            break;
            case R.id.back_icon: {

                saveWithFinish();
            }
            break;
            case R.id.img_state:{
                userStepThreeRegistrationViewBind.tv_state.performClick();//  open state dialog
            }
            break;
            case R.id.tv_state:{
                   userStepThreeRegistrationActivity.opendialogForState(); //method  call for show state
            }
            break;

            case R.id.tv_city:{
                if (RegistrationConstant.STATE.isEmpty()){
                    MyToast.show(userStepThreeRegistrationActivity,"Please  select state",true);

                }else {
                    userStepThreeRegistrationActivity.opendialogForCity();//method call for show city

                }
            }
            break;
            case R.id.img_city:{
                userStepThreeRegistrationViewBind.tv_city.performClick(); //  open city dialog
            }
               break;
        }
    }



    void saveWithFinish(){

        String address=   userStepThreeRegistrationViewBind.address_etnid.getText().toString();
        String pin=    userStepThreeRegistrationViewBind.pin_etnid.getText().toString();
        RegistrationConstant.ADDRESS=address;
        RegistrationConstant.PIN=pin;
        userStepThreeRegistrationActivity.finish();
    }




}