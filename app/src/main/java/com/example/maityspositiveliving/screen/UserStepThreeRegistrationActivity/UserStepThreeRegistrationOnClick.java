package com.example.maityspositiveliving.screen.UserStepThreeRegistrationActivity;

import android.content.Intent;
import android.view.View;

import com.example.maityspositiveliving.R;
import com.example.maityspositiveliving.screen.UserThankYouActivity.UserThankYouActivity;
import com.example.maityspositiveliving.utils.RegistrationConstant;


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
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.nextid: {

                Intent mainIntent = new Intent(userStepThreeRegistrationActivity, UserThankYouActivity.class).addFlags(
                        Intent.FLAG_ACTIVITY_CLEAR_TASK |
                                Intent.FLAG_ACTIVITY_CLEAR_TOP
                );
                userStepThreeRegistrationActivity.startActivity(mainIntent);

            }
            break;
            case R.id.back_icon: {

                saveWithFinish();
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