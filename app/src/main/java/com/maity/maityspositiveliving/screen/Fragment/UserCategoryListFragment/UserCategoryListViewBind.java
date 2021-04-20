package com.maity.maityspositiveliving.screen.Fragment.UserCategoryListFragment;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.maity.maityspositiveliving.R;
import com.rts.commonutils_2_0.deviceinfo.DeviceResolution;

public class UserCategoryListViewBind extends DeviceResolution {
    UserCategoryListFragment userCategoryListFragment;
    View view;


 RecyclerView rv_parentcategory;

    public UserCategoryListViewBind(UserCategoryListFragment userCategoryListFragment, View view) {
        super(userCategoryListFragment.getActivity());
        this.userCategoryListFragment=userCategoryListFragment;
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
