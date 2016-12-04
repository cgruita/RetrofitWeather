package com.gruita.retrofit.wscalls.weekly;

import com.gruita.retrofit.model.WeeklyResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by cgruita on 12/2/16.
 */

public interface WeeklyApiInterface {

//    "http://api.openweathermap.org/data/2.5/weather?
//          q=Atlanta,GA&units=imperial&appid=6b0bff18a9694faebff99ad3cec72ed2;


//    http://api.openweathermap.org/data/2.5/forecast/daily?q=Atlanta,GA&units=imperial&cnt=5&appid=6b0bff18a9694faebff99ad3cec72ed2;

    @GET("data/2.5/forecast/daily")
    Call<WeeklyResponse> getWeeklyResponse(
            @Query("q") String location,
            @Query("units") String units,
            @Query("appId") String appid);
}
