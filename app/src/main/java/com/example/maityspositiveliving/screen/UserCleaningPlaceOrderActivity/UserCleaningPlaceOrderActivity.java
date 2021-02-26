package com.example.maityspositiveliving.screen.UserCleaningPlaceOrderActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maityspositiveliving.R;
import com.example.maityspositiveliving.Retrofit.interfaces.OnCallBackListner;
import com.example.maityspositiveliving.Retrofit.models.ApiRequest;
import com.example.maityspositiveliving.screen.UserCleaningActivity.UserCleaningActivity;
import com.example.maityspositiveliving.screen.UserCongratulationAcitivity.UserCongratulationActivity;
import com.example.maityspositiveliving.utils.ApplicationConstant;
import com.example.maityspositiveliving.utils.MyToast;
import com.razorpay.Checkout;
import com.razorpay.PaymentData;
import com.razorpay.PaymentResultListener;
import com.razorpay.PaymentResultWithDataListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class UserCleaningPlaceOrderActivity extends AppCompatActivity implements PaymentResultWithDataListener, OnCallBackListner {
    public static final String TAG=UserCleaningPlaceOrderActivity.class.getSimpleName();

    ApiRequest request;


    TextView headername_tvid,tv_service_charge,tv_GST,tv_tamount,tv_place_order,tv_note;
    int tAmount;
    RelativeLayout back_icon;
    String amount,note,razorpay_order_id,razorpay_signature;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_cleaning_place_order);

        headername_tvid=findViewById(R.id.headername_tvid);
        back_icon=findViewById(R.id.back_icon);
        tv_service_charge=findViewById(R.id.tv_service_charge);
        tv_GST=findViewById(R.id.tv_GST);
        tv_tamount=findViewById(R.id.tv_tamount);
        tv_place_order=findViewById(R.id.tv_place_order);
        tv_note=findViewById(R.id.tv_note);

        headername_tvid.setText("CLEANING");

        amount=   getIntent().getStringExtra("amount");
        note=   getIntent().getStringExtra("note");
        razorpay_order_id=getIntent().getStringExtra("razorpay_order_id");

         tv_note.setText(note);

        int GST= Integer.valueOf(amount) * 18/100;

        tv_service_charge.setText(amount);

        tv_GST.setText(String.valueOf(GST));
         tAmount=Integer.valueOf(amount)+GST;


        tv_tamount.setText("\u20B9"+String.valueOf(tAmount));
        tAmount  =Math.round(Float.parseFloat(String.valueOf(tAmount))*100);


        request=new ApiRequest(this,this);



        back_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        tv_place_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startPayment();
            }
        });

    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }


    public void startPayment() {
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
        final Activity activity = this;

        /**
         * Pass your payment options to the Razorpay Checkout as a JSONObject
         */
        try {
            JSONObject options = new JSONObject();

            options.put("name", "Merchant Name");
            options.put("description", "Reference No. #123456");
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            options.put("order_id", razorpay_order_id);//from response of step 3.
            options.put("theme.color", "#3399cc");
            options.put("currency", "INR");
            options.put("amount", tAmount);//pass amount in currency subunits
            options.put("prefill.email", "Sunita.Chowdhury@example.com");
            options.put("prefill.contact","8777069940");
            checkout.open(activity, options);
        } catch(Exception e) {
            Log.e(TAG, "Error in starting Razorpay Checkout", e);
        }
    }




    @Override
    public void onPaymentSuccess(String s, PaymentData paymentData) {


        Log.d("sunitaaaa", "razorpay_payment_id: "+s);
        Log.d("sunitaaaa", "razorpay_order_id: "+razorpay_order_id);
        Log.d("sunitaaaa", "razorpay_signature: "+paymentData.getSignature());
        razorpay_signature=  paymentData.getSignature();

        apiForpaymentverify(razorpay_order_id,s,razorpay_signature);

    }


    @Override
    public void onPaymentError(int i, String s, PaymentData paymentData) {
        Toast.makeText(this, ""+s, Toast.LENGTH_SHORT).show();

    }


    public  void apiForpaymentverify(String razorpay_order_id,String razorpay_payment_id,String razorpay_signature){
        HashMap<String,String> hashMap=new HashMap<>();
        hashMap.put("order_id", "1");
        hashMap.put("razorpay_order_id",razorpay_order_id);
        hashMap.put("razorpay_payment_id",razorpay_payment_id);
        hashMap.put("razorpay_signature",razorpay_signature);


        request.callPOST(ApplicationConstant.paymentverify_url,hashMap,"paymentverify");


    }

    @Override
    public void OnCallBackSuccess(String tag, String body) {
        if (tag.equalsIgnoreCase("paymentverify")){
            try {
                JSONObject jsonObject=new JSONObject(body);
                MyToast.show(this,""+jsonObject.getString("_message"),true);


                Intent intent=new Intent(UserCleaningPlaceOrderActivity.this, UserCongratulationActivity.class);
                startActivity(intent);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void OnCallBackError(String tag, String error, int i) {

    }
}