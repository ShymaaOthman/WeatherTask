package com.shymaaothman.weathertask.data;

import android.support.annotation.NonNull;

import com.shymaaothman.weathertask.data.remote.models.WeatherData;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by shymaaothman on 7/22/17.
 */

public class WeatherRepository implements WeatherDataSource{


    private WeatherDataSource mWeatherRemoteDataSource;
    private static WeatherRepository INSTANCE;

    @Inject
    public WeatherRepository(WeatherDataSource weatherDataSource){

        mWeatherRemoteDataSource = weatherDataSource ;

    }

    /**
     * Returns the single instance of this class, creating it if necessary.
     */
    public static WeatherRepository getInstance(WeatherDataSource mWeatherRemoteDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new WeatherRepository(mWeatherRemoteDataSource);
        }
        return INSTANCE;
    }


    @Override
    public Observable<WeatherData> getWeatherData(String city , String appId) {

        Observable<WeatherData> cityObs = mWeatherRemoteDataSource.getWeatherData(city,appId);

        return cityObs;
    }


}
