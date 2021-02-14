package com.example.maityspositiveliving.screen.UserStepThreeRegistrationActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.maityspositiveliving.POJO.StateList;
import com.example.maityspositiveliving.R;

import com.example.maityspositiveliving.Retrofit.interfaces.OnCallBackListner;
import com.example.maityspositiveliving.Retrofit.models.ApiRequest;
import com.example.maityspositiveliving.screen.UserThankYouActivity.UserThankYouActivity;
import com.example.maityspositiveliving.utils.ApplicationConstant;
import com.example.maityspositiveliving.utils.MyToast;
import com.example.maityspositiveliving.utils.RegistrationConstant;
import com.example.maityspositiveliving.utils.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserStepThreeRegistrationActivity extends AppCompatActivity implements OnCallBackListner {
    UserStepThreeRegistrationViewBind userStepThreeRegistrationViewBind;
    UserStepThreeRegistrationOnClick userStepThreeRegistrationOnClick;
    ArrayList<StateList> stateLists;
    TextView txt_State_item;
    String Gender,DOB,etn_nameid,etn_email,address,pin;
    int state,city;
    ApiRequest request;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view= LayoutInflater.from(this).inflate(R.layout.activity_user_step_three_registration,null);
        setContentView(view);
        userStepThreeRegistrationViewBind= new UserStepThreeRegistrationViewBind(this,view);
        userStepThreeRegistrationOnClick=new UserStepThreeRegistrationOnClick(this,userStepThreeRegistrationViewBind);
        userStepThreeRegistrationViewBind.address_etnid.setText(RegistrationConstant.ADDRESS);
        userStepThreeRegistrationViewBind.pin_etnid.setText(RegistrationConstant.PIN);
        request=new ApiRequest(this,this);

        stateLists=new ArrayList<>();
        stateLists.add(new StateList("0","Select State","0"));

        apiForState();


      /*  ArrayList<String> Sp_State= new ArrayList<>();
        Sp_State.clear();
        Sp_State.add("Select State");
        Sp_State.add("Assam");
        Sp_State.add("Bihar");
        Sp_State.add("Goa");
        Sp_State.add("West Bengal");
        SelectStateAdapter Stateadapter=new SelectStateAdapter( UserStepThreeRegistrationActivity.this,Sp_State);
        userStepThreeRegistrationViewBind.select_state_spinnerid.setAdapter(Stateadapter);

        userStepThreeRegistrationViewBind.select_state_spinnerid.setSelection(RegistrationConstant.STATE);*/



        ArrayList<String> Sp_list= new ArrayList<>();
        Sp_list.clear();
        Sp_list.add("Select City");
        Sp_list.add("Kolkata");
        Sp_list.add("Asansol");
        Sp_list.add("Siliguri");
        Sp_list.add("Durgapur");
        Sp_list.add("Bardhaman");
        Sp_list.add("English Bazar");
        Sp_list.add("Habra");
        Sp_list.add("Ranahat");
        Sp_list.add("Bankura");
        Sp_list.add("Darjeeling");
        Sp_list.add("Bangaon");
        Sp_list.add("Cooch Behar");


        SelectCityAdapter adapter=new SelectCityAdapter( UserStepThreeRegistrationActivity.this,Sp_list);
        userStepThreeRegistrationViewBind.select_city_spinnerid.setAdapter(adapter);

        userStepThreeRegistrationViewBind.select_city_spinnerid.setSelection(RegistrationConstant.CITY);

    }

    private void apiForState() {

        request.callGET(ApplicationConstant.state_url,"state");
    }
    public void apiForRegister(){
        RegistrationConstant.PIN=   userStepThreeRegistrationViewBind.pin_etnid.getText().toString();
        RegistrationConstant.ADDRESS= userStepThreeRegistrationViewBind.address_etnid.getText().toString();

        HashMap<String,String> hashMap=new HashMap<>();
        hashMap.put("customar_name",RegistrationConstant.NAME);
        hashMap.put("customar_email",RegistrationConstant.EMAIL_ID);
        hashMap.put("customar_phone",RegistrationConstant.MOBILE_NUMBER);
        hashMap.put("customar_dob",RegistrationConstant.DOB);

        hashMap.put("customar_gender",RegistrationConstant.GENDER);

        hashMap.put("customar_country","1");

        hashMap.put("customar_state", String.valueOf(RegistrationConstant.STATE));

        hashMap.put("customar_city",RegistrationConstant.CITY_NAME);

        hashMap.put("customar_address",RegistrationConstant.ADDRESS);

        hashMap.put("customar_pincode",RegistrationConstant.PIN);

        hashMap.put("password","12345");

        hashMap.put("cpassword","12345");
        Log.d("sunita", "apiForRegister: "+hashMap);

        request.callPOST(ApplicationConstant.register_url,hashMap,"Register");


    }

    @Override
    public void OnCallBackSuccess(String tag, String body) {

        if (tag.equalsIgnoreCase("state")){
            try {
                JSONArray jsonArray = new JSONArray(body);
                for (int i=0;i<jsonArray.length();i++){
                    JSONObject jsonObject=jsonArray.getJSONObject(i);
                    StateList stateList=new StateList(
                            jsonObject.getString("id"),
                       jsonObject.getString("state_name"),


                            jsonObject.getString("country_id")
                    );


                    stateLists.add(stateList);

                    SelectStateAdapter Stateadapter=new SelectStateAdapter( UserStepThreeRegistrationActivity.this,stateLists);
                    userStepThreeRegistrationViewBind.select_state_spinnerid.setAdapter(Stateadapter);

                    userStepThreeRegistrationViewBind.select_city_spinnerid.setSelection(RegistrationConstant.CITY);


                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


      else   if (tag.equalsIgnoreCase("Register")){
            try {
                JSONObject jsonObject=new JSONObject(body);
                JSONObject jsonObject1=jsonObject.getJSONObject("messages");

                SessionManager.setnamevalue(RegistrationConstant.NAME);
                SessionManager.setemailidvalue(RegistrationConstant.EMAIL_ID);
                SessionManager.setphnovalue(RegistrationConstant.MOBILE_NUMBER);
                SessionManager.setDOBvalue(RegistrationConstant.DOB);
                SessionManager.setGendervalue(RegistrationConstant.GENDER);
                SessionManager.setcustomar_countryvalue("1");
                SessionManager.setSelectStatevalue(RegistrationConstant.STATE);
                SessionManager.setSelectCityvalue(RegistrationConstant.CITY_NAME);
                SessionManager.setAddressvalue(RegistrationConstant.ADDRESS);
                SessionManager.setPinNovalue(RegistrationConstant.PIN);
                SessionManager.setPassword("12345");
                SessionManager.setcPassword("12345");
                MyToast.show(UserStepThreeRegistrationActivity.this,""+jsonObject1.getString("success"),true);


                Intent mainIntent = new Intent(UserStepThreeRegistrationActivity.this, UserThankYouActivity.class).addFlags(
                        Intent.FLAG_ACTIVITY_CLEAR_TASK |
                                Intent.FLAG_ACTIVITY_CLEAR_TOP
                );
                startActivity(mainIntent);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }


    }

    @Override
    public void OnCallBackError(String tag, String error, int i) {

    }




    public class SelectStateAdapter extends BaseAdapter {
        Context context;
        ArrayList<StateList> extraList;
        LayoutInflater inflter;

        public SelectStateAdapter(Context applicationContext, ArrayList<StateList> extraList) {
            this.context = applicationContext;
            this.extraList = extraList;
            inflter = (LayoutInflater.from(applicationContext));
        }

        @Override
        public int getCount() {
            return extraList.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @SuppressLint("SetTextI18n")
        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            view = inflter.inflate(R.layout.lyt_spinner_item, null);
            txt_State_item = view.findViewById(R.id.txt_item);


            View view1 = view.findViewById(R.id.view);
            //   TextView price = view.findViewById(R.id.txtprice)
            //
            //  ;
            if (i==0){
                view1.setVisibility(View.GONE);
            }
            // state=extraList.get(i);
            txt_State_item.setText(extraList.get(i).getState_name());


            userStepThreeRegistrationViewBind.select_state_spinnerid.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


                    view1.setVisibility(View.GONE);


                    state=i;
                    RegistrationConstant.STATE=i;
                    // extraList.add(SessionManager.getSelectStatevalue());


                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            return view;
        }
    }


    public class SelectCityAdapter extends BaseAdapter {
        Context context;
        ArrayList<String> extraList;
        LayoutInflater inflter;

        public SelectCityAdapter(Context applicationContext, ArrayList<String> extraList) {
            this.context = applicationContext;
            this.extraList = extraList;
            inflter = (LayoutInflater.from(applicationContext));
        }

        @Override
        public int getCount() {
            return extraList.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @SuppressLint("SetTextI18n")
        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            view = inflter.inflate(R.layout.lyt_spinner_item, null);
            TextView txt_item = view.findViewById(R.id.txt_item);


            View view1 = view.findViewById(R.id.view);
            //   TextView price = view.findViewById(R.id.txtprice)
            //
            //  ;
            if (i==0){
                view1.setVisibility(View.GONE);
            }
            txt_item.setText(extraList.get(i));





            userStepThreeRegistrationViewBind.select_city_spinnerid.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                    view1.setVisibility(View.GONE);
                    //  SessionManager.setSelectCityvalue(i);
                    city=i;
                    RegistrationConstant.CITY=i;
                    RegistrationConstant.CITY_NAME=extraList.get(i);

                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            return view;
        }
    }


    @Override
    public void onBackPressed() {
        userStepThreeRegistrationOnClick.saveWithFinish();
    }
}