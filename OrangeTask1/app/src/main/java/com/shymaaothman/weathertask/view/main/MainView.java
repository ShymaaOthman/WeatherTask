package com.shymaaothman.weathertask.view.main;

import com.google.firebase.auth.FirebaseUser;
import com.shymaaothman.weathertask.data.remote.models.WeatherData;

import net.derohimat.baseapp.view.BaseView;


interface MainView extends BaseView {

    void showProgress();

    void hideProgress();

    void getUserData(FirebaseUser firebaseUser);

    void showWeatherData(WeatherData weatherData);

}
