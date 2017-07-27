package com.shymaaothman.weathertask.data.remote;


import android.support.annotation.NonNull;

import com.shymaaothman.weathertask.data.WeatherRepository;
import com.shymaaothman.weathertask.data.remote.networking.NetworkService;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.shymaaothman.weathertask.data.WeatherDataSource;
import com.shymaaothman.weathertask.data.remote.models.WeatherData ;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class WeatherRemoteDataSource implements WeatherDataSource {
    public static final String BASEURL = "http://api.openweathermap.org/data/2.5/";
    private static WeatherRemoteDataSource INSTANCE;


    public static WeatherRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new WeatherRemoteDataSource();
        }
        return INSTANCE;

    }


    private NetworkService getWebService() {

        NetworkService requestInterface = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(NetworkService.class);


        return requestInterface;
    }


    @Override
    public Observable<WeatherData> getWeatherData(String city , String appId) {

        Observable<WeatherData> weatherData = getWebService().getWeatherData(city ,appId);

        return weatherData;
    }


}
