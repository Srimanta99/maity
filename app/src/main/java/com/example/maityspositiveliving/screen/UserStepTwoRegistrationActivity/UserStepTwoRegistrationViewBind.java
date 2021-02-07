package com.example.maityspositiveliving.screen.UserStepTwoRegistrationActivity;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.example.maityspositiveliving.R;
import com.rts.commonutils_2_0.deviceinfo.DeviceResolution;

public class UserStepTwoRegistrationViewBind extends DeviceResolution {
    UserStepTwoRegistrationActivity userStepTwoRegistrationActivity;
    View view;
    RelativeLayout nextid,back_icon;
    LinearLayout select_calender;
    TextView dob_tvid;
    ImageView male_ivid,female_ivid,other_ivid;

    public UserStepTwoRegistrationViewBind(UserStepTwoRegistrationActivity userStepTwoRegistrationActivity, View view) {
        super(userStepTwoRegistrationActivity);
        this.userStepTwoRegistrationActivity=userStepTwoRegistrationActivity;
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
        nextid=view.findViewById(R.id.nextid);
        back_icon=view.findViewById(R.id.back_icon);
        select_calender=view.findViewById(R.id.select_calender);
        dob_tvid=view.findViewById(R.id.dob_tvid);
        male_ivid=view.findViewById(R.id.male_ivid);
        female_ivid=view.findViewById(R.id.female_ivid);
        other_ivid=view.findViewById(R.id.other_ivid);
    }
}
