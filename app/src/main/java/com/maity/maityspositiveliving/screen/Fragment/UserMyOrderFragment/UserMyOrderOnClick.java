package com.maity.maityspositiveliving.screen.Fragment.UserMyOrderFragment;

import android.view.View;


public class UserMyOrderOnClick implements View.OnClickListener{
    UserMyOrderFragment userMyOrderFragment;
    UserMyOrderViewBind userMyOrderViewBind;

    public UserMyOrderOnClick(UserMyOrderFragment userMyOrderFragment, UserMyOrderViewBind userMyOrderViewBind) {
        this.userMyOrderFragment=userMyOrderFragment;
        this.userMyOrderViewBind=userMyOrderViewBind;
        setonclicklistner();
    }
    // set click listner.
    private void setonclicklistner() {

        userMyOrderViewBind.rv_orderlist.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
       /*     case R.id.rv_orderlist:{
                // Toast.makeText(mainActivity,"hello",Toast.LENGTH_LONG).show();


            }
            break;*/
        }
    }
}
