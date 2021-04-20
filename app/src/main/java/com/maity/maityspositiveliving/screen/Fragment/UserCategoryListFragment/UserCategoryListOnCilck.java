package com.maity.maityspositiveliving.screen.Fragment.UserCategoryListFragment;

import android.view.View;

public class UserCategoryListOnCilck implements View.OnClickListener{
    UserCategoryListFragment userCategoryListFragment;
    UserCategoryListViewBind userCategoryListViewBind;

    public UserCategoryListOnCilck(UserCategoryListFragment userCategoryListFragment, UserCategoryListViewBind userCategoryListViewBind) {
        this.userCategoryListFragment=userCategoryListFragment;
        this.userCategoryListViewBind=userCategoryListViewBind;
        setonclicklistner();
    }
    // set click listner.
    private void setonclicklistner() {
        //userDeshBoardViewBind.household_lvid.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){


        }
    }
}