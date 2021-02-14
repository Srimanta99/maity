package com.example.maityspositiveliving.Retrofit.interfaces;

public interface OnCallBackListner {

    void OnCallBackSuccess(String tag, String body);
    void OnCallBackError(String tag, String error, int i);
}
