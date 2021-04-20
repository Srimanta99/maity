package com.maity.maityspositiveliving.screen.Fragment.UserMyWalletFragment;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.maity.maityspositiveliving.R;
import com.maity.maityspositiveliving.Retrofit.interfaces.OnCallBackListner;
import com.maity.maityspositiveliving.Retrofit.models.ApiRequest;
import com.maity.maityspositiveliving.utils.ApplicationConstant;
import com.maity.maityspositiveliving.utils.MyToast;
import com.maity.maityspositiveliving.utils.SessionManager;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.razorpay.Checkout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.

 * create an instance of this fragment.
 */
public class UserMyWalletFragment extends Fragment implements  OnCallBackListner {

    UserMyWalletViewBind userPaymentViewBind;
    UserMyWalletOnClick userPaymentOnClick;
    Boolean saveLogin,saveRegistration;
    public static String   amount;
   public static String razorpay_order_id;
    public static int tAmount;
   public static BottomSheetDialog mBottomSheetDialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_payment, container, false);
    }
    //openbottomdialog
    public void OpenBottomDialog(final Activity activity) {
        View sheetView = activity.getLayoutInflater().inflate(R.layout.my_wallet_bottom_layout, null);
        ViewGroup parentViewGroup = (ViewGroup) sheetView.getParent();
        if (parentViewGroup != null) {
            parentViewGroup.removeAllViews();
        }

         mBottomSheetDialog = new BottomSheetDialog(activity, R.style.AppBottomSheetDialogTheme);
        mBottomSheetDialog.setContentView(sheetView);
        mBottomSheetDialog.show();
        FrameLayout bottomSheet = mBottomSheetDialog.findViewById(com.google.android.material.R.id.design_bottom_sheet);
        mBottomSheetDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        //BottomSheetBehavior.from(bottomSheet).setState(BottomSheetBehavior.STATE_EXPANDED);
            EditText etn_amount;
            RelativeLayout submit_btnid;

      ApiRequest  request=new ApiRequest(activity,this);

        etn_amount = sheetView.findViewById(R.id.etn_amount);
        submit_btnid = sheetView.findViewById(R.id.submit_btnid);

        saveLogin= SessionManager.getAfterLogin();
        saveRegistration= SessionManager.getLogged();

        submit_btnid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (saveLogin) {
                    amount  =etn_amount.getText().toString();


                    HashMap<String,String> hashMap=new HashMap<>();
                    hashMap.put("customer_id", SessionManager.getLoginidvalue());
                    hashMap.put("amount", amount);
                    request.callPOSTJSON_OBJECT(ApplicationConstant.addWalletBalance_url, hashMap, "addWalletBalance");


                }else if (saveRegistration) {

                    amount  =etn_amount.getText().toString();


                    HashMap<String,String> hashMap=new HashMap<>();
                    hashMap.put("customer_id", SessionManager.getregistrationidvalue());
                    hashMap.put("amount", amount);
                    request.callPOSTJSON_OBJECT(ApplicationConstant.addWalletBalance_url, hashMap, "addWalletBalance");
                }



            }
        });
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        userPaymentViewBind= new UserMyWalletViewBind(this,view);
        userPaymentOnClick=new UserMyWalletOnClick(this,userPaymentViewBind);

        saveLogin= SessionManager.getAfterLogin();
        saveRegistration= SessionManager.getLogged();

        if (saveLogin) {
            userPaymentViewBind.tv_customer_wallet_balance.setText("\u20B9"+ SessionManager.getLogincustomer_wallet_balance());


        }else if (saveRegistration) {
            userPaymentViewBind.tv_customer_wallet_balance.setText("\u20B9"+ SessionManager.getregcustomer_wallet_balancevalue());

        }
        super.onViewCreated(view, savedInstanceState);
    }


    @Override
    public void OnCallBackSuccess(String tag, String body) {

    }

    @Override
    public void OnCallBackSuccess(String tag, JSONObject jsonObject) {
        if (tag.equalsIgnoreCase("addWalletBalance")) {
            try {
                Log.d("sunitt", "OnCallBackSuccess: "+jsonObject);
                MyToast.show(getActivity(), "" + jsonObject.getString("_message"), true);
                JSONObject jsonObject1=jsonObject.getJSONObject("_data");
                   razorpay_order_id=  jsonObject1.getString("razorpay_order_id");
                Log.d("sunita", "OnCallBackSuccess: "+ razorpay_order_id);

                if (jsonObject.getString("_success").equalsIgnoreCase("1")){
                    startPayment(); //implement rejorpay  for online payment

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void OnCallBackError(String tag, String error, int i) {

    }

    //implement rejorpay for online payment
    public  void  startPayment() {
        /**
         * Instantiate Checkout
         */
        Checkout checkout = new Checkout();
        checkout.setKeyID("rzp_test_ADTjttKoCWTfXq");

        /**
         * Set your logo here
         */
        //  checkout.setImage(R.drawable.logo);

        /**
         * Reference to current activity
         */
        final Activity activity = (Activity) getContext();

        /**
         * Pass your payment options to the Razorpay Checkout as a JSONObject
         */
        try {
          tAmount=   Math.round(Float.parseFloat(String.valueOf(amount))*100);


            JSONObject options = new JSONObject();

            options.put("name", "Score Global Pvt Ltd");
           // options.put("description", "Reference No. #123456");
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            options.put("order_id", razorpay_order_id);//from response of step 3.
            options.put("theme.color", "#3399cc");
            options.put("currency", "INR");
            options.put("amount", tAmount);//pass amount in currency subunits
            options.put("prefill.email", "info@scoreglobal.in");
            options.put("prefill.contact","9046099084");
            checkout.open(activity, options);
        } catch(Exception e) {
            Log.e("error", "Error in starting Razorpay Checkout", e);
        }
    }



}