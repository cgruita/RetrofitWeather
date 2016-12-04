package com.gruita.retrofit.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by cgruita on 12/3/16.
 */

public class DailyResponse {

    @SerializedName("weather")
    private List<com.gruita.retrofit.model.Weather> listWeather;

    @SerializedName("main")
    private DailyMain main;


    public List<Weather> getListWeather() {
        return listWeather;
    }

    public DailyMain getMain() {
        return main;
    }

    public Wind getWind() {
        return wind;
    }



    @SerializedName("wind")
    private Wind wind;


    public static class DailyMain{

        @SerializedName("temp")
        private double temp;

        @SerializedName("pressure")
        private int pressure;

        @SerializedName("humidity")
        private int humidity;

        @SerializedName("temp_min")
        private double temp_min;

        @SerializedName("temp_max")
        private double temp_max;

    }

    public static class Wind{
        @SerializedName("speed")
        private double speed;

        @SerializedName("deg")
        private double deg;

    }

}