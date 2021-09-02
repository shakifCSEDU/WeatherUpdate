package com.example.weatherupdate;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface weatherapi {
    @GET("weather")
    Call<Example>getWeather(@Query("q") String cityname,
                            @Query("appid") String apikey);


}
