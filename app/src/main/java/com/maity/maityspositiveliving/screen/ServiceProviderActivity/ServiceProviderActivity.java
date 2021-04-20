package com.maity.maityspositiveliving.screen.ServiceProviderActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.maity.maityspositiveliving.Interface.StateInterface;
import com.maity.maityspositiveliving.POJO.ParentcategoryList;
import com.maity.maityspositiveliving.R;
import com.maity.maityspositiveliving.Retrofit.interfaces.OnCallBackListner;
import com.maity.maityspositiveliving.Retrofit.models.ApiRequest;
import com.maity.maityspositiveliving.screen.Adapter.ServiceAreaAdapter;
import com.maity.maityspositiveliving.screen.ProviderThankYouActivty.ProviderThankYouActivity;
import com.maity.maityspositiveliving.utils.ApplicationConstant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

public class ServiceProviderActivity extends AppCompatActivity implements OnCallBackListner {
    ServiceProviderViewBind serviceProviderViewBind;
    ServiceProviderOnClick serviceProviderOnClick;
    String  Gender,provider_category="null";
    Dialog dialog;
    ServiceAreaAdapter serviceAreaAdapter;
    ApiRequest apiRequest;
    List<ParentcategoryList> parentcategoryLists;
    EditText etn_search;
    RecyclerView rv_service_area;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_service_provider);

        View view= LayoutInflater.from(this).inflate(R.layout.activity_service_provider,null);
        setContentView(view);
        serviceProviderViewBind= new ServiceProviderViewBind(this,view);
        serviceProviderOnClick=new ServiceProviderOnClick(this,serviceProviderViewBind);
        serviceProviderViewBind.  headername_tvid.setText("SERVICE PROVIDER");

        apiRequest=new ApiRequest(this,this);

        parentcategoryLists=new ArrayList<>(); // categorylist

        serviceProviderViewBind. male_ivid.setImageResource(R.drawable.fill);
        Gender="Male";


    }



    // method call for isValidEmailformet
    public boolean isValidEmailId(String email){

        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();
    }

    public void opendialogForState() {
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_for_servicearea_spinner);


        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);
        dialog.getWindow().setLayout((int) (405 * outMetrics.density), (int) (536 * outMetrics.density));
        dialog.show();


        apiForparentcategory(); // api implement for categorylist

         etn_search=dialog.findViewById(R.id.etn_search);
        rv_service_area =dialog.findViewById(R.id.rv_service_area);

          etn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( etn_search.getText().toString().length() > 0) {
                    // api call for show state
                    apiForparentcategory();

                }
            }
        });
    }

    public void apiForparentcategory(){
        apiRequest.callGET(ApplicationConstant.parentcategory_url,"parentcategory");
    }

    public void apiforProviderApi_add(){
        HashMap<String,String> hashMap=new HashMap<>();
        hashMap.put("provider_name",serviceProviderOnClick.provider_name);
        hashMap.put("provider_email",serviceProviderOnClick. provider_email);
        hashMap.put("provider_phone",serviceProviderOnClick.provider_phone);
        hashMap.put("provider_category",provider_category);
        hashMap.put("provider_address",serviceProviderOnClick.provider_address);
        hashMap.put("provider_pincode",serviceProviderOnClick.provider_pincode);
        hashMap.put("provider_gender",Gender);

        Log.d("hash", "apiforProviderApi_add: "+hashMap);

        apiRequest.callPOSTJSON_OBJECT(ApplicationConstant.ProviderApi_add_url,hashMap,"ProviderApi_add");
    }

    @Override
    public void OnCallBackSuccess(String tag, String body) {
        parentcategoryLists.clear();
        if (tag.equalsIgnoreCase("parentcategory")){
            try {

                JSONArray jsonArray=new JSONArray(body);

                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);


                    ParentcategoryList parentcategoryList = new ParentcategoryList(
                            jsonObject1.getString("id"),
                            jsonObject1.getString("category_name"),
                            jsonObject1.getString("category_desc"),
                            jsonObject1.getString("status")

                    );
                    parentcategoryLists.add(parentcategoryList);
                    serviceAreaAdapter = new ServiceAreaAdapter(ServiceProviderActivity.this, parentcategoryLists, new StateInterface() {
                        @Override
                        public void statename(int pos) {
                              etn_search.setText(parentcategoryLists.get(pos).getCategory_name());
                            dialog.dismiss();
                            serviceProviderViewBind.  tv_state.setText(parentcategoryLists.get(pos).getCategory_name());
                            provider_category=parentcategoryLists.get(pos).getId();
                        }
                    });
                    rv_service_area.setAdapter(serviceAreaAdapter);

                    LinearLayoutManager linearLayoutManager=new LinearLayoutManager( ServiceProviderActivity.this);

                    rv_service_area.setLayoutManager(linearLayoutManager);

                     etn_search.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                        }

                        @Override
                        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                        }

                        @Override
                        public void afterTextChanged(Editable editable) {

                            filter(editable.toString());

                        }
                    });
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }


    }

    @Override
    public void OnCallBackSuccess(String tag, JSONObject jsonObject) {
        if (tag.equalsIgnoreCase("ProviderApi_add")){

            try {
                if (jsonObject.getString("_success").equalsIgnoreCase("1")) {
                    Intent intent = new Intent(ServiceProviderActivity.this, ProviderThankYouActivity.class);
                    startActivity(intent);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void OnCallBackError(String tag, String error, int i) {

    }
    private void filter(String text) {
        ArrayList<ParentcategoryList> filteredList = new ArrayList<>();

        for (ParentcategoryList item : parentcategoryLists) {
            if (item.getCategory_name().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }

        if (serviceAreaAdapter!=null){
            serviceAreaAdapter.filterList(filteredList);


            serviceAreaAdapter = new ServiceAreaAdapter( this, filteredList, new StateInterface() {
                @Override
                public void statename(int pos) {
                    etn_search.setText(filteredList.get(pos).getCategory_name());
                    dialog.dismiss();
                    serviceProviderViewBind.  tv_state.setText(filteredList.get(pos).getCategory_name());

                    provider_category=parentcategoryLists.get(pos).getId();

                }
            });
           rv_service_area.setAdapter(serviceAreaAdapter);

        }


    }

}