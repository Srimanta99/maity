package com.maity.maityspositiveliving.screen.UserSubCategoryPlaceOrderActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.maity.maityspositiveliving.R;
import com.maity.maityspositiveliving.Retrofit.interfaces.OnCallBackListner;
import com.maity.maityspositiveliving.Retrofit.models.ApiRequest;
import com.maity.maityspositiveliving.screen.UserCongratulationAcitivity.UserCongratulationActivity;
import com.maity.maityspositiveliving.utils.ApplicationConstant;
import com.maity.maityspositiveliving.utils.MyToast;
import com.maity.maityspositiveliving.utils.SessionManager;
import com.razorpay.Checkout;
import com.razorpay.PaymentData;
import com.razorpay.PaymentResultWithDataListener;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class UserSubCategoryPlaceOrderActivity extends AppCompatActivity implements PaymentResultWithDataListener, OnCallBackListner {
    UserSubCatePlaceOrderViewBind userSubCatePlaceOrderViewBind;
    UserSubCatePlaceOrderOnClick userSubCatePlaceOrderOnClick;
    ApiRequest request;

    AlertDialog dialog;
    int tAmount,GST;

    ArrayList<String> fileType;
    String amount,note,razorpay_order_id,razorpay_signature,categories_id;
    ArrayList<File> fileArray;
    Boolean saveLogin,saveRegistration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_user_cleaning_place_order);
        View view= LayoutInflater.from(this).inflate(R.layout.activity_user_cleaning_place_order,null);
        setContentView(view);
        userSubCatePlaceOrderViewBind= new UserSubCatePlaceOrderViewBind(this,view);
        userSubCatePlaceOrderOnClick=new UserSubCatePlaceOrderOnClick(this,userSubCatePlaceOrderViewBind);


        userSubCatePlaceOrderViewBind.headername_tvid.setText("Confirm your order");
        userSubCatePlaceOrderViewBind.tv_sub_category.setText(SessionManager.getsub_Categoryname());
        userSubCatePlaceOrderViewBind.tv_category_name.setText(SessionManager.getCategoryname());

        amount=   getIntent().getStringExtra("amount");
        note=   getIntent().getStringExtra("note");
        fileArray = (ArrayList<File>)getIntent().getSerializableExtra("fileArray");
        fileType= (ArrayList<String>)getIntent().getSerializableExtra("filetype");
        categories_id=   getIntent().getStringExtra("categories_id");
        razorpay_order_id=getIntent().getStringExtra("razorpay_order_id");

        userSubCatePlaceOrderViewBind.tv_note.setText(note);

         GST= Integer.valueOf(amount) * 18/100;

        userSubCatePlaceOrderViewBind.tv_service_charge.setText(amount);

        userSubCatePlaceOrderViewBind. tv_GST.setText(String.valueOf(GST));
         tAmount=Integer.valueOf(amount)+GST;
        //tAmount=   Math.round(Float.parseFloat(String.valueOf(tAmount))*100);


        userSubCatePlaceOrderViewBind.   tv_tamount.setText("\u20B9"+tAmount);

        request=new ApiRequest(this,this);




    }

    public void opendialog_Online_OR_Wallet(){
        AlertDialog.Builder builder=new AlertDialog.Builder(UserSubCategoryPlaceOrderActivity.this,R.style.CustomAlertDialog);
        View view1= LayoutInflater.from(getApplicationContext()).inflate(R.layout.payalert__layout,null);

        builder.setView(view1);
        dialog=builder.create();

        dialog.setCancelable(true);
        dialog.show();
        LinearLayout lv_payonline,lv_paywallet;
        lv_payonline=view1.findViewById(R.id.lv_payonline);
        lv_paywallet=view1.findViewById(R.id.lv_paywallet);

        saveLogin= SessionManager.getAfterLogin();
        saveRegistration= SessionManager.getLogged();

        lv_payonline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (saveLogin) {
                    // After login call order api
                   // onlineapiorderAfterLogin();
                    // start payment through Result pay
                    startPayment();

                }else if (saveRegistration) {
                    // After registration call order api
                    startPayment();
                    //onlineapiorderAfterReg();
                }
            }
        });

        lv_paywallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                if (saveLogin) {
                    int  getLogincustomer_wallet_balance= Integer.parseInt(SessionManager.getLogincustomer_wallet_balance());
                    if (getLogincustomer_wallet_balance >= tAmount){
                        // After login call paymentByWallet api
                        apicallAfterLoginforpaymentByWallet();
                    }else {
                        MyToast.show(UserSubCategoryPlaceOrderActivity.this,"Don't have suficient balence",true);
                    }

                }else if (saveRegistration) {
                    int  getregcustomer_wallet_balancevalue= Integer.parseInt(SessionManager.getregcustomer_wallet_balancevalue());
                    if (getregcustomer_wallet_balancevalue >= tAmount){
                        // After registration call paymentByWallet api
                        apicallAfterregforpaymentByWallet();
                    }else {
                        MyToast.show(UserSubCategoryPlaceOrderActivity.this,"Don't have suficient balence",true);
                    }

                }

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

    // After login call paymentByWallet api
    public void apicallAfterLoginforpaymentByWallet(){
        dialog.dismiss();
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();
       // int GST= Integer.valueOf(amount) * 18/100;
       // int  tAmount=Integer.valueOf(amount)+GST;
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        builder.addFormDataPart("category_id",categories_id);


        builder.addFormDataPart("customer_id",SessionManager.getLoginidvalue());
        // builder.addFormDataPart("customer_note",description);
        builder.addFormDataPart("customer_note",note);

        builder.addFormDataPart("amount",amount);
        builder.addFormDataPart("amount_total", String.valueOf(tAmount));
        //  builder.addFormDataPart("amount_total", String.valueOf(tAmount));

        if (fileArray.size()>0) {

            for (int i=0;i<fileArray.size();i++) {
                builder.addFormDataPart("customer_file[]",((File) fileArray.get(i)).getName(), RequestBody.create(
                        MediaType.parse(String.valueOf(fileType.get(i))), (File) fileArray.get(i)));
                //builder.addFormDataPart("customer_file[]", fileArray.get(i).toString(), RequestBody.create(MediaType.parse("image/jpg"), fileArray.get(i)));
            }
        }
        RequestBody requestBody = builder.build();
        Request request = null;


        request = new Request.Builder()
                .addHeader("Content-Type","application/json")
                .addHeader("Content-Type", "multipart/form-data")
                .url(ApplicationConstant.paymentByWallet_url)
                .post(requestBody)
                .build();

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100, TimeUnit.SECONDS)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                if(progressDialog.isShowing())
                    progressDialog.dismiss();
            }

            @Override
            public void onResponse(@NotNull Call call,  Response response) throws IOException {
                if(progressDialog.isShowing())
                    progressDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    // Log.d("sunitt", "OnCallBackSuccess: "+jsonObject.);
                    int _success=jsonObject.optInt("_success");
                    if (_success==1) {
                        JSONObject jsonObject1 = jsonObject.getJSONObject("_data");
                        String customer_wallet_balance = jsonObject1.getString("_customer_wallet_balance");
                        SessionManager.setLogincustomer_wallet_balance(customer_wallet_balance);
                        Log.d("sunita", "OnCallBackSuccess: " + razorpay_order_id);
                        UserSubCategoryPlaceOrderActivity.this.runOnUiThread(new Runnable() {
                            public void run() {
                               // MyToast.show(UserSubCategoryPlaceOrderActivity.this, "" + jsonObject.optString("_message"), true);

                                Intent intent=new Intent(UserSubCategoryPlaceOrderActivity.this, UserCongratulationActivity.class);
                                startActivity(intent);

                            }
                        });


                    }else{
                        UserSubCategoryPlaceOrderActivity.this.runOnUiThread(new Runnable() {
                            public void run() {
                            //    MyToast.show(UserSubCategoryPlaceOrderActivity.this, "" + jsonObject.optString("_message"), true);
                            }
                        });

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    // After registration call paymentByWallet api
    public void apicallAfterregforpaymentByWallet(){
        dialog.dismiss();
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        // int GST= Integer.valueOf(amount) * 18/100;
        // int  tAmount=Integer.valueOf(amount)+GST;
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        builder.addFormDataPart("category_id",categories_id);


        builder.addFormDataPart("customer_id",SessionManager.getregistrationidvalue());
        // builder.addFormDataPart("customer_note",description);
        builder.addFormDataPart("customer_note",note);

        builder.addFormDataPart("amount",amount);
        builder.addFormDataPart("amount_total", String.valueOf(tAmount));
        //  builder.addFormDataPart("amount_total", String.valueOf(tAmount));

        if (fileArray.size()>0) {

            for (int i=0;i<fileArray.size();i++) {
                builder.addFormDataPart("customer_file[]",((File) fileArray.get(i)).getName(), RequestBody.create(
                        MediaType.parse(String.valueOf(fileType.get(i))), (File) fileArray.get(i)));
                //builder.addFormDataPart("customer_file[]", fileArray.get(i).toString(), RequestBody.create(MediaType.parse("image/jpg"), fileArray.get(i)));
            }
        }
        RequestBody requestBody = builder.build();
        Request request = null;


        request = new Request.Builder()
                .addHeader("Content-Type","application/json")
                .addHeader("Content-Type", "multipart/form-data")
                .url(ApplicationConstant.paymentByWallet_url)
                .post(requestBody)
                .build();

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100, TimeUnit.SECONDS)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                if(progressDialog.isShowing())
                    progressDialog.dismiss();
            }

            @Override
            public void onResponse(@NotNull Call call,  Response response) throws IOException {
                if(progressDialog.isShowing())
                    progressDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    // Log.d("sunitt", "OnCallBackSuccess: "+jsonObject.);
                    int _success=jsonObject.optInt("_success");
                    if (_success==1) {
                        JSONObject jsonObject1 = jsonObject.getJSONObject("_data");
                        String customer_wallet_balance = jsonObject1.getString("_customer_wallet_balance");
                        SessionManager.setregcustomer_wallet_balancevalue(customer_wallet_balance);
                        Log.d("sunita", "OnCallBackSuccess: " + razorpay_order_id);
                        UserSubCategoryPlaceOrderActivity.this.runOnUiThread(new Runnable() {
                            public void run() {
                                // MyToast.show(UserSubCategoryPlaceOrderActivity.this, "" + jsonObject.optString("_message"), true);

                                Intent intent=new Intent(UserSubCategoryPlaceOrderActivity.this, UserCongratulationActivity.class);
                                startActivity(intent);

                            }
                        });


                    }else{
                        UserSubCategoryPlaceOrderActivity.this.runOnUiThread(new Runnable() {
                            public void run() {
                                //    MyToast.show(UserSubCategoryPlaceOrderActivity.this, "" + jsonObject.optString("_message"), true);
                            }
                        });

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    // After login call order api
    public void onlineapiorderAfterLogin(){
        dialog.dismiss();
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        builder.addFormDataPart("category_id",categories_id);
        builder.addFormDataPart("customer_id",SessionManager.getLoginidvalue());
        // builder.addFormDataPart("customer_note",description);
        builder.addFormDataPart("customer_note",note);

        builder.addFormDataPart("amount",amount);
        builder.addFormDataPart("amount_total", String.valueOf(tAmount));
        //  builder.addFormDataPart("amount_total", String.valueOf(tAmount));

        if (fileArray.size()>0) {

            for (int i=0;i<fileArray.size();i++) {
                builder.addFormDataPart("customer_file[]",((File) fileArray.get(i)).getName(), RequestBody.create(
                        MediaType.parse(String.valueOf(fileType.get(i))), (File) fileArray.get(i)));

                Log.d("amisunita", "onlineapiorderAfterLogin: "+categories_id+""+SessionManager.getLoginidvalue()+""+
                        note+""+amount+""+""+String.valueOf(tAmount)+""+fileArray);
            }
        }
        RequestBody requestBody = builder.build();
        Request request = null;

        request = new Request.Builder()
                .addHeader("Content-Type","application/json")
                .addHeader("Content-Type", "multipart/form-data")
                .url(ApplicationConstant.OrderApi_url)
                .post(requestBody)
                .build();

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100, TimeUnit.SECONDS)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                if(progressDialog.isShowing())
                    progressDialog.dismiss();
            }

            @Override
            public void onResponse(@NotNull Call call,  Response response) throws IOException {
                if(progressDialog.isShowing())
                    progressDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    // Log.d("sunitt", "OnCallBackSuccess: "+jsonObject.);
                    int _success=jsonObject.optInt("_success");
                    if (_success==1) {
                        JSONObject jsonObject1 = jsonObject.getJSONObject("_data");

                          String razorpay_order_id = jsonObject1.getString("razorpay_order_id");
                        Log.d("sunita", "OnCallBackSuccess: " + razorpay_order_id);
                        UserSubCategoryPlaceOrderActivity.this.runOnUiThread(new Runnable() {
                            public void run() {
                              //  MyToast.show(UserSubCategoryPlaceOrderActivity.this, "" + jsonObject.optString("_message"), true);

                                // start payment through Result pay
                                startPayment();
                            }
                        });


                    }else{
                        UserSubCategoryPlaceOrderActivity.this.runOnUiThread(new Runnable() {
                            public void run() {
                                MyToast.show(UserSubCategoryPlaceOrderActivity.this, "" + jsonObject.optString("_message"), true);
                            }
                        });

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // After registration call order api
    public void onlineapiorderAfterReg(){
        dialog.dismiss();
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        builder.addFormDataPart("category_id",categories_id);
        builder.addFormDataPart("customer_id",SessionManager.getregistrationidvalue());
        // builder.addFormDataPart("customer_note",description);
        builder.addFormDataPart("customer_note",note);

        builder.addFormDataPart("amount",amount);
        builder.addFormDataPart("amount_total", String.valueOf(tAmount));
        //  builder.addFormDataPart("amount_total", String.valueOf(tAmount));

        if (fileArray.size()>0) {

            for (int i=0;i<fileArray.size();i++) {
                builder.addFormDataPart("customer_file[]",((File) fileArray.get(i)).getName(), RequestBody.create(
                        MediaType.parse(String.valueOf(fileType.get(i))), (File) fileArray.get(i)));
            }
        }
        RequestBody requestBody = builder.build();
        Request request = null;

        request = new Request.Builder()
                .addHeader("Content-Type","application/json")
                .addHeader("Content-Type", "multipart/form-data")
                .url(ApplicationConstant.OrderApi_url)
                .post(requestBody)
                .build();

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100, TimeUnit.SECONDS)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                if(progressDialog.isShowing())
                    progressDialog.dismiss();
            }

            @Override
            public void onResponse(@NotNull Call call,  Response response) throws IOException {
                if(progressDialog.isShowing())
                    progressDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    // Log.d("sunitt", "OnCallBackSuccess: "+jsonObject.);
                    int _success=jsonObject.optInt("_success");
                    if (_success==1) {
                        JSONObject jsonObject1 = jsonObject.getJSONObject("_data");

                        String razorpay_order_id = jsonObject1.getString("razorpay_order_id");
                        Log.d("sunita", "OnCallBackSuccess: " + razorpay_order_id);
                        UserSubCategoryPlaceOrderActivity.this.runOnUiThread(new Runnable() {
                            public void run() {
                                //  MyToast.show(UserSubCategoryPlaceOrderActivity.this, "" + jsonObject.optString("_message"), true);


                                startPayment();
                            }
                        });


                    }else{
                        UserSubCategoryPlaceOrderActivity.this.runOnUiThread(new Runnable() {
                            public void run() {
                                MyToast.show(UserSubCategoryPlaceOrderActivity.this, "" + jsonObject.optString("_message"), true);
                            }
                        });

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // start payment through Result pay
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
        final Activity activity = this;

        /**
         * Pass your payment options to the Razorpay Checkout as a JSONObject
         */
        try {
            JSONObject options = new JSONObject();

            options.put("name", "Score Global Pvt Ltd");
           // options.put("description", "Reference No. #123456");
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            options.put("order_id", razorpay_order_id);//from response of step 3.
            options.put("theme.color", "#3399cc");
            options.put("currency", "INR");
            options.put("amount", tAmount);//pass amount in currency subunits
            Log.d("amountttt", "startPayment: "+tAmount);
            options.put("prefill.email", "info@scoreglobal.in");
            options.put("prefill.contact","9046099084");
            checkout.open(activity, options);
        } catch(Exception e) {
            Log.e("", "Error in starting Razorpay Checkout", e);
        }
    }

    @Override
    public void onPaymentSuccess(String s, PaymentData paymentData) {


        Log.d("sunitaaaa", "razorpay_payment_id: "+s);
        Log.d("sunitaaaa", "razorpay_order_id: "+razorpay_order_id);
        Log.d("sunitaaaa", "razorpay_signature: "+paymentData.getSignature());
        razorpay_signature=  paymentData.getSignature();

        // api call for paymentverify
        apiForpaymentverify(razorpay_order_id,s,razorpay_signature);

    }


    @Override
    public void onPaymentError(int i, String s, PaymentData paymentData) {
        Toast.makeText(this, ""+s, Toast.LENGTH_SHORT).show();

    }

    // api call for paymentverify
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
               // MyToast.show(this,""+jsonObject.getString("_message"),true);


                Intent intent=new Intent(UserSubCategoryPlaceOrderActivity.this, UserCongratulationActivity.class);
                startActivity(intent);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void OnCallBackSuccess(String tag, JSONObject jsonObject) {

    }

    @Override
    public void OnCallBackError(String tag, String error, int i) {

    }
}