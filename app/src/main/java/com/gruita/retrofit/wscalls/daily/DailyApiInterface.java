package com.gruita.retrofit.wscalls.daily;


import com.gruita.retrofit.model.DailyResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by cgruita on 12/2/16.
 */

public interface DailyApiInterface {

//    "http://api.openweathermap.org/data/2.5/weather?
//          q=Atlanta,GA&units=imperial&appid=6b0bff18a9694faebff99ad3cec72ed2;

    @GET("data/2.5/weather")
    Call<DailyResponse> getDailyResponse(
            @Query("q") String location,
            @Query("units") String units,
            @Query("appId") String appid);
}
