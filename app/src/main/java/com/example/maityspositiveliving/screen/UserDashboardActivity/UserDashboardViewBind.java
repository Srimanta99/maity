package com.example.maityspositiveliving.screen.UserDashboardActivity;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.drawerlayout.widget.DrawerLayout;

import com.example.maityspositiveliving.R;
import com.google.android.material.navigation.NavigationView;
import com.rts.commonutils_2_0.deviceinfo.DeviceResolution;

public class UserDashboardViewBind extends DeviceResolution {
    UserDashboardActivity userDashboardActivity;
    View view;
    RelativeLayout menu_icon;
    LinearLayout dashboardid,myorderid,paymentid,settingsid;
    DrawerLayout drawer;
     ImageView editid;
    NavigationView navigationView;
    TextView dashboard_tvid;

    public UserDashboardViewBind(UserDashboardActivity userDashboardActivity, View view) {
        super(userDashboardActivity);
        this.userDashboardActivity=userDashboardActivity;
        this.view=view;
        initviewBind();
        setCustomTypeface();
    }

    // for adding custom typeface
    private void setCustomTypeface() {

    }

    // for findview
    public void initviewBind() {

        drawer =view.findViewById(R.id.drawer_layout);
        navigationView =view.findViewById(R.id.nav_view);
        menu_icon = view.findViewById(R.id.menu_icon);
        dashboardid=view.findViewById(R.id.dashboardid);
        myorderid=view.findViewById(R.id.myorderid);
        paymentid=view.findViewById(R.id.paymentid);
        settingsid=view.findViewById(R.id.settingsid);
        editid=view.findViewById(R.id.editid);
        dashboard_tvid=view.findViewById(R.id.dashboard_tvid);

    }
}