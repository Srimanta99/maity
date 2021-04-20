package com.maity.maityspositiveliving.screen.UserStepTwoRegistrationActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;

import androidx.appcompat.app.AppCompatActivity;


import com.maity.maityspositiveliving.R;
import com.maity.maityspositiveliving.utils.RegistrationConstant;

import java.util.Calendar;

public class UserStepTwoRegistrationActivity extends AppCompatActivity  {
    UserStepTwoRegistrationViewBind userStepTwoRegistrationViewBind;
    UserStepTwoRegistrationOnClick userStepTwoRegistrationOnClick;
    String  DOB, Gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view= LayoutInflater.from(this).inflate(R.layout.activity_user_step_two_registration,null);
        setContentView(view);
        userStepTwoRegistrationViewBind= new UserStepTwoRegistrationViewBind(this,view);
        userStepTwoRegistrationOnClick=new UserStepTwoRegistrationOnClick(this,userStepTwoRegistrationViewBind);

        userStepTwoRegistrationViewBind.dob_tvid.setText(RegistrationConstant.DOB);//if go to another page then back this page then set DOB
        Gender=RegistrationConstant.GENDER; //if go to another page then back this page then set GENDER

        if (Gender != null && Gender.equalsIgnoreCase("M")){
            userStepTwoRegistrationViewBind.male_ivid.setImageResource(R.drawable.red_circle);

        }else if (Gender != null && Gender.equalsIgnoreCase("F")){
            userStepTwoRegistrationViewBind.female_ivid.setImageResource(R.drawable.red_circle);

        }else if (Gender != null && Gender.equalsIgnoreCase("O")){
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