package com.example.maityspositiveliving.screen.Fragment.UserUpdateProfileFragment;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;

import com.example.maityspositiveliving.R;
import com.example.maityspositiveliving.screen.Fragment.UserPaymentFragment.UserPaymentFragment;
import com.example.maityspositiveliving.screen.Fragment.UserPaymentFragment.UserPaymentViewBind;
import com.example.maityspositiveliving.screen.UserCongratulationAcitivity.UserCongratulationActivity;

public class UserUpdateProfileOnClick implements View.OnClickListener{
    UserUpdateProfileFragment userUpdateProfileFragment;
    UserUpdateProfileViewBInd userUpdateProfileViewBInd;

    public UserUpdateProfileOnClick(UserUpdateProfileFragment userUpdateProfileFragment,UserUpdateProfileViewBInd userUpdateProfileViewBInd) {
        this.userUpdateProfileFragment=userUpdateProfileFragment;
        this.userUpdateProfileViewBInd=userUpdateProfileViewBInd;
        setonclicklistner();
    }
    // set click listner.
    private void setonclicklistner() {
        userUpdateProfileViewBInd.btn_submit.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_submit:{
                // Toast.makeText(mainActivity,"hello",Toast.LENGTH_LONG).show();


               userUpdateProfileFragment. apiForupdate();

            }
            break;


        }
    }
}

