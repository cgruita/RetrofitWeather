package com.gruita.retrofit.wscalls;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by cgruita on 12/2/16.
 */

public class ApiClient {

    //    whole url
//    public static final String URL = "http://medium.ngtv.io/media/{mediaId}/{deviceType}/primetime";


//    http://api.openweathermap.org/data/2.5/forecast/daily?q=Atlanta,GA&units=imperial&cnt=5&appid=6b0bff18a9694faebff99ad3cec72ed2

    public static final String BASE_URL = "http://api.openweathermap.org/";
    private static Retrofit retrofit = null;


    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}

