package com.example.maityspositiveliving.screen.UserStepTwoRegistrationActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;

import androidx.appcompat.app.AppCompatActivity;


import com.example.maityspositiveliving.R;
import com.example.maityspositiveliving.screen.UserStepThreeRegistrationActivity.UserStepThreeRegistrationActivity;
import com.example.maityspositiveliving.utils.RegistrationConstant;

import java.util.Calendar;

public class UserStepTwoRegistrationActivity extends AppCompatActivity {
    UserStepTwoRegistrationViewBind userStepTwoRegistrationViewBind;
    UserStepTwoRegistrationOnClick userStepTwoRegistrationOnClick;
    String  etn_nameid,etn_email,DOB,Gender,pin,address,DOBB;
    UserStepThreeRegistrationActivity userStepThreeRegistrationActivity;
    int state,city;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view= LayoutInflater.from(this).inflate(R.layout.activity_user_step_two_registration,null);
        setContentView(view);
        userStepTwoRegistrationViewBind= new UserStepTwoRegistrationViewBind(this,view);
        userStepTwoRegistrationOnClick=new UserStepTwoRegistrationOnClick(this,userStepTwoRegistrationViewBind);


        userStepTwoRegistrationViewBind.dob_tvid.setText(RegistrationConstant.DOB);
        // userStepTwoRegistrationViewBind.dob_tvid.setText(SessionManager.getDOBvalue());


        //  Log.d("gender", "onCreate: "+ SessionManager.getGendervalue());

        //   String Gender=  SessionManager.getGendervalue();

        Gender=RegistrationConstant.GENDER;

        if (Gender != null && Gender.equalsIgnoreCase("Male")){
            userStepTwoRegistrationViewBind.male_ivid.setImageResource(R.drawable.red_circle);

        }else if (Gender != null && Gender.equalsIgnoreCase("Female")){
            userStepTwoRegistrationViewBind.female_ivid.setImageResource(R.drawable.red_circle);

        }else if (Gender != null && Gender.equalsIgnoreCase("Other")){
            userStepTwoRegistrationViewBind.other_ivid.setImageResource(R.drawable.red_circle);

        }
    }

    public void calender(){

        Calendar newCalendar = Calendar.getInstance();
        final DatePickerDialog StartTime = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                DOB=  String.format("%02d",dayOfMonth) + " / "+ addZeroLeading(monthOfYear + 1) + " / "
                        + year;
                userStepTwoRegistrationViewBind.dob_tvid.setText(String.format("%02d",dayOfMonth) + " / "+ addZeroLeading(monthOfYear + 1) + " / "
                        + year);
                // SessionManager.setDOBvalue(DOB);

            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        StartTime.show();
    }

    public String addZeroLeading(int num){
        if(num<10){
            String retstr=String.valueOf(num);
            return  "0"+retstr;
        }
        else
            return String.valueOf(num);


    }

    @Override
    public void onBackPressed() {
        userStepTwoRegistrationOnClick.finis();
    }
}