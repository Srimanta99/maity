package com.example.maityspositiveliving.screen.UserStepThreeRegistrationActivity;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;


import com.example.maityspositiveliving.R;
import com.rts.commonutils_2_0.deviceinfo.DeviceResolution;

public class UserStepThreeRegistrationViewBind extends DeviceResolution {
    UserStepThreeRegistrationActivity userStepThreeRegistrationActivity;
    View view;
    RelativeLayout nextid,back_icon;
    EditText address_etnid,pin_etnid;
    Spinner select_state_spinnerid,select_city_spinnerid;
    ImageView img_state;

    public UserStepThreeRegistrationViewBind(UserStepThreeRegistrationActivity userStepThreeRegistrationActivity, View view) {
        super(userStepThreeRegistrationActivity);
        this.userStepThreeRegistrationActivity=userStepThreeRegistrationActivity;
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
        pin_etnid=view.findViewById(R.id.pin_etnid);
        address_etnid=view.findViewById(R.id.address_etnid);
        select_state_spinnerid=view.findViewById(R.id.select_state_spinnerid);
        select_city_spinnerid=view.findViewById(R.id.select_city_spinnerid);
        img_state=view.findViewById(R.id.img_state);

    }
}