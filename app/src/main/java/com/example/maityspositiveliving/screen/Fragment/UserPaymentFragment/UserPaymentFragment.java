package com.example.maityspositiveliving.screen.Fragment.UserPaymentFragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.maityspositiveliving.R;
import com.example.maityspositiveliving.screen.UserCleaningActivity.UserCleaningOnClick;
import com.example.maityspositiveliving.screen.UserCleaningActivity.UserCleaningViewBind;
import com.example.maityspositiveliving.screen.UserCongratulationAcitivity.UserCongratulationActivity;
import com.example.maityspositiveliving.screen.UserHouseHoldActivity.UserHouseHoldActivity;
import com.google.android.material.bottomsheet.BottomSheetDialog;

/**
 * A simple {@link Fragment} subclass.

 * create an instance of this fragment.
 */
public class UserPaymentFragment extends Fragment {

    UserPaymentViewBind userPaymentViewBind;
    UserPaymentOnClick userPaymentOnClick;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_payment, container, false);
    }

/*
    public static void OpenBottomDialog(final Activity activity) {
        View sheetView = activity.getLayoutInflater().inflate(R.layout.payment_bottom_layout, null);
        ViewGroup parentViewGroup = (ViewGroup) sheetView.getParent();
        if (parentViewGroup != null) {
            parentViewGroup.removeAllViews();
        }

        final BottomSheetDialog mBottomSheetDialog = new BottomSheetDialog(activity,R.style.AppBottomSheetDialogTheme);
        mBottomSheetDialog.setContentView(sheetView);
        mBottomSheetDialog.show();
        FrameLayout bottomSheet = mBottomSheetDialog.findViewById(com.google.android.material.R.id.design_bottom_sheet);
        mBottomSheetDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        //BottomSheetBehavior.from(bottomSheet).setState(BottomSheetBehavior.STATE_EXPANDED);
        LinearLayout bhim_upi_lvid,debit_card_lvid,credit_card_lvid;
        RelativeLayout payment_btnid=sheetView.findViewById(R.id.payment_btnid);
        bhim_upi_lvid=sheetView.findViewById(R.id.bhim_upi_lvid);
        debit_card_lvid=sheetView.findViewById(R.id.debit_card_lvid);
        credit_card_lvid=sheetView.findViewById(R.id.credit_card_lvid);


        payment_btnid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(activity, UserCongratulationActivity.class);
               activity.startActivity(intent);

            }
        });





       */
/* ImageView imgclose = sheetView.findViewById(R.id.imgclose);
        TextView txttitle = sheetView.findViewById(R.id.tvTitle);
        Button btnNotNow = sheetView.findViewById(R.id.btnNotNow);
        Button btnUpadateNow = sheetView.findViewById(R.id.btnUpdateNow);
        if (Constant.VERSION_STATUS.equals("0")) {
            btnNotNow.setVisibility(View.VISIBLE);
            imgclose.setVisibility(View.VISIBLE);
            mBottomSheetDialog.setCancelable(true);
        } else
            mBottomSheetDialog.setCancelable(false);


        imgclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBottomSheetDialog.isShowing())
                    mBottomSheetDialog.dismiss();
            }
        });
        btnNotNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBottomSheetDialog.isShowing())
                    mBottomSheetDialog.dismiss();
            }
        });

        btnUpadateNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(Constant.PLAY_STORE_LINK + activity.getPackageName())));
            }
        });*//*

    }
*/
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        userPaymentViewBind= new UserPaymentViewBind(this,view);
        userPaymentOnClick=new UserPaymentOnClick(this,userPaymentViewBind);



       /* addmoney_btnid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });*/
        super.onViewCreated(view, savedInstanceState);
    }
}