package com.maity.maityspositiveliving.Retrofit.interfaces;

import org.json.JSONObject;

public interface OnCallBackListner {

    void OnCallBackSuccess(String tag, String body);

    void OnCallBackSuccess(String tag, JSONObject jsonObject);

    void OnCallBackError(String tag, String error, int i);
}
