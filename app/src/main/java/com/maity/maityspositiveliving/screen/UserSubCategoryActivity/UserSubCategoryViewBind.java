package com.maity.maityspositiveliving.screen.UserSubCategoryActivity;

import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.maity.maityspositiveliving.R;
import com.rts.commonutils_2_0.deviceinfo.DeviceResolution;

public class UserSubCategoryViewBind extends DeviceResolution {
    UserSubCategoryActivity userCleaningActivity;
    View view;
    RelativeLayout back_icon;
    TextView toolname_id,tv_email;
    RelativeLayout submit_btnid;
    TextView headername_tvid,note_tvid,tv_upload,tv_amount,tv_address,tv_phno,tv_name;
    EditText etn_note;
    LinearLayout lv_attachmentone;


    public UserSubCategoryViewBind(UserSubCategoryActivity userCleaningActivity, View view) {
        super(userCleaningActivity);
        this.userCleaningActivity=userCleaningActivity;
        this.view=view;
        initviewBind();
        setCustomTypeface();
    }

    // for adding custom typeface
    private void setCustomTypeface() {

    }

    // for findview
    private void initviewBind() {
        back_icon =view. findViewById(R.id.back_icon);
        submit_btnid=view.findViewById(R.id.submit_btnid);
        headername_tvid=view.findViewById(R.id.headername_tvid);
        note_tvid=view.findViewById(R.id.note_tvid);
        tv_upload=view.findViewById(R.id.tv_upload);
        tv_amount=view.findViewById(R.id.tv_amount);
        lv_attachmentone=view.findViewById(R.id.lv_attachmentone);
        etn_note=view.findViewById(R.id.etn_note);
        tv_name=view.findViewById(R.id.tv_name);
        tv_phno=view.findViewById(R.id.tv_phno);
        tv_address=view.findViewById(R.id.tv_address);
        tv_email=view.findViewById(R.id.tv_email);
    }
}
