package com.example.maityspositiveliving.screen.UserStepThreeRegistrationActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import com.example.maityspositiveliving.R;
import com.example.maityspositiveliving.utils.RegistrationConstant;

import java.util.ArrayList;

public class UserStepThreeRegistrationActivity extends AppCompatActivity {
    UserStepThreeRegistrationViewBind userStepThreeRegistrationViewBind;
    UserStepThreeRegistrationOnClick userStepThreeRegistrationOnClick;
    TextView txt_State_item;
    String Gender,DOB,etn_nameid,etn_email,address,pin;
    int state,city;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view= LayoutInflater.from(this).inflate(R.layout.activity_user_step_three_registration,null);
        setContentView(view);
        userStepThreeRegistrationViewBind= new UserStepThreeRegistrationViewBind(this,view);
        userStepThreeRegistrationOnClick=new UserStepThreeRegistrationOnClick(this,userStepThreeRegistrationViewBind);



        userStepThreeRegistrationViewBind.address_etnid.setText(RegistrationConstant.ADDRESS);
        userStepThreeRegistrationViewBind.pin_etnid.setText(RegistrationConstant.PIN);




        ArrayList<String> Sp_State= new ArrayList<>();
        Sp_State.clear();
        Sp_State.add("Select State");
        Sp_State.add("Assam");
        Sp_State.add("Bihar");
        Sp_State.add("Goa");
        Sp_State.add("West Bengal");
        SelectStateAdapter Stateadapter=new SelectStateAdapter( UserStepThreeRegistrationActivity.this,Sp_State);
        userStepThreeRegistrationViewBind.select_state_spinnerid.setAdapter(Stateadapter);





        userStepThreeRegistrationViewBind.select_state_spinnerid.setSelection(RegistrationConstant.STATE);



        ArrayList<String> Sp_list= new ArrayList<>();
        Sp_list.clear();
        Sp_list.add("Select City");
        Sp_list.add("Monthly");
        Sp_list.add("Kolkata");
        SelectCityAdapter adapter=new SelectCityAdapter( UserStepThreeRegistrationActivity.this,Sp_list);
        userStepThreeRegistrationViewBind.select_city_spinnerid.setAdapter(adapter);

        userStepThreeRegistrationViewBind.select_city_spinnerid.setSelection(RegistrationConstant.CITY);


    }

    public class SelectStateAdapter extends BaseAdapter {
        Context context;
        ArrayList<String> extraList;
        LayoutInflater inflter;

        public SelectStateAdapter(Context applicationContext, ArrayList<String> extraList) {
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
            txt_State_item.setText(extraList.get(i));


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