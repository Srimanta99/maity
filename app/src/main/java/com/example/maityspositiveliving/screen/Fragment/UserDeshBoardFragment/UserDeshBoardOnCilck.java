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
        userDeshBoardViewBind.household_lvid.setOnClickListener(this);
       /* userDeshBoardViewBind.lvidteachers.setOnClickListener(this);
        userDeshBoardViewBind.classlvid.setOnClickListener(this);
        userDeshBoardViewBind.lvidholidaylist.setOnClickListener(this);
        userDeshBoardViewBind.lvstudentsfeesid.setOnClickListener(this);
        userDeshBoardViewBind.lvidFeedbacklist.setOnClickListener(this);
        userDeshBoardViewBind.lvidhometask.setOnClickListener(this);
        userDeshBoardViewBind.lvidtoday.setOnClickListener(this);*/

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.household_lvid:{
                // Toast.makeText(mainActivity,"hello",Toast.LENGTH_LONG).show();

                Intent intent=new Intent(userDeshBoardFragment.getActivity(), UserHouseHoldActivity.class);
                userDeshBoardFragment.getActivity().startActivity(intent);

            }
            break;
           /* case R.id.lvidteachers:{
                // Toast.makeText(mainActivity,"hello",Toast.LENGTH_LONG).show();


                Intent intent=new Intent(certerManagerFragment.getContext(), CenterTeacherActivity.class);
                certerManagerFragment.startActivity(intent);
            }
            break;
            case R.id.classlvid:{
                // Toast.makeText(mainActivity,"hello",Toast.LENGTH_LONG).show();


                Intent intent=new Intent(certerManagerFragment.getContext(), CenterClassActivity.class);
                certerManagerFragment.startActivity(intent);
            }
            break;

            case R.id.lvidholidaylist:{
                Intent intent=new Intent(certerManagerFragment.getContext(), CenterHolidayListActivity.class);
                certerManagerFragment.startActivity(intent);
            }
            break;

            case R.id.lvstudentsfeesid :{
                Intent intent=new Intent(certerManagerFragment.getContext(), CenterStudentsFeesActivity.class);
                certerManagerFragment.startActivity(intent);
            }
            break;
            case R.id.lvidhometask :{
                Intent intent=new Intent(certerManagerFragment.getContext(), CenterHomeTaskActivity.class);
                certerManagerFragment.startActivity(intent);
            }
            break;

            case R.id.lvidtoday :{
                Intent intent=new Intent(certerManagerFragment.getContext(), CenterTodayActivity.class);
                certerManagerFragment.startActivity(intent);
            }
            break;

            case R.id.lvidFeedbacklist :{
                Intent intent=new Intent(certerManagerFragment.getContext(), CenterFeedbacklistActivity.class);
                certerManagerFragment.startActivity(intent);
            }
            break;
*/

        }
    }
}