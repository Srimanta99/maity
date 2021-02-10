package com.example.maityspositiveliving.screen.Fragment.UserDeshBoardFragment;

import android.view.View;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.RecyclerView;

import com.example.maityspositiveliving.R;
import com.rts.commonutils_2_0.deviceinfo.DeviceResolution;

public class UserDeshBoardViewBind extends DeviceResolution {
    UserDeshBoardFragment userDeshBoardFragment;
    View view;

    LinearLayout household_lvid,lvidteachers,classlvid,lvidholidaylist,lvstudentsfeesid,lvidFeedbacklist,lvidtoday,lvidhometask;
 RecyclerView rv_parentcategory;

    public UserDeshBoardViewBind( UserDeshBoardFragment userDeshBoardFragment, View view) {
        super(userDeshBoardFragment.getActivity());
        this.userDeshBoardFragment=userDeshBoardFragment;
        this.view=view;
        initviewBind();
        setCustomTypeface();
    }

    // for adding custom typeface
    private void setCustomTypeface() {
        // studentlvid.setTypeface(getbebas(mainActivity));
//        btn_hello_wrold.setTypeface(getbebas(mainActivity));
    }

    // for findview
    private void initviewBind() {
      //  household_lvid=view.findViewById(R.id.household_lvid);
        rv_parentcategory=view.findViewById(R.id.rv_parentcategory);
    }
}
