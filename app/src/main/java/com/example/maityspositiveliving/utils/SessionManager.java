package com.example.maityspositiveliving.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {

    private static SharedPreferences preferences;
    private static SharedPreferences.Editor editor;

    public SessionManager(Context context) {

        preferences = context.getSharedPreferences("hvfuyf", 0);
        editor = preferences.edit();
    }




    public static void setLogged(boolean is) {
        editor.putBoolean("login", is);
        editor.apply();
    }





    public static void setotpenable(boolean is) {
        editor.putBoolean("otp", is);
        editor.apply();
    }

    public static void setotp(String is) {
        editor.putString("otp", is);
        editor.apply();
    }


    public static String getotp() {
        return preferences.getString("otp", "");
    }








    public static void setidboolean(boolean is) {
        editor.putBoolean("MyPREFERENCES", is);
        editor.apply();
    }


    public static boolean getidboolean() {
        return preferences.getBoolean("MyPREFERENCES", false);
    }


    public static void setidvalue(String id){
        editor.putString("id", id);
        editor.apply();
    }
    public  static String getidvalue(){
        return preferences.getString("id","");
    }





    public static void setnamevalue(String name){
        editor.putString("name", name);
        editor.apply();
    }
    public  static String getnamevalue(){
        return preferences.getString("name","");
    }

    public static void setemailidvalue(String email){
        editor.putString("email", email);
        editor.apply();
    }
    public  static String getemailidvalue(){
        return preferences.getString("email","");
    }

    public static void setGendervalue(String email){
        editor.putString("Gender", email);
        editor.apply();
    }
    public  static String getGendervalue(){
        return preferences.getString("Gender","");
    }

    public static void setDOBvalue(String email){
        editor.putString("DOB", email);
        editor.apply();
    }
    public  static String getDOBvalue(){
        return preferences.getString("DOB","");
    }



    public static void setcustomar_countryvalue(String customar_country){
        editor.putString("customar_country", customar_country);
        editor.apply();
    }
    public  static String getcustomar_countryvalue(){
        return preferences.getString("customar_country","");
    }


    public static void setAddressvalue(String email){
        editor.putString("Address", email);
        editor.apply();
    }
    public  static String getAddressvalue(){
        return preferences.getString("Address","");
    }

    public static void setPinNovalue(String email){
        editor.putString("PinNo", email);
        editor.apply();
    }
    public  static String getPinNovalue(){
        return preferences.getString("PinNo","");
    }

    public static void setSelectStatevalue(int i){
        editor.putInt("SelectState", i);
        editor.apply();
    }
    public  static int getSelectStatevalue(){
        return preferences.getInt("SelectState",0);
    }


    public static void setSelectCityvalue(String i){
        editor.putString("SelectCity", i);
        editor.apply();
    }
    public  static String getSelectCityvalue(){
        return preferences.getString("SelectCity","");
    }


    public static void setPassword(String i){
        editor.putString("Password", i);
        editor.apply();
    }
    public  static String getPassword(){
        return preferences.getString("Password","");
    }


    public static void setcPassword(String i){
        editor.putString("cPassword", i);
        editor.apply();
    }
    public  static String getcPassword(){
        return preferences.getString("cPassword","");
    }



    public static void setphnovalue(String phno){
        editor.putString("phno", phno);
        editor.apply();
    }
    public  static String getphnovalue(){
        return preferences.getString("phno","");
    }

    public static void setimagevalue(String image){
        editor.putString("image", image);
        editor.apply();
    }
    public  static String getimagevalue(){
        return preferences.getString("image","");
    }










}
