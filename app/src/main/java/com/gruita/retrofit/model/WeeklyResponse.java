package com.gruita.retrofit.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by cgruita on 12/2/16.
 */

public class WeeklyResponse {

    @SerializedName("city")
//    private City city;

    public List<WeeklyDay> getListDays() {
        return listDays;
    }

    @SerializedName("list")
    private List<WeeklyDay> listDays;

    private WeeklyDay getFirstWeeklyDay(){
        WeeklyDay result = null;
        if(listDays != null && listDays.size() > 0){
            result = listDays.get(0);
        }
        return result;
    }

    public int getHumidity(){
        int result = 0;
        WeeklyDay weeklyDay = getFirstWeeklyDay();
        if(weeklyDay != null){
            result = weeklyDay.getHumidity();
        }
        return result;

    }

    public double getPressure(){
        double result = 0.0D;
        WeeklyDay weeklyDay = getFirstWeeklyDay();
        if(weeklyDay != null){
            result = weeklyDay.getPressure();
        }
        return result;
    }

    public double getSpeed(){
        double result = 0.0D;
        WeeklyDay weeklyDay = getFirstWeeklyDay();
        if(weeklyDay != null){
            result = weeklyDay.getSpeed();
        }
        return result;

    }

    public static class City{

        @SerializedName("id")
        private int id;

        @SerializedName("name")
        private String name;

        @SerializedName("coord")
        private Coordinates coordinates;

        @SerializedName("country")
        private String country;

        @SerializedName("population")
        private int population;

        private class Coordinates {

            @SerializedName("lon")
            private double lon;


            @SerializedName("lat")
            private double lat;
        }


    }

    public static class WeeklyDay {



        private int day;

        @SerializedName("temp")
        private Temperature temp;

        @SerializedName("weather")
        private List<Weather> listWeather;

        @SerializedName("pressure")
        private double pressure;

        @SerializedName("humidity")
        private int humidity;

        public int getHumidity() {
            return humidity;
        }

        public Weather getFirstWeather(){
            Weather weather = null;

            if(listWeather != null && listWeather.size() > 0) {
                weather = listWeather.get(0);
            }

            return weather;
        }

        public double getPressure() {
            return pressure;
        }

        public double getSpeed() {
            return speed;
        }

        @SerializedName("speed")
        private double speed;


        public static class Temperature{

            @SerializedName("day")
            private double day;

            @SerializedName("min")
            private double min;



            @SerializedName("max")
            private double max;

            @SerializedName("night")
            private double night;

            @SerializedName("eve")
            private double eve;

            @SerializedName("morn")
            private double morn;

            public double getMax() {
                return max;
            }

            public double getMin() {
                return min;
            }

            @Override
            public String toString(){
                return "day: " + day + ", min: " + min + ", max: " + max + ", night: " + night + ", eve: " + eve +
                        ", morn:" +
                        morn;
            }

        }

        public Temperature getTemp() {
            return temp;
        }

        public List<Weather> getWeather() {
            return listWeather;
        }

        public int getDay() {
            return day;
        }

        public void setDay(int day) {
            this.day = day;
        }

        @Override
        public String toString(){
            StringBuffer sb = new StringBuffer();
            sb.append("\nWeeklyDay: \ntemp:" );
            sb.append(temp + "\n");
            for (Weather weather:listWeather) {
                sb.append(weather + "\n");
            }
            return sb.toString();
        }



    }

    @SerializedName("cod")
    private int cod;

    @SerializedName("message")
    private double message;

    public int getCnt() {
        return cnt;
    }

    @SerializedName("cnt")
    private int cnt;

}
