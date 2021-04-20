package com.maity.maityspositiveliving.utils;

public class ApplicationConstant {
    public  static  int SPLASH_DISPLAY_LENGTH=2000;

    public static String url="http://3.7.81.124/web-apps/maity/";

   // public static String url="https://maitys.univexsolutions.in/";
    public static String country_code_url=url+"CountryApi/countryList";
    public static String state_url=url+"StateApi/countryid/1";
    public static String register_url=url+"CustomarApi/insert";
    public static String getOTP_url=url+"CustomarApi/getOTP";
    public static String verifyOTP_url=url+"CustomarApi/verifyOTP";
    public static String update_url=url+"CustomarApi/update/10";
    public static String parentcategory_url=url+"CategoryApi/parentcategory/0";
    public static String subcategory_url=url+"CategoryApi/subcategory/";
    public static String OrderApi_url=url+"OrderApi/insert";
    public static String paymentverify_url=url+"payment/verify";
    public static String isEmailUnique_url=url+"CustomarApi/isEmailUnique";
    public static String isContactNUmberUnique_url=url+"CustomarApi/isContactNUmberUnique";
    public static String CustomarApilogin_url=url+"CustomarApi/login";
    public static String addWalletBalance_url=url+"OrderApi/addWalletBalance";
    public static String verifyWalletPayment_url=url+"OrderApi/verifyWalletPayment";
    public static String paymentByWallet_url=url+"OrderApi/paymentByWallet";
    public static String OrderApilist_url=url+"OrderApi/list";
    public static String orderDelete_url=url+"OrderApi/orderDelete";
    public static String CityApi_url=url+"CityApi/stateid/";

    public static String ProviderApi_add_url=url+"ProviderApi/add";
}
