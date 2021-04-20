package com.maity.maityspositiveliving.screen.Fragment.UserUpdateProfileFragment;

import android.view.View;

import com.maity.maityspositiveliving.R;

import static com.maity.maityspositiveliving.screen.Fragment.UserUpdateProfileFragment.UserUpdateProfileFragment.saveLogin;
import static com.maity.maityspositiveliving.screen.Fragment.UserUpdateProfileFragment.UserUpdateProfileFragment.saveRegistration;

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

                if (saveLogin) {
                    userUpdateProfileFragment. apiForupdateAferlogin(); // implement api for update profile


                }else if (saveRegistration) {
                    userUpdateProfileFragment.apiForupdateAferregistration();
                }

            }
            break;


        }
    }
}

