package com.example.examprojectcontrasoft.Interfaces;

import com.example.examprojectcontrasoft.Models.LoggedInUser;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RetrofitAPIInterface {

    @POST("/api/login")
    Call<Void> login(@Query("email") String email,
                             @Query("password") String password);

    @GET("/api/function/fetch-data")
    Call<LoggedInUser> fetchData(@Header("Cookie") String sessionIdCookie);
}
