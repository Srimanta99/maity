package com.maity.maityspositiveliving.screen.UserLoginActivity;

import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.ImageView;

import com.maity.maityspositiveliving.R;
import com.maity.maityspositiveliving.utils.MyToast;

public class UserLoginOnClick implements View.OnClickListener{
    UserLoginActivity userLoginActivity;
    UserLoginViewBind userLoginViewBind;

    public UserLoginOnClick( UserLoginActivity userLoginActivity, UserLoginViewBind userLoginViewBind) {
        this.userLoginActivity=userLoginActivity;
        this.userLoginViewBind=userLoginViewBind;
        setonclicklistner();
    }
    // set click listner.
    private void setonclicklistner() {

        userLoginViewBind.loginid.setOnClickListener(this);
        userLoginViewBind.show_pass_id.setOnClickListener(this);
      //  userLoginActivity.lv_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.show_pass_id: {
                // Toast.makeText(mainActivity,"hello",Toast.LENGTH_LONG).show();
                if (view.getId() == R.id.show_pass_id) {

                    if (userLoginViewBind.etn_password.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())) {
                        userLoginViewBind.show_pass_id.setImageResource(R.drawable.showpassword);

                        //Show Password
                        userLoginViewBind.etn_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    } else {
                        ((ImageView) (view)).setImageResource(R.drawable.hidepassword);

                        //Hide Password
                        userLoginViewBind.etn_password.setTransformationMethod(PasswordTransformationMethod.getInstance());

                    }
                }
            }
                break;

            case R.id.loginid:{
                String etn_mobno =userLoginViewBind.etn_mobno.getText().toString();
                String etn_pass =userLoginViewBind.etn_password.getText().toString();

                if (etn_mobno.isEmpty()){
                    MyToast.show(userLoginActivity,"Please enter your mobile number",true);

                }  if (etn_pass.isEmpty()){
                    MyToast.show(userLoginActivity,"Please enter password",true);

                }
                else  if (etn_mobno.length()==10){

                    userLoginActivity.apicallForCustomarApilogin();     // api call for user login

                }else {
                    MyToast.show(userLoginActivity,"Please enter 10 numbers",true);
                }

            }
            break;

/*
            case  R.id.lv_login:{
                Intent mainIntent = new Intent(userMobileNumberActivity, UserLoginActivity.class);
                userMobileNumberActivity.startActivity(mainIntent);
            }
            break;*/


        }
    }
}
