package com.gruita.retrofit.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.gruita.retrofit.C;
import com.gruita.retrofit.R;
import com.gruita.retrofit.model.WeeklyResponse;
import com.gruita.retrofit.wscalls.ApiClient;
import com.gruita.retrofit.wscalls.weekly.WeeklyApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    private boolean gotDaily = false;
    private boolean gotCurrent = false;
    private List<WeeklyResponse.WeeklyDay> list = new ArrayList<>();

    public static final String TAG = "Weather";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        callWeeklyWS();
    }

    private void setUpRecyclerView() {

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        RecyclerAdapter adapter = new RecyclerAdapter(this, list);
        recyclerView.setAdapter(adapter);

        LinearLayoutManager mLinearLayoutManagerVertical = new LinearLayoutManager(this); // (Context context, int spanCount)
        mLinearLayoutManagerVertical.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLinearLayoutManagerVertical);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

    }


    private void callWeeklyWS() {
        WeeklyApiInterface apiService =
                ApiClient.getClient().create(WeeklyApiInterface.class);

        String location = "Cumming,GA";
        String units = "imperial";
        String appid = "6b0bff18a9694faebff99ad3cec72ed2";

        Call<WeeklyResponse> call = apiService.getWeeklyResponse(location, units, appid);
        call.enqueue(new retrofit2.Callback<WeeklyResponse>() {
            @Override
            public void onResponse(Call<WeeklyResponse> call, Response<WeeklyResponse> response) {
                WeeklyResponse body = response.body();
                int count = body.getCnt();
                Log.d(C.TAG, "count: " + count);
                int currentDay = 0;
                List<WeeklyResponse.WeeklyDay> listWeatherUpdate = body.getListDays();
                for (WeeklyResponse.WeeklyDay day: listWeatherUpdate) {
                    Log.d(C.TAG, "day: " + day.toString());
                    list.add(day);
                    day.setDay(currentDay);
                    currentDay++;
                }

                setUpRecyclerView();

            }

            @Override
            public void onFailure(Call<WeeklyResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, "Error calling the MediaJson WS: " + t.toString());
            }
        });

    }
/*
    private void callDailyWS() {
        DailyApiInterface apiService =
                ApiClient.getClient().create(DailyApiInterface.class);

        String location = "Cumming,GA";
        String units = "imperial";
        String appid = "6b0bff18a9694faebff99ad3cec72ed2";

        String deviceType = "phone";
        Call<DailyResponse> call = apiService.getDailyResponse(location, units, appid);
        call.enqueue(new retrofit2.Callback<DailyResponse>() {
            @Override
            public void onResponse(Call<DailyResponse> call, Response<DailyResponse> response) {
                int statusCode = response.code();
                DailyResponse body = response.body();
                List<Weather> listWeatherUpdate = body.getListWeather();
                for (Weather weather: listWeatherUpdate) {
                    Log.d(C.TAG, "weather: " + weather);
                }
            }

            @Override
            public void onFailure(Call<DailyResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, "Error calling the MediaJson WS: " + t.toString());
            }
        });

    }
    */

}
