package com.maity.maityspositiveliving.screen.UserDeshboardActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.maity.maityspositiveliving.R;
import com.maity.maityspositiveliving.Retrofit.interfaces.OnCallBackListner;
import com.maity.maityspositiveliving.Retrofit.models.ApiRequest;
import com.maity.maityspositiveliving.screen.Fragment.UserCategoryListFragment.UserCategoryListFragment;
import com.maity.maityspositiveliving.utils.ApplicationConstant;
import com.maity.maityspositiveliving.utils.MyToast;
import com.maity.maityspositiveliving.utils.SessionManager;
import com.razorpay.PaymentData;
import com.razorpay.PaymentResultWithDataListener;

import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;

import java.util.HashMap;

import static com.maity.maityspositiveliving.screen.Fragment.UserMyWalletFragment.UserMyWalletFragment.amount;
import static com.maity.maityspositiveliving.screen.Fragment.UserMyWalletFragment.UserMyWalletFragment.mBottomSheetDialog;
import static com.maity.maityspositiveliving.screen.Fragment.UserMyWalletFragment.UserMyWalletFragment.razorpay_order_id;
import static com.maity.maityspositiveliving.screen.Fragment.UserMyWalletFragment.UserMyWalletViewBind.tv_customer_wallet_balance;

public class UserDeshboardActivity extends AppCompatActivity implements PaymentResultWithDataListener , OnCallBackListner {

    UserDeshboardViewBind userDashboardViewBind;
    UserDeshboardOnClick userDashboardOnClick;
    Boolean saveLogin,saveRegistration;
    ApiRequest request;
    public  static   String customer_wallet_balance;
    String currentVersion;
    Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);
        View view= LayoutInflater.from(this).inflate(R.layout.activity_user_dashboard,null);
        setContentView(view);

        userDashboardViewBind= new UserDeshboardViewBind(this,view);
        userDashboardOnClick=new UserDeshboardOnClick(this,userDashboardViewBind);

        request =new ApiRequest(UserDeshboardActivity.this,this);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentid, new UserCategoryListFragment()).commit();
            userDashboardViewBind.navigationView.setCheckedItem(R.id.dashboardid);

        }

        try {
            currentVersion = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
            Log.d("sunita", "onCreate: "+currentVersion);
            new GetVersionCode().execute();

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        saveLogin= SessionManager.getAfterLogin();//get boolean value after login
        saveRegistration= SessionManager.getLogged(); //get boolean value after registration


        if (saveLogin) {
            userDashboardViewBind.tv_name.setText(SessionManager.getLoginnamevalue());// after login get name then settext in all edittext
            userDashboardViewBind.tv_phno.setText(SessionManager.getLoginphnovalue());// after login get phno then settext in all edittext


        }else if (saveRegistration) {

            userDashboardViewBind.tv_name.setText(SessionManager.getnamevalue());// after registration get name then settext in all edittext
            userDashboardViewBind.tv_phno.setText(SessionManager.getphnovalue());// after registration get email then settext in all edittext
        }

    }


    public void onBackPressed() {
        finish();
    }



    @Override
    public void onPaymentSuccess(String s, PaymentData paymentData) {
        Log.d("sunitaaaa", "razorpay_payment_id: "+s);
          Log.d("sunitaaaa", "razorpay_order_id: "+razorpay_order_id);
        Log.d("sunitaaaa", "razorpay_signature: "+paymentData.getSignature());
       String razorpay_signature=  paymentData.getSignature();
       try {
           // api implement for paymentverify
           apiForpaymentverify(razorpay_order_id,s,razorpay_signature);

       }catch (Exception e){
       }

    }

    @Override
    public void onPaymentError(int i, String s, PaymentData paymentData) {

    }
    // api implement for paymentverify

    public void apiForpaymentverify(String razorpay_order_id, String Payment_id,String razorpay_signature){

        if (saveLogin) {
            HashMap<String,String> hashMap=new HashMap<>();
            hashMap.put("amount", String.valueOf(amount));
            hashMap.put("customer_id", SessionManager.getLoginidvalue());
            hashMap.put("razorpay_order_id",razorpay_order_id);
            hashMap.put("razorpay_payment_id",Payment_id);
            hashMap.put("razorpay_signature",razorpay_signature);

            Log.d("hash", "apiForpaymentverify: "+hashMap);
            request.callPOSTJSON_OBJECT(ApplicationConstant.verifyWalletPayment_url,hashMap,"verifyWalletPaymentLogin");

        }else if (saveRegistration) {

            HashMap<String,String> hashMap=new HashMap<>();
            hashMap.put("amount", String.valueOf(amount));
            hashMap.put("customer_id", SessionManager.getregistrationidvalue());
            hashMap.put("razorpay_order_id",razorpay_order_id);
            hashMap.put("razorpay_payment_id",Payment_id);
            hashMap.put("razorpay_signature",razorpay_signature);

            Log.d("hash", "apiForpaymentverify: "+hashMap);
            request.callPOSTJSON_OBJECT(ApplicationConstant.verifyWalletPayment_url,hashMap,"verifyWalletPaymentReg");        }


    }

    @Override
    public void OnCallBackSuccess(String tag, String body) {

    }

    @Override
    public void OnCallBackSuccess(String tag, JSONObject jsonObject) {
        if (tag.equalsIgnoreCase("verifyWalletPaymentLogin")) {

            try {

                MyToast.show(this, "" + jsonObject.getString("_message"), true);

                if (jsonObject.getString("_success").equalsIgnoreCase("1")) {
                    JSONObject json = jsonObject.getJSONObject("_data");
                    customer_wallet_balance = json.getString("_customer_wallet_balance");

                    tv_customer_wallet_balance.setText("\u20B9" + customer_wallet_balance);
                    SessionManager.setLogincustomer_wallet_balance(customer_wallet_balance); //set update customer_wallet_balance

                    mBottomSheetDialog.dismiss();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else
        if (tag.equalsIgnoreCase("verifyWalletPaymentReg")) {

            try {

                MyToast.show(this, "" + jsonObject.getString("_message"), true);

                if (jsonObject.getString("_success").equalsIgnoreCase("1")) {
                    JSONObject json = jsonObject.getJSONObject("_data");
                    customer_wallet_balance = json.getString("_customer_wallet_balance");

                    tv_customer_wallet_balance.setText("\u20B9" + customer_wallet_balance);
                    SessionManager.setregcustomer_wallet_balancevalue(customer_wallet_balance); //set update customer_wallet_balance

                    mBottomSheetDialog.dismiss();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void OnCallBackError(String tag, String error, int i) {

    }



    public class GetVersionCode extends AsyncTask<Void, String, String> {
        @Override
        protected String doInBackground(Void... voids) {

            String newVersion = null;
            try {
                newVersion = Jsoup.connect("https://play.google.com/store/apps/details?id=" + UserDeshboardActivity.this.getPackageName() + "&hl=it")
                        .timeout(30000)
                        .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                        .referrer("http://www.google.com")
                        .get()
                        .select(".hAyfc .htlgb")
                        .get(7)
                        .ownText();
                return newVersion;
            } catch (Exception e) {
                return newVersion;
            }
        }

        @Override
        protected void onPostExecute(String onlineVersion) {
            super.onPostExecute(onlineVersion);
            Log.d("update", "Current version " + currentVersion + "playstore version " + onlineVersion);

            double onlineversioncode=2.0;

            if (onlineVersion != null && !onlineVersion.isEmpty()) {
                if (Float.valueOf(currentVersion) < Float.valueOf(onlineVersion)) {
                    //show dialog
                    showUpdateDialog();
                }
            }

                /*if (Float.valueOf(currentVersion) < onlineversioncode) {
                    //show dialog
                    showUpdateDialog();
                }*/

        }
    }


    public void showUpdateDialog() {
        android.app.AlertDialog.Builder builder=new android.app.AlertDialog.Builder(this,R.style.CustomAlertDialogforchooseimage);
        View view1= LayoutInflater.from(getApplicationContext()).inflate(R.layout.change_version_layout,null);

        builder.setView(view1);
        dialog=builder.create();

        dialog.setCancelable(true);
        dialog.show();

        TextView tv_update,tv_cencel;

        tv_cencel=dialog.findViewById(R.id.tv_cencel);
        tv_update=dialog.findViewById(R.id.tv_update);

        tv_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String appPackageName = currentVersion; // getPackageName() from Context or Activity object
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$appPackageName")));
                } catch (Exception e) {
                    startActivity(
                            new Intent(
                                    Intent.ACTION_VIEW,
                                    Uri.parse("https://play.google.com/store/apps/details?id=$appPackageName")
                            )
                    );
                }
            }
        });

        tv_cencel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

    }
}