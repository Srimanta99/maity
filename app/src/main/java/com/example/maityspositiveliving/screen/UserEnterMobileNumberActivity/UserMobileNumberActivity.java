package com.example.maityspositiveliving.screen.UserEnterMobileNumberActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.maityspositiveliving.POJO.CountrycodeList;
import com.example.maityspositiveliving.POJO.StateList;
import com.example.maityspositiveliving.R;
import com.example.maityspositiveliving.interfaces.OnCallBackListner;
import com.example.maityspositiveliving.models.ApiRequest;
import com.example.maityspositiveliving.screen.UserStepThreeRegistrationActivity.UserStepThreeRegistrationActivity;
import com.example.maityspositiveliving.utils.ApplicationConstant;
import com.example.maityspositiveliving.utils.RegistrationConstant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class UserMobileNumberActivity extends AppCompatActivity implements OnCallBackListner {
    UserMobileNumberViewBind userMobileNumberViewBind;
    UserMobileNumberOnClick userMobileNumberOnClick;
    ArrayList<CountrycodeList> countrycodeLists;
    ApiRequest apiRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view= LayoutInflater.from(this).inflate(R.layout.activity_user_mobile_number,null);
        setContentView(view);
        userMobileNumberViewBind= new UserMobileNumberViewBind(this,view);
        userMobileNumberOnClick=new UserMobileNumberOnClick(this,userMobileNumberViewBind);

        apiRequest=new ApiRequest(this,this);
        countrycodeLists=new ArrayList<>();
        apiForCountryCode();

    }

    private void apiForCountryCode() {

        apiRequest.callGET(ApplicationConstant.country_code_url,"CountryCode");
    }


    @Override
    public void OnCallBackSuccess(String tag, String body) {
        if (tag.equalsIgnoreCase("CountryCode")){
            try {
                JSONArray jsonArray = new JSONArray(body);
                for (int i=0;i<jsonArray.length();i++){
                    JSONObject jsonObject=jsonArray.getJSONObject(i);
                    CountrycodeList countrycodeList=new CountrycodeList(
                            jsonObject.getString("id"),
                            jsonObject.getString("country_name")

                    );
                    Log.d("stateList", "OnCallBackSuccess: "+jsonObject.getString("id"));


                    countrycodeLists.add(countrycodeList);

                   SelectCountryCodeAdapter Countryadapter=new SelectCountryCodeAdapter( UserMobileNumberActivity.this,countrycodeLists);
                    userMobileNumberViewBind.spinner_country_code.setAdapter(Countryadapter);



                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void OnCallBackError(String tag, String error, int i) {

    }

    public class SelectCountryCodeAdapter extends BaseAdapter {
        Context context;
        ArrayList<CountrycodeList> extraList;
        LayoutInflater inflter;

        public SelectCountryCodeAdapter(Context applicationContext, ArrayList<CountrycodeList> extraList) {
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
          TextView txt_State_item = view.findViewById(R.id.txt_item);


            View view1 = view.findViewById(R.id.view);

            if (i==0){
                view1.setVisibility(View.GONE);
            }
            txt_State_item.setText(extraList.get(i).getId());


            userMobileNumberViewBind.spinner_country_code.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


                    view1.setVisibility(View.GONE);



                    // extraList.add(SessionManager.getSelectStatevalue());


                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            return view;
        }
    }

}