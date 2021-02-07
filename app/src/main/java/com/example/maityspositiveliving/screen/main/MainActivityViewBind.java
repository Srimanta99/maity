package com.example.maityspositiveliving.screen.main;

import android.view.View;
import android.widget.RelativeLayout;

import com.example.maityspositiveliving.R;
import com.rts.commonutils_2_0.deviceinfo.DeviceResolution;


public class MainActivityViewBind extends DeviceResolution {
    MainActivity mainActivity;
    View view;
    RelativeLayout serviceuserid,serviceproviderid;

    public MainActivityViewBind(MainActivity mainActivity, View view) {
        super(mainActivity);
        this.mainActivity=mainActivity;
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
        serviceuserid=view.findViewById(R.id.serviceuserid);
        serviceproviderid=view.findViewById(R.id.serviceproviderid);

    }
}
