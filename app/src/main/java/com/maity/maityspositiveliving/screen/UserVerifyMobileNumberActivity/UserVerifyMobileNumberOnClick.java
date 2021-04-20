package com.maity.maityspositiveliving.screen.UserVerifyMobileNumberActivity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.maity.maityspositiveliving.R;
import com.maity.maityspositiveliving.screen.UserEnterMobileNumberActivity.UserMobileNumberActivity;
import com.maity.maityspositiveliving.screen.UserStepOneRegistrationActivity.UserStepOneRegistrationActivity;

public class UserVerifyMobileNumberOnClick implements View.OnClickListener{
    UserVerifyMobileNumberActivity userVerifyMobileNumberActivity;
    UserVerifyMobileNumberViewBind userVerifyMobileNumberViewBind;
    String otp;

    public UserVerifyMobileNumberOnClick( UserVerifyMobileNumberActivity userVerifyMobileNumberActivity,UserVerifyMobileNumberViewBind userVerifyMobileNumberViewBind) {
        this.userVerifyMobileNumberActivity=userVerifyMobileNumberActivity;
        this.userVerifyMobileNumberViewBind=userVerifyMobileNumberViewBind;
        setonclicklistner();
    }
    // set click listner.
    private void setonclicklistner() {
        userVerifyMobileNumberViewBind.verifylvid.setOnClickListener(this);
        userVerifyMobileNumberViewBind.back_icon.setOnClickListener(this);
userVerifyMobileNumberViewBind.resendlvid.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.verifylvid:{

               otp=    userVerifyMobileNumberViewBind.etotpno1id.getText().toString()+ userVerifyMobileNumberViewBind.etotpno2id.getText().toString()+userVerifyMobileNumberViewBind.etotpno3id.getText().toString()+
                        userVerifyMobileNumberViewBind.etotpno4id.getText().toString();
                Log.d("sunnnnnnnnnnn", "onClick: "+otp);


                    userVerifyMobileNumberActivity.  apicallForverifyOTP(otp);



            }
            break;

            case R.id.resendlvid:{

                userVerifyMobileNumberActivity. apicallForgetOTP(userVerifyMobileNumberActivity.customer_phone);


            }
            break;

            case R.id.back_icon:{
                Intent mainIntent = new Intent(userVerifyMobileNumberActivity, UserMobileNumberActivity.class);
                userVerifyMobileNumberActivity.startActivity(mainIntent);
                userVerifyMobileNumberActivity.finish();
            }
            break;





        }
    }
}
