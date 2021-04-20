package com.maity.maityspositiveliving.screen.ServiceProviderActivity;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.maity.maityspositiveliving.R;
import com.rts.commonutils_2_0.deviceinfo.DeviceResolution;

public class ServiceProviderViewBind extends DeviceResolution {
    ServiceProviderActivity serviceProviderActivity;
    View view;
    TextView headername_tvid;
    ImageView male_ivid,female_ivid,other_ivid,img_state;
    RelativeLayout back_icon;
    TextView tv_state;

    EditText etn_emailid,etn_nameid,etn_mobno,etn_address,pin_etnid;
    LinearLayout lv_submit;
    public ServiceProviderViewBind(ServiceProviderActivity serviceProviderActivity, View view) {
        super(serviceProviderActivity);
        this.serviceProviderActivity=serviceProviderActivity;
        this.view=view;
        initviewBind();
        setCustomTypeface();
    }

    // for adding custom typeface
    private void setCustomTypeface() {

    }

    // for findview
    private void initviewBind() {
        headername_tvid=view. findViewById(R.id.headername_tvid);
        male_ivid=view.findViewById(R.id.male_ivid);
        female_ivid=view.findViewById(R.id.female_ivid);
        other_ivid=view.findViewById(R.id.other_ivid);
        back_icon=view.findViewById(R.id.back_icon);
        tv_state=view.findViewById(R.id.tv_state);
        etn_emailid=view.findViewById(R.id.etn_emailid);
        etn_nameid=view.findViewById(R.id.etn_nameid);
        lv_submit=view.findViewById(R.id.lv_submit);
        img_state=view.findViewById(R.id.img_state);
        etn_mobno=view.findViewById(R.id.etn_mobno);
        etn_address=view.findViewById(R.id.etn_address);
        pin_etnid=view.findViewById(R.id.pin_etnid);


    }
}
