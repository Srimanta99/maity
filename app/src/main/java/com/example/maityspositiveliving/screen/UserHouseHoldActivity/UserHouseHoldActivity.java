package com.example.maityspositiveliving.screen.UserHouseHoldActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.maityspositiveliving.POJO.SubcategoryList;
import com.example.maityspositiveliving.R;
import com.example.maityspositiveliving.Retrofit.interfaces.OnCallBackListner;
import com.example.maityspositiveliving.Retrofit.models.ApiRequest;
import com.example.maityspositiveliving.screen.Adapter.ParentcategoryAdapter;
import com.example.maityspositiveliving.screen.Adapter.SubcategoryAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class UserHouseHoldActivity extends AppCompatActivity implements OnCallBackListner {

    UserHouseHoldViewBind userHouseHoldViewBind;
    UserHouseHoldOnClick userHouseHoldOnClick;
    ApiRequest apiRequest;
    List<SubcategoryList> subcategoryLists;
    String subcategory_url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view= LayoutInflater.from(this).inflate(R.layout.activity_user_house_hold,null);
        setContentView(view);
        userHouseHoldViewBind= new UserHouseHoldViewBind(this,view);
        userHouseHoldOnClick=new UserHouseHoldOnClick(this,userHouseHoldViewBind);
        apiRequest=new ApiRequest(this,this);

        subcategoryLists=new ArrayList<>();

        subcategory_url=   getIntent().getStringExtra("subcategory_url");
        Log.d("subcategory_url", "onCreate: "+subcategory_url);
       apiForsubcategory(subcategory_url);
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }


    public void apiForsubcategory(String subcategory_url){
        apiRequest.callGET(subcategory_url,"subcategory");

    }

    @Override
    public void OnCallBackSuccess(String tag, String body) {

        try {
            JSONArray jsonArray=new JSONArray(body);
            for (int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject=jsonArray.getJSONObject(i);
                SubcategoryList subcategoryList=new SubcategoryList(
                        jsonObject.getString("id"),
                        jsonObject.getString("category_name"),
                        jsonObject.getString("category_desc"),
                        jsonObject.getString("category_icon"),
                        jsonObject.getString("parent_category"),
                        jsonObject.getString("amount"),
                        jsonObject.getString("status")

                        );

                subcategoryLists.add(subcategoryList);
                SubcategoryAdapter subcategoryAdapter = new SubcategoryAdapter(this, subcategoryLists);
                userHouseHoldViewBind. rv_subcategory.setAdapter(subcategoryAdapter);

                LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
                userHouseHoldViewBind. rv_subcategory.setLayoutManager(linearLayoutManager);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void OnCallBackError(String tag, String error, int i) {

    }
}