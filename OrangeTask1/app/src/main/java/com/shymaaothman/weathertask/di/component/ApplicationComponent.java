package com.shymaaothman.weathertask.di.component;

import com.google.firebase.auth.FirebaseAuth;

import com.shymaaothman.weathertask.FireAuthApplication;
import com.shymaaothman.weathertask.data.WeatherRepository;
import com.shymaaothman.weathertask.data.local.PreferencesHelper;
import com.shymaaothman.weathertask.di.module.ApplicationModule;
import com.shymaaothman.weathertask.view.forgot.ForgotPresenter;
import com.shymaaothman.weathertask.view.login.LoginPresenter;
import com.shymaaothman.weathertask.view.main.MainPresenter;
import com.shymaaothman.weathertask.view.register.RegisterPresenter;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    void inject(LoginPresenter loginPresenter);

    void inject(RegisterPresenter registerPresenter);

    void inject(MainPresenter mainPresenter);

    void inject(ForgotPresenter forgotPresenter);

    void inject(FireAuthApplication baseApplication);

    void inject (WeatherRepository weatherRepository);

    EventBus eventBus();

    PreferencesHelper prefsHelper();

    FirebaseAuth firebaseAuth();

    WeatherRepository weatherRepository();

}