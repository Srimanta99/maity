package com.example.maityspositiveliving.screen.UserStepThreeRegistrationActivity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.maityspositiveliving.R;
import com.example.maityspositiveliving.screen.UserThankYouActivity.UserThankYouActivity;
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
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.nextid: {



                userStepThreeRegistrationActivity.apiForRegister();


             /*   Intent mainIntent = new Intent(userStepThreeRegistrationActivity, UserThankYouActivity.class).addFlags(
                        Intent.FLAG_ACTIVITY_CLEAR_TASK |
                                Intent.FLAG_ACTIVITY_CLEAR_TOP
                );
                userStepThreeRegistrationActivity.  startActivity(mainIntent);
*/
            }
            break;
            case R.id.back_icon: {

                saveWithFinish();
            }
            break;
            case R.id.img_state:{
                userStepThreeRegistrationViewBind.select_state_spinnerid.performClick();
            }

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