package com.shymaaothman.weathertask.di.component;

import com.shymaaothman.weathertask.di.ActivityScope;
import com.shymaaothman.weathertask.view.forgot.ForgotActivity;
import com.shymaaothman.weathertask.view.login.LoginActivity;
import com.shymaaothman.weathertask.view.main.MainActivity;
import com.shymaaothman.weathertask.view.register.RegisterActivity;

import dagger.Component;



@ActivityScope
@Component(dependencies = ApplicationComponent.class)
public interface ActivityComponent extends ApplicationComponent {

    void inject(LoginActivity loginActivity);

    void inject(RegisterActivity registerActivity);

    void inject(MainActivity mainActivity);

    void inject(ForgotActivity forgotActivity);



}