package com.shymaaothman.weathertask.data.remote.networking;

import com.shymaaothman.weathertask.data.remote.models.WeatherData;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by shymaaothman on 7/22/17.
 */

public interface NetworkService {

    @GET("weather?")
    Observable<WeatherData> getWeatherData(@Query("q") String city, @Query("appid") String appid) ;
}
