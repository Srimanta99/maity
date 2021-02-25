package com.example.maityspositiveliving.screen.UserCleaningPlaceOrderActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maityspositiveliving.R;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

public class UserCleaningPlaceOrderActivity extends AppCompatActivity implements PaymentResultListener {
    public static final String TAG=UserCleaningPlaceOrderActivity.class.getSimpleName();

    TextView headername_tvid,tv_service_charge,tv_GST,tv_tamount,tv_place_order,tv_note;
    int tAmount;
    RelativeLayout back_icon;
    String amount,note;

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

         tv_note.setText(note);

        int GST= Integer.valueOf(amount) * 18/100;

        tv_service_charge.setText(amount);

        tv_GST.setText(String.valueOf(GST));
         tAmount=Integer.valueOf(amount)+GST;


        tv_tamount.setText("\u20B9"+String.valueOf(tAmount));
        tAmount  =Math.round(Float.parseFloat(String.valueOf(tAmount))*100);


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
            //   options.put("order_id", "order_DBJOWzybf0sJbb");//from response of step 3.
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
    public void onPaymentSuccess(String s) {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setMessage(s);
        builder.show();
    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(this, ""+s, Toast.LENGTH_SHORT).show();

    }
}