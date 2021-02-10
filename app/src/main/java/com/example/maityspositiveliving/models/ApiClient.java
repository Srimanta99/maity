package com.example.maityspositiveliving.models;

import com.example.maityspositiveliving.utils.ApplicationConstant;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;




// import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ApiClient {
  //   public static final String BASE_URL = "http://babybunny.in/babybunny/App/Center_admin/todayinovation";

   // public static final String BASE_URL = "http://45.77.244.128:8089/";
    private static Retrofit retrofit = null;
    private static OkHttpClient.Builder   httpClient = new OkHttpClient.Builder();

    public static Retrofit getClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.addInterceptor(logging);
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(90, TimeUnit.SECONDS)
                .writeTimeout(90, TimeUnit.SECONDS)
                .readTimeout(90, TimeUnit.SECONDS).build();
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(ApplicationConstant.url)
                    .client(httpClient.build())
                    .client(client)
                     .addConverterFactory(ScalarsConverterFactory.create())
                   // .addConverterFactory(GsonConverterFactory.create())
                  //  .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }


    public static OkHttpClient.Builder getHttpClient() {
        if (httpClient != null) {
            return httpClient;
        } else {
            return null;
        }
    }
}