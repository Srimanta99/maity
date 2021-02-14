package com.example.maityspositiveliving.screen.UserStepThreeRegistrationActivity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.maityspositiveliving.R;
import com.example.maityspositiveliving.screen.UserThankYouActivity.UserThankYouActivity;
import com.example.maityspositiveliving.utils.MyToast;
import com.example.maityspositiveliving.utils.RegistrationConstant;
import com.example.maityspositiveliving.utils.SessionManager;

import java.util.HashMap;


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
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.nextid: {




                if (RegistrationConstant.STATE==0){
                    MyToast.show(userStepThreeRegistrationActivity,"please  Select State",true);

                }else if (RegistrationConstant.CITY==0){
                    MyToast.show(userStepThreeRegistrationActivity,"please Select City",true);

                }else if (userStepThreeRegistrationViewBind.address_etnid.getText().toString().isEmpty()){
                    MyToast.show(userStepThreeRegistrationActivity,"please Enter Your Address",true);

                }else if (userStepThreeRegistrationViewBind.pin_etnid.getText().toString().isEmpty()){
                    MyToast.show(userStepThreeRegistrationActivity,"please Enter Your Pin",true);

                }
                else {
                    userStepThreeRegistrationActivity.apiForRegister();

                }

           /*     Intent mainIntent = new Intent(userStepThreeRegistrationActivity, UserThankYouActivity.class).addFlags(
                        Intent.FLAG_ACTIVITY_CLEAR_TASK |
                                Intent.FLAG_ACTIVITY_CLEAR_TOP
                );
                userStepThreeRegistrationActivity.  startActivity(mainIntent);*/
            }
            break;
            case R.id.back_icon: {

                saveWithFinish();
            }
            break;
            case R.id.img_state:{
                userStepThreeRegistrationViewBind.select_state_spinnerid.performClick();
            }
            break;
            case R.id.img_city:{
                userStepThreeRegistrationViewBind.select_city_spinnerid.performClick();
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