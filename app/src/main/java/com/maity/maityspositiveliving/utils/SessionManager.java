package com.maity.maityspositiveliving.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {

    private static SharedPreferences preferences;
    private static SharedPreferences.Editor editor;

    public SessionManager(Context context) {

        preferences = context.getSharedPreferences("hvfuyf", 0);
        editor = preferences.edit();
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








    public static void setLogged(boolean is) {
        editor.putBoolean("logged", is);
        editor.apply();
    }


    public static boolean getLogged() {

        return preferences.getBoolean("logged", false);
    }

    public static void setAfterLogin(boolean is) {
        editor.putBoolean("login", is);
        editor.apply();
    }


    public static boolean getAfterLogin() {

        return preferences.getBoolean("login", false);
    }


    public static void setLoginidvalue(String id){
        editor.putString("id", id);
        editor.apply();
    }
    public  static String getLoginidvalue(){
        return preferences.getString("id","");
    }

    public static void setLoginphnovalue(String id){
        editor.putString("loginphno", id);
        editor.apply();
    }
    public  static String getLoginphnovalue(){
        return preferences.getString("loginphno","");
    }

    public static void setLoginPassword(String i){
        editor.putString("LoginPassword", i);
        editor.apply();
    }
    public  static String getLoginPassword(){
        return preferences.getString("LoginPassword","");
    }

    public static void setLoginnamevalue(String name){
        editor.putString("Loginname", name);
        editor.apply();
    }
    public  static String getLoginnamevalue(){
        return preferences.getString("Loginname","");
    }

    public static void setLoginaddressvalue(String name){
        editor.putString("Loginaddress", name);
        editor.apply();
    }
    public  static String getLoginaddressvalue(){
        return preferences.getString("Loginaddress","");
    }


    public static void setLoginemailidvalue(String email){
        editor.putString("Loginemail", email);
        editor.apply();
    }
    public  static String getLoginemailidvalue(){
        return preferences.getString("Loginemail","");
    }

    public static void setnamevalue(String name){
        editor.putString("name", name);
        editor.apply();
    }
    public  static String getnamevalue(){
        return preferences.getString("name","");
    }

    public static void setregistrationidvalue(String name){
        editor.putString("registrationid", name);
        editor.apply();
    }
    public  static String getregistrationidvalue(){
        return preferences.getString("registrationid","");
    }

    public static void setregcustomer_wallet_balancevalue(String name){
        editor.putString("regcustomer_wallet_balance", name);
        editor.apply();
    }
    public  static String getregcustomer_wallet_balancevalue(){
        return preferences.getString("regcustomer_wallet_balance","");
    }


    public static void setreg_passwordvalue(String name){
        editor.putString("reg_password", name);
        editor.apply();
    }
    public  static String getreg_passwordvalue(){
        return preferences.getString("reg_password","");
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

    public static void setSelectStatevalue(String i){
        editor.putString("SelectState", i);
        editor.apply();
    }
    public  static String getSelectStatevalue(){
        return preferences.getString("SelectState","");
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



    public static void setCategoryname(String name){
        editor.putString("Categoryname", name);
        editor.apply();
    }
    public  static String getCategoryname(){
        return preferences.getString("Categoryname","");
    }


    public static void setsub_Categoryname(String name){
        editor.putString("sub_Categoryname", name);
        editor.apply();
    }
    public  static String getsub_Categoryname(){
        return preferences.getString("sub_Categoryname","");
    }

    public static void setLogincustomer_wallet_balance(String customer_wallet_balance){
        editor.putString("customer_wallet_balance", customer_wallet_balance);
        editor.apply();
    }
    public  static String getLogincustomer_wallet_balance(){
        return preferences.getString("customer_wallet_balance","");
    }


    public static void setLogincustomar_dob(String customer_wallet_balance){
        editor.putString("customar_dob", customer_wallet_balance);
        editor.apply();
    }
    public  static String getLogincustomar_dob(){
        return preferences.getString("customar_dob","");
    }
    public static void setLogincustomar_gender(String customer_wallet_balance){
        editor.putString("customar_gender", customer_wallet_balance);
        editor.apply();
    }
    public  static String getLogincustomar_gender(){
        return preferences.getString("customar_gender","");
    }
    public static void setLogincustomar_country(String customer_wallet_balance){
        editor.putString("customar_country", customer_wallet_balance);
        editor.apply();
    }
    public  static String getLogincustomar_country(){
        return preferences.getString("customar_country","");
    }
    public static void setLogincustomar_state(String customer_wallet_balance){
        editor.putString("customar_state", customer_wallet_balance);
        editor.apply();
    }
    public  static String getLogincustomar_state(){
        return preferences.getString("customar_state","");
    }
    public static void setLogincustomar_city(String customar_city){
        editor.putString("customar_city", customar_city);
        editor.apply();
    }
    public  static String getLogincustomar_city(){
        return preferences.getString("customar_city","");
    }
    public static void setLogincustomar_pincode(String customer_wallet_balance){
        editor.putString("customar_pincode", customer_wallet_balance);
        editor.apply();
    }
    public  static String getLogincustomar_pincode(){
        return preferences.getString("customar_pincode","");
    }




}
