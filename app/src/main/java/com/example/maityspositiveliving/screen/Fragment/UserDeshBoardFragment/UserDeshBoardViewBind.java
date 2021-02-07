package com.example.maityspositiveliving.screen.Fragment.UserDeshBoardFragment;

import android.view.View;
import android.widget.LinearLayout;

import com.example.maityspositiveliving.R;
import com.rts.commonutils_2_0.deviceinfo.DeviceResolution;

public class UserDeshBoardViewBind extends DeviceResolution {
    UserDeshBoardFragment userDeshBoardFragment;
    View view;

    LinearLayout household_lvid,lvidteachers,classlvid,lvidholidaylist,lvstudentsfeesid,lvidFeedbacklist,lvidtoday,lvidhometask;


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
        household_lvid=view.findViewById(R.id.household_lvid);
       /* lvidteachers=view.findViewById(R.id.lvidteachers);
        classlvid=view.findViewById(R.id.classlvid);
        lvidholidaylist=view.findViewById(R.id.lvidholidaylist);
        lvstudentsfeesid=view.findViewById(R.id.lvstudentsfeesid);
        lvidhometask=view.findViewById(R.id.lvidhometask);
        lvidFeedbacklist=view.findViewById(R.id.lvidFeedbacklist);
        lvidtoday=view.findViewById(R.id.lvidtoday);*/
    }
}
