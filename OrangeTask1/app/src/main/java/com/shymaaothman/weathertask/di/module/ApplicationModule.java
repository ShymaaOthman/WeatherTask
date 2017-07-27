package com.shymaaothman.weathertask.di.module;

import com.google.firebase.auth.FirebaseAuth;

import com.shymaaothman.weathertask.FireAuthApplication;
import com.shymaaothman.weathertask.data.WeatherDataSource;
import com.shymaaothman.weathertask.data.WeatherRepository;
import com.shymaaothman.weathertask.data.local.PreferencesHelper;
import com.shymaaothman.weathertask.data.remote.WeatherRemoteDataSource;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private final FireAuthApplication mBaseApplication;

    public ApplicationModule(FireAuthApplication baseApplication) {
        this.mBaseApplication = baseApplication;
    }

    @Provides
    @Singleton
    FireAuthApplication provideApplication() {
        return mBaseApplication;
    }

    @Provides
    @Singleton
    FirebaseAuth provideFireBaseAuth() {
        return FirebaseAuth.getInstance();
    }

    @Provides
    @Singleton
    EventBus eventBus() {
        return new EventBus();
    }

    @Provides
    @Singleton
    PreferencesHelper prefsHelper() {
        return new PreferencesHelper(mBaseApplication);
    }

    @Provides
    @Singleton
    WeatherRepository provideWeatherRepository() {
        return WeatherRepository.getInstance(provideWeatherDataSource());
    }
    @Provides
    @Singleton
    WeatherDataSource provideWeatherDataSource() {
        return WeatherRepository.getInstance(WeatherRemoteDataSource.getInstance());
    }

}