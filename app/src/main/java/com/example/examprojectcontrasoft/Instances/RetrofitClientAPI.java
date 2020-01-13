package com.example.examprojectcontrasoft.Instances;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientAPI {

    private static Retrofit retrofit;
    // This needs to be changed for the exam presentation, since this needs the
    // local IP of the router in use.
    private static final String BASE_URL = "http://192.168.0.16:8081/";

    public static Retrofit getRetrofitInstance() {

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}