package com.maity.maityspositiveliving.screen.Fragment.UserMyWalletFragment;

import android.view.View;

import com.maity.maityspositiveliving.R;

public class UserMyWalletOnClick implements View.OnClickListener{
    UserMyWalletFragment userPaymentFragment;
    UserMyWalletViewBind userPaymentViewBind;

    public UserMyWalletOnClick(UserMyWalletFragment userPaymentFragment, UserMyWalletViewBind userPaymentViewBind) {
        this.userPaymentFragment=userPaymentFragment;
        this.userPaymentViewBind=userPaymentViewBind;
        setonclicklistner();
    }
    // set click listner.
    private void setonclicklistner() {
        userPaymentViewBind.addmoney_btnid.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
           case R.id.addmoney_btnid:{

             userPaymentFragment.  OpenBottomDialog(userPaymentFragment.getActivity()); //openbottomdialog


           }
            break;
        }
    }
}

