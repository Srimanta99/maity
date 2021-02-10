package com.example.maityspositiveliving.interfaces;

import org.json.JSONObject;

public interface OnCallBackListner {

    void OnCallBackSuccess(String tag, String body);
    void OnCallBackError(String tag, String error, int i);
}
