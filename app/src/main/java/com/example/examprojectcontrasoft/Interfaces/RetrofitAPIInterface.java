package com.example.examprojectcontrasoft.Interfaces;

import com.example.examprojectcontrasoft.Models.LoggedInUser;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RetrofitAPIInterface {

    @POST("/api/login")
    Call<LoggedInUser> login(@Query("email") String email,
                             @Query("password") String password);
}
