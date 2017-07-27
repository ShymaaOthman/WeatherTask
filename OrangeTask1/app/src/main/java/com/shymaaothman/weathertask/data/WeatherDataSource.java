package com.shymaaothman.weathertask.data;

import android.support.annotation.NonNull;

import com.shymaaothman.weathertask.data.remote.models.WeatherData;

import io.reactivex.Observable;

/**
 * Created by shymaaothman on 7/22/17.
 */

public interface WeatherDataSource {

    Observable<WeatherData> getWeatherData(String city , String appId);



}
