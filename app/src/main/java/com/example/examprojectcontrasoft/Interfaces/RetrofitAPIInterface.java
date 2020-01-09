package com.example.examprojectcontrasoft.Interfaces;

import com.example.examprojectcontrasoft.Models.CheckIn;
import com.example.examprojectcontrasoft.Models.CheckOut;
import com.example.examprojectcontrasoft.Models.LoggedInUser;
import com.example.examprojectcontrasoft.Models.PauseEnd;
import com.example.examprojectcontrasoft.Models.PauseStart;
import com.example.examprojectcontrasoft.Models.WorkedDaysTwoDates;

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

    @POST("/api/function/check-in")
    Call<CheckIn> checkIn(@Header("Cookie") String sessionIdCookie, @Query("staff_id") Long staffId);

    @POST("/api/function/check-out")
    Call<CheckOut> checkOut(@Header("Cookie") String sessionIdCookie, @Query("staff_id") Long staffId);

    @POST("/api/function/pause-start")
    Call<PauseStart> pauseStart(@Header("Cookie") String sessionIdCookie, @Query("staff_id") Long staffId);

    @POST("/api/function/pause-end")
    Call<PauseEnd> pauseEnd(@Header("Cookie") String sessionIdCookie, @Query("staff_id") Long staffId);

    @GET("/api/function/worked-days-two-dates")
    Call<WorkedDaysTwoDates> getWorkedDaysBetweenTwoDates(@Header("Cookie") String sessionIdCookie,
                                                          @Query("staff_id") Long staffId,
                                                          @Query("day1") int day1,
                                                          @Query("month1") int month1,
                                                          @Query("year1") int year1,
                                                          @Query("day2") int day2,
                                                          @Query("month2") int month2,
                                                          @Query("year2") int year2);
}
