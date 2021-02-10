package com.example.maityspositiveliving.screen.Fragment.UserDeshBoardFragment;

import android.content.Intent;
import android.view.View;

import com.example.maityspositiveliving.R;
import com.example.maityspositiveliving.screen.UserHouseHoldActivity.UserHouseHoldActivity;

public class UserDeshBoardOnCilck implements View.OnClickListener{
    UserDeshBoardFragment userDeshBoardFragment;
    UserDeshBoardViewBind userDeshBoardViewBind;

    public UserDeshBoardOnCilck( UserDeshBoardFragment userDeshBoardFragment,UserDeshBoardViewBind userDeshBoardViewBind) {
        this.userDeshBoardFragment=userDeshBoardFragment;
        this.userDeshBoardViewBind=userDeshBoardViewBind;
        setonclicklistner();
    }
    // set click listner.
    private void setonclicklistner() {
        //userDeshBoardViewBind.household_lvid.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
           /* case R.id.household_lvid:{
                // Toast.makeText(mainActivity,"hello",Toast.LENGTH_LONG).show();

                Intent intent=new Intent(userDeshBoardFragment.getActivity(), UserHouseHoldActivity.class);
                userDeshBoardFragment.getActivity().startActivity(intent);

            }
            break;*/

        }
    }
}