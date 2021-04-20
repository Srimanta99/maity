package com.maity.maityspositiveliving.screen.UserStepThreeRegistrationActivity;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import com.maity.maityspositiveliving.R;
import com.rts.commonutils_2_0.deviceinfo.DeviceResolution;

public class UserStepThreeRegistrationViewBind extends DeviceResolution {
    UserStepThreeRegistrationActivity userStepThreeRegistrationActivity;
    View view;
    RelativeLayout nextid,back_icon;
    EditText address_etnid,pin_etnid;
    TextView tv_state,tv_city;
    RecyclerView rv_state,rv_city;
    EditText etn_search;
    ImageView img_state,img_city;

    public UserStepThreeRegistrationViewBind(UserStepThreeRegistrationActivity userStepThreeRegistrationActivity, View view) {
        super(userStepThreeRegistrationActivity);
        this.userStepThreeRegistrationActivity=userStepThreeRegistrationActivity;
        this.view=view;
        initviewBind();
        setCustomTypeface();
    }

    // for adding custom typeface
    private void setCustomTypeface() {

    }

    // for findview
    private void initviewBind() {
        nextid=view.findViewById(R.id.nextid);
        back_icon=view.findViewById(R.id.back_icon);
        pin_etnid=view.findViewById(R.id.pin_etnid);
        address_etnid=view.findViewById(R.id.address_etnid);
        tv_state=view.findViewById(R.id.tv_state);
        tv_city=view.findViewById(R.id.tv_city);
        img_state=view.findViewById(R.id.img_state);
        img_city=view.findViewById(R.id.img_city);
        rv_state=view.findViewById(R.id.rv_state);
        rv_city=view.findViewById(R.id.rv_city);

        etn_search=view.findViewById(R.id.etn_search);

    }
}